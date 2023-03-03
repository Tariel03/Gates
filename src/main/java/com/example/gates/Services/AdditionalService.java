package com.example.gates.Services;

import com.example.gates.Models.About;
import com.example.gates.Models.Advantages;
import com.example.gates.Models.Review;
import com.example.gates.Repositories.AboutRepository;
import com.example.gates.Repositories.AdvantagesRepository;
import com.example.gates.Repositories.ReviewRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AdditionalService {
    AdvantagesRepository advantagesRepository;
    AboutRepository aboutRepository;
    ReviewRepository reviewRepository;

    public List<Advantages> findAdvantages(){
        return advantagesRepository.findAll();
    }
    public List<About> findAboutInfo(){
        return aboutRepository.findAll();
    }
    public List<Review> findReviews(){
        return reviewRepository.findAll();
    }
    public void saveReview(Review review){
        reviewRepository.save(review);
    }
}
