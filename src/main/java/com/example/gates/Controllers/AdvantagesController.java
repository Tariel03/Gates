package com.example.gates.Controllers;

import com.example.gates.Models.Advantages;
import com.example.gates.Services.AdditionalService;
import com.example.gates.Services.GatesService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class AdvantagesController {
    AdditionalService additionalService;
    GatesService gatesService;
    @GetMapping("/advantages")
//    @CrossOrigin(origins = {"http://localhost:3000", "*"})
    @CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3001", "*"})
    public Advantages findByGates(@RequestParam int gates_id ){
        return additionalService.advantages(gatesService.findById(gates_id).get());
    }



}
