package com.grinyov.bulletinboard.controller;

import com.grinyov.bulletinboard.dao.CategoryDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Vitaliy Grinyov
 * @since 2016
 */
@Controller
@RequestMapping("/about")
public class AboutController {

    @Autowired
    private CategoryDao categoryDao;

    @Value("${page_title.about}")
    private String aboutPageTitle;

    @RequestMapping
    public String main(ModelMap model) {
        model.addAttribute("about_active", "active");
        model.addAttribute("page", "about");
        model.addAttribute("page_title", aboutPageTitle);
        model.addAttribute("categories", categoryDao.getAllCategories());
        model.addAttribute("categoryUrlPrefix", "/?");

        return "page";
    }
}
