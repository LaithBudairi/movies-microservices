package com.exalt.moviecatalogservice.client;


import com.exalt.moviecatalogservice.model.UserRating;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Headers("Content-Type: application/json")
@FeignClient("ratings-data-service")
@Component
public interface MovieCatalogClient {

    @RequestMapping(method = RequestMethod.GET, value = "/ratingsdata/user/{userId}")
//    @HystrixCommand(fallbackMethod = "getFallbackUserRating")
    UserRating getUserRating(@PathVariable("userId") String userId);

}
