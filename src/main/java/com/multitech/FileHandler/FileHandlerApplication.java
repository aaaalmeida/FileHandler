package com.multitech.FileHandler;

import com.multitech.FileHandler.service.LivroService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@SpringBootApplication
@RestController
public class FileHandlerApplication {

	public static void main(String[] args) {
		SpringApplication.run(FileHandlerApplication.class, args);
	}

    @Bean
    CommandLineRunner startup(LivroService livroService) {
        return args -> {
            livroService.init();
        };
    }
}
