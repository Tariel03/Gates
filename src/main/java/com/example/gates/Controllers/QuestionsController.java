package com.example.gates.Controllers;

import com.example.gates.Models.Questions;
import com.example.gates.Services.MainService;
import com.example.gates.Services.QuestionsService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/questions")
public class QuestionsController {
   MainService mainService;
    @GetMapping
    public List<Questions> allQuestions(){
        return mainService.allQuestions();
    }

}
