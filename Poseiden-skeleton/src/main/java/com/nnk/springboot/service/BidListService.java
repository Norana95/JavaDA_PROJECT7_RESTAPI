package com.nnk.springboot.service;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BidListService {

    @Autowired
    BidListRepository bidListRepository;

    public void addBid(BidList bidList){
        bidListRepository.save(bidList);
    }

    public BidList findBidByAccount(String account){
        return bidListRepository.findBidByAccount(account);
    }
    public Optional<BidList> findBidListById(Integer id){
       return bidListRepository.findById(id);
    }

    public void updateBid(BidList bidList){
        bidList.setAccount(bidList.getAccount());
        bidList.setType(bidList.getType());
        bidList.setBidQuantity(bidList.getBidQuantity());
        bidListRepository.save(bidList);
    }

    public void deleteBid(BidList bidList){
        bidListRepository.delete(bidList);
    }

    public List<BidList> getAllBid(){
        return bidListRepository.findAll();
    }

}
