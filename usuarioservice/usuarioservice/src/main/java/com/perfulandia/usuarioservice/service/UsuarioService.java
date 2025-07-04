package com.perfulandia.usuarioservice.service;

import com.perfulandia.usuarioservice.model.Usuario;
import com.perfulandia.usuarioservice.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {
    private final UsuarioRepository repo;
    //Constructor para poder consumir la interfaz
    public UsuarioService(UsuarioRepository repo){
        this.repo=repo;
    }
    //Listar
    public List<Usuario> listar(){
        return repo.findAll();
    }
    public Usuario crear(Usuario usuario) {
        usuario.setId(null); // fuerza creación nueva
        return repo.save(usuario);
    }
    //Buscar por id
    public Usuario buscar(long id){
        return repo.findById(id).orElse(null);
    }
    //Eliminar id
    public void eliminar(long id){
        repo.deleteById(id);
    }
    //Modificar


    public Usuario actualizar(Long id, Usuario usuario) {
        Usuario existente = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        // Asignar manualmente los nuevos valores
        existente.setNombre(usuario.getNombre());
        existente.setCorreo(usuario.getCorreo());
        existente.setRol(usuario.getRol());

        // La versión es manejada automáticamente por Hibernate
        return repo.save(existente);
    }

}