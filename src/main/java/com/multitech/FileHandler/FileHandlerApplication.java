package com.multitech.FileHandler;

import com.multitech.FileHandler.service.AdminService;
import com.multitech.FileHandler.service.LivroService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class FileHandlerApplication {

	public static void main(String[] args) {
		SpringApplication.run(FileHandlerApplication.class, args);
	}

    @Bean
    CommandLineRunner startup(LivroService livroService, AdminService adminService) {
        return args -> {
            livroService.init();
            adminService.init();
        };
    }
}
