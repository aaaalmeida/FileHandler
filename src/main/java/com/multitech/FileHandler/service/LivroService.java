package com.multitech.FileHandler.service;

import com.multitech.FileHandler.DTO.LivroDTO;
import com.multitech.FileHandler.domain.Curso;
import com.multitech.FileHandler.domain.Livro;
import com.multitech.FileHandler.exception.EmptyFileException;
import com.multitech.FileHandler.exception.IllegalFileFormatException;
import com.multitech.FileHandler.repository.CursoRepository;
import com.multitech.FileHandler.repository.LivroRepository;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.*;
import java.util.*;

@Service
public class LivroService{
    @Autowired
    private LivroRepository livroRepository;
    @Autowired
    private CursoRepository cursoRepository;
    private static Logger logger = LoggerFactory.getLogger(LivroService.class);
    private final Path uploadDir = Paths.get("ebooks");

    public List<Livro> listAll() {
        return livroRepository.findAll();
    }

    public Optional<Livro> findLivroById(Long id) {
        return livroRepository.findById(id);
    }

    public List<Livro> findByIdCurso(Long idCurso) {
        return livroRepository.findByCursoId(idCurso);
    }

    public void deleteLivroById(Long id) {
        livroRepository.deleteById(id);
    }

    public Livro uploadFile(LivroDTO dto, MultipartFile file) {
        try {
            // validate if file exists
            if (file.isEmpty()) {
                throw new EmptyFileException("Failed to store empty file.");
            }

            // validate file name
            if (file.getName().isBlank()) {
                throw new IllegalFileFormatException("File must have a name.");
            }

            // validate if file is a pdf
            if (!file.getContentType().equalsIgnoreCase("application/pdf")) {
                throw new IllegalFileFormatException("File must be a pdf.");
            }

            Path destinationPath = uploadDir.resolve(dto.nome()).normalize().toAbsolutePath();

            // avoid "/../"
            if (!destinationPath.getParent().equals(uploadDir)) {
                logger.info(destinationPath.toString());
                logger.info(uploadDir.toString());
//                throw new SecurityException();
            }

            // save file in directory
            Files.copy(file.getInputStream(), destinationPath, StandardCopyOption.REPLACE_EXISTING);

            Set<Curso> cursos = new HashSet<>();
            dto.idCurso().forEach(cursoId -> cursoRepository.findById(cursoId).ifPresent(cursos::add));

            Livro livro = new Livro();
            livro.setNome(dto.nome());
            livro.setCursos(cursos);

            // save entity in db
            return livroRepository.save(livro);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Resource downloadFile(Long id) {
        logger.warn("DOWNLOAD FILE ID"+ id);

        Livro livro = livroRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        logger.warn(livro.toString());
        try {
            Path file = uploadDir.resolve(livro.getNome()).normalize();
            Resource resource = new UrlResource(file.toUri());
            if (!resource.exists()) {
                throw new FileNotFoundException("File do not exists");
            }

            return resource;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void init() {
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
