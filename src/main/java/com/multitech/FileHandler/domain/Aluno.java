package com.multitech.FileHandler.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "aluno")
public class Aluno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    @JsonIgnore
    private String senha;
    @JsonIgnore
    private byte[] salt;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_curso")
    private Curso curso;
}
