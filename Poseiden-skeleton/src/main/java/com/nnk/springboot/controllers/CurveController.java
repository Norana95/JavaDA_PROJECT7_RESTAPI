package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.service.CurvePointService;
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
import java.util.List;

@Controller
public class CurveController {

    @Autowired
    CurvePointService curvePointService;

    @Autowired
    UserDetailsServiceImpl userService;

    Logger logger = LoggerFactory.getLogger(CurveController.class);

    @RequestMapping("/curvePoint/list")
    public String home(Model model, Principal principal) {
        model.addAttribute("userName", userService.getUserFromPrincipal(principal));
        List<CurvePoint> curvePointList = curvePointService.findAllCurvePoint();
        logger.info("get home in Controller");
        model.addAttribute("curvePoints", curvePointList);
        return "curvePoint/list";
    }

    @GetMapping("/curvePoint/add")
    public String addCurvePointForm(CurvePoint curvePoint) {
        logger.info("get addCurvePointForm donne !");
        return "curvePoint/add";
    }

    @PostMapping("/curvePoint/validate")
    public String validate(@Valid CurvePoint curvePoint, BindingResult result, Model model) {
        if (result.hasErrors() || curvePointService.findCurvePointById(curvePoint.curveId).isPresent()) {
            logger.error("curvePoint exist in database or input error ");
            model.addAttribute("msgerror", "curvePoint exist in database");
            return "/curvePoint/add";
        }
        else {
            curvePointService.saveCurvePoint(curvePoint);
            logger.info("validate curvePoint done in Controller");
        }
        return "redirect:/curvePoint/list";
    }

    @GetMapping("/curvePoint/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        CurvePoint curvePoint = curvePointService.findCurvePointById(id).orElseThrow(() -> new IllegalArgumentException("Invalid curvePoint Id:" + id));
        logger.info("Get UpdateForm curvePoint in contorller done !");
        model.addAttribute("curvePoint", curvePoint);
        return "curvePoint/update";
    }

    @PostMapping("/curvePoint/update/{id}")
    public String updateCurvePoint(@PathVariable("id") Integer id, @Valid CurvePoint curvePoint,
                            BindingResult result, Model model) {
        if (result.hasErrors()) {
            logger.error("error input");
            return "curvePoint/update";
        }
        curvePointService.updateCurvePoint(curvePoint);
        logger.info("updateCurvePoint in the controller done !");
        return "redirect:/curvePoint/list";
    }

    @GetMapping("/curvePoint/delete/{id}")
    public String deleteCurvePoint(@PathVariable("id") Integer id, Model model) {
        CurvePoint curvePoint = curvePointService.findCurvePointById(id).orElseThrow(() -> new IllegalArgumentException("Invalid curvePoint Id:" + id));
        curvePointService.deleteCurvePoint(curvePoint);
        logger.info("delete curvePoint in Controller done !");
        return "redirect:/curvePoint/list";
    }
}
