package com.example.gates.Models;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "done_projects")
public class Done_projects {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;
    @ManyToOne
    @JoinColumn(name = "orders_id")
    Orders orders;
    @Column(name = "photo")
    byte[] photo;
    @Column(name = "type")
    String type;
}
