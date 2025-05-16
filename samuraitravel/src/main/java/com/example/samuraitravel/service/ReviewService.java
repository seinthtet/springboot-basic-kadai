package com.example.samuraitravel.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.samuraitravel.entity.House;
import com.example.samuraitravel.entity.Review;
import com.example.samuraitravel.entity.User;
import com.example.samuraitravel.form.ReviewEditForm;
import com.example.samuraitravel.form.ReviewRegisterForm;
import com.example.samuraitravel.repository.ReviewRepository;

@Service
public class ReviewService {
    private final ReviewRepository reviewRepository;

    public ReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @Transactional
    public void create(ReviewRegisterForm reviewRegisterForm, User user, House house) {
    	Review review = new Review();
    	review.setHouse(house);
    	review.setUser(user);
    	review.setRating(reviewRegisterForm.getRating());
    	review.setComment(reviewRegisterForm.getComment());
        reviewRepository.save(review);
    }

    @Transactional
    public void update(ReviewEditForm reviewEditForm) {
        Review review = reviewRepository.getReferenceById(reviewEditForm.getId());
        review.setRating(reviewEditForm.getRating());
        review.setComment(reviewEditForm.getComment());
        reviewRepository.save(review);
    }
}