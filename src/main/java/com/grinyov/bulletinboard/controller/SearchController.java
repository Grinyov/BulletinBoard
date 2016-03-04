package com.grinyov.bulletinboard.controller;

import com.grinyov.bulletinboard.dao.AdvertDao;
import com.grinyov.bulletinboard.dao.CategoryDao;
import com.grinyov.bulletinboard.model.Advert;
import com.grinyov.bulletinboard.model.Category;
import com.grinyov.bulletinboard.util.AdvertComparator;
import com.grinyov.bulletinboard.util.HtmlSpecialChars;
import com.grinyov.bulletinboard.util.Pagination;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collections;
import java.util.List;

/**
 * @author Vitaliy Grinyov
 * @since 2016
 */
@Controller
public class SearchController {
    @Autowired
    private AdvertDao advertDao;

    @Autowired
    private CategoryDao categoryDao;

    @Autowired
    private Pagination pagination;

    //@Autowired
    private HtmlSpecialChars htmlSpecialChars;

    @Value("${page_title.search}")
    private String searchPageTitle;

    @Value("${title.sort_by_id}")
    private String sortByIdTitle;

    @Value("${title.sort_by_date}")
    private String sortByDateTitle;

    @Value("${title.sort_by_price}")
    private String sortByPriceTitle;

    /*
        Simple url link builder that dynamically adds "?" or "&" to the beginning
        of the following link.
      */
    private static final StringBuilder urlPrefix = new StringBuilder();

    @RequestMapping("/search")
    public String main(@RequestParam(value = "q", required = false) String searchQuery,
                       @RequestParam(value = "page", required = false) String pageIdStr,
                       @RequestParam(value = "sort", required = false) String sortBy,
                       @RequestParam(value = "category", required = false) String categoryIdStr,
                       ModelMap model) {

        List<Advert> adverts;

        if (StringUtils.isNotBlank(searchQuery)) {
            // avoiding double quotes
            searchQuery = htmlSpecialChars.replaceChars(searchQuery);
            adverts = advertDao.searchAdvert(searchQuery);
            for (Advert advert : adverts) {
                advert.getPublication();
            }
        } else {
            return "redirect:/";
        }

        if (adverts.size() == 0) {
            urlPrefix.append("/?");
        } else {
            urlPrefix.append("?q=").append(searchQuery).append("&amp;");
        }

        String categoryUrlPrefix = urlPrefix.toString();
        String sortUrlPrefix;
        String pageUrlPrefix;

        final List<Category> categories = categoryDao.getAllCategories();

        int categoryId = 0;
        if (StringUtils.isNotBlank(categoryIdStr)) {
            try {
                categoryId = Integer.parseInt(categoryIdStr);
                if (categoryId >= 1 && categoryId <= categories.size()) {
                    adverts = advertDao.getAdvertsByCategoryId(categoryId);
                    sortUrlPrefix = urlPrefix.append("category=").append(categoryId).append("&amp;").toString();
                } else
                    throw new NumberFormatException();
            } catch (NumberFormatException ignored) {
                sortUrlPrefix = categoryUrlPrefix;
            }
        } else {
            sortUrlPrefix = categoryUrlPrefix;
        }

        String sortByTitle = null;

        if (StringUtils.isNotBlank(sortBy)) {
            urlPrefix.append("sort=");
            switch (sortBy) {
                case "id":
                    pageUrlPrefix = urlPrefix.append("id&amp;").toString();
                    sortByTitle = sortByIdTitle;
                    Collections.sort(adverts, AdvertComparator.COMPARE_BY_ID);
                    break;
                case "date":
                    pageUrlPrefix = urlPrefix.append("date&amp;").toString();
                    sortByTitle = sortByDateTitle;
                    Collections.sort(adverts, AdvertComparator.COMPARE_BY_DATE);
                    break;
                default:
                    pageUrlPrefix = sortUrlPrefix;
                    break;
            }
        } else {
            pageUrlPrefix = sortUrlPrefix;
            sortByTitle = "";
        }

        urlPrefix.setLength(0);

        if (adverts.size() == 0) {
            model.addAttribute("error", "true");
        } else {
            // pagination
            int pagesCount = pagination.getPagesNumber(adverts);
            int pageId = 1; // default page number

            try {
                if (StringUtils.isNotBlank(pageIdStr)) {
                    pageId = Integer.parseInt(pageIdStr);
                    if (pageId < 1 || pageId > pagesCount)
                        pageId = 1; // rollback page number
                }
            } catch (NumberFormatException ignored) {
            }

            // arrow paginators logic
            if (pageId > 1)
                model.addAttribute("pageBackward", true);
            if (pageId < pagesCount)
                model.addAttribute("pageForward", true);

            model.addAttribute("pagesCount", pagesCount);
            model.addAttribute("currentPageId", pageId);
            model.addAttribute("annc", pagination.getItemsPerPage(adverts, pageId));
        }

        model.addAttribute("search", searchQuery);
        model.addAttribute("currentCategoryId", categoryId);
        model.addAttribute("categoryUrlPrefix", categoryUrlPrefix);
        model.addAttribute("sortUrlPrefix", sortUrlPrefix);
        model.addAttribute("sortByTitle", sortByTitle);
        model.addAttribute("pageUrlPrefix", pageUrlPrefix);
        model.addAttribute("categories", categories);

        model.addAttribute("page", "search");
        model.addAttribute("page_title", searchPageTitle);

        return "page";
    }
}
