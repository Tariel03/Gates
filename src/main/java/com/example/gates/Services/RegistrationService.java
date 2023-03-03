package com.example.gates.Services;

import com.example.gates.Models.Admin;
import com.example.gates.Repositories.AdminRepository;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@AllArgsConstructor
public class RegistrationService {
    private final PasswordEncoder passwordEncoder;
    private final AdminRepository adminRepository;

    @Transactional
    public void register(Admin admin){
        String pass = passwordEncoder.encode(admin.getPassword());
        admin.setPassword(pass);
        admin.setRole("ROLE_ADMIN");
        adminRepository.save(admin);
    }
    public Admin currentUser (){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails personDetails = (UserDetails) authentication.getPrincipal();
        String username =  personDetails.getUsername();
        Optional<Admin>userOptional = adminRepository.findByUsername(username);
        return userOptional.orElse(null);
    }

}
