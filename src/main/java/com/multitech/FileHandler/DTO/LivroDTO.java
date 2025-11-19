package com.multitech.FileHandler.DTO;

import java.util.Set;

public record LivroDTO(
        String  nome,
        Set<Long> idCurso
) {
}
