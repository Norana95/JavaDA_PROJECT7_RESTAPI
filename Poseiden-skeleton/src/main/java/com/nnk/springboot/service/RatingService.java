package com.nnk.springboot.service;

import com.nnk.springboot.controllers.BidListController;
import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.repositories.RatingRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RatingService {

    @Autowired
    RatingRepository ratingRepository;

    Logger logger = LoggerFactory.getLogger(RatingService.class);

    public void addRating(Rating rating) {
        ratingRepository.save(rating);
        logger.info("rating saved !");
    }

    public Optional<Rating> findRatingById(Integer id) {
        logger.info("inside method findRatingById in RatingService");
        return ratingRepository.findById(id);
    }

    public void updateRating(Rating rating) {
        rating.setMoodysRating(rating.getMoodysRating());
        rating.setSandPRating(rating.getSandPRating());
        rating.setFitchRating(rating.getFitchRating());
        rating.setOrderNumber(rating.getOrderNumber());
        ratingRepository.save(rating);
        logger.info("rating updated !");
    }

    public void deleteRating(Rating rating) {
        ratingRepository.delete(rating);
        logger.info("rating deleted !");
    }

    public List<Rating> getAllRating() {
        logger.info("inside method getAllRating in RatingService");
        return ratingRepository.findAll();
    }

    public Rating findByOrderNumber(Integer orderNumber) {
        logger.info("inside method findByOrderNumber in RatingService");
        return ratingRepository.findRatingByOrderNumber(orderNumber);
    }
}
