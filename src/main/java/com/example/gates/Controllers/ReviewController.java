package com.example.gates.Controllers;

import com.example.gates.Models.Gates;
import com.example.gates.Models.Review;
import com.example.gates.Services.AdditionalService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/review")
public class ReviewController {
    AdditionalService additionalService;
    @GetMapping()
    @CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3001", "*"})

    public List<Review> reviews(){
        return additionalService.findReviews();
    }
    @GetMapping("/{id}")
    @CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3001", "*"})

    public Review review(@PathVariable int id){
        return additionalService.findReviewById(id);
    }

}
