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
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "animales")
public class Mascota implements Serializable {

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

	@Column(name = "tamanyo", length = 30, nullable = true)
	private String tamanyo;

	@Column(name = "nombre", length = 30, nullable = false)
	private String nombre;

	@Column(name = "genero", length = 10, nullable = false)
	private String genero;

	@Column(name = "foto_url", length = 200, nullable = true)
	private String foto;

	@Column(name = "fecha_de_adopcion", nullable = true)
	@Temporal(TemporalType.DATE)
	private Date fechaAdopcion;

	@Column(name = "fecha_de_alta", nullable = true)
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date fechaAlta;

	@Column(name = "descripcion", length = 250, nullable = true)
	private String descripcion;

	@Column(name = "disponible", nullable = false)
	private boolean estado;

	@JoinColumn(name = "id_protectora")
	@OneToOne(fetch = FetchType.EAGER)
	private Protectora protectora;

	@PrePersist
	public void prePersist() {
		fechaAlta = new Date();
	}

	public Long getId() {
		return id;
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

	public String getTamanyo() {
		return tamanyo;
	}

	public void setTamaño(String tamanyo) {
		this.tamanyo = tamanyo;
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

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	public Protectora getProtectora() {
		return protectora;
	}

	public void setProtectora(Protectora protectora) {
		this.protectora = protectora;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
}
