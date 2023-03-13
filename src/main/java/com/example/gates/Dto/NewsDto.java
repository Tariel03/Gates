package com.example.gates.Dto;

import com.example.gates.Models.Admin;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDate;

public class NewsDto {
    String description;
    String link;
    String header;
    LocalDate date;
    Admin admin;
    public NewsDto(String description, String header, Admin admin) {
        this.description = description;
        this.header = header;
        this.date = LocalDate.now();
        this.admin = admin;
    }
}
