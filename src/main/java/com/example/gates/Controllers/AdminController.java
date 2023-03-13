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
import org.springframework.security.web.authentication.session.ChangeSessionIdAuthenticationStrategy;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
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
    OrderService orderService;
    ChangeService changeService;
    ServicesService servicesService;
    AdditionalService additionalService;
    RegistrationService registrationService;


    @PostMapping("/gates/save")
    @Operation(summary = "Write comment", description = "This request makes a new order")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = Gates.class))))})
    public ResponseEntity<Gates> saveGates(@RequestBody Gates gates,@RequestParam int id, BindingResult result) {
        if (result.hasErrors()) {
            StringBuilder message = new StringBuilder();
            List<FieldError> fieldErrors = result.getFieldErrors();
            for (FieldError error : fieldErrors
            ) {
                message.append(error.getField()).append("-").append(error.getDefaultMessage());
            }
            throw new MainException(message.toString());
        }
        gates.setGates_type(gatesService.findTypeById(id));
        gatesService.save(gates);
        return ResponseEntity.ok(gates);
    }

    @DeleteMapping("/gates/delete")
    public ResponseEntity<HttpStatus> deleteGates(@RequestParam("id") int id) {
        gatesService.delete(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PostMapping("/done/save")
    public ResponseEntity<HttpStatus> saveDone(@RequestParam int id , BindingResult result){
        if (result.hasErrors()) {
            StringBuilder message = new StringBuilder();
            List<FieldError> fieldErrors = result.getFieldErrors();
            for (FieldError error : fieldErrors
            ) {
                message.append(error.getField()).append("-").append(error.getDefaultMessage());
            }
            throw new MainException(message.toString());
        }
        Done done = new Done();
        done.setOrder(orderService.findById(id).orElse(null));
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
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = News.class)))) })
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
        news.setAdmin(registrationService.currentUser());
        newsService.save(news);
        return ResponseEntity.ok("In process");
    }
    @DeleteMapping("/news/delete")
    public ResponseEntity<HttpStatus> deleteNews(int id){
        newsService.deleteById(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }
    @PostMapping("/review/save")
    public ResponseEntity<Review> saveReview(@Valid @RequestBody ReviewDto reviewDto,@RequestParam int id, BindingResult result){
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
        review.setGatesType(gatesService.findTypeById(id));
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
    @PostMapping("/order/makeOrder")
    @Operation(summary = "Make order", description = "This request makes a new order")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = Order.class)))) })
    public ResponseEntity<String> save(@RequestBody OrdersDto ordersDto, BindingResult result){
        if (result.hasErrors()) {
            StringBuilder message = new StringBuilder();
            List<FieldError> fieldErrors = result.getFieldErrors();
            for (FieldError error : fieldErrors
            ) {
                message.append(error.getField()).append("-").append(error.getDefaultMessage());
            }
            throw new MainException(message.toString());
        }
        Order  order = convertToOrder(ordersDto);
        orderService.receivedStatus(order);
        Changes changes = changeService.generate(order,order.getStatus(), registrationService.currentUser());
        changeService.save(changes);
        orderService.save(order);
        return ResponseEntity.ok("In process");
    }
    @GetMapping("/changes")
    public List<Changes> allChanges(){
        return changeService.allChanges();
    }
    @GetMapping("/changes/{id}")
    public ResponseEntity<Changes> findById(@PathVariable int id ){
        return ResponseEntity.ok(changeService.findById(id));
    }
    @PutMapping("/order/update/{id}")
    public ResponseEntity<String> update(@PathVariable int id){
        Order order = orderService.findById(id).get();
        order.setAdmin(registrationService.currentUser());
        orderService.updatedStatus(order, "updated");
        orderService.save(order);
        Changes changes = changeService.generate(order, order.getStatus(), registrationService.currentUser());
        changeService.save(changes);
        return ResponseEntity.ok("Order status has been changed");
    }


    public News convertToNews(NewsDto newsDto) {
        return this.modelMapper.map(newsDto, News.class);
    }
    public Review convertToReview(ReviewDto reviewDto){
        return this.modelMapper.map(reviewDto, Review.class);
    }
    public Services convertToServices(ServicesDto servicesDto){return  this.modelMapper.map(servicesDto, Services.class);}
    public Order convertToOrder(OrdersDto orderDto) {
        return this.modelMapper.map(orderDto, Order.class);
    }


}
