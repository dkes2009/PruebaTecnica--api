package com.ApiPruebatecnica.Entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name= "BD_TICKETS")
@Data
public class TicketEntity {
	private static final long serialVersionUID = 2L;
	public enum Estatus {
		ABIERTO,
		CERRADO,
	}

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "idtikect_seq_gen")
	@SequenceGenerator(name = "idtikect_seq_gen", sequenceName = "idtikect_seq", allocationSize = 1)
	@Column(name = "IDTICKET")
	private long IdTicket;

	@ManyToOne
	@JoinColumn(name = "BD_USUARIOS")
	private UsuarioEntity Usuario;

	@Column(name = "FECHA_CREACION")
	private LocalDate FechaCreacion;

	@Column(name = "FECHA_ACTUALIZACION")
	private LocalDate FechaActualizacion;

	@Enumerated(EnumType.STRING) // Almacenar como texto en la BD
	@Column(nullable = false)
	private Estatus estatus;


	public long getIdTicket() {
		return IdTicket;
	}

	public void setIdTicket(long idTicket) {
		IdTicket = idTicket;
	}

	public UsuarioEntity getUsuario() {
		return this.Usuario;
	}

	public void setUsuario(UsuarioEntity usuario) {
		this.Usuario = usuario;
	}

	public LocalDate getFechaCreacion() {
		return FechaCreacion;
	}

	public void setFechaCreacion(LocalDate fechaCreacion) {
		FechaCreacion = fechaCreacion;
	}

	public LocalDate getFechaActualizacion(LocalDate now) {
		return FechaActualizacion;
	}

	public void setFechaActualizacion(LocalDate fechaActualizacion) {
		FechaActualizacion = fechaActualizacion;
	}


	public Estatus getEstatus() {
		return estatus;
	}

	public void setEstatus(Estatus estatus) {
		this.estatus = estatus;
	}

	@Override
	public String toString() {
		return "TicketEntity{" +
				"IdTicket=" + IdTicket +
				", Usuario=" + Usuario +
				", FechaCreacion=" + FechaCreacion +
				", FechaActualizacion=" + FechaActualizacion +
				", estatus=" + estatus +
				'}';
	}
}

