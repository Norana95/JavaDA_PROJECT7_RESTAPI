package com.nnk.springboot.service;

import com.nnk.springboot.controllers.BidListController;
import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.repositories.TradeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TradeService {

    @Autowired
    TradeRepository tradeRepository;

    Logger logger = LoggerFactory.getLogger(TradeService.class);

    public void addTrade(Trade trade){
        tradeRepository.save(trade);
        logger.info("trade saved !");
    }

    public Optional<Trade> findTradeById(Integer id){
        logger.info("inside method findTradeById in TradeService");
        return tradeRepository.findById(id);
    }

    public void updateTrade(Trade trade){
        trade.setAccount(trade.getAccount());
        trade.setType(trade.getType());
        trade.setBuyQuantity(trade.getBuyQuantity());
        tradeRepository.save(trade);
        logger.info("trade updated !");
    }

    public void deleteTrade(Trade trade){
        tradeRepository.delete(trade);
        logger.info("trade deleted !");
    }

    public List<Trade> getAllTrade(){
        logger.info("inside method getAllTrade in TradeService");
        return tradeRepository.findAll();
    }

}
