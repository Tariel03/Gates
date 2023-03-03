package com.example.gates.Repositories;

import com.example.gates.Models.Gates;
import com.example.gates.Models.Gates_type;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GatesRepository extends JpaRepository<Gates, Integer> {
}
