package com.grinyov.bulletinboard.controller;

import com.grinyov.bulletinboard.dao.AdvertDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Vitaliy Grinyov
 * @since 2016
 */
@Controller
public class DeleteController {
    @Autowired
    private AdvertDao advertDao;

    @RequestMapping(value = "/delete")
    public String deleteById(@RequestParam(value = "id", required = false) String idStr) {
        long id;
        try {
            id = Long.parseLong(idStr);
            advertDao.deleteAdvert(id);
        } catch (NumberFormatException ignored) {
        }

        return "redirect:/";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String deleteChecks(@RequestParam(value = "checks", required = false) String[] checks) {
        if (checks.length == 0) {
            return "redirect:/";
        }

        int checksLength = checks.length;
        long[] ids = new long[checksLength];

        try {
            for (int i = 0; i < checksLength; i++) {
                ids[i] = Long.parseLong(checks[i]);
            }

            advertDao.deleteAdverts(ids);
        } catch (NumberFormatException ignored) {
            return "redirect:/";
        }

        return "redirect:/";
    }
}
