package com.example.gates.Services;

import com.example.gates.Models.Admin;
import com.example.gates.Models.Changes;
import com.example.gates.Models.Order;
import com.example.gates.Repositories.ChangesRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

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

    public Changes generate(Order order , String status, Admin admin){
        return new Changes(LocalDate.now(), status, admin,order);

    }
    public Changes generate(Order order , String status){
        return new Changes(LocalDate.now(), status, order);

    }
    public List<Changes> allChanges(){
        return changesRepository.findAll();
    }
    public Changes setAdmin(Admin admin,Changes changes){
        changes.setAdmin(admin);
        return changes;

    }





}
