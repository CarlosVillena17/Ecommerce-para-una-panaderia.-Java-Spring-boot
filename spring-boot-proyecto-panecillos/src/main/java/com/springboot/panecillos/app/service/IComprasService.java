package com.springboot.panecillos.app.service;

import java.util.List;

import com.springboot.panecillos.app.models.domain.Compras;

public interface IComprasService {
	public List<Compras> listar(); 
	public void guardar(Compras compra);
	public List<Compras> comprasDelUsuario(Long idcliente);
}
