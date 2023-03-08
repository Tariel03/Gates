package com.example.gates.Services;

import com.example.gates.Models.Admin;
import com.example.gates.Models.Changes;
import com.example.gates.Models.Order;
import com.example.gates.Repositories.ChangesRepository;
import com.example.gates.Repositories.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class OrderService {
    OrderRepository orderRepository;
    ChangesRepository changesRepository;
    public List<Order> findAll(){
        return orderRepository.findAll(Sort.by(Sort.Direction.ASC));
    }
    public void save(Order order){
        orderRepository.save(order);
    }
    public Order receivedStatus(Order order){
        order.setStatus("received");
        orderRepository.save(order);
        return order;
    }
    public Order updatedStatus(Order order){
        order.setStatus("updated");
        orderRepository.save(order);
        return order;
    }
    public Order doneStatus(Order order){
        order.setStatus("done");
        orderRepository.save(order);
        return order;
    }
    public Optional<Order> findById(int id){
        return orderRepository.findById(id);
    }
    public void delete(int id){
        orderRepository.deleteById(id);
    }



}
