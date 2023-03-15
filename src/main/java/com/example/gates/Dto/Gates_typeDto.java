package com.example.gates.Dto;

import lombok.Data;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class Gates_typeDto {
    @Column(name = "type")
    @Size(min = 4, max = 20, message = "Type must be between 4 and 20 characters")
    String type;
    @Column(name = "link")
    @NotNull
    String link;
}
