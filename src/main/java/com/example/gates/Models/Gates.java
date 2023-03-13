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
    @JoinColumn(name = "tb_gates_type_id" )
    @ManyToOne
   Gates_type gates_type;

    public Gates(String link, Gates_type gatesType) {
        this.link = link;
        this.gates_type = gatesType;
    }
}
