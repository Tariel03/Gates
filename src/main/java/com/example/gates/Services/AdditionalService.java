package com.example.gates.Services;

import com.example.gates.Models.Advantages;
import com.example.gates.Models.Review;
import com.example.gates.Repositories.AdvantagesRepository;
import com.example.gates.Repositories.ReviewRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AdditionalService {
    ReviewRepository reviewRepository;
    AdvantagesRepository advantagesRepository;
    public List<Review> findReviews(){
        return reviewRepository.findAll();
    }
    public List<Advantages> findAdvantages(){return advantagesRepository.findAll();}
    public void saveReview(Review review){
        reviewRepository.save(review);
    }
}
