package com.springboot.panecillos.app.service;

import java.util.List;


import com.springboot.panecillos.app.models.domain.Categoria;

public interface ICategoriaService {
	public List<Categoria> buscarTodasCategoria();
	public void save(Categoria categoria);
	public Categoria findOne(Long id);
	public void delete(Long id);
	
}
