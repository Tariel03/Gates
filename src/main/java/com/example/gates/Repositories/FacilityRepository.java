package com.example.gates.Repositories;

import com.example.gates.Models.Facility;
import com.example.gates.Models.Services;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FacilityRepository extends JpaRepository<Facility, Long> {
}