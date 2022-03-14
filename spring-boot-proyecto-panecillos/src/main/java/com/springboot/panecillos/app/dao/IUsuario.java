package com.springboot.panecillos.app.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.springboot.panecillos.app.models.domain.Usuario;

@Repository
public interface IUsuario extends CrudRepository<Usuario, Long> {
	@Query(value = "select * from usuario where email=?1 and password=?2", nativeQuery = true)
	 Usuario buscarPorCorreoyContra(String email, String password);
}
