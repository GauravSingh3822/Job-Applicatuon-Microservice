package com.NexDew.reviewms.review.service.impl;


import com.NexDew.reviewms.review.entity.Review;
import com.NexDew.reviewms.review.repository.ReviewRepository;
import com.NexDew.reviewms.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;



    @Override
    public List<Review> findAllReviews(Long companyId) {
        return reviewRepository.findByCompanyId(companyId);
    }

    @Override
    public boolean addReview(Long companyId, Review review) {

        if(companyId!=null && review!=null){
            review.setCompanyId(companyId);
            reviewRepository.save(review);
            return true;
        }
        return false;
    }

    @Override
    public Review getReviewById(Long reviewId) {
        return reviewRepository.findById(reviewId).orElse(null);

    }

    @Override
    public boolean reviewDeleteById(Long reviewId) {
        Review review = reviewRepository.findById(reviewId).orElse(null);

        if(review!=null){
            reviewRepository.deleteById(reviewId);
            return true;
        }
        return false;
    }

    @Override
    public boolean reviewUpdateByID(Long reviewId, Review updatedReview) {
        Review review = reviewRepository.findById(reviewId).orElse(null);
        if(review!=null){
            review.setTitle(updatedReview.getTitle());
            review.setDescription(updatedReview.getDescription());
            review.setRating(updatedReview.getRating());
            review.setCompanyId(updatedReview.getCompanyId());
            reviewRepository.save(review);
            return true;
        }
        return false;

    }
}
