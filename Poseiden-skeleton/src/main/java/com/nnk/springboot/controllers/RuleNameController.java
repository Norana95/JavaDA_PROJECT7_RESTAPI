package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.service.RuleNameService;
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

@Controller
public class RuleNameController {

    @Autowired
    RuleNameService ruleNameService;

    Logger logger = LoggerFactory.getLogger(RuleNameController.class);

    @RequestMapping("/ruleName/list")
    public String home(Model model) {
        model.addAttribute("rules", ruleNameService.getAllRule());
        logger.info("get page Home in controller done !");
        return "ruleName/list";
    }

    @GetMapping("/ruleName/add")
    public String addRuleForm(RuleName ruleName) {
        logger.info("get add rule form in controller!");
        return "ruleName/add";
    }

    @PostMapping("/ruleName/validate")
    public String validate(@Valid RuleName rule, BindingResult result, Model model) {
        if (result.hasErrors()) {
            logger.error("error input ruleName in controller");
            return "ruleName/add";
        }
        ruleNameService.addRule(rule);
        logger.info("validate rating done in Controller");
        return "redirect:/ruleName/list";
    }

    @GetMapping("/ruleName/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        RuleName rule = ruleNameService.findRuleById(id).orElseThrow(() -> new IllegalArgumentException("Invalid rule Id:" + id));
        model.addAttribute("rule", rule);
        logger.info("Get UpdateForm ruleName in contorller done !");
        return "ruleName/update";
    }

    @PostMapping("/ruleName/update/{id}")
    public String updateRuleName(@PathVariable("id") Integer id, @Valid RuleName rule,
                                 BindingResult result, Model model) {
        if (result.hasErrors()) {
            logger.error("error input ruleName");
            return "ruleName/update";
        }
        ruleNameService.updateRule(rule);
        logger.info("update ruleName in the controller done !");
        return "redirect:/ruleName/list";
    }

    @GetMapping("/ruleName/delete/{id}")
    public String deleteRuleName(@PathVariable("id") Integer id, Model model) {
        RuleName rule = ruleNameService.findRuleById(id).orElseThrow(() -> new IllegalArgumentException("Invalid rule Id:" + id));
        ruleNameService.deleteRule(rule);
        logger.info("delete ruleName in Controller done !");
        return "redirect:/ruleName/list";
    }
}
