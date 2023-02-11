package com.example.gates.Models;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "answer")
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;
    @Column(name = "answer")
    String answer;
    @ManyToOne
    @JoinColumn(name = "questions_id")
    Questions questions;
    @ManyToOne
    @JoinColumn(name = "admin_id")
    Admin admin;

}
