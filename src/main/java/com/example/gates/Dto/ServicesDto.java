package com.example.gates.Dto;

import lombok.Data;

import javax.persistence.Column;
@Data
public class ServicesDto {
    @Column(name = "name")
    String name;
    @Column(name = "photo")
    byte[] photo;
}
