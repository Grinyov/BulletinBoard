package com.grinyov.bulletinboard.controller;

import com.grinyov.bulletinboard.dao.AccountDao;
import com.grinyov.bulletinboard.dao.AdvertDao;
import com.grinyov.bulletinboard.dao.CategoryDao;
import com.grinyov.bulletinboard.model.Advert;
import com.grinyov.bulletinboard.util.HtmlSpecialChars;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Timestamp;
import java.util.*;


/**
 * @author Vitaliy Grinyov
 * @since 2016
 */
@Controller
public class AdvertController {


    @Autowired
    private AdvertDao advertDao;

    @Autowired
    private CategoryDao categoryDao;

    @Autowired
    private AccountDao accountDao;

    @Autowired
    private HtmlSpecialChars htmlSpecialChars;

    @Value("${page_title.new_ann}")
    private String newAnnPageTitle;

    @Value("${error.empty_fields_detected}")
    private String errorEmptyFieldsDetected;

    @Value("${error.incorrect_price}")
    private String errorIncorrectPrice;

    @Value("${error.incorrect_category}")
    private String errorIncorrectCategory;

    @Value("${error.incorrect_contact}")
    private String errorIncorrectContact;

    @Value("${error.add_annc_error}")
    private String errorFatal;

    private List<String> errors = new ArrayList<>();
    private Map<String, String> paramMap = new HashMap<>();

    @RequestMapping("/new_advert")
    public String main(ModelMap model) {

        if (errors.size() != 0) {
            model.addAttribute("errors", errors);
            model.addAllAttributes(paramMap);
            errors = new ArrayList<>();
            paramMap = new HashMap<>();
        }

        model.addAttribute("categories", categoryDao.getAllCategories());
        model.addAttribute("categoryUrlPrefix", "/?");

        model.addAttribute("page", "new_ann");
        model.addAttribute("newann_active", "active");
        model.addAttribute("page_title", newAnnPageTitle);

        return "page";
    }

    @RequestMapping(value = "/new_advert", method = RequestMethod.POST)
    public String createAnn(@RequestParam(value = "account", required = false) String accountStr,
                            @RequestParam(value = "category", required = false) String category,
                            @RequestParam(value = "title", required = false) String title,
                            @RequestParam(value = "text", required = false) String text,
                            @RequestParam(value = "publication", required = false) String publication
                            ) {

        paramMap.put("account", accountStr);
        paramMap.put("category", category);
        paramMap.put("title", title);
        paramMap.put("text", text);
        paramMap.put("publication", publication);


        if (StringUtils.isBlank(category)
                || StringUtils.isBlank(accountStr)
                || StringUtils.isBlank(title)
                || StringUtils.isBlank(text)
                || StringUtils.isBlank(publication)) {
            errors.add(errorEmptyFieldsDetected);
            return "redirect:/new_announcement";
        }

        // avoiding unwanted html characters
        accountStr = htmlSpecialChars.replaceChars(accountStr);
        category = htmlSpecialChars.replaceChars(category);
        title = htmlSpecialChars.replaceChars(title);
        text = htmlSpecialChars.replaceChars(text);
        int categoryId = 0;
        String name = "";


        // parsing category id
        try {
            categoryId = Integer.parseInt(category);
            if (categoryId <= 0) {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException ignored) {
            errors.add(errorIncorrectCategory);
        }


        Advert advert = new Advert(accountDao.getAccountByName(name), categoryDao.getCategoryById(categoryId), title,
                text, new Timestamp(new Date().getTime()));
        if (!advertDao.addAdvert(advert)) {
            errors.add(errorFatal);
            return "redirect:/new_announcement";
        }

        return "redirect:/";
    }

}
