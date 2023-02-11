package com.example.gates.Repositories;

import com.example.gates.Models.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface AdminRepository extends JpaRepository<Admin, int> , JpaSpecificationExecutor<Admin> {
}