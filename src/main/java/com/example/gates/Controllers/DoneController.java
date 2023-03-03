package com.example.gates.Controllers;

import com.example.gates.Dto.DoneDto;
import com.example.gates.Dto.NewsDto;
import com.example.gates.Exceptions.MainException;
import com.example.gates.Models.Done;
import com.example.gates.Models.News;
import com.example.gates.Repositories.DoneRepository;
import com.example.gates.Services.DoneService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.function.EntityResponse;

import javax.persistence.Access;
import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/done")
public class DoneController {
    DoneService doneService;
    ModelMapper modelMapper;
    private final DoneRepository doneRepository;

    @GetMapping()
    public List<Done> findAll(){
        return doneService.findAll();
    }
    @GetMapping("{id}")
    public Done findById(@PathVariable int id){
        return doneRepository.findById(id).get();
    }


    public Done convertToDone(DoneDto doneDto){return this.modelMapper.map(doneDto, Done.class);}


}
