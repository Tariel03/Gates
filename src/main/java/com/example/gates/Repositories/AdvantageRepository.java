package com.example.gates.Repositories;

import com.example.gates.Models.Advantages;
import com.example.gates.Models.Gates;
import com.example.gates.Models.gatesType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdvantageRepository extends JpaRepository<Advantages,Integer> {
    Advantages findAdvantagesByGates(Gates gates);
}
