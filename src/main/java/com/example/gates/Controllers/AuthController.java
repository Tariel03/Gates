package com.example.gates.Controllers;

import com.example.gates.Config.JWTFilter;
import com.example.gates.Dto.AdminDto;
import com.example.gates.Dto.AuthDto;
import com.example.gates.Models.Admin;
import com.example.gates.Repositories.AdminRepository;
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
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController {
    AuthenticationManager authenticationManager;
    JwtUtil jwtUtil;
    ModelMapper modelMapper;
    MessageSource messageSource;
    AdminRepository adminRepository;
    RegistrationService registrationService;
    @PostMapping("/registration")
//    @CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3001"})
    @CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3001", "*"})
    @Operation(summary = "Registration", description = "This request creates a new user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = Admin.class)))) })
    public Map<String, String> performRegistration(@RequestBody @Valid AdminDto adminDto,
                                                   BindingResult bindingResult) {
        Admin admin = convertToUser(adminDto);
        if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            assert fieldError != null;
            String message = messageSource.getMessage(fieldError,null);
            return Map.of("message",message );
        }
        if(adminRepository.findByUsername(admin.getUsername()).isPresent()) {
            return Map.of("User by this username exists",admin.getUsername());
        }
        registrationService.register(admin);

        String token = jwtUtil.generateToken(admin.getUsername());
        return Map.of("jwt-token", token);
    }

    @PostMapping("/login")
//    @CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3001"})
    @CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3001", "*"})
    @Operation(summary = "Login", description = "This request is used for logging in")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = Admin.class)))) })
    public Map<String, String> performLogin(@RequestBody AuthDto authDto) {
        UsernamePasswordAuthenticationToken authInputToken =
                new UsernamePasswordAuthenticationToken(authDto.getUsername(),
                        authDto.getPassword());
        try {
            authenticationManager.authenticate(authInputToken);
        } catch (BadCredentialsException e) {
            return Map.of("message", "Incorrect credentials!");
        }

        String token = jwtUtil.generateToken(authDto.getUsername());
        return Map.of("jwt-token", token);
    }

    public Admin convertToUser(AdminDto adminDto) {
        return this.modelMapper.map(adminDto,Admin.class);
    }



}
