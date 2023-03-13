package com.example.gates.Controllers;

import com.example.gates.Models.Advantages;
import com.example.gates.Models.Review;
import com.example.gates.Services.AdditionalService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/other")
public class OtherController {
    AdditionalService additionalService;
    @GetMapping("/reviews")
    public List<Review> reviews(){
        return additionalService.findReviews();
    }
    @GetMapping("/advantages")
    public List<Advantages> advantages(){return additionalService.findAdvantages();}

}
