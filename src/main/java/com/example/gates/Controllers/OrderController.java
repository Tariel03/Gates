package com.example.gates.Controllers;

import com.example.gates.Exceptions.MainException;
import com.example.gates.Models.Order;
import com.example.gates.Services.NewsService;
import com.example.gates.Services.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import com.example.gates.Dto.OrdersDto;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/order")
public class OrderController  {
    OrderService orderService;
    private final ModelMapper modelMapper;
    @GetMapping()
    public List<Order> findAll(){
        return orderService.findAll();
    }

    public Order getOne(@PathVariable int id){
        return orderService.findById(id).orElse(null);
    }





}
