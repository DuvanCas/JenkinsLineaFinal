package edu.udec.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name = "direccion")
public class Direccion {
	
	@Id
	private Integer id;
	
	@ApiModelProperty(notes = "La direccion del autor debe ser mayor a 3 caracteres")
	@NotNull(message = "Campo direccion vacio")
	@Size(min = 3, message = "La direccion debe tener al menos 3 caracteres")
	@Column(name = "descripcion", nullable = false, length = 25)
	private String descripcion;
	
	@ApiModelProperty(notes = "El barrio del autor debe ser mayor a 3 caracteres")
	@NotNull(message = "Campo bariro vacio")
	@Size(min = 3, message = "El barrio debe tener al menos 3 caracteres")
	@Column(name = "barrio", nullable = false, length = 25)
	private String barrio;
	
	@OneToOne
	@MapsId
	private Autor autor;
	
	/* 
	 ******************************GETTERS AND SETTERS******************************************************* 
	 */

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getBarrio() {
		return barrio;
	}

	public void setBarrio(String barrio) {
		this.barrio = barrio;
	}
	@JsonIgnore
	public Autor getAutor() {
		return autor;
	}

	public void setAutor(Autor autor) {
		this.autor = autor;
	}
	
	
}
