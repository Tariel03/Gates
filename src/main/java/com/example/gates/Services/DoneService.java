package com.example.gates.Services;

import com.example.gates.Models.Done;
import com.example.gates.Repositories.DoneRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class DoneService {
    DoneRepository doneRepository;
    public List<Done> findAll(){
        return doneRepository.findAll();
    }
    public Optional<Done>findById(int id){
        return doneRepository.findById(id);
    }
    public void delete(int id){
        doneRepository.deleteById(id);
    }
    public void save(Done done){
        doneRepository.save(done);
    }

}
