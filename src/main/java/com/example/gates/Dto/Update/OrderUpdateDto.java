package com.example.gates.Dto.Update;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderUpdateDto {
    String message;
    String name;
    LocalDate localDate;
    String contact;
    public OrderUpdateDto(String message, String name,  String contact) {
        this.message = message;
        this.name = name;
        this.localDate = LocalDate.now();
        this.contact = contact;
    }
}
