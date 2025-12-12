package com.multitech.FileHandler.controller;

import com.multitech.FileHandler.DTO.AlunoDTO;
import com.multitech.FileHandler.domain.Aluno;
import com.multitech.FileHandler.service.AlunoService;
import io.swagger.v3.oas.annotations.Operation;
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

//    @GetMapping
//    @Operation(summary = "Lista todos alunos")
//    public ResponseEntity<List<Aluno>> findAll(){
//        return ResponseEntity.status(HttpStatus.OK).body(alunoService.listAll());
//    }
//
//    @PostMapping
//    @Operation(summary = "Cadastra novo aluno")
//    public ResponseEntity<Aluno> create(@RequestBody AlunoDTO dto) {
//        Aluno aluno = alunoService.create(dto);
//        return ResponseEntity.status(HttpStatus.CREATED).body(aluno);
//    }
//
//    @GetMapping("/{id}")
//    @Operation(summary = "Procura aluno por id")
//    public ResponseEntity<Aluno> findAlunoById(@PathVariable Long id) {
//        Aluno aluno = alunoService.findById(id).orElseThrow(EntityNotFoundException::new);
//        return ResponseEntity.status(HttpStatus.OK).body(aluno);
//    }
//
//    @DeleteMapping("/{id}")
//    @Operation(summary = "Apaga aluno")
//    public void deleteAlunoById(@PathVariable Long id) {
//        alunoService.deleteAlunoById(id);
//    }
}
