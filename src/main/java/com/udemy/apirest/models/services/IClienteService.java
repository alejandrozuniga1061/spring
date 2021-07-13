package com.udemy.apirest.models.services;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import com.udemy.apirest.models.entity.Cliente;
import com.udemy.apirest.models.entity.Factura;
import com.udemy.apirest.models.entity.Producto;
import com.udemy.apirest.models.entity.Region;

public interface IClienteService {
	List<Cliente> findAll();
	Page<Cliente> findAll(Pageable pageable);
	Cliente save(Cliente cliente);
	void delete(Long id);
	Cliente findById(Long id);
	Cliente update(Cliente clienteActualizado, Long id);
	Cliente upload(MultipartFile archivo, Long id) throws IOException;
	Resource obtenerFoto(String nombreFoto) throws MalformedURLException;
	List<Region> findAllRegiones();
	Factura findFacturaById(Long id);
	Factura saveFactura(Factura factura);
	void deleteFacturaById(Long id);
	List<Producto> findProductoByNombre(String term);
}
