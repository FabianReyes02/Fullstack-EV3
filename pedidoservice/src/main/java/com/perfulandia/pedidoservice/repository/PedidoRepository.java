package com.perfulandia.pedidoservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.perfulandia.pedidoservice.model.Pedido;
import com.perfulandia.pedidoservice.repository.PedidoRepository;

public interface PedidoRepository extends JpaRepository<Pedido, Long>{
}
