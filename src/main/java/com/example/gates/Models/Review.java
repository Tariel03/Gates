package com.example.gates.Models;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "tb_review")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;
    String name;
    String link;
    @JoinColumn
    @ManyToOne
    Gates gates;
    String text;
}
