package com.example.gates.Repositories;

import com.example.gates.Models.Gates;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GatesRepository extends JpaRepository<Gates, Integer> {
    List<Gates> findGatesByStatus(boolean status);
}
