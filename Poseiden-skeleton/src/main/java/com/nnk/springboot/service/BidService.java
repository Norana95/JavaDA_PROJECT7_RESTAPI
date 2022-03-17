package com.nnk.springboot.service;

import com.nnk.springboot.domain.Bid;
import com.nnk.springboot.repositories.BidRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BidService {

    @Autowired
    BidRepository bidRepository;

    public void createBid(Bid bid){
        bidRepository.save(bid);
    }
    public void findBid(Integer id){
        bidRepository.findById(id);
    }
    public void updateBid(Bid bid){
        bid.setAccount(bid.getAccount());
        bid.setType(bid.getType());
        bid.setBidQuantity(bid.getBidQuantity());
        bid.setAskQuantity(bid.getAskQuantity());
        bid.setBid(bid.getBid());
        bid.setAsk(bid.getAsk());
        bid.setBenchmark(bid.getBenchmark());
        bid.setBidListDate(bid.getBidListDate());
        bid.setCommentary(bid.getCommentary());
        bid.setSecurity(bid.getSecurity());
        bid.setStatus(bid.getStatus());
        bid.setTrader(bid.getTrader());
        bid.setBook(bid.getBook());
        bid.setCreationName(bid.getCreationName());
        bid.setCreationDate(bid.getCreationDate());
        bid.setRevisionName(bid.getRevisionName());
        bid.setRevisionDate(bid.getRevisionDate());
        bid.setDealName(bid.getDealName());
        bid.setDealType(bid.getDealType());
        bid.setSourceListId(bid.getSourceListId());
        bid.setSide(bid.getSide());
    }
    public void deleteBid(Bid bid){
        bidRepository.delete(bid);
    }

}
