package com.example.gates.Dto.Update;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewsUpdateDto {
    String description;
    String link;
    String header;
    LocalDate date;
    public NewsUpdateDto(String description, String link, String header) {
        this.description = description;
        this.link = link;
        this.header = header;
        this.date = LocalDate.now();
    }
}
