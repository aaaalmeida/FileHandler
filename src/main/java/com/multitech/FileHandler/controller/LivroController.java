package com.multitech.FileHandler.controller;

import com.multitech.FileHandler.domain.Livro;
import com.multitech.FileHandler.service.LivroService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/livro")
public class LivroController {
    @Autowired
    private LivroService livroService;

    @GetMapping
    public ResponseEntity<List<Livro>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(livroService.listAll());
    }

    @PostMapping
    public ResponseEntity<Livro> createLivro(@RequestBody Livro livro) {
        return ResponseEntity.status(HttpStatus.CREATED).body(livroService.save(livro));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Livro> findLivroById(@PathVariable Long id) {
        Livro livro = livroService.findLivroById(id).orElseThrow(EntityNotFoundException::new);
        return ResponseEntity.status(HttpStatus.OK).body(livro);
    }

    @DeleteMapping("/{id}")
    public void deleteLivroById(@PathVariable Long id) {
        livroService.deleteLivroById(id);
    }
}
