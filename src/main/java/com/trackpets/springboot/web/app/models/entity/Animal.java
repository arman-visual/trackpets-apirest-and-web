package com.trackpets.springboot.web.app.models.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "animales")
public class Animal implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_animal")
	private Long id;

	@Column(name = "dni_animal", length = 20, nullable = true)
	private String dni;

	@Column(name = "raza", length = 30, nullable = false)
	private String raza;

	@Column(name = "edad", length = 2, nullable = true)
	private int edad;

	@Column(name = "tamaño", length = 30, nullable = true)
	private String tamaño;

	@Column(name = "nombre", length = 30, nullable = false)
	private String nombre;

	@Column(name = "fecha_de_adopcion", nullable = true)
	@Temporal(TemporalType.DATE)
	private Date fechaAdopcion;

	@Column(name = "fecha_de_alta", nullable = true)
	@Temporal(TemporalType.DATE)
	private Date fechaAlta;

	@Column(name = "descripcion", length = 250, nullable = true)
	private String descripcion;

	@Column(name = "disponible", nullable = false)
	private Boolean estado;

	@JoinColumn(name = "id_protectora")
	@OneToOne(fetch = FetchType.LAZY)
	private Protectora herramienta;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getRaza() {
		return raza;
	}

	public void setRaza(String raza) {
		this.raza = raza;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public String getTamaño() {
		return tamaño;
	}

	public void setTamaño(String tamaño) {
		this.tamaño = tamaño;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Date getFechaAdopcion() {
		return fechaAdopcion;
	}

	public void setFechaAdopcion(Date fechaAdopcion) {
		this.fechaAdopcion = fechaAdopcion;
	}

	public Date getFechaAlta() {
		return fechaAlta;
	}

	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Boolean getEstado() {
		return estado;
	}

	public void setEstado(Boolean estado) {
		this.estado = estado;
	}

	public Protectora getHerramienta() {
		return herramienta;
	}

	public void setHerramienta(Protectora herramienta) {
		this.herramienta = herramienta;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
}
