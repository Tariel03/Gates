package com.example.gates.Repositories;

import com.example.gates.Models.Questions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionsRepository  extends JpaRepository<Questions, Integer> {

}
