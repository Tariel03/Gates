package com.example.gates.Models;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "questions")
public class Questions {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;
    @Column(name = "fullname")
    String fullname;
    @Column(name = "question")
    String questions;
    @Column(name = "contact")
    String contact;
    @Column(name = "status")
    String status;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Questions questions = (Questions) o;
        return false;
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
