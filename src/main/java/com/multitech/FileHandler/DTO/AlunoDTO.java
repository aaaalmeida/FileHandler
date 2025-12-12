package com.multitech.FileHandler.DTO;

public record AlunoDTO(
        String  nome,
        String  email,
        String  senha,
        Long    idCurso
) { }
