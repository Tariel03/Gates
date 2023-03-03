package com.example.gates.Repositories;

import com.example.gates.Models.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface NewsRepository extends JpaRepository<News,Integer>
{
//    @Query("SELECT s from Customer s where s.email = ?1")



}
