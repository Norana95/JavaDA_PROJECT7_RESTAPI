package com.nnk.springboot.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.security.RolesAllowed;

@Controller
public class HomeController {

    Logger logger = LoggerFactory.getLogger(HomeController.class);

    @RequestMapping("/")
    public String home(Model model) {
        logger.info("get home page controller");
        return "home";
    }

    @RequestMapping("/admin/home")
    public String adminHome(Model model) {
        logger.info("inside adminHome Controller");
        return "redirect:/bidList/list";
    }


}
