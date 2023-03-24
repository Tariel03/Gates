package com.example.gates.Repositories;

import com.example.gates.Models.gatesType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface Gates_typeRepository extends JpaRepository<gatesType, Integer> {
    Optional<gatesType>findByType(String type);
}
