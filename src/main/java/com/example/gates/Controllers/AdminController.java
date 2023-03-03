package com.example.gates.Controllers;

import com.example.gates.Dto.*;
import com.example.gates.Exceptions.MainException;
import com.example.gates.Models.*;
import com.example.gates.Services.*;
import com.example.gates.util.JwtUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/admin")
public class AdminController {
    JwtUtil jwtUtil;
    GatesService gatesService;
    AuthenticationManager authenticationManager;
    DoneService doneService;
    ModelMapper modelMapper;
    NewsService newsService;
    ServicesService servicesService;
    AdditionalService additionalService;

    @PostMapping("/login")
    @Operation(summary = "Login", description = "This request is used for logging in")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = Admin.class))))})
    public Map<String, String> performLogin(@RequestBody AuthDto authenticationDTO) {
        UsernamePasswordAuthenticationToken authInputToken =
                new UsernamePasswordAuthenticationToken(authenticationDTO.getUsername(),
                        authenticationDTO.getPassword());

        try {
            authenticationManager.authenticate(authInputToken);
        } catch (BadCredentialsException e) {
            return Map.of("message", "Incorrect credentials!");
        }

        String token = jwtUtil.generateToken(authenticationDTO.getUsername());
        return Map.of("jwt-token", token);
    }

    @PostMapping("/gates/save")
    @Operation(summary = "Write comment", description = "This request makes a new order")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = Gates.class))))})
    public ResponseEntity<Gates> saveGates(@RequestBody Gates gates, BindingResult result) {
        if (result.hasErrors()) {
            StringBuilder message = new StringBuilder();
            List<FieldError> fieldErrors = result.getFieldErrors();
            for (FieldError error : fieldErrors
            ) {
                message.append(error.getField()).append("-").append(error.getDefaultMessage());
            }
            throw new MainException(message.toString());
        }
        gatesService.save(gates);
        return ResponseEntity.ok(gates);
    }

    @DeleteMapping("/gates/delete")
    public ResponseEntity<HttpStatus> deleteGates(@RequestParam("id") int id) {
        gatesService.delete(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PostMapping("/done/save")
    public ResponseEntity<HttpStatus> saveDone(@RequestBody DoneDto doneDto, BindingResult result){
        if (result.hasErrors()) {
            StringBuilder message = new StringBuilder();
            List<FieldError> fieldErrors = result.getFieldErrors();
            for (FieldError error : fieldErrors
            ) {
                message.append(error.getField()).append("-").append(error.getDefaultMessage());
            }
            throw new MainException(message.toString());
        }
        Done done = convertToDone(doneDto);
        doneService.save(done);

        return ResponseEntity.ok(HttpStatus.OK);
    }
    @DeleteMapping("/done/delete")
    public ResponseEntity<HttpStatus> deleteDone(int id){
        doneService.delete(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }
    @PostMapping("/news/save")
    @Operation(summary = "Write news", description = "This request makes a new order")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = Order.class)))) })
    public ResponseEntity<String> saveNews(@RequestBody NewsDto newsDto, BindingResult result){
        if (result.hasErrors()) {
            StringBuilder message = new StringBuilder();
            List<FieldError> fieldErrors = result.getFieldErrors();
            for (FieldError error : fieldErrors
            ) {
                message.append(error.getField()).append("-").append(error.getDefaultMessage());
            }
            throw new MainException(message.toString());
        }
        News news = convertToNews(newsDto);
        newsService.save(news);
        return ResponseEntity.ok("In process");
    }
    @DeleteMapping("/news/delete")
    public ResponseEntity<HttpStatus> deleteNews(int id){
        newsService.deleteById(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }
    @PostMapping("/review/save")
    public ResponseEntity<Review> saveReview(@Valid @RequestBody ReviewDto reviewDto, BindingResult result){
        if(result.hasErrors()){
            StringBuilder message = new StringBuilder();
            List<FieldError> fieldErrors = result.getFieldErrors();
            for (FieldError error:fieldErrors
                 ) {
                message.append(error.getField()).append("-").append(error.getDefaultMessage());
            }
            throw new MainException(message.toString());
        }
        Review review   = convertToReview(reviewDto);
        additionalService.saveReview(review);
        return ResponseEntity.ok(review);
    }
    @PostMapping("services/save")
    public ResponseEntity<Services> saveServices(@Valid @RequestBody ServicesDto servicesDto, BindingResult result){
        if(result.hasErrors()){
            StringBuilder message = new StringBuilder();
            List<FieldError> fieldErrors = result.getFieldErrors();
            for (FieldError error:fieldErrors
            ) {
                message.append(error.getField()).append("-").append(error.getDefaultMessage());
            }
            throw new MainException(message.toString());
        }
       Services services = convertToServices(servicesDto);
        servicesService.save(services);
        return ResponseEntity.ok(services);
    }


    public News convertToNews(NewsDto newsDto) {
        return this.modelMapper.map(newsDto, News.class);
    }
    public Review convertToReview(ReviewDto reviewDto){
        return this.modelMapper.map(reviewDto, Review.class);
    }
    public Done convertToDone(DoneDto doneDto){return this.modelMapper.map(doneDto, Done.class);}
    public Services convertToServices(ServicesDto servicesDto){return  this.modelMapper.map(servicesDto, Services.class);}

}
