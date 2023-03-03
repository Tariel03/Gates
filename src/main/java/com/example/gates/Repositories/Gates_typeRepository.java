package com.example.gates.Repositories;

import com.example.gates.Models.Gates_type;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface Gates_typeRepository extends JpaRepository<Gates_type, Integer> {
    Optional<Gates_type>findByType(String type);
}
