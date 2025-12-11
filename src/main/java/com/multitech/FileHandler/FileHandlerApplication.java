package com.multitech.FileHandler;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class FileHandlerApplication {
	public static void main(String[] args) {
		SpringApplication.run(FileHandlerApplication.class, args);
	}
}
