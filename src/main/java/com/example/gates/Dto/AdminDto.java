package com.example.gates.Dto;

import lombok.Data;

import javax.persistence.Column;
import javax.validation.constraints.Size;

@Data
public class AdminDto {
    @Column(name = "name")
    @Size(message = "Name must be between 5 and 100 characters long", min = 5, max = 100)
    String name;
    @Column(name = "username",unique = true)
    @Size(message = "Name must be between 5 and 24 characters long", min = 5, max = 24)
    String username;
    @Size(message = "Name must be between 5 and 100 characters long", min = 5, max = 100)
    @Column(name = "password")
    String password;
    @Column(name = "role")
    String role;


}
