package com.perfulandia.pedidoservice.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;               // Identificador único del usuario
    private String nombre;         // Nombre del usuario
    private String email;          // Email del usuario
    // Otros campos que consideres necesarios
}
