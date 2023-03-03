package com.example.gates.Services;

import com.example.gates.Models.Services;
import com.example.gates.Repositories.ServicesRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ServicesService{
    ServicesRepository servicesRepository;

    public List<Services> serviceList(){
        return servicesRepository.findAll();
    }
    public void deleteById(int id){
        servicesRepository.deleteById(id);
    }
    public Services findById(int id){
        Optional<Services> optionalServices = servicesRepository.findById(id);
        if(optionalServices.isPresent()){
            return optionalServices.get();
        }
        return null;
    }
    public void save(Services services){
        servicesRepository.save(services);
    }



}
