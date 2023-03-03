package com.example.gates.Dto;

import com.example.gates.Models.Order;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
public class DoneDto {
    @NotNull
    Order order;

}
