package com.ApiPruebatecnica.Entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name= "BD_USUARIOS")
@Data
public class UsuarioEntity {
	private static final long serialVersionUID = 1L;


	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "idusuario_seq_gen")
	@SequenceGenerator(name = "idusuario_seq_gen", sequenceName = "idusuario_seq", allocationSize = 1)
	@Column(name="IDUSUARIO")
	private long IdUsuario;

	@Column(name="NOMBRE")
	private String Nombre;

	@Column(name="APELLIDOS")
	private String Apellidos;

	@Column(name="FECHA_CREACION")
	private LocalDate FechaCreacion;

	@Column(name="FECHA_ACTUALIZACION", nullable = true)
	private LocalDate FechaActualizacion;

	public long getIdUsuario() {
		return IdUsuario;
	}

	public void setIdUsuario(long idUsuario) {
		IdUsuario = idUsuario;
	}

	public String getNombre() {
		return Nombre;
	}

	public void setNombre(String nombre) {
		Nombre = nombre;
	}

	public String getApellidos() {
		return Apellidos;
	}

	public void setApellidos(String apellidos) {
		Apellidos = apellidos;
	}

	public LocalDate getFechaCreacion(LocalDate now) {
		return FechaCreacion;
	}

	public void setFechaCreacion(LocalDate fechaCreacion) {
		FechaCreacion = fechaCreacion;
	}

	public LocalDate getFechaActualizacion() {
		return FechaActualizacion;
	}

	public void setFechaActualizacion(LocalDate fechaActualizacion) {
		FechaActualizacion = fechaActualizacion;
	}

	@Override
	public String toString() {
		return "UsuarioEntity{" +
				"IdUsuario=" + IdUsuario +
				", Nombre='" + Nombre + '\'' +
				", Apellidos='" + Apellidos + '\'' +
				", FechaCreacion=" + FechaCreacion +
				", FechaActualizacion=" + FechaActualizacion +
				'}';
	}
}
