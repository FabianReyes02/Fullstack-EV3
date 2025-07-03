package com.perfulandia.pedidoservice.controller;

import com.perfulandia.pedidoservice.model.Usuario;
import com.perfulandia.pedidoservice.service.PedidoService;
import com.perfulandia.pedidoservice.model.Pedido;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import com.perfulandia.pedidoservice.model.Producto;

import java.util.List;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    private final PedidoService pedidoService;
    private final RestTemplate restTemplate;

    public PedidoController(PedidoService pedidoService, RestTemplate restTemplate) {
        this.pedidoService = pedidoService;
        this.restTemplate = restTemplate;
    }
    @GetMapping
    public List<Pedido> listar() {
        return pedidoService.listar();
    }
    @PostMapping
    public Pedido guardar(@RequestBody Pedido pedido) {
        return pedidoService.guardar(pedido);
    }
    @GetMapping("/{id}")
    public Pedido buscarPorId(@PathVariable Long id) {
        return pedidoService.buscarPorId(id);
    }
    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        pedidoService.eliminar(id);
    }
    @GetMapping("/producto/{id}")
    public Producto obtenerProducto(@PathVariable long id){
        return restTemplate.getForObject("http://localhost:8080/api/productos/" + id, Producto.class);
    }
    // Modificar
    @PatchMapping("/{id}")
    public ResponseEntity<Pedido> modificar(@PathVariable Long id, @RequestBody Pedido pedidoActualizado) {
        Pedido pedidoModificado = pedidoService.modificar(id, pedidoActualizado);
        if (pedidoModificado != null) {
            return ResponseEntity.ok(pedidoModificado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/usuario/{id}")
    public Usuario obtenerUsuario(@PathVariable long id) {
        return restTemplate.getForObject("http://localhost:8081/api/usuarios/" + id, Usuario.class);
    }
}
