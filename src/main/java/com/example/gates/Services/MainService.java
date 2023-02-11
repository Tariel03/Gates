package com.example.gates.Services;

import com.example.gates.Models.Questions;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class MainService {
    QuestionsService questionsService;

    public List<Questions> allQuestions(){
        return questionsService.all();
    }

}
