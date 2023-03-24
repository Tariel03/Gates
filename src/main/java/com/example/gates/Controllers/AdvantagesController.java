package com.example.gates.Controllers;

import com.example.gates.Models.Advantages;
import com.example.gates.Services.AdditionalService;
import com.example.gates.Services.GatesService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class AdvantagesController {
    AdditionalService additionalService;
    GatesService gatesService;
    @GetMapping("/advantages")
    public Advantages findByGates(@RequestParam int gates_id ){
        return additionalService.advantages(gatesService.findTypeById(gates_id));
    }



}
