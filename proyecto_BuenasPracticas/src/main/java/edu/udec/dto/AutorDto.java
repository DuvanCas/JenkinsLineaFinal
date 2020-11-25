package edu.udec.dto;

import java.time.LocalDate;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModelProperty;

public class AutorDto {

	@NotNull(message = "Id es requerido para editar")
	private Integer id;
	
	@ApiModelProperty(notes = "El Nombre del autor debe ser mayor a 3 caracteres")
	@NotNull(message = "Campo nombre vacio")
	@Size(min = 3, message = "El Nombre debe tener al menos 3 caracteres")
	private String nombre;
	
	@ApiModelProperty(notes = "El apellido del estudiante debe ser mayor a 3 caracteres")
	@NotNull(message = "Campo apelido vacio")
	@Size(min = 3, message = "El apellido debe tener al menos 3 caracteres")
	private String apellido;
	
	@NotNull(message = "Por favor ingrese una fecha")
	private LocalDate fechaNacimiento;
	
	@ApiModelProperty(notes = "cedula del autor debe ser formato valido")
	@NotNull(message = "Campo cedula vacio")
	@Size(min = 7, max = 11, message = "La cedula debe ser de al menos entre 7 y 11 caracteres")
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

	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}
	
	
}
