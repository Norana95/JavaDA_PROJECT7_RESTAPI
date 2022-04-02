package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.service.BidListService;
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
public class BidListController {

    @Autowired
    BidListService bidListService;

    Logger logger = LoggerFactory.getLogger(BidListController.class);

    @RequestMapping("/bidList/list")
    public String home(Model model)
    {
        model.addAttribute("bidlists", bidListService.getAllBid());
        logger.info("get bidlist done !");
        return "bidList/list";
    }

    @GetMapping("/bidList/add")
    public String addBidForm(BidList bid) {
        logger.info("add bid in the controller done !");
        return "bidList/add";
    }

    @PostMapping("/bidList/validate")
    public String validate(@Valid BidList bid, BindingResult result, Model model) {
        if (result.hasErrors() || bidListService.findBidByAccount(bid.getAccount()) != null) {
            logger.error("bidlist exist in database");
            model.addAttribute("msgerror", "bidlist exist in database");
            return "/bidList/add";
        }
        else {
            bidListService.addBid(bid);
            logger.info("bid add !");
        }
        return "redirect:/bidList/list";
    }

    @GetMapping("/bidList/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        BidList bidList = bidListService.findBidListById(id).orElseThrow(() -> new IllegalArgumentException("Invalid bid Id:" + id));
        logger.info("Get UpdateForm in contorller done !");
        model.addAttribute("bidList", bidList);
        return "bidList/update";
    }

    @PostMapping("/bidList/update/{id}")
    public String updateBid(@PathVariable("id") Integer id, @Valid BidList bidList,
                            BindingResult result, Model model) {
        if (result.hasErrors()) {
            logger.error("error input");
            return "bidList/update";
        }
        bidListService.updateBid(bidList);
        logger.info("updateBid in the controller done !");
        return "redirect:/bidList/list";
    }

    @GetMapping("/bidList/delete/{id}")
    public String deleteBid(@PathVariable("id") Integer id, Model model) {
        BidList bidList = bidListService.findBidListById(id).orElseThrow(() -> new IllegalArgumentException("Invalid bid Id:" + id));
        bidListService.deleteBid(bidList);
        logger.info("delete bid in Controller done !");
        return "redirect:/bidList/list";
    }
}
