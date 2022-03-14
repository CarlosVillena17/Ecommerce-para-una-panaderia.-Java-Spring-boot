package com.springboot.panecillos.app.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.springboot.panecillos.app.models.domain.Compras;

@Repository
public interface ICompra  extends CrudRepository<Compras, Long>{
	@Query(value = "select * from compras where idcliente=?1", nativeQuery = true)
	public List<Compras> comprasDelusuario(Long idcliente);
}
