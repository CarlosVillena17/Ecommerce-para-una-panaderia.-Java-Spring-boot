package com.springboot.panecillos.app.service;

import java.util.List;

import com.springboot.panecillos.app.models.domain.DetalleCompras;

public interface IDetalleCompraService {
	public List<DetalleCompras> listar(); 
	public void guardar(DetalleCompras detalleCompras);
	public List<DetalleCompras> listarDetallePorId(Long idcompra);
}
