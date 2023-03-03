package com.example.gates.Services;

import com.example.gates.Models.Admin;
import com.example.gates.Repositories.AdminRepository;
import com.example.gates.Security.AdminDetails;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdminDetailsService implements UserDetailsService {
    AdminRepository adminRepository;
    @Autowired
    public AdminDetailsService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Admin> optionalAdmin = adminRepository.findByUsername(username);
        if(optionalAdmin.isEmpty()){
            throw  new UsernameNotFoundException("User not found");
        }
        return new AdminDetails(optionalAdmin.get());
    }
}
