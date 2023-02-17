package com.example.gates.Services;

import com.example.gates.Models.Order;
import com.example.gates.Repositories.ChangesRepository;
import com.example.gates.Repositories.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class OrderSevice {
    OrderRepository orderRepository;
    ChangesRepository changesRepository;
    public List<Order> findAll(){
        return orderRepository.findAll();
    }


}
