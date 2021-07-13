package com.udemy.apirest.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.udemy.apirest.models.entity.Cliente;
import com.udemy.apirest.models.services.IClienteService;

@ExtendWith(MockitoExtension.class)
public class ClienteRestControllerTest {
	
	//Lo correcto es tes
	
	//Se crean mocks para el service que es utilizado en el controller
	@Mock 
	private IClienteService clienteService; 
	
	//Inyecta la clase que vamos a testear
	@InjectMocks
	ClienteRestController clienteController;
	
	//Versiòn 4 de junit, para la versiòn 5 ponemos la notaciòn @ExtendWith(MockitoExtension.class)
//	@Before
//	public void init() {
//		MockitoAnnotations.initMocks(this);
//	}
	
	@BeforeAll
	public static void init() {
		System.out.println("Hola mundo");
	}
	
	@Test
	public void indexTest() {
		final Cliente cliente = new Cliente();
		List<Cliente> clientesTest = new ArrayList<>();
		clientesTest.add(cliente);
		//Se le aplica el when al método del Mock que se desea burlar o simular
		Mockito.when(clienteService.findAll()).thenReturn(clientesTest);
		
		
		List<Cliente> clientes = clienteController.index();
		assertNotNull(clientes);
		assertFalse(clientes.isEmpty());
		assertEquals(clientes.size(), 1);
	}
	
	

	@Test
	public void sumarSinNulos() {
		ClienteRestController clienteController = new ClienteRestController();
		Integer a = 3,b = 3;
		Integer resultado = clienteController.sumar(a, b);
		Integer resultadoCorrecto = 6;
		assertEquals(resultado, resultadoCorrecto);
	}
	
	@Test
	public void sumarConNulos() {
		ClienteRestController clienteController = new ClienteRestController();
		Integer a = null,b = 3;
		assertThrows(IllegalArgumentException.class, () ->{
			clienteController.sumar(a, b);
		});
		//Mockito.doThrow(Exception.class).when(clienteService).save(Mockito.any(Clazz.class));
	}
	

}
