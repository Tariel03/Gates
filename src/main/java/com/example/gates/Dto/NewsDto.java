package com.example.gates.Dto;

import lombok.Data;


import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
@Data
public class NewsDto {
    @Column(name = "description")
    String description;
    @Column(name = "header")
    String header;
    LocalDate date;


    public NewsDto(String description, String header) {
        this.description = description;
        this.header = header;
        this.date = LocalDate.now();
    }
}
