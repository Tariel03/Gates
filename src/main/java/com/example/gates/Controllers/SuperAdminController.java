package com.example.gates.Controllers;

import com.example.gates.Dto.AdminDto;
import com.example.gates.Models.Admin;
import com.example.gates.Repositories.AdminRepository;
import com.example.gates.Services.AdminService;
import com.example.gates.Services.RegistrationService;
import com.example.gates.util.JwtUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/super")
public class SuperAdminController {
    AdminService adminService;
    AdminRepository adminRepository;
    MessageSource messageSource;
    RegistrationService registrationService;
    JwtUtil jwtUtil;
    ModelMapper modelMapper;

//    @DeleteMapping("/delete/{id}")
//    public String delete(@PathVariable int id){
//        adminRepository.deleteById(id);
//        return "Successfully deleted";
//    }
    @PutMapping("/upgrade/{id}")
    public Admin upgradeToSuper(@PathVariable int id){
            Admin admin = adminService.findById(id);
            admin.setRole("ROLE_SUPERADMIN");
            adminRepository.save(admin);
            return admin;
    }
    @PutMapping("/block/{id}")
    public Admin blockAdmin(@PathVariable int id){
            Admin admin = adminService.findById(id);
            admin.setRole(null);
            adminService.save(admin);
            return admin;
    }
    @PutMapping("restore/{id}")
    public Admin restoreAdmin(@PathVariable int id) {
            Admin admin = adminService.findById(id);
            admin.setRole("ROLE_ADMIN");
            adminRepository.save(admin);
            return admin;
    }



}
