package com.springboot.panecillos.app.models.domain;

import java.util.ArrayList;
import java.util.List;


import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "categoria")
public class Categoria {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idcategoria;
	private String nombre;
	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name="idcategoria")
	private List<Producto>  producto;
	
	public Categoria() {
		this.producto=new ArrayList<Producto>();
	}

	public Long getIdcategoria() {
		return idcategoria;
	}
	public void setIdcategoria(Long idcategoria) {
		this.idcategoria = idcategoria;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public List<Producto> getProducto() {
		return producto;
	}
	public void setProducto(List<Producto> producto) {
		this.producto = producto;
	}

}
