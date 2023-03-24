package com.example.gates.Dto;

import lombok.Data;

import javax.validation.constraints.Size;

@Data
public class ReviewDto {
    @Size(min = 4, max = 100, message = "Name must be between 4 and 100 characters long")
    String name;
    @Size(min = 10, max = 200, message = "Text must be between 10 and 100 characters long")
    String text;

}
