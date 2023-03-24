package com.example.gates.Controllers;

import com.example.gates.Models.Gates;
import com.example.gates.Models.Review;
import com.example.gates.Services.AdditionalService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/review")
public class ReviewController {
    AdditionalService additionalService;
    @GetMapping()
    public List<Review> reviews(){
        return additionalService.findReviews();
    }
    @GetMapping("/{id}")
    public Review review(@PathVariable int id){
        return additionalService.findReviewById(id);
    }

}
