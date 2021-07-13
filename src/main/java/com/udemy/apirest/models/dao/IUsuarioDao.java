package com.udemy.apirest.models.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.udemy.apirest.models.entity.Usuario;

public interface IUsuarioDao extends CrudRepository<Usuario, Long> {

	Usuario findByUsername(String username);
	
	@Query("Select u from Usuario u where u.username = ?1")
	Usuario findByUsername2(String username);
}
