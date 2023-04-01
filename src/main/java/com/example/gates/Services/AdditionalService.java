package com.example.gates.Services;

import com.example.gates.Models.Advantages;
import com.example.gates.Models.Gates;
import com.example.gates.Models.gatesType;
import com.example.gates.Models.Review;
import com.example.gates.Repositories.AdvantageRepository;
import com.example.gates.Repositories.ReviewRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AdditionalService {
    ReviewRepository reviewRepository;
    AdvantageRepository advantageRepository;
    public List<Review> findReviews(){
        return reviewRepository.findAll();
    }
    public void saveReview(Review review){
        reviewRepository.save(review);
    }

    public Review findReviewById(int id){
        return reviewRepository.findById(id).orElse(null);
    }

    public Advantages saveAdvantages(Advantages advantages){
        return advantageRepository.save(advantages);
    }
    public Advantages advantages(Gates gates){
        return advantageRepository.findAdvantagesByGates(gates);
    }

}
