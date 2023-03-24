package com.example.gates.Config;

import com.example.gates.Config.JWTFilter;
import com.example.gates.Services.AdminDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final AdminDetailsService adminDetailsService;
    private final JWTFilter jwtFilter;
    private final EncryptionConfig encryptionConfig;
    public SecurityConfig(boolean disableDefaults, AdminDetailsService adminDetailsService, JWTFilter jwtFilter, EncryptionConfig encryptionConfig) {
        super(disableDefaults);
        this.adminDetailsService = adminDetailsService;
        this.jwtFilter = jwtFilter;
        this.encryptionConfig = encryptionConfig;
    }
    @Autowired
    public SecurityConfig(AdminDetailsService adminDetailsService, JWTFilter jwtFilter, EncryptionConfig encryptionConfig) {
        this.adminDetailsService = adminDetailsService;
        this.jwtFilter = jwtFilter;
        this.encryptionConfig = encryptionConfig;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // конфигурируем сам Spring Security
        // конфигурируем авторизацию
        http.   
                cors().disable()
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/super/**", "/auth/registration").hasRole("SUPERADMIN")
                .antMatchers("/admin/**").hasAnyRole("ADMIN","SUPERADMIN")
                .antMatchers( "/h2-console/**","/auth/login","/auth/registration","/v3/api-docs/**",
                        "/swagger-ui/**","/swagger-ui.html","/gates/**", "/services/**", "/news/**", "/done/**","/review/**", "/advantages/**").permitAll()
                .anyRequest().authenticated()
                .and()
//                .formLogin().loginPage("/auth/login")
//                .loginProcessingUrl("/auth/process_login")
//                .failureUrl("/auth/login?error")
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login")
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
                http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(adminDetailsService)
                .passwordEncoder(encryptionConfig.getPasswordEncoder());
    }
//    @Bean
//    public PasswordEncoder getPasswordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }



}