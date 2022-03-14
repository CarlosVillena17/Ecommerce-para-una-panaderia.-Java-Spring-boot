package com.springboot.panecillos.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.panecillos.app.dao.ICompra;
import com.springboot.panecillos.app.models.domain.Compras;

@Service
public class CompraServiceImpl implements IComprasService{

	@Autowired
	private ICompra compradao;
	
	@Override
	public List<Compras> listar() {
		// TODO Auto-generated method stub
		return (List<Compras>) compradao.findAll();
	}

	@Override
	public void guardar(Compras compra) {
		compradao.save(compra);
	}

	@Override
	public List<Compras> comprasDelUsuario(Long idcliente) {
		
		return compradao.comprasDelusuario(idcliente);
	}
	

}
