package com.multitech.FileHandler.controller;

import com.multitech.FileHandler.domain.Curso;
import com.multitech.FileHandler.service.CursoService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/curso")
public class CursoController {
    @Autowired
    private CursoService cursoService;

    private static Logger logger = LoggerFactory.getLogger(CursoController.class);

    @GetMapping
    @Operation(summary = "Lista todos cursos")
    public ResponseEntity<List<Curso>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(cursoService.findAll());
    }

    @PostMapping
    @Operation(summary = "Cria novo curso")
    public ResponseEntity<Curso> createCurso(@ModelAttribute Curso curso) {
        logger.info(curso.toString());
        return ResponseEntity.status(HttpStatus.CREATED).body(cursoService.save(curso));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Procura curso por id")
    public ResponseEntity<Curso> findCursoById(@PathVariable Long id) {
        Curso curso = cursoService.findCursoById(id).orElseThrow(EntityNotFoundException::new);
        return ResponseEntity.status(HttpStatus.OK).body(curso);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Apaga curso por id")
    public void deleteCursoById(@PathVariable Long id) {
        cursoService.deleteCursoById(id);
    }
}
