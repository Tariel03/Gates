package com.example.gates.Dto.Update;

import lombok.Data;

import javax.validation.constraints.Size;

@Data
public class ReviewUpdateDto {
    String name;
    String text;
}
