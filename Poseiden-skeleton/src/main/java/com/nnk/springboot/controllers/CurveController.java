package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.service.CurvePointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
public class CurveController {

    @Autowired
    CurvePointService curvePointService;

    @RequestMapping("/curvePoint/list")
    public String home(Model model) {
        List<CurvePoint> curvePointList = curvePointService.findAllCurvePoint();
        model.addAttribute("curvePoints", curvePointList);
        return "curvePoint/list";
    }

    @GetMapping("/curvePoint/add")
    public String addCurvePointForm(CurvePoint curvePoint) {
        return "curvePoint/add";
    }

    @PostMapping("/curvePoint/validate")
    public String validate(@Valid CurvePoint curvePoint, BindingResult result, Model model) {
        if (result.hasErrors() || curvePointService.findCurvePointById(curvePoint.curveId).isPresent()) {
            model.addAttribute("msgerror", "curvePoint exist in database");
            return "/curvePoint/add";
        }
        else {
            curvePointService.saveCurvePoint(curvePoint);
        }
        return "redirect:/curvePoint/list";
    }

    @GetMapping("/curvePoint/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        CurvePoint curvePoint = curvePointService.findCurvePointById(id).orElseThrow(() -> new IllegalArgumentException("Invalid curvePoint Id:" + id));
        model.addAttribute("curvePoint", curvePoint);
        return "curvePoint/update";
    }

    @PostMapping("/curvePoint/update/{id}")
    public String updateBid(@PathVariable("id") Integer id, @Valid CurvePoint curvePoint,
                            BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "curvePoint/update";
        }
        curvePointService.updateCurvePoint(curvePoint);
        return "redirect:/curvePoint/list";
    }

    @GetMapping("/curvePoint/delete/{id}")
    public String deleteBid(@PathVariable("id") Integer id, Model model) {
        CurvePoint curvePoint = curvePointService.findCurvePointById(id).orElseThrow(() -> new IllegalArgumentException("Invalid curvePoint Id:" + id));
        curvePointService.deleteCurvePoint(curvePoint);
        return "redirect:/curvePoint/list";
    }
}
