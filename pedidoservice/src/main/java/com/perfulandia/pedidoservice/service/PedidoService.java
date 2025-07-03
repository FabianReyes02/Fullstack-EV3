package com.perfulandia.pedidoservice.service;

import com.perfulandia.pedidoservice.model.Usuario;
import com.perfulandia.pedidoservice.repository.PedidoRepository;
import com.perfulandia.pedidoservice.model.Pedido;
import org.springframework.stereotype.Service;
import java.util.List;
import com.perfulandia.pedidoservice.repository.UsuarioRepository;
@Service
public class PedidoService {
    private final PedidoRepository repo;
    private final UsuarioRepository usuarioRepo;

    public PedidoService(PedidoRepository repo, UsuarioRepository usuarioRepo) {
        this.repo = repo;
        this.usuarioRepo = usuarioRepo;
    }
    //listar
    public List<Pedido> listar() {
        return repo.findAll();
    }
    //buscar por id
    public Pedido buscarPorId(Long id) {
        return repo.findById(id).orElse(null);
    }
    //Eliminar
    public void eliminar(long id){
        repo.deleteById(id);
    }
    //Modificar
    public Pedido modificar(long id, Pedido pedidoActualizado) {
        return repo.findById(id).map(pedidoExistente -> {
                    pedidoExistente.setUsuarioId(pedidoActualizado.getUsuarioId());
                    pedidoExistente.setTotal(pedidoActualizado.getTotal());
                    pedidoExistente.setEstado(pedidoActualizado.getEstado());
                    pedidoExistente.setProductos(pedidoActualizado.getProductos());
                    return repo.save(pedidoExistente);
                })
                .orElse(null);
    }
        // Guardar
        public Pedido guardar(Pedido pedido) {
            // Verificar si el usuario existe
            Usuario usuario = usuarioRepo.findById(pedido.getUsuarioId()).orElse(null);
            if (usuario == null) {
                throw new RuntimeException("Usuario no encontrado");
            }
            return repo.save(pedido);
        }
    }
