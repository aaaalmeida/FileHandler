package com.multitech.FileHandler.controller;

import com.multitech.FileHandler.domain.Curso;
import com.multitech.FileHandler.service.CursoService;
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
    public ResponseEntity<List<Curso>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(cursoService.findAll());
    }

    @PostMapping
    public ResponseEntity<Curso> createCurso(@ModelAttribute Curso curso) {
        logger.info(curso.toString());
        return ResponseEntity.status(HttpStatus.CREATED).body(cursoService.save(curso));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Curso> findCursoById(@PathVariable Long id) {
        Curso curso = cursoService.findCursoById(id).orElseThrow(EntityNotFoundException::new);
        return ResponseEntity.status(HttpStatus.OK).body(curso);
    }

    @DeleteMapping("/{id}")
    public void deleteCursoById(@PathVariable Long id) {
        cursoService.deleteCursoById(id);
    }
}
