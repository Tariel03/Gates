package com.example.gates.Controllers;

import com.example.gates.Models.Services;
import com.example.gates.Repositories.ServicesRepository;
import com.example.gates.Services.ServicesService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/services")
public class ServicesController {
    ServicesService servicesService;

    @GetMapping()
    public List<Services> servicesList(){
        return  servicesService.serviceList();
    }
    @GetMapping("/{id}")
    public Services findById(@PathVariable int id){
        return servicesService.findById(id);
    }
}
