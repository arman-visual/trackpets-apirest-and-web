package com.trackpets.springboot.web.app.models.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "protectoras")
public class Protectora implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;

	@Column(name = "numero_entidad_social", nullable = false)
	private String numeroEntidad;

	@Column(name = "nombre_protectora", length = 120, nullable = false)
	private String nombre;

	@Column(name = "telefono", length = 15, nullable = false)
	private String telefono;

	@Column(name = "correo_electronico", length = 120, nullable = false)
	private String correo;

	@Column(name = "direccion", length = 150, nullable = false)
	private String direccion;
	

	public Long getId() {
		return id;
	}

	public String getNumeroEntidad() {
		return numeroEntidad;
	}

	public void setNumeroEntidad(String numeroEntidad) {
		this.numeroEntidad = numeroEntidad;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
