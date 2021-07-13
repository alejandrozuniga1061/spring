package com.udemy.apirest.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.udemy.apirest.models.entity.Producto;

public interface IProductoDao extends CrudRepository<Producto, Long>{
	
	@Query("select p from Producto p where p.nombre like %?1%")
	public List<Producto> findByNombre(String term);
	
	//Mètodo de JPA Containing es un like y el ignore case para no tener en cuenta las minusculas
	public List<Producto> findByNombreContainingIgnoreCase(String term);
}
