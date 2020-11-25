package edu.udec.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name = "libro")
public class Libro {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ApiModelProperty(notes = "El Nombre del libro debe ser mayor a 3 caracteres")
	@NotNull(message = "Campo nombre libro vacio")
	@Size(min = 3, message = "El Nombre del libro debe tener al menos 3 caracteres")
	@Column(name = "nombre", nullable = false, length = 25)
	private String nombre;
	
	@ApiModelProperty(notes = "La descrpcion debe ser mayor a 10 caracteres")
	@NotNull(message = "Campo descripcion vacio")
	@Size(min = 10, message = "la descripcion debe tener al menos 10 caracteres")
	@Column(name = "descripcion", nullable = false, length = 100)
	private String descripcion;
	
	@ApiModelProperty(notes = "El numero de paginas es mayor a 1")
	@NotNull(message = "Campo numero de paginas vacio")
	@Column(name = "numero_paginas", nullable = false)
	private Integer numeroPaginas;
	
	@ManyToOne
	@JoinColumn(name = "id_autor ", nullable = false, foreignKey = @ForeignKey(name = "FK_Autor_Libro"))
	private Autor autor;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
	public Integer getNumeroPaginas() {
		return numeroPaginas;
	}
	public void setNumeroPaginas(Integer numeroPaginas) {
		this.numeroPaginas = numeroPaginas;
	}
	@JsonIgnore
	public Autor getAutor() {
		return autor;
	}
	public void setAutor(Autor autor) {
		this.autor = autor;
	}
}
