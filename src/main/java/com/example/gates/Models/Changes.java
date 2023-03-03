package com.example.gates.Models;

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
    @JoinColumn(name = "admin_id")
    @ManyToOne
    Admin admin;
    @JoinColumn(name = "order_id")
    @ManyToOne
    Order order;
}
