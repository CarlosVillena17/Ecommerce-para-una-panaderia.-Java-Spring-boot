package com.springboot.panecillos.app.service;

import java.util.List;

import com.springboot.panecillos.app.models.domain.Pago;

public interface IPagoService {
	public List<Pago> listar(); 
	public void guardar(Pago pago);
}
