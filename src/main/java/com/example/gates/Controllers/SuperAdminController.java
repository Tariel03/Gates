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
import java.util.List;
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
    @CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3001", "*"})

    public String upgradeToSuper(@PathVariable int id){
            Admin admin = adminService.findById(id);
            if(admin.getRole().equals("ROLE_ADMIN")){
                admin.setRole("ROLE_SUPERADMIN");
                adminRepository.save(admin);
                return admin + " is upgraded to Super Admin";
            }
            return admin + " can't be upgraded";
    }
    @PutMapping("/block/{id}")
    @CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3001", "*"})

    public String blockAdmin(@PathVariable int id){
            Admin admin = adminService.findById(id);
            if(!admin.getRole().equalsIgnoreCase("ROLE_SUPERADMIN")) {
                admin.setRole(null);
                adminService.save(admin);
                return admin + " Is not changeable";
            }
            return admin + " is changed";
    }
    @PutMapping("restore/{id}")
    @CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3001", "*"})

    public String restoreAdmin(@PathVariable int id) {
            Admin admin = adminService.findById(id);
            if( admin.getRole().equals("ROLE_SUPERADMIN") || admin.getRole().equals("ROLE_ADMIN") ) {
                return admin + " doesn't need to be restored";
            }
            admin.setRole("ROLE_ADMIN");
            adminRepository.save(admin);
            return admin + " is restored back to admin";
    }
    @GetMapping("/admins")
    @CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3001", "*"})
    public List<Admin> adminList(){
        return adminService.adminList();
    }



}
