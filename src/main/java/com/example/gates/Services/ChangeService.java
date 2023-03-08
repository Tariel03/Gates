package com.example.gates.Services;

import com.example.gates.Models.Admin;
import com.example.gates.Models.Changes;
import com.example.gates.Models.Order;
import com.example.gates.Repositories.ChangesRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@AllArgsConstructor
public class ChangeService {
    ChangesRepository changesRepository;
    OrderService orderService;
    public void save(Changes changes){
        changesRepository.save(changes);
    }
    public Changes findById(int id){
        return changesRepository.findById(id).orElse(null);
    }
    public Changes setOrder(Order order, Changes changes){
        changes.setOrder(order);
        changes.setDate(LocalDate.now());
        return changes;
    }
    public Changes setAdmin(Admin admin,Changes changes){
        changes.setAdmin(admin);
        changesRepository.save(changes);
        return changes;

    }

    public Changes setStatus(Changes changes,Order order){
        changes.setInfo(order.getStatus());
        changes.setOrder(order);
        return changes;

    }




}
