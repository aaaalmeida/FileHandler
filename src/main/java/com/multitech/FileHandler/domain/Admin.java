package com.multitech.FileHandler.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(
    name = "admin",
    uniqueConstraints = @UniqueConstraint(columnNames = {"email"})
)
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String senha;
    @JsonIgnore
    private byte[] salt;
}