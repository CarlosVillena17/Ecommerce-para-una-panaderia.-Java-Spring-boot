package com.springboot.panecillos.app.models.domain;

public class CarritoCompras {
		private int item; 
		private Long idproducto; 
		private String nombre; 
		private String descripcion; 
		private double 	precioCompra;
		private int cantidad; 
		private double subTotal;
		private String foto;
		public int getItem() {
			return item;
		}
		public void setItem(int item) {
			this.item = item;
		}
		public Long getIdproducto() {
			return idproducto;
		}
		public void setIdproducto(Long idproducto) {
			this.idproducto = idproducto;
		}
		public String getNombre() {
			return nombre;
		}
		public void setNombre(String nombre) {
			this.nombre = nombre;
		}
		public String getDescripcion() {
			return descripcion;
		}
		public void setDescripcion(String descripcion) {
			this.descripcion = descripcion;
		}
		public double getPrecioCompra() {
			return precioCompra;
		}
		public void setPrecioCompra(double precioCompra) {
			this.precioCompra = precioCompra;
		}
		public int getCantidad() {
			return cantidad;
		}
		public void setCantidad(int cantidad) {
			this.cantidad = cantidad;
		}
		public double getSubTotal() {
			return subTotal;
		}
		public void setSubTotal(double subTotal) {
			this.subTotal = subTotal;
		}
		public String getFoto() {
			return foto;
		}
		public void setFoto(String foto) {
			this.foto = foto;
		}
}
