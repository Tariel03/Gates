package com.example.gates.Controllers;

import com.example.gates.Models.Done;
import com.example.gates.Repositories.DoneRepository;
import com.example.gates.Services.DoneService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/done")
public class DoneController {
    DoneService doneService;
    ModelMapper modelMapper;
    private final DoneRepository doneRepository;

    @GetMapping()
    @CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3001", "*"})
    public List<Done> findAll(){
        return doneService.findAll();
    }
    @GetMapping("/{id}")
    @CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3001", "*"})
    public Done findById(@PathVariable int id){
        return doneRepository.findById(id).get();
    }




}
