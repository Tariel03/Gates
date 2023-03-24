package com.example.gates.Controllers;

import com.example.gates.Dto.NewsDto;
import com.example.gates.Dto.OrdersDto;
import com.example.gates.Exceptions.MainException;
import com.example.gates.Models.News;
import com.example.gates.Models.Order;
import com.example.gates.Services.NewsService;
//import io.swagger.v3.oas.annotations.Operation;
//import io.swagger.v3.oas.annotations.media.ArraySchema;
//import io.swagger.v3.oas.annotations.media.Content;
//import io.swagger.v3.oas.annotations.media.Schema;
//import io.swagger.v3.oas.annotations.responses.ApiResponse;
//import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/news")
public class NewsController {
    NewsService newsService;
    ModelMapper modelMapper;
    @GetMapping()
    public List<News> newsByDate(){
        return  newsService.newsOrder();
    }
    @GetMapping("{id}")
    public News findById(@PathVariable int id){
       return newsService.findById(id);
    }




}
