package com.springboot.panecillos.app.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.springboot.panecillos.app.models.domain.CarritoCompras;

@Service
public class CarritoServiceImpl implements ICarritoService {

	private List<CarritoCompras> listaCarrito;
	private Double descuento=0.0;

	public List<CarritoCompras> getListaCarrito() {
		return listaCarrito;
	}

	public void setListaCarrito(List<CarritoCompras> listaCarrito) {
		this.listaCarrito = listaCarrito;
	}

	public Double getDescuento() {
		return descuento;
	}

	public void setDescuento(Double descuento) {
		this.descuento = descuento;
	}

	public CarritoServiceImpl() {
		this.listaCarrito=new ArrayList<CarritoCompras>();
	}
		
	@Override
	public CarritoCompras buscarPorId(Long idproducto) {
		CarritoCompras carrito=null;
		for(CarritoCompras carro: this.listaCarrito) {
			if(carro.getIdproducto()==idproducto) {
				carrito=carro;
				break;
			}
		}
		return carrito;
	}

	@Override
	public List<CarritoCompras> listar() {
		return this.listaCarrito;
	}

	@Override
	public void eliminar(Long idproducto) {
		CarritoCompras carritoCompras=buscarPorId(idproducto);
		this.listaCarrito.remove(carritoCompras);
	}

	@Override
	public void guardar(CarritoCompras carrito) {
		this.listaCarrito.add(carrito);
	}
	
	@Override
	public void descuento(Double descuento) {
		this.descuento=descuento;
	}
	
	@Override
	public Double calcularTotal() {
		Double totalPagar=0.0;
		for(CarritoCompras carrito: this.listaCarrito) {
			totalPagar=totalPagar+carrito.getSubTotal();
		}
		totalPagar=totalPagar-this.descuento;
		return totalPagar;
	}

	@Override
	public Double calcularTotalSinDescuento() {
		Double totalPagar=0.0;
		for(CarritoCompras carrito: this.listaCarrito) {
			totalPagar=totalPagar+carrito.getSubTotal();
		}
		return totalPagar;
	}

}
