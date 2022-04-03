package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.service.TradeService;
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
public class TradeController {

    @Autowired
    TradeService tradeService;

    Logger logger = LoggerFactory.getLogger(TradeController.class);

    @RequestMapping("/trade/list")
    public String home(Model model) {
        model.addAttribute("trades", tradeService.getAllTrade());
        logger.info("get page Home trade in controller done !");
        return "trade/list";
    }

    @GetMapping("/trade/add")
    public String addTrade(Trade trade) {
        logger.info("get add trade form in controller!");
        return "trade/add";
    }

    @PostMapping("/trade/validate")
    public String validate(@Valid Trade trade, BindingResult result, Model model) {
        if (result.hasErrors() || tradeService.findTradeById(trade.getTradeId()).isPresent()) {
            model.addAttribute("msgerror", "trade exist in database");
            logger.error("error input trade in controller or trade exist in database");
            return "/trade/add";
        }
        else {
            tradeService.addTrade(trade);
            logger.info("validate trade done in Controller");
        }
        return "redirect:/trade/list";
    }

    @GetMapping("/trade/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        Trade trade = tradeService.findTradeById(id).orElseThrow(() -> new IllegalArgumentException("Invalid trade Id:" + id));
        model.addAttribute("trade", trade);
        logger.info("Get UpdateForm trade in contorller done !");
        return "trade/update";
    }

    @PostMapping("/trade/update/{id}")
    public String updateTrade(@PathVariable("id") Integer id, @Valid Trade trade,
                              BindingResult result, Model model) {
        if (result.hasErrors()) {
            logger.error("error input trade");
            return "trade/update";
        }
        tradeService.updateTrade(trade);
        logger.info("update trade in the controller done !");
        return "redirect:/trade/list";
    }

    @GetMapping("/trade/delete/{id}")
    public String deleteTrade(@PathVariable("id") Integer id, Model model) {
        Trade trade = tradeService.findTradeById(id).orElseThrow(() -> new IllegalArgumentException("Invalid trade Id:" + id));
        tradeService.deleteTrade(trade);
        logger.info("delete trade in Controller done !");
        return "redirect:/trade/list";
    }
}
