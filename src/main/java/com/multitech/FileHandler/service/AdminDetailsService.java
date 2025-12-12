package com.multitech.FileHandler.service;

import com.multitech.FileHandler.domain.Admin;
import com.multitech.FileHandler.repository.AdminRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AdminDetailsService implements UserDetailsService {
    private static final Logger logger = LoggerFactory.getLogger(LivroService.class);

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    public AdminRepository adminRepository;

    @Override
    public UserDetails loadUserByUsername(String email){
        Admin admin = adminRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return User.builder()
                .username(admin.getEmail())
                .password(admin.getSenha())
                .roles("ADMIN")
                .build();
    }
}
