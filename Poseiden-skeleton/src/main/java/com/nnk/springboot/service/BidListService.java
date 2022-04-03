package com.nnk.springboot.service;

import com.nnk.springboot.controllers.BidListController;
import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BidListService {

    @Autowired
    BidListRepository bidListRepository;

    Logger logger = LoggerFactory.getLogger(BidListService.class);

    public void addBid(BidList bidList){
        bidListRepository.save(bidList);
        logger.info("bid saved !");
    }

    public BidList findBidByAccount(String account){
        logger.info("inside method findBidByAccount in BidListService");
        return bidListRepository.findBidByAccount(account);
    }
    public Optional<BidList> findBidListById(Integer id){
        logger.info("inside method findBidListById in BidList service");
        return bidListRepository.findById(id);
    }

    public void updateBid(BidList bidList){
        bidList.setAccount(bidList.getAccount());
        bidList.setType(bidList.getType());
        bidList.setBidQuantity(bidList.getBidQuantity());
        bidListRepository.save(bidList);
        logger.info("bid updated !");
    }

    public void deleteBid(BidList bidList){
        bidListRepository.delete(bidList);
        logger.info("bid deleted !");
    }

    public List<BidList> getAllBid(){
        logger.info("inside method getAllBid in bidlist service");
        return bidListRepository.findAll();
    }

}
