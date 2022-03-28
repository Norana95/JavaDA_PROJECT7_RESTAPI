package com.nnk.springboot.service;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.repositories.TradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TradeService {

    @Autowired
    TradeRepository tradeRepository;

    public void addTrade(Trade trade){
        tradeRepository.save(trade);
    }

    public Optional<Trade> findTradeById(Integer id){
        return tradeRepository.findById(id);
    }

    public void updateTrade(Trade trade){
        trade.setAccount(trade.getAccount());
        trade.setType(trade.getType());
        trade.setBuyQuantity(trade.getBuyQuantity());
        tradeRepository.save(trade);
    }

    public void deleteTrade(Trade trade){
        tradeRepository.delete(trade);
    }

    public List<Trade> getAllTrade(){
        return tradeRepository.findAll();
    }

}
