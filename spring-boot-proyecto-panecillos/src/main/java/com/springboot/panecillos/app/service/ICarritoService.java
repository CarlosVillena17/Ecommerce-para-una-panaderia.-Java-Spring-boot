package com.springboot.panecillos.app.service;

import java.util.List;

import com.springboot.panecillos.app.models.domain.CarritoCompras;

public interface ICarritoService {
	public CarritoCompras buscarPorId(Long idproducto);
	public List<CarritoCompras> listar();
	public void eliminar(Long idproducto);
	public void guardar(CarritoCompras carrito);
	public Double calcularTotal();
	public Double calcularTotalSinDescuento();
	public void descuento(Double descuento);
	
}
