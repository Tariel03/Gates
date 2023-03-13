package com.example.gates.Dto;

import com.example.gates.Models.Gates_type;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Column;

@Data
@AllArgsConstructor
public class GatesDto {
    Gates_type gatesType;
    @Column(name = "link")
    String link;

}
