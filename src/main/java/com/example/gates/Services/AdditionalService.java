package com.example.gates.Services;

import com.example.gates.Models.Review;
import com.example.gates.Repositories.ReviewRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AdditionalService {
    ReviewRepository reviewRepository;
    public List<Review> findReviews(){
        return reviewRepository.findAll();
    }
    public void saveReview(Review review){
        reviewRepository.save(review);
    }

    public Review findReviewById(int id){
        return reviewRepository.findById(id).orElse(null);
    }
}
