package com.perfulandia.pedidoservice.service;

import com.perfulandia.pedidoservice.model.Usuario;
import com.perfulandia.pedidoservice.model.Pedido;
import com.perfulandia.pedidoservice.repository.PedidoRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class PedidoService {

    private final PedidoRepository repo;
    private final RestTemplate restTemplate;

    // Constructor con inyección de RestTemplate
    public PedidoService(PedidoRepository repo, RestTemplate restTemplate) {
        this.repo = repo;
        this.restTemplate = restTemplate;
    }

    // Listar
    public List<Pedido> listar() {
        return repo.findAll();
    }

    // Buscar por id
    public Pedido buscarPorId(Long id) {
        return repo.findById(id).orElse(null);
    }

    // Eliminar
    public void eliminar(long id){
        repo.deleteById(id);
    }

    // Modificar
    public Pedido modificar(long id, Pedido pedidoActualizado) {
        return repo.findById(id).map(pedidoExistente -> {
            pedidoExistente.setUsuarioId(pedidoActualizado.getUsuarioId());
            pedidoExistente.setTotal(pedidoActualizado.getTotal());
            pedidoExistente.setEstado(pedidoActualizado.getEstado());
            pedidoExistente.setProductos(pedidoActualizado.getProductos());
            return repo.save(pedidoExistente);
        }).orElse(null);
    }

    // Guardar
    public Pedido guardar(Pedido pedido) {
        try {
            // Validar que el usuario exista en el microservicio usuarios
            Usuario usuario = restTemplate.getForObject(
                    "http://localhost:8081/api/usuarios/" + pedido.getUsuarioId(),
                    Usuario.class
            );
            if (usuario == null) {
                throw new RuntimeException("Usuario no encontrado en el microservicio de usuarios");
            }
        } catch (Exception e) {
            throw new RuntimeException("Error verificando usuario: " + e.getMessage());
        }

        return repo.save(pedido);
    }
}

