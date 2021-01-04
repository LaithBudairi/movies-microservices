package com.exalt.ratingsdataservice.controller;

import com.exalt.ratingsdataservice.model.Rating;
import com.exalt.ratingsdataservice.model.UserRating;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/ratingsdata")
public class RatingsDataController {

    Logger log = LoggerFactory.getLogger(RatingsDataController.class);

    @RequestMapping("/movies/{movieId}")
    public Rating getMovieRating(@PathVariable("movieId") String movieId) {
        return new Rating(movieId, 4);
    }

    @RequestMapping("/user/{userId}")
    public UserRating getUserRatings(@PathVariable("userId") String userId) {
        log.info("Getting user ratings");
        UserRating userRating = new UserRating();
        userRating.initData(userId);
        return userRating;
    }
}