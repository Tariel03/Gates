package com.example.gates.Repositories;

import com.example.gates.Models.News;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NewsRepository extends JpaRepository<News,Integer>
{
}
