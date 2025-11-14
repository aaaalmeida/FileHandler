package com.multitech.FileHandler.controller;

import com.multitech.FileHandler.domain.Aluno;
import com.multitech.FileHandler.service.AlunoService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/aluno")
public class AlunoController {
    @Autowired
    private AlunoService alunoService;

    @GetMapping
    public ResponseEntity<List<Aluno>> findAll(){
        return ResponseEntity.status(HttpStatus.OK).body(alunoService.listAll());
    }

    @PostMapping
    public ResponseEntity<Aluno> create(@RequestBody Aluno aluno) {
        return ResponseEntity.status(HttpStatus.CREATED).body(alunoService.save(aluno));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Aluno> findAlunoById(@PathVariable Long id) {
        Aluno aluno = alunoService.findById(id).orElseThrow(EntityNotFoundException::new);
        return ResponseEntity.status(HttpStatus.OK).body(aluno);
    }

    @DeleteMapping("/{id}")
    public void deleteAlunoById(@PathVariable Long id) {
        alunoService.deleteAlunoById(id);
    }
}
