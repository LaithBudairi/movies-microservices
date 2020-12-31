package com.exalt.moviecatalogservice.controller;

import com.exalt.moviecatalogservice.client.MovieCatalogClient;
import com.exalt.moviecatalogservice.model.CatalogItem;
import com.exalt.moviecatalogservice.model.Movie;
import com.exalt.moviecatalogservice.model.Rating;
import com.exalt.moviecatalogservice.model.UserRating;
import com.exalt.moviecatalogservice.service.MovieInfo;
import com.exalt.moviecatalogservice.service.UserRatingInfo;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class MovieCatalogController {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    WebClient.Builder webClientBuilder;

    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private MovieInfo movieInfo;

    @Autowired
    private UserRatingInfo userRatingInfo;

//    @Autowired
//    private MovieCatalogClient movieCatalogClient;

    @RequestMapping("/{userId}")
    public List<CatalogItem> getCatalog(@PathVariable("userId") String userId) {

        UserRating userRating = userRatingInfo.getUserRating(userId);

        return userRating.getRatings().stream()
                .map(rating -> {
                    return movieInfo.getCatalogItem(rating);
                })
                .collect(Collectors.toList());
    }

}









//            Movie movie = webClientBuilder.build()
//                    .get()
//                    .uri("http://localhost:8082/movies/" + rating.getMovieId())
//                    .retrieve()
//                    .bodyToMono(Movie.class)
//                    .block();
