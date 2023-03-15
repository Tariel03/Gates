package com.example.gates.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "tb_order")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;
    @Column(name = "message")
    String message;
    @Column(name = "name")
    String name;
    @Column(name = "status")
    String status;
    @Column(name = "contact")
    String contact;
    @JoinColumn(name = "tb_admin_id")
    @ManyToOne
    @JsonIgnore
    Admin admin;

    public Order(String message, String name, String contact) {
        this.message = message;
        this.name = name;
        this.contact = contact;
    }
}
