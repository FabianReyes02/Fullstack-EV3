package com.perfulandia.pedidoservice.controller;

import com.perfulandia.pedidoservice.controller.PedidoController;
import com.perfulandia.pedidoservice.model.Pedido;
import com.perfulandia.pedidoservice.model.Producto;
import com.perfulandia.pedidoservice.model.Usuario;
import com.perfulandia.pedidoservice.service.PedidoService;

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

@WebMvcTest(PedidoController.class)
public class PedidoControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private PedidoService service;

    private final ObjectMapper mapper = new ObjectMapper();

    @Test
    @DisplayName("Testing Controller 1 - Obtener todo")
    void testGetAll() throws Exception {

        Usuario user = new Usuario(5L,"Roberto","Robertito666@gmail.com");
        //se crea un usuario para el test

        Producto producto1 = Producto.builder() //se crea un objeto de producto para el test
                .id(1L)
                .nombre("Carolina Herrera")
                .precio(85990)
                .stock(10)
                .build();

        List<Long> items = List.of(producto1.getId()); //Se crea la lista con el producto1

        Pedido pedido1 = new Pedido(1L, user.getId(), producto1.getPrecio(),"ENVIADO",items);
        //se crea el pedido, Agregando los atributos de requeridos


        when(service.listar()).thenReturn(List.of(pedido1));

        mockMvc.perform(get("/api/pedidos")) //se hace la solicitud de GET en el endpoint
                .andExpect(status().isOk())//código 200
                .andExpect(jsonPath("$[0].id").value(1L)); //Si devuelve 1 en el id del pedido es exitoso
    }
    //POST
    @Test
    @DisplayName("Testing Controller 2 - Guardar POST")
    void testPost() throws Exception{

        Usuario user2 = new Usuario(8L,"Carlos","CarlitosGamer2009@gmail.com");
        //se crea un usuario para el test

        Producto producto2 = Producto.builder() //se crea un objeto de producto para el test
                .id(2L)
                .nombre("Millonaire")
                .precio(15990)
                .stock(13)
                .build();

        List<Long> items2 = List.of(producto2.getId()); //Se crea la lista con el producto1


        //se crea el pedido, Agregando los atributos de requeridos

        //---------------------------------------------

        Usuario user = new Usuario(5L,"Roberto","Robertito666@gmail.com");
        //se crea un usuario para el test

        Producto producto1 = Producto.builder() //se crea un objeto de producto para el test
                .id(1L)
                .nombre("Carolina Herrera")
                .precio(85990)
                .stock(10)
                .build();

        List<Long> items = List.of(producto1.getId()); //Se crea la lista con el producto1 (ids de productos, en este cado de un solo producto)

        Pedido pedido1 = new Pedido(1L, user.getId(), producto1.getPrecio(),"ENVIADO",items);
        //se crea el pedido, Agregando los atributos de requeridos

        Pedido pedido2 = new Pedido(2L, user2.getId(), producto2.getPrecio(),"PENDIENTE",items2);
        //Se crea otro pedido para el test

        when(service.guardar(any())).thenReturn(pedido1);

        mockMvc.perform(post("/api/pedidos")
                        .contentType("application/json")//indicar que el contenido es JSON
                        .content(mapper.writeValueAsString(pedido2)))// Convertimos el objeto JSON
                .andExpect(status().isOk()) //200
                .andExpect(jsonPath("$.estado").value("ENVIADO"));
    }


    @Test
    @DisplayName("Testing Controller 3 - DELETE")
    void testDelete() throws Exception {
        doNothing().when(service).eliminar(1L);

        mockMvc.perform(delete("/api/pedidos/1"))
                .andExpect(status().isOk());
    }



}
