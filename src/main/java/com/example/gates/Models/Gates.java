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
    @Column(name = "photo")
    byte[] photo;
    @JoinColumn(name = "tb_gates_type_id" )
    @ManyToOne
   Gates_type gates_type;

    public Gates(byte[] photo, Gates_type gatesType) {
        this.photo = photo;
        this.gates_type = gatesType;
    }
}
