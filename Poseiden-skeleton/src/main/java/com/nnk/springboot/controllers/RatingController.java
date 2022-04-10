package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.service.RatingService;
import com.nnk.springboot.service.implement.UserDetailsServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.security.Principal;

@Controller
public class RatingController {

    @Autowired
    RatingService ratingService;

    @Autowired
    UserDetailsServiceImpl userService;

    Logger logger = LoggerFactory.getLogger(RatingController.class);

    @RequestMapping("/rating/list")
    public String home(Model model, Principal principal) {
        model.addAttribute("userName", userService.getUserFromPrincipal(principal));
        model.addAttribute("ratings", ratingService.getAllRating());
        logger.info("get home rating in Controller");
        return "rating/list";
    }

    @GetMapping("/rating/add")
    public String addRatingForm(Rating rating) {
        logger.info("get addRatingForm donne !");
        return "rating/add";
    }

    @PostMapping("/rating/validate")
    public String validate(@Valid Rating rating, BindingResult result, Model model) {
        if (result.hasErrors() || ratingService.findByOrderNumber(rating.getOrderNumber()) != null) {
            model.addAttribute("msgerror", "rating exist in database");
            logger.error("rating exist in database or input error ");
            return "rating/add";
        }
        else {
            ratingService.addRating(rating);
            logger.info("validate rating done in Controller");
        }
        return "redirect:/rating/list";
    }

    @GetMapping("/rating/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        Rating rating = ratingService.findRatingById(id).orElseThrow(() -> new IllegalArgumentException("Invalid rating Id:" + id));
        model.addAttribute("rating", rating);
        logger.info("Get UpdateForm rating in contorller done !");
        return "rating/update";
    }

    @PostMapping("/rating/update/{id}")
    public String updateRating(@PathVariable("id") Integer id, @Valid Rating rating,
                               BindingResult result, Model model) {
        if (result.hasErrors()) {
            logger.error("error input");
            return "rating/update";
        }
        ratingService.updateRating(rating);
        logger.info("updateRating in the controller done !");
        return "redirect:/rating/list";
    }

    @GetMapping("/rating/delete/{id}")
    public String deleteRating(@PathVariable("id") Integer id, Model model) {
        Rating rating = ratingService.findRatingById(id).orElseThrow(() -> new IllegalArgumentException("Invalid rating Id:" + id));
        ratingService.deleteRating(rating);
        logger.info("delete rating in Controller done !");
        return "redirect:/rating/list";
    }
}
