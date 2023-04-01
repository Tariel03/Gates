package com.example.gates.Models;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@Table(name = "tb_gates")
public class Gates {
    @Id
    @GeneratedValue( strategy = GenerationType.AUTO)
    int id;
    @Column(name = "link")
    String link;
    @Column(name = "description",columnDefinition = "TEXT")
    String description;
    @Column(name =  "header")
    String header;


    public Gates(String link) {
        this.link = link;
    }
}
