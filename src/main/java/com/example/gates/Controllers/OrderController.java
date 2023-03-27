package com.example.gates.Controllers;

import com.example.gates.Exceptions.MainException;
import com.example.gates.Models.Changes;
import com.example.gates.Models.Order;
import com.example.gates.Services.ChangeService;
import com.example.gates.Services.NewsService;
import com.example.gates.Services.OrderService;
import com.example.gates.Services.RegistrationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jdk.jfr.Registered;
import lombok.AllArgsConstructor;
import com.example.gates.Dto.OrdersDto;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/order")
public class OrderController  {
    ModelMapper modelMapper;
    ChangeService changeService;
    RegistrationService registrationService;
    OrderService orderService;

    @PostMapping("/save")
    @Operation(summary = "Make order", description = "This request makes a new order")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = OrdersDto.class)))) })
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
        Changes changes = changeService.generate(order,order.getStatus());
        changeService.save(changes);
        orderService.save(order);
        return ResponseEntity.ok("In process");
    }
    public Order convertToOrder(OrdersDto orderDto) {
        return this.modelMapper.map(orderDto, Order.class);
    }




}
