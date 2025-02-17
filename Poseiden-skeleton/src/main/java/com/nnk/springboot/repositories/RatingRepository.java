package com.nnk.springboot.repositories;

import com.nnk.springboot.domain.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Integer> {

    @Query("SELECT u FROM Rating u WHERE u.orderNumber = :orderNumber")
    Rating findRatingByOrderNumber(Integer orderNumber);
}
