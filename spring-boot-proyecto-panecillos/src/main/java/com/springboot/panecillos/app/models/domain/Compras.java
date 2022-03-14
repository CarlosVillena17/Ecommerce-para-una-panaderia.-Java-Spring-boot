package com.springboot.panecillos.app.models.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "compras")
public class Compras {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idcompras;
	private Double monto;
	private Long idcliente;
	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "idcompras", referencedColumnName = "idcompras")
	private List<DetalleCompras> detalles;
	@ManyToOne
	@JoinColumn(name = "idpago")
	private Pago pago; 
	@Temporal(TemporalType.DATE)
	@Column(name = "fecha")
	private Date fecha;
	
	public Compras() {
		this.detalles=new ArrayList<DetalleCompras>();
	}
	
	@PrePersist
	private void prePersist() {
		this.fecha=new Date();
	}
	
	public void agregar(DetalleCompras detalleCompras) {
		this.detalles.add(detalleCompras);
	}

	public Long getIdcompras() {
		return idcompras;
	}

	public void setIdcompras(Long idcompras) {
		this.idcompras = idcompras;
	}

	public Double getMonto() {
		return monto;
	}

	public void setMonto(Double monto) {
		this.monto = monto;
	}

	public List<DetalleCompras> getDetalles() {
		return detalles;
	}

	public void setDetalles(List<DetalleCompras> detalles) {
		this.detalles = detalles;
	}

	public Pago getPago() {
		return pago;
	}

	public void setPago(Pago pago) {
		this.pago = pago;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Long getIdcliente() {
		return idcliente;
	}

	public void setIdcliente(Long idcliente) {
		this.idcliente = idcliente;
	}

}
