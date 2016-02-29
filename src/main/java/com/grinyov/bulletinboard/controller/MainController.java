package com.grinyov.bulletinboard.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Vitaliy Grinyov
 * @since 2016
 */
public class MainController {
    @RequestMapping("/index")
    public String start(Model model){
        return "index";
    }
}
