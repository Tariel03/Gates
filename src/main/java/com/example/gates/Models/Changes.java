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
@Table(name = "tb_changes")
public class Changes {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;
    @Column(name = "date")
    LocalDate date;
    @Column(name = "info")
    String info;
    @JoinColumn(name = "admin_id")
    @ManyToOne
    @JsonIgnore
    Admin admin;
    @JoinColumn(name = "order_id")
    @ManyToOne
    Order order;

    public Changes(LocalDate date, String info, Admin admin, Order order) {
        this.date = date;
        this.info = info;
        this.admin = admin;
        this.order = order;
    }
}
