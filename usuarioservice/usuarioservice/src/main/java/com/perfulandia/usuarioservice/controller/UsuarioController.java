package com.perfulandia.usuarioservice.controller;

import com.perfulandia.usuarioservice.model.Usuario;
import com.perfulandia.usuarioservice.repository.UsuarioRepository;
import com.perfulandia.usuarioservice.service.UsuarioService;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final UsuarioService service;
    //Constructor para poder consumir la interfaz
    public UsuarioController(UsuarioService service){
        this.service=service;
    }

    @GetMapping
    public List<Usuario> listar(){
        return service.listar();
    }

    @PostMapping
    public Usuario crear(@RequestBody Usuario usuario){
        return service.crear(usuario);
    }

    @GetMapping("/{id}")
    public Usuario buscar(@PathVariable long id){
        return service.buscar(id);
    }


    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable long id){
        service.eliminar(id);
    }

    @PatchMapping("/{id}")
    public void modificar(@PathVariable long id, @RequestBody Usuario usuario){

    }
}
