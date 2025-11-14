package com.multitech.FileHandler;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@SpringBootApplication
@RestController
public class FileHandlerApplication {

	public static void main(String[] args) {
		SpringApplication.run(FileHandlerApplication.class, args);
	}

    @GetMapping("/hello")
    public String hello(){
        LocalDateTime ldt = LocalDateTime.now();
        return ldt.toString();
    }
}
