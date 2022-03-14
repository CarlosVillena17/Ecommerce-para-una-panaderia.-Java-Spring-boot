package com.springboot.panecillos.app.service;

import java.util.List;

import com.springboot.panecillos.app.models.domain.Producto;

public interface IProductoService  {
	public List<Producto> findAll();
	public Producto buscarPorId(Long id);
	public List<Producto> listarProductosBusqueda(String nombre);
	public void eliminar(Long id);
	public void guardar(Producto producto);
}
