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
    public SecurityConfig(boolean disableDefaults, AdminDetailsService adminDetailsService, JWTFilter jwtFilter) {
        super(disableDefaults);
        this.adminDetailsService = adminDetailsService;
        this.jwtFilter = jwtFilter;
    }
    @Autowired
    public SecurityConfig(AdminDetailsService adminDetailsService, JWTFilter jwtFilter) {
        this.adminDetailsService = adminDetailsService;
        this.jwtFilter = jwtFilter;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // конфигурируем сам Spring Security
        // конфигурируем авторизацию
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/super/**").hasRole("SUPERADMIN")
                .antMatchers("/admin/**").hasAnyRole("ADMIN","SUPERADMIN")
                .antMatchers( "/admin/login","/v3/api-docs/**","/swagger-ui/**","/swagger-ui.html","/gates/**", "/services/**").permitAll()
                .anyRequest().authenticated()

                .and()
                .formLogin().loginPage("/admin/login")
                .loginProcessingUrl("/admin/process_login")
                .failureUrl("/admin/login?error")
                .and()
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
                .passwordEncoder(getPasswordEncoder());
    }
    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }



}