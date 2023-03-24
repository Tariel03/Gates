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
    @Column(name = "description")
    String description;
    @JoinColumn(name = "tb_gates_type_id" )
    @ManyToOne
    gatesType gatesType;

    public Gates(String link, gatesType gatesType) {
        this.link = link;
        this.gatesType = gatesType;
    }
}
