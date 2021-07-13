package com.udemy.apirest.models.services;

import com.udemy.apirest.models.entity.Usuario;

public interface IUsuarioService {
	Usuario findByUsername(String username);
}
