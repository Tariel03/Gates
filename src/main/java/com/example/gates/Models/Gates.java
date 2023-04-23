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
    @Column(name = "status")
    boolean status;

    public Gates(String link, String description, String header) {
        this.link = link;
        this.description = description;
        this.header = header;
        this.status = true;
    }

    public Gates(String link) {
        this.link = link;
    }
}
