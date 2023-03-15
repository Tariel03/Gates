package com.example.gates.Dto;

import lombok.Data;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class ServicesDto {
    @Column(name = "name")
    @Size(min=4, max = 25, message = "Name of services must be between 4 and 25")
    String name;
    @NotNull
    @Column(name = "link")
    String link;

}
