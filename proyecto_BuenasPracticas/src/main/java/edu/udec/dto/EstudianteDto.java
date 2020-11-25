package edu.udec.dto;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Los parametros implementados dentro de la clase Estudiante son...")
public class EstudianteDto {
	
	
	private Integer id;
	
	@ApiModelProperty(notes = "El Nombre del estudiante debe ser mayor a 3 caracteres")
	@NotNull(message = "Campo nombre vacio")
	@NotEmpty(message="El nombre de la persona es obligatorio")
	@Size(min = 3, message = "El Nombre debe tener al menos 3 caracteres")
	private String nombreEstudiante;
	
	@ApiModelProperty(notes = "El Apellido del estudiante debe ser mayor a 3 caracteres")
	@NotNull(message = "Campo apellido vacio")
	@NotEmpty(message="El apellido de la persona es obligatorio")
	@Size(min = 3, message = "El apellido debe tener al menos 3 caracteres")
	private String apellidoEstudiante;
	
	@ApiModelProperty(notes = "La edad del estudiante debe ser menor o igual a 100")
	@Max(value = 100, message ="Edad Fuera de Rango")
	@NotNull(message = "Campo edad vacio")
	private int edadEstudiante;
	
	@ApiModelProperty(notes = "El Nombre del colegio del estudiante debe ser mayor a 3 caracteres")
	@NotNull(message = "Campo nombre de colegio vacio")
	@NotEmpty(message="El nombre del colegio es obligatorio")
	@Size(min = 3, message = "El Nombre del colegio debe tener al menos 3 caracteres")
	private String colegioEstudiante;
	
	@ApiModelProperty(notes = "El correo debe ir en formato valido")
	@Email(message = "Correo Invalido")
	@NotNull(message = "Campo correo de colegio vacio")
	@Column(name = "correo", nullable = false, length = 25)
	private String correoEstudiante;
	
	@ApiModelProperty(notes = "Fecha de nacimiento de estudiante es formato valido")
	@NotNull(message = "Por favor ingrese una fecha")
	private LocalDateTime fecha;
	
	@ApiModelProperty(notes = "cedula del estudiante debe ser formato valido")
	@NotNull(message = "Campo cedula vacio")
	@Min(value = 1000, message = "la cedula debe ser mayor a 1000")
	@Max(value = 10000, message = "la cedula debe ser menor a 10000")
	private int cedula;
	
	
	

	public EstudianteDto(
			@NotNull(message = "Campo nombre vacio") @NotEmpty(message = "El nombre de la persona es obligatorio") @Size(min = 3, message = "El Nombre debe tener al menos 3 caracteres") String nombreEstudiante,
			@NotNull(message = "Campo apellido vacio") @NotEmpty(message = "El apellido de la persona es obligatorio") @Size(min = 3, message = "El apellido debe tener al menos 3 caracteres") String apellidoEstudiante,
			@Max(value = 100, message = "Edad Fuera de Rango") @NotNull(message = "Campo edad vacio") int edadEstudiante,
			@NotNull(message = "Campo nombre de colegio vacio") @NotEmpty(message = "El nombre del colegio es obligatorio") @Size(min = 3, message = "El Nombre del colegio debe tener al menos 3 caracteres") String colegioEstudiante,
			@Email(message = "Correo Invalido") @NotNull(message = "Campo correo de colegio vacio") String correoEstudiante,
			@NotNull(message = "Por favor ingrese una fecha") LocalDateTime fecha,
			@NotNull(message = "Campo cedula vacio") @Min(value = 1000, message = "la cedula debe ser mayor a 1000") @Max(value = 10000, message = "la cedula debe ser menor a 10000") int cedula) {
		super();
		this.nombreEstudiante = nombreEstudiante;
		this.apellidoEstudiante = apellidoEstudiante;
		this.edadEstudiante = edadEstudiante;
		this.colegioEstudiante = colegioEstudiante;
		this.correoEstudiante = correoEstudiante;
		this.fecha = fecha;
		this.cedula = cedula;
	}







	public EstudianteDto() {
	}







	public Integer getId() {
		return id;
	}



	public void setId(Integer id) {
		this.id = id;
	}



	public String getNombreEstudiante() {
		return nombreEstudiante;
	}



	public void setNombreEstudiante(String nombreEstudiante) {
		this.nombreEstudiante = nombreEstudiante;
	}



	public String getApellidoEstudiante() {
		return apellidoEstudiante;
	}



	public void setApellidoEstudiante(String apellidoEstudiante) {
		this.apellidoEstudiante = apellidoEstudiante;
	}



	public int getEdadEstudiante() {
		return edadEstudiante;
	}



	public void setEdadEstudiante(int edadEstudiante) {
		this.edadEstudiante = edadEstudiante;
	}



	public String getColegioEstudiante() {
		return colegioEstudiante;
	}



	public void setColegioEstudiante(String colegioEstudiante) {
		this.colegioEstudiante = colegioEstudiante;
	}



	public String getCorreoEstudiante() {
		return correoEstudiante;
	}



	public void setCorreoEstudiante(String correoEstudiante) {
		this.correoEstudiante = correoEstudiante;
	}



	public LocalDateTime getFecha() {
		return fecha;
	}



	public void setFecha(LocalDateTime fecha) {
		this.fecha = fecha;
	}

	
}
