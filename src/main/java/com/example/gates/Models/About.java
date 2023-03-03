package com.example.gates.Models;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "tb_about")
public class About {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;
    String description;
    @Column(name = "header")
    String header;
    @Column(name = "photo")
    byte[] photo;



}
