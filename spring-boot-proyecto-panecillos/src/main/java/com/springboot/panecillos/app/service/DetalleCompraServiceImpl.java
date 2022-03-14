package com.springboot.panecillos.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.panecillos.app.dao.IDetalleCompra;
import com.springboot.panecillos.app.models.domain.DetalleCompras;

@Service
public class DetalleCompraServiceImpl  implements IDetalleCompraService{
	@Autowired
	private IDetalleCompra detalleCompraDao;
	
	
	@Override
	public List<DetalleCompras> listar() {
		// TODO Auto-generated method stub
		return (List<DetalleCompras>) detalleCompraDao.findAll();
	}

	@Override
	public void guardar(DetalleCompras detalleCompras) {
		detalleCompraDao.save(detalleCompras);
	}

	@Override
	public List<DetalleCompras> listarDetallePorId(Long idcompra) {
		detalleCompraDao.detallesCompras(idcompra);
		return null;
	}
	
}
