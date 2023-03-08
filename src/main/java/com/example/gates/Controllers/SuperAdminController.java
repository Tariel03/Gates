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
    @PostMapping("/registration")
    @Operation(summary = "Registration", description = "This request creates a new user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = Admin.class)))) })

    public ResponseEntity<String> registration(@RequestBody @Valid AdminDto adminDto, BindingResult bindingResult){
        Admin admin = convertToUser(adminDto);
        if(bindingResult.hasErrors()){
            FieldError fieldError = bindingResult.getFieldError();
            assert fieldError != null;
            String message = messageSource.getMessage(fieldError, null);
            return ResponseEntity.ok(message);
        }
        if(adminRepository.findByUsername(admin.getUsername()).isPresent()) {
            return ResponseEntity.ok("User by this username exists");
        }
        return ResponseEntity.ok("Created an admin account");

    }
    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable int id){
        adminRepository.deleteById(id);
        return "Successfully deleted";
    }
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

    public Admin convertToUser(AdminDto adminDto) {
        return this.modelMapper.map(adminDto,Admin.class);
    }


}
