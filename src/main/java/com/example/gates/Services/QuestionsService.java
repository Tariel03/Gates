package com.example.gates.Services;

import com.example.gates.Models.Questions;
import com.example.gates.Repositories.QuestionsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class QuestionsService {
    QuestionsRepository questionsRepository;

    public List<Questions> all(){
        return questionsRepository.findAll();
    }

}
