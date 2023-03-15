package com.example.gates.Dto.Update;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
public class OrderUpdateDto {
    String message;
    String name;
    LocalDate localDate;
    String contact;
    public OrderUpdateDto(String message, String name, LocalDate localDate, String contact) {
        this.message = message;
        this.name = name;
        this.localDate = localDate;
        this.contact = contact;
    }
}
