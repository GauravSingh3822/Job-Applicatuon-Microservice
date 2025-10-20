package com.nexdew.jobms.job.clients;

import com.nexdew.jobms.job.external.Review;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "REVIEW-SERVICE",url = "${review-service.url}")
public interface ReviewClient {
    // Define methods to interact with the Review service here
    // For example:
    // @GetMapping("/reviews/{id}")
    // Review getReviewById(@PathVariable("id") Long id);

    @GetMapping("/reviews")
    List<Review> getReview(@RequestParam("companyId") Long companyId); // Example method to get reviews by company ID with Feign
}
