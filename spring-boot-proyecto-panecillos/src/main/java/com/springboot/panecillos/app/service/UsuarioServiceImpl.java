package com.springboot.panecillos.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.panecillos.app.dao.IUsuario;
import com.springboot.panecillos.app.models.domain.Usuario;

@Service
public class UsuarioServiceImpl  implements IUsuarioService {
	@Autowired
	private IUsuario usuarioDao;
	
	@Override
	public Usuario buscarPorId(Long idusuario) {
		Optional<Usuario> optional=usuarioDao.findById(idusuario);
		if(optional.isPresent()) {
			return optional.get();
		}
		return null;
	}

	@Override
	public List<Usuario> listar() {
		// TODO Auto-generated method stub
		return (List<Usuario>) usuarioDao.findAll();
	}

	@Override
	public void eliminar(Long idusuario) {
		usuarioDao.deleteById(idusuario);
		
	}

	@Override
	public void guardar(Usuario carrito) {
		usuarioDao.save(carrito);
		
	}

	@Override
	public Usuario buscarPorCorreoyNombre(String email, String password) {
		Usuario usuario=usuarioDao.buscarPorCorreoyContra(email, password);
		return usuario;
	}
	
}
