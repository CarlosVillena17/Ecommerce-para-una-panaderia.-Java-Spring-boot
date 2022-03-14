package com.springboot.panecillos.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.panecillos.app.dao.ICategoriaDao;
import com.springboot.panecillos.app.models.domain.Categoria;

@Service
public class CategoriaServiceImpl implements ICategoriaService{

	@Autowired
	private ICategoriaDao categoriaDao;
	@Override
	public List<Categoria> buscarTodasCategoria() {
		return (List<Categoria>) categoriaDao.findAll();
	}
	@Override
	public void save(Categoria categoria) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public Categoria findOne(Long id) {
		Optional<Categoria> optional=categoriaDao.findById(id);
		if(optional.isPresent()) {
			return optional.get(); 
		}
		return null;
	}
	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		
	}

}
