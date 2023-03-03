package com.example.gates.Controllers;

import com.example.gates.Dto.NewsDto;
import com.example.gates.Exceptions.MainException;
import com.example.gates.Models.Gates;
import com.example.gates.Models.Gates_type;
import com.example.gates.Models.News;
import com.example.gates.Models.Order;
import com.example.gates.Repositories.GatesRepository;
import com.example.gates.Repositories.Gates_typeRepository;
import com.example.gates.Services.GatesService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/gates")
public class GatesController {
    GatesService gatesService;

    @GetMapping()
    public List<Gates> gatesList(){
        return gatesService.findAll();
    }
    @GetMapping("/types")
    public List<Gates_type> showGatesTypes(){
        return gatesService.findAllGateTypes();
    }


}
