package com.perfulandia.pedidoservice.Service;

import com.perfulandia.pedidoservice.model.Pedido;
import com.perfulandia.pedidoservice.model.Producto;
import com.perfulandia.pedidoservice.repository.PedidoRepository;

import com.perfulandia.pedidoservice.service.PedidoService;

import jakarta.persistence.ElementCollection;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

//Librerías para usar mockito
import org.mockito.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*; //Mocks Simular inserciones, datos de listas etc.

public class PedidoServiceTest {

    //Creando una instancia de Mocks=Simular para poder iyectarlas donde sea necesario
    @InjectMocks
    private PedidoService service;
    //Creando un mock del repositorio //objeto simulado
    @Mock
    private PedidoRepository repo;

    //Constructor para inicializar test antes de cada prueba
    public PedidoServiceTest(){

        //Abre e inializa los mocks anotados con @Mock y @InjectMocks
        MockitoAnnotations.openMocks(this);
    }
    @Test
    @DisplayName("Testing 1 - FindAll Service")
    void testFindAll(){
        Producto producto = Producto.builder() //se crea un objeto de producto para el test
                .id(1L)
                .nombre("Carolina Herrera")
                .precio(85990)
                .stock(10)
                .build();

        List<Long> items = List.of(producto.getId());
        Pedido pedido = new Pedido(1L,10L,64990,"ENVIADO",items);
        //Datos simulados

        when(repo.findAll()).thenReturn(List.of(pedido));
        //Llamar al metodo de servicio que sera probado
        List<Pedido> resultado =  service.listar();
        //Verificacion
        assertEquals(1, resultado.size());
    }
}
