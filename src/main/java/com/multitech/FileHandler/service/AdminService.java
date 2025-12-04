package com.multitech.FileHandler.service;

import com.multitech.FileHandler.crypto.PasswordUtils;
import com.multitech.FileHandler.domain.Admin;
import com.multitech.FileHandler.repository.AdminRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Base64;

@Service
public class AdminService {
    private static Logger logger = LoggerFactory.getLogger(LivroService.class);

    @Autowired
    public AdminRepository adminRepository;

    @Value("${default-password}")
    private String unhashedPassword;
    @Value("${default-email}")
    private String email;

    public void init() {
        // already exists
        if(adminRepository.findByEmail(email).isPresent()) {
            logger.warn("Already inserted admin");
            return;
        }

        byte[] salt = PasswordUtils.nextSalt();
        byte[] hash = PasswordUtils.hashPassword(unhashedPassword.toCharArray(), salt);

        Admin admin = new Admin();
        admin.setEmail(email);
        admin.setSalt(salt);
        admin.setSenha(Base64.getEncoder().encodeToString(hash));

        adminRepository.save(admin);
    };
}
