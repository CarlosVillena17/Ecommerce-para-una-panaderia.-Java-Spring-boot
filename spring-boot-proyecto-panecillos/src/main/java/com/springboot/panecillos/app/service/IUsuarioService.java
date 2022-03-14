package com.springboot.panecillos.app.service;

import java.util.List;

import com.springboot.panecillos.app.models.domain.CarritoCompras;
import com.springboot.panecillos.app.models.domain.Usuario;

public interface IUsuarioService {
	public Usuario buscarPorId(Long idusuario);
	public List<Usuario> listar();
	public void eliminar(Long idusuario);
	public void guardar(Usuario usuario);
	public Usuario buscarPorCorreoyNombre(String email, String password);
}
