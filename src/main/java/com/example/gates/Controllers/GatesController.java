package com.example.gates.Controllers;

import com.example.gates.Models.Gates;
import com.example.gates.Models.gatesType;
import com.example.gates.Services.GatesService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.persistence.ManyToOne;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/gates")
public class GatesController {
    @ManyToOne
    GatesService gatesService;

    @GetMapping()
    public List<Gates> gatesList(){
        return gatesService.findAll();
    }
    @GetMapping("/{id}")
    public Gates gatesById(@PathVariable int id){
        return gatesService.findById(id).orElse(null);
    }
    @GetMapping("/types/{id}")
    public gatesType gatesTypeById(@PathVariable int id){
        return gatesService.findTypeById(id);
    }
    @GetMapping("/types")
    public List<gatesType> showGatesTypes(){
        return gatesService.findAllGateTypes();
    }


}
