package com.exalt.moviecatalogservice.client;


import com.exalt.moviecatalogservice.model.Rating;
import com.exalt.moviecatalogservice.model.UserRating;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Arrays;

@Headers("Content-Type: application/json")
@FeignClient(name = "ratings-data-service", fallback = MovieCatalogClient.RatingFallback.class)
@Component
public interface MovieCatalogClient {

    @RequestMapping(method = RequestMethod.GET, value = "/ratingsdata/user/{userId}")
//    @HystrixCommand(fallbackMethod = "getFallbackUserRating")
    UserRating getUserRating(@PathVariable("userId") String userId);

//    public default UserRating getFallbackUserRating(String userId) {
//        UserRating userRating = new UserRating();
//        userRating.setUserId(userId);
//        userRating.setRatings(
//                Arrays.asList(new Rating("0", 0))
//        );
//
//        return userRating;
//    }

    @Component
    class RatingFallback implements MovieCatalogClient {
        @Override
        public UserRating getUserRating(String userId) {
            UserRating userRating = new UserRating();
            userRating.setUserId(userId);
            userRating.setRatings(
                    Arrays.asList(new Rating("0", 0))
            );

            return userRating;
        }
    }

}
