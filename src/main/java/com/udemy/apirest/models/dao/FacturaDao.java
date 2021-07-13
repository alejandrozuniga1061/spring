package com.udemy.apirest.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.udemy.apirest.models.entity.Factura;

public interface FacturaDao extends CrudRepository<Factura, Long> {

}
