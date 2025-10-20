package com.NexDew.reviewms.review.service;



import com.NexDew.reviewms.review.entity.Review;

import java.util.List;

public interface ReviewService {
    List<Review> findAllReviews(Long companyId);

    boolean addReview(Long companyId,Review review);

    Review getReviewById(Long reviewId);

    boolean reviewDeleteById(Long reviewId);

    boolean reviewUpdateByID(Long reviewId, Review review);
}
