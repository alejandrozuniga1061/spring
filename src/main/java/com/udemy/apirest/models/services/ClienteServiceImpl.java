package com.udemy.apirest.models.services;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.udemy.apirest.models.dao.FacturaDao;
import com.udemy.apirest.models.dao.IClienteDao;
import com.udemy.apirest.models.dao.IProductoDao;
import com.udemy.apirest.models.entity.Cliente;
import com.udemy.apirest.models.entity.Factura;
import com.udemy.apirest.models.entity.Producto;
import com.udemy.apirest.models.entity.Region;
import com.udemy.apirest.utils.Constantes.Multimedia;
import com.udemy.apirest.utils.FileUtils;

@Service
public class ClienteServiceImpl implements IClienteService {

	private IClienteDao clienteDao;
	private final Logger log = LoggerFactory.getLogger(ClienteServiceImpl.class);
	private FacturaDao facturaDao;
	private IProductoDao productoDao;
	
	@Autowired
	public void setIProdcutoDao(IProductoDao productoDao) {
		this.productoDao = productoDao;
	}
	
	@Autowired
	public void setIClienteDao(FacturaDao facturaDao) {
		this.facturaDao = facturaDao;
	}
	
	@Autowired
	public void setIClienteDao(IClienteDao clienteDao) {
		this.clienteDao = clienteDao;
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Cliente> findAll() {
		return (List<Cliente>) clienteDao.findAll();
	}
	
	@Override
	@Transactional(readOnly = true)
	public Page<Cliente> findAll(Pageable pageable) {
		return clienteDao.findAll(pageable);
	}

	@Override
	@Transactional
	public Cliente save(Cliente cliente) {
		// TODO Auto-generated method stub
		return clienteDao.save(cliente);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		Cliente cliente = this.findById(id);
		eliminarFotoAnterior(cliente);
		clienteDao.deleteById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public Cliente findById(Long id) {
		// TODO Auto-generated method stub
		return clienteDao.findById(id).orElse(null);
	}
	
	@Override
	@Transactional
	public Cliente update(Cliente clienteActualizado, Long id) {
		Cliente clienteActual = this.findById(id);
		if(Objects.isNull(clienteActual)) {
			return null;
		}
		clienteActual.setNombre(clienteActualizado.getNombre());
		clienteActual.setApellido(clienteActualizado.getApellido());
		clienteActual.setEmail(clienteActualizado.getEmail());
		clienteActual.setCreateAt(clienteActualizado.getCreateAt());
		clienteActual.setRegion(clienteActualizado.getRegion());
		return clienteDao.save(clienteActual);
	}
	
	@Override
	@Transactional
	public Cliente upload(MultipartFile archivo, Long id) throws IOException {
		Cliente cliente = this.findById(id);
		if(Objects.nonNull(cliente)) {
			String nombreArchivo = FileUtils.guardarArchivo(archivo, Multimedia.URL);
			log.info(nombreArchivo);
			eliminarFotoAnterior(cliente);
			cliente.setFoto(nombreArchivo);
			this.save(cliente);
		}
		return cliente;
	}
	
	private void eliminarFotoAnterior(Cliente cliente) {
		String nombreFotoAnterior = cliente.getFoto();
		if(Objects.nonNull(nombreFotoAnterior) && nombreFotoAnterior.length() > 0) {
			FileUtils.eliminarArchivo(nombreFotoAnterior, Multimedia.URL);
		}
	}

	@Override
	@Transactional(readOnly = true)
	public Resource obtenerFoto(String nombreFoto) throws MalformedURLException{
		
		Resource recurso = obtenerRecurso(nombreFoto,  Multimedia.URL);		
		
		if(Objects.isNull(recurso) || (!recurso.exists() && !recurso.isReadable())){
			recurso = obtenerRecurso(Multimedia.IMG_SIN_USUARIO, Multimedia.IMAGE_STATIC);
			log.error("Error no se pudo cargar la imágen: "+ nombreFoto);
		}
		
		return recurso;
	}
	
	private Resource obtenerRecurso(String nombreFoto, String url) throws MalformedURLException {
		return FileUtils.obtenerArchivo(nombreFoto, url);
	}

	@Override
	@Transactional(readOnly=true)
	public List<Region> findAllRegiones() {
		// TODO Auto-generated method stub
		return clienteDao.findAllRegiones();
	}

	@Override
	@Transactional(readOnly=true)
	public Factura findFacturaById(Long id) {
		// TODO Auto-generated method stub
		return facturaDao.findById(id).orElseGet(null);
	}

	@Override
	@Transactional
	public Factura saveFactura(Factura factura) {
		// TODO Auto-generated method stub
		return facturaDao.save(factura);
	}

	@Override
	@Transactional
	public void deleteFacturaById(Long id) {
		facturaDao.deleteById(id);
	}

	@Override
	@Transactional(readOnly=true)
	public List<Producto> findProductoByNombre(String term) {
		return productoDao.findByNombreContainingIgnoreCase(term);
	}

}
