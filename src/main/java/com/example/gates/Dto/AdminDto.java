package com.example.gates.Dto;

import lombok.Data;

import javax.persistence.Column;
import javax.validation.constraints.Size;

@Data
public class AdminDto {
    @Size(message = "Name must be between 5 and 100 characters long", min = 5, max = 100)
    String name;
    @Size(message = "Name must be between 5 and 24 characters long", min = 5, max = 24)
    String username;
    @Size(message = "Name must be between 5 and 100 characters long", min = 5, max = 100)
    String password;
    String role;


}
