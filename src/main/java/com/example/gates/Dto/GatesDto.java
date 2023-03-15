package com.example.gates.Dto;

import com.example.gates.Models.Gates_type;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Data
@AllArgsConstructor
public class GatesDto {
    @Column(name = "link")
    String link;

}
