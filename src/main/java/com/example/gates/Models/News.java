package com.example.gates.Models;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "tb_news")
public class News {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;
    @Size(min = 5, max = 100, message = "Between 5 and 200!")
    @Column(name = "description")
    String description;
    @Column(name = "header")
    @Size(min = 5, max = 20, message = "Between 5 and 20!")
    String header;
    @Column(name = "date")
    LocalDate date;
    @Column(name = "main_photo")
    String main_photo;
    @Column(name = "second_photo")
    String second_photo;
    @JoinColumn(name = "tb_admin_id")
    @ManyToOne
    Admin admin;


}
