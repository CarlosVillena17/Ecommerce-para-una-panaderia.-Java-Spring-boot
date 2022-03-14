package com.springboot.panecillos.app.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.springboot.panecillos.app.models.domain.Categoria;

@Repository
public interface ICategoriaDao extends CrudRepository<Categoria, Long> {
		
}
