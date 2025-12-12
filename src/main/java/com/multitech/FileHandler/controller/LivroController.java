package com.multitech.FileHandler.controller;

import com.multitech.FileHandler.DTO.LivroDTO;
import com.multitech.FileHandler.domain.Livro;
import com.multitech.FileHandler.service.LivroService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/livro")
public class LivroController {
    @Autowired
    private LivroService livroService;
    private static Logger logger = LoggerFactory.getLogger(LivroController.class);

    @GetMapping
    @Operation(summary = "Lista todos livros")
    public ResponseEntity<List<Livro>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(livroService.listAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Procura livro por id")
    public ResponseEntity<Livro> findLivroById(@PathVariable Long id) {
        Livro livro = livroService.findLivroById(id).orElseThrow(EntityNotFoundException::new);
        return ResponseEntity.status(HttpStatus.OK).body(livro);
    }

    @GetMapping("/curso/{cursoId}")
    @Operation(summary = "Lista livros pelo id do curso")
    public ResponseEntity<List<Livro>> findLivroByCursoId(@PathVariable Long cursoId) {
        return ResponseEntity.ok(livroService.findByIdCurso(cursoId));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Apaga livro por id")
    public void deleteLivroById(@PathVariable Long id) {
        livroService.deleteLivroById(id);
    }

    @PostMapping(
      value="/upload",
      consumes=MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Adiciona novo livro")
    public ResponseEntity<Livro> uploadFile(
            @RequestPart("file") MultipartFile file,
            @ModelAttribute LivroDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(livroService.uploadFile(dto, file));
    }

    @GetMapping("/download/{id}")
    @Operation(summary = "Baixa livro por id")
    public ResponseEntity<Resource> downloadFileById(@PathVariable Long id) {
        Resource file = livroService.downloadFile(id);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_PDF)
                .header(HttpHeaders.CONTENT_DISPOSITION,"inline; filename=\"" + file.getFilename() + ".pdf\"")
                .body(file);
    }
}
