package com.perfulandia.pedidoservice.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;               // Identificador único del pedido
    private long usuarioId;        // ID del usuario que realiza el pedido
    private double total;          // Total del pedido (suma de precios)
    private String estado;         // Estado del pedido (por ejemplo: "PENDIENTE", "ENVIADO", "ENTREGADO")
    @ElementCollection
    private List<Long> productos;  // Lista de IDs de productos incluidos en el pedido
}