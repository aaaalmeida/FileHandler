package com.multitech.FileHandler.initializer;

import com.multitech.FileHandler.domain.Admin;
import com.multitech.FileHandler.repository.AdminRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class AdminInitializer implements CommandLineRunner {
    private static final Logger logger = LoggerFactory.getLogger(AdminInitializer.class);

    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;

    public AdminInitializer(AdminRepository adminRepository, PasswordEncoder passwordEncoder) {
        this.adminRepository = adminRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Value("${default-password}")
    private String unhashedPassword;
    @Value("${default-email}")
    private String email;

    public void run(String ...args) {
        // create an admin account if not exists
        if(adminRepository.findByEmail(email).isPresent()) {
            logger.warn("Already inserted admin");
            return;
        }
        Admin admin = new Admin();
        admin.setEmail(email);
        admin.setSenha(passwordEncoder.encode(unhashedPassword));

        adminRepository.save(admin);
    };
}
