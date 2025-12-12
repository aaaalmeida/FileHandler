package com.multitech.FileHandler.initializer;

import com.multitech.FileHandler.domain.Livro;
import com.multitech.FileHandler.repository.LivroRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.*;

@Component
public class LivroRepositoryInitializer implements CommandLineRunner {
    @Autowired
    private LivroRepository livroRepository;

    private final Path uploadDir = Paths.get("ebooks");
    private final static Logger logger = LoggerFactory.getLogger(LivroRepositoryInitializer.class);

    public void run(String ...args) {
        try {
            // create directory if not exists
            Files.createDirectories(uploadDir);

            // create file registry in db if it's in directory and not previously registered
            DirectoryStream<Path> stream = Files.newDirectoryStream(uploadDir);
            for (Path file : stream) {
                if (Files.isRegularFile(file)) {
                    String fileName = file.getFileName().toString();
                    if(!livroRepository.existsByNome(fileName)) {
                        Livro livro = new Livro();
                        livro.setNome(fileName);
                        livroRepository.save(livro);
                        logger.info("New file found in directory and added to db: " + fileName);
                    }
                }
            }
        } catch (IOException e) {
            if (e instanceof FileAlreadyExistsException) {
                logger.warn("Already created directory");
                return;
            }
            throw new RuntimeException("Failed to create directory", e);
        }
    }
}
