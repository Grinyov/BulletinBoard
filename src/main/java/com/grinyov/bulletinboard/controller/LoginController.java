package com.grinyov.bulletinboard.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Vitaliy Grinyov
 * @since 2016
 */
    @Controller
    public class LoginController {
        @RequestMapping("/login")
        public String loginPage(Model model){
            return "login";
        }
}
