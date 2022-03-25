package com.nnk.springboot.service;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.repositories.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RatingService {

    @Autowired
    RatingRepository ratingRepository;

    public void addRating(Rating rating) {
        ratingRepository.save(rating);
    }

    public Optional<Rating> findRatingById(Integer id) {
        return ratingRepository.findById(id);
    }

    public void updateRating(Rating rating) {
        rating.setMoodysRating(rating.getMoodysRating());
        rating.setSandPRating(rating.getSandPRating());
        rating.setFitchRating(rating.getFitchRating());
        rating.setOrderNumber(rating.getOrderNumber());
        ratingRepository.save(rating);
    }

    public void deleteRating(Rating rating) {
        ratingRepository.delete(rating);
    }

    public List<Rating> getAllRating() {
        return ratingRepository.findAll();
    }

    public Rating findByOrderNumber(Integer orderNumber) {
        return ratingRepository.findRatingByOrderNumber(orderNumber);
    }
}
