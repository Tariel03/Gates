package com.example.gates.Dto;

import com.example.gates.Models.Gates_type;
import lombok.Data;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class ReviewDto {
    @Size(min = 4, max = 100, message = "Name must be between 4 and 100 characters long")
    String name;
    @Size(min = 10, max = 200, message = "Text must be between 10 and 100 characters long")
    String text;

}
