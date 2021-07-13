package com.udemy.apirest.services;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Objects;
import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.udemy.apirest.models.dao.IClienteDao;
import com.udemy.apirest.models.entity.Cliente;
import com.udemy.apirest.models.services.ClienteServiceImpl;

@ExtendWith(MockitoExtension.class)
public class ClienteServiceTest {
	
	private static final Long ID_CLIENTE_VACIO = 2L;
	private static final Long ID_CLIENTE_INFORMACION = 3L;
	private static final String NOMBRE_CLIENTE_INFORMACION = "Alejandro";
	private static final Optional<Cliente> CLIENTE_VACIO = Optional.empty();
	private static final Cliente CLIENTE = new Cliente();
	private static final Optional<Cliente> CLIENTE_INFORMACION = Optional.of(CLIENTE);

	@InjectMocks
	private ClienteServiceImpl clienteService;
	
	@Mock
	private IClienteDao clienteDao;
	
	@BeforeAll
	public static void init() {
		CLIENTE.setId(ID_CLIENTE_INFORMACION);
		CLIENTE.setNombre(NOMBRE_CLIENTE_INFORMACION);
	}
	
	@Test
	public void findByIdTest() {
		Mockito.when(clienteDao.findById(ID_CLIENTE_VACIO)).thenReturn(CLIENTE_VACIO);
		Mockito.when(clienteDao.findById(ID_CLIENTE_INFORMACION)).thenReturn(CLIENTE_INFORMACION);
		Cliente clienteVacio = clienteService.findById(ID_CLIENTE_VACIO);
		assertEquals(Objects.isNull(clienteVacio), true);
		Cliente clienteInformacion = clienteService.findById(ID_CLIENTE_INFORMACION);
		assertEquals(Objects.nonNull(clienteInformacion), true);
		
	}
	
}
