package com.udemy.apirest.controllers;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.udemy.apirest.models.entity.Cliente;
import com.udemy.apirest.models.entity.Region;
import com.udemy.apirest.models.services.IClienteService;

@CrossOrigin(origins = {"*"})
@RestController
@RequestMapping("/api")
public class ClienteRestController {
	
	private IClienteService clienteService; 

	@Autowired 
	private void setClienteService(IClienteService clienteService) {
		this.clienteService = clienteService;
	}
	
	@GetMapping
	public int sumar(Integer a, Integer b) {
		if(Objects.nonNull(a) && Objects.nonNull(b)) {
			return a + b;
		}
		throw new IllegalArgumentException();
	}

	@GetMapping("/clientes")
	public List<Cliente> index() {
		return this.clienteService.findAll();
	}
	
	@GetMapping("/clientes/page/{page}")
	public Page<Cliente> index(@PathVariable("page") Integer page) {
		return this.clienteService.findAll(PageRequest.of(page, 4));
	}

	@Secured({"ROLE_ADMIN", "ROLE_USER"})
	@GetMapping("/clientes/{id}")
	public ResponseEntity<?> show(@PathVariable Long id) {
		Cliente cliente = null;
		Map<String, Object> error = new HashMap<>();
		try {
			cliente = clienteService.findById(id);
		} catch (DataAccessException e) {
			error.put("mensaje", "Error al realizar la consulta en la bd.");
			error.put("error", e.getMostSpecificCause().getMessage());
			return new ResponseEntity<Map<String, Object>>(error, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if (Objects.nonNull(cliente)) {
			return new ResponseEntity<Cliente>(cliente, HttpStatus.OK);
		}
		error.put("mensaje", "El cliente con id ".concat(id.toString()).concat(" no existe."));
		return new ResponseEntity<Map<String, Object>>(error, HttpStatus.NOT_FOUND);
	}


	@Secured("ROLE_ADMIN")
	@PostMapping("/clientes")
	public ResponseEntity<?> create(@Valid @RequestBody Cliente cliente, BindingResult result) {
		
		Map<String, Object> response = new HashMap<>();
		if(result.hasErrors()) {
			List<String> errors = result.getFieldErrors()
										.stream()
										.map(error -> 
											"El campo " + error.getField()+" "+ error.getDefaultMessage())
										.collect(Collectors.toList());
			
			response.put("errors", errors);
			
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		try {
			Cliente clienteNuevo = clienteService.save(cliente);
			response.put("mensaje", "El cliente ha sido creado con éxito!");
			response.put("cliente", clienteNuevo);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la bd.");
			response.put("error", e.getMostSpecificCause().getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}


	@Secured("ROLE_ADMIN")
	@PutMapping("/clientes/{id}")
	public ResponseEntity<?> update(@Valid @RequestBody Cliente cliente,  BindingResult result, @PathVariable Long id) {
		Map<String, Object> response = new HashMap<>();
		if(result.hasErrors()) {
			List<String> errors = result.getFieldErrors()
										.stream()
										.map(error -> 
											"El campo " + error.getField()+" "+ error.getDefaultMessage())
										.collect(Collectors.toList());
			
			response.put("errors", errors);
			
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		try {
		  Cliente modificado = clienteService.update(cliente, id);
		  if(Objects.isNull(modificado)) {
			  response.put("mensaje", "El cliente con id ".concat(id.toString()).concat(" no existe."));
			  return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		  }else {
			  response.put("mensaje", "El cliente ha sido actualizado!");
			  response.put("cliente", modificado);
			  return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
		  }
		} catch(DataAccessException e) {
			response.put("mensaje", "Error al actualizar el cliente.");
			response.put("error", e.getMostSpecificCause().getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Secured("ROLE_ALEJO")
	@DeleteMapping("/clientes/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		Map<String, Object> response = new HashMap<>();
		try {
			clienteService.delete(id);	
			response.put("mensaje", "El cliente ha sido eliminado!");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
		}catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la bd.");
			response.put("error", e.getMostSpecificCause().getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	

	@Secured({"ROLE_ADMIN", "ROLE_USER"})
	@PostMapping("/clientes/upload")
	public ResponseEntity<?> upload(@RequestParam("archivo") MultipartFile archivo, @RequestParam("id")Long id){
		 Map<String, Object> response = new HashMap<>();
		 try{
			 Cliente cliente = clienteService.upload(archivo, id);
			 response.put("cliente", cliente);
			 response.put("mensaje", "Ha subido correctamente la imagen: "+ cliente.getFoto());
			 return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
		 }catch(IOException e) {
			 response.put("mensaje", "Error al subir la imágen del cliente "+archivo.getOriginalFilename());
			 response.put("error", e.getCause().getMessage());
			 return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		 }
	}
	
	@GetMapping("/uploads/img/{nombreFoto:.+}")
	public ResponseEntity<Resource> verFoto(@PathVariable String nombreFoto){
		
		Resource recurso = null;
		try {
			recurso = clienteService.obtenerFoto(nombreFoto);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		HttpHeaders cabecera = new HttpHeaders();
		cabecera.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\""+ recurso.getFilename()+"\"");
		return new ResponseEntity<Resource>(recurso, cabecera, HttpStatus.OK);
	}

	@Secured("ROLE_ADMIN")
	@GetMapping("/clientes/regiones")
	public List<Region> listarRegiones() {
		return this.clienteService.findAllRegiones();
	}
	
}
