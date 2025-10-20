package com.NexDew.reviewms.review.contoller;


import com.NexDew.reviewms.review.entity.Review;
import com.NexDew.reviewms.review.messaging.ReviewMessageProducer;
import com.NexDew.reviewms.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviews")
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;
    private final ReviewMessageProducer reviewMessageProducer;



    @GetMapping
    public ResponseEntity<List<Review>> getAllReviews(@RequestParam Long companyId){
        return ResponseEntity.ok(reviewService.findAllReviews(companyId));
    }


    @PostMapping
    public ResponseEntity<String> addReview(@RequestParam Long companyId,
                                               @RequestBody Review review){
        boolean isReviewSaved = reviewService.addReview(companyId, review);
        if(isReviewSaved){
            reviewMessageProducer.sendMessage(review);
            return new ResponseEntity<>("Review Added Successfully!", HttpStatus.CREATED);
        }else {
            return new ResponseEntity<>("Company Not Found By Id..Please Enter Correct Company Id", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{reviewId}")
    public ResponseEntity<Review> getReview(@PathVariable Long  reviewId){
        return new ResponseEntity<>(reviewService.getReviewById(reviewId),HttpStatus.OK);
    }

    @DeleteMapping("/{reviewId}")
    public ResponseEntity<String> deleteReviewById(@PathVariable Long reviewId){
        boolean isDeleted = reviewService.reviewDeleteById(reviewId);
        if(isDeleted){
            return new ResponseEntity<>("Review Deleted Successfully!", HttpStatus.OK);
        }
        return new ResponseEntity<>("Review Not Found By Id..Please Enter Correct Review Id", HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{reviewId}")
    public ResponseEntity<String> updateReview(@PathVariable Long reviewId,@RequestBody Review review){
        boolean isUpdated = reviewService.reviewUpdateByID(reviewId,review);
        if(isUpdated){
            return new ResponseEntity<>("Review Updated Successfully!", HttpStatus.OK);
        }
        return new ResponseEntity<>("Review Not Found By Id..Please Enter Correct Review Id",HttpStatus.NOT_FOUND);
    }

    @GetMapping("/averageRating")
    public Double getAverageRating(@RequestParam Long companyId){
        List<Review> allReviews = reviewService.findAllReviews(companyId);
        return allReviews.stream()
                .mapToDouble(Review::getRating)
                .average()
                .orElse(0.0);
    }
}
