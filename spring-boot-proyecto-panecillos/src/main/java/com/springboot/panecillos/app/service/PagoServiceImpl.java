package com.springboot.panecillos.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.panecillos.app.dao.IPago;
import com.springboot.panecillos.app.models.domain.Pago;
@Service
public class PagoServiceImpl implements IPagoService {
	@Autowired
	private IPago pagoDao;
	
	@Override
	public List<Pago> listar() {
		return (List<Pago>) pagoDao.findAll();
	}

	@Override
	public void guardar(Pago pago) {
		pagoDao.save(pago);
	}
	
}
