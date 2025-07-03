package com.perfulandia.pedidoservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.perfulandia.pedidoservice.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
