package com.perfulandia.usuarioservice.Controller;
import com.perfulandia.usuarioservice.controller.UsuarioController;
import com.perfulandia.usuarioservice.model.Usuario;
import com.perfulandia.usuarioservice.service.UsuarioService;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.springframework.test.context.bean.override.mockito.MockitoBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;
@WebMvcTest(UsuarioController.class)
public class UsuarioControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private UsuarioService service;

    private final ObjectMapper mapper = new ObjectMapper();

    @Test
    @DisplayName("Testing Controller 1 - Obtener todo")
    void testGetAll() throws Exception {
        when(service.listar()).thenReturn(List.of(new Usuario(1L, "Juan","juanito@Gmail.com","ADMIN")));

        mockMvc.perform(get("/api/usuarios"))
                .andExpect(status().isOk())//código 200
                .andExpect(jsonPath("$[0].nombre").value("Juan"));
    }
    //POST
    @Test
    @DisplayName("Testing Controller 2 - Guardar POST")
    void testPost() throws Exception{

        Usuario v = new Usuario( null, "Joaquin", "Joaco@gmail.com","ADMIN");

        when(service.crear(any())).thenReturn(new Usuario(1L,"Miguel", "miguelito@gmail.com","ADMIN"));

        mockMvc.perform(post("/api/usuarios")
                        .contentType("application/json")//indicar que el contenido es JSON
                        .content(mapper.writeValueAsString(v)))// Convertimos el objeto JSON
                .andExpect(status().isOk()) //200
                .andExpect(jsonPath("$.nombre").value("Miguel"));
    }


    @Test
    @DisplayName("Testing Controller 3 - DELETE")
    void testDelete() throws Exception {
        doNothing().when(service).eliminar(1L);

        mockMvc.perform(delete("/api/usuarios/1"))
                .andExpect(status().isOk());
    }

}
