package com.example.gates.Dto;

import com.example.gates.Models.Admin;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
public class OrdersDto {
    @Size(max = 64, message = "Description is max 64 character long")
    String message;
    @Size(message = "Name must be between 5 and 100 characters long", min = 5, max = 100)
    String name;
    @NotNull
    LocalDate localDate;
    @Size(message = "Number must be between 5 and 100 characters long", min = 5, max = 100)
    String contact;
    public OrdersDto(String message, String name, LocalDate localDate, String contact) {
        this.message = message;
        this.name = name;
        this.localDate = localDate;
        this.contact = contact;
    }
}
