package com.multitech.FileHandler.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

import java.util.Set;

public record LivroDTO(
        @NotBlank
        String  nome,
        @NotEmpty
        Set<Long> idCurso
) {
}
