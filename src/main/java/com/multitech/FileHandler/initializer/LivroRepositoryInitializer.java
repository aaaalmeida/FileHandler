package com.multitech.FileHandler.initializer;

import com.multitech.FileHandler.service.LivroService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Component
public class LivroRepositoryInitializer implements CommandLineRunner {
    private final Path uploadDir = Paths.get("ebooks");
    private static Logger logger = LoggerFactory.getLogger(LivroService.class);

    public void run(String ...args) {
        try {
            Files.createDirectory(uploadDir);
        } catch (IOException e) {
            if (e instanceof FileAlreadyExistsException) {
                logger.warn("Already created directory");
                return;
            }
            throw new RuntimeException("Failed to create directory", e);
        }
    }
}
