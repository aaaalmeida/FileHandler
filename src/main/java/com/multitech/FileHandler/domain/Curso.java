package com.multitech.FileHandler.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "curso")
public class Curso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "curso", fetch = FetchType.LAZY)
    private Set<Aluno> alunos = new HashSet<>();
}
