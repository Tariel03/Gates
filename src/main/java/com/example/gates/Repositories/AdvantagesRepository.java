package com.example.gates.Repositories;

import com.example.gates.Models.Advantages;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdvantagesRepository extends JpaRepository<Advantages,Integer> {
}
