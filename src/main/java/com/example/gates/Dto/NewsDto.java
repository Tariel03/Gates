package com.example.gates.Dto;

import lombok.Data;


import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
@Data
public class NewsDto {
    @Size(min = 5, max = 100)
    String description;
    @NotNull
    String link;
    @NotNull
    String header;
    LocalDate date;
    public NewsDto(String description, String link, String header) {
        this.description = description;
        this.link = link;
        this.header = header;
        this.date = LocalDate.now();
    }

}
