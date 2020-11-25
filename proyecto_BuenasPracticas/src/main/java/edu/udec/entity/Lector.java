package edu.udec.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name = "lector")
public class Lector {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ApiModelProperty(notes = "El Nombre del lector debe ser mayor a 3 caracteres")
	@NotNull(message = "Campo nombre vacio")
	@Size(min = 3, message = "El Nombre debe tener al menos 3 caracteres")
	@Column(name = "nombre", nullable = false, length = 25)
	private String nombre;
	
	@ApiModelProperty(notes = "El apellido del lector debe ser mayor a 3 caracteres")
	@NotNull(message = "Campo apelido vacio")
	@Size(min = 3, message = "El apellido debe tener al menos 3 caracteres")
	@Column(name = "apellido", nullable = false, length = 25)
	private String apellido;
	
	@ApiModelProperty(notes = "cedula del lector debe ser formato valido")
	@NotNull(message = "Campo cedula vacio")
	@Size(min = 7, max = 11, message = "La cedula debe ser de al menos entre 7 y 11 caracteres")
	@Column(name = "cedula", nullable = false)
	private String cedula;

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

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}
	
	

}
