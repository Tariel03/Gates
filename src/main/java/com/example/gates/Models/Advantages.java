package com.example.gates.Models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;
import java.util.Map;

@Entity
@ToString
@Getter
@Setter
@NoArgsConstructor
@Table(name = "tb_advantages")
public class Advantages {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;
    @ElementCollection
    @CollectionTable(name = "mapOfAdvantages")
    Map<String, String> advantages;
    @JoinColumn(name = "tb_gates_type_id" )
    @ManyToOne
    gatesType gatesType;

    public Advantages(Map<String, String> advantages) {
        this.advantages = advantages;
    }
}
