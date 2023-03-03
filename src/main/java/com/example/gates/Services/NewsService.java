package com.example.gates.Services;

import com.example.gates.Models.News;
import com.example.gates.Repositories.NewsRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class NewsService {
    NewsRepository newsRepository;
    public List<News> newsOrder(){
        return newsRepository.findAll(Sort.by(Sort.Direction.ASC, "date"));
    }
    public void save(News news){
        newsRepository.save(news);
    }

    public News findById(int id){
        return newsRepository.findById(id).get();
    }
    public void deleteById(int id){
        newsRepository.deleteById(id);
    }

}
