package com.example.gates.Services;

import com.example.gates.Models.Admin;
import com.example.gates.Repositories.AdminRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AdminService {
    AdminRepository adminRepository;
    public List<Admin> adminList(){
        return adminRepository.findAll();
    }
    public void save(Admin admin){
        adminRepository.save(admin);
    }
    public Admin findById(int id){
        return adminRepository.findById(id).orElse(null);
    }
    public Admin findByUsername(String username){
        return adminRepository.findByUsername(username).orElse(null);
    }

    public void deleteById(int id){
        adminRepository.deleteById(id);
    }
}
