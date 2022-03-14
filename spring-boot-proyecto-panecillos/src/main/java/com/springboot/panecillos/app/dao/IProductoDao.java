package com.springboot.panecillos.app.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.springboot.panecillos.app.models.domain.Producto;

@Repository
public interface IProductoDao extends CrudRepository<Producto, Long>{
	@Query(value = "select * from productos where nombre like %?1%", nativeQuery = true)
	public List<Producto> listarProductosBusqueda(String nombre);
}
