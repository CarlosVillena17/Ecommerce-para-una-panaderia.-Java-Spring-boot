package com.springboot.panecillos.app.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.springboot.panecillos.app.models.domain.DetalleCompras;
@Repository
public interface IDetalleCompra extends CrudRepository<DetalleCompras, Long>{
		@Query(value = "select * from detalle_compras where idcompras=?1", nativeQuery = true)
		public List<DetalleCompras> detallesCompras(Long idcompra);
}
