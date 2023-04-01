package com.example.gates.Controllers;

import com.example.gates.Models.Gates;
import com.example.gates.Models.gatesType;
import com.example.gates.Services.GatesService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.ManyToOne;
import java.nio.charset.StandardCharsets;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/gates")
public class GatesController {
    @ManyToOne
    GatesService gatesService;

    @GetMapping()
    @CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3001", "*"})

    public List<Gates> gatesList(){
        return gatesService.findAll();
    }
    @GetMapping("/{id}")
    @CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3001", "*"})
    public Gates gatesById(@PathVariable int id){
        return gatesService.findById(id).orElse(null);
    }
//    @GetMapping("/types/{id}")
//    @CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3001", "*"})
//    public gatesType gatesTypeById(@PathVariable int id){
//        return gatesService.findTypeById(id);
//    }

//    @GetMapping("/types/photo/{id}")
//    @CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3001"})
//
//    public ResponseEntity<byte[]> getPhoto(@PathVariable int id) {
//        // Code to retrieve the photo by ID and return it as a byte array
//        byte[] photoData = gatesService.findTypeById(id).getLink().getBytes(StandardCharsets.UTF_8);
//        // ...
//
//        // Return the photo data and set the Content-Type header to "image/jpeg"
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.IMAGE_JPEG);
//        return new ResponseEntity<>(photoData, headers, HttpStatus.OK);
//    }


//    @GetMapping("/types")
//    @CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3001", "*"})
//    public List<gatesType> showGatesTypes(){
//        return gatesService.findAllGateTypes();
//    }


}
