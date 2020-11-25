package edu.udec.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name = "estudiante")
public class Estudiante {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ApiModelProperty(notes = "El Nombre del estudiante debe ser mayor a 3 caracteres")
	@NotNull(message = "Campo nombre vacio")
	@Size(min = 3, message = "El Nombre debe tener al menos 3 caracteres")
	@Column(name = "nombre", nullable = false, length = 25)
	private String nombreEstudiante;

	@ApiModelProperty(notes = "El Apellido del estudiante debe ser mayor a 3 caracteres")
	@NotNull(message = "Campo apellido vacio")
	@Size(min = 3, message = "El apellido debe tener al menos 3 caracteres")
	@Column(name = "apellido", nullable = false, length = 25)
	private String apellidoEstudiante;	

	@ApiModelProperty(notes = "La edad del estudiante debe ser menor o igual a 100")
	@Max(value = 100, message ="Edad Fuera de Rango")
	@Column(name = "edad", nullable = false)
	private int edadEstudiante;

	@ApiModelProperty(notes = "El Nombre del colegio del estudiante debe ser mayor a 3 caracteres")
	@NotNull(message = "Campo nombre de colegio vacio")
	@NotEmpty(message="El nombre del colegio es obligatorio")
	@Size(min = 3, message = "El Nombre del colegio debe tener al menos 3 caracteres")
	@Column(name = "colegio", nullable = false, length = 25)
	private String colegioEstudiante;
	
	@Email(message = "Correo Invalido")
	@NotNull(message = "Campo correo de colegio vacio")
	@Column(name = "correo", nullable = false, length = 25)
	private String correoEstudiante;
	
	@DateTimeFormat(iso = ISO.DATE_TIME, pattern = "yyyy-MM-ddTHH:mm:ss")
	@NotNull(message = "Por favor ingrese una fecha")
	@Column(name = "fecha") 
	private LocalDateTime fecha;
	
	
	@ApiModelProperty(notes = "cedula del estudiante debe ser formato valido")
	@NotNull(message = "Campo cedula vacio")
	@Min(value = 1000, message = "la cedula debe ser mayor a 1000")
	@Max(value = 10000, message = "la cedula debe ser menor a 10000")
	@Column(name = "cedula", nullable = false)
	private int cedula;
	
	/**
	 * Get nombreEstudiante que puede retornar el nombre almacenado en el
	 * */
	public String getNombreEstudiante() {
		return nombreEstudiante;
	}

	/**
	 * Set nombreEstudiante que puede modificar el nombre del estudiante
	 * */
	public void setNombreEstudiante(String nombreEstudiante) {
		this.nombreEstudiante = nombreEstudiante;
	}
	

	/**
	 * Get ApellidoEstudiante que puede retornar el apellido almacenado en el
	 * */
	public String getApellidoEstudiante() {
		return apellidoEstudiante;
	}

	/**
	 * Set ApellidoEstudiante que puede  almacenar el apellido del estudiante
	 * */
	public void setApellidoEstudiante(String apellidoEstudiante) {
		this.apellidoEstudiante = apellidoEstudiante;
	}

	/**
	 * Get EdadEstudiante que puede retornar la edad almacenado en el
	 * */
	public int getEdadEstudiante() {
		return edadEstudiante;
	}

	/**
	 * Set edadEstudiante que puede almacenar la edad del estudiante
	 * */
	public void setEdadEstudiante(int edadEstudiante) {
		this.edadEstudiante = edadEstudiante;
	}

	/**
	 * Get ColegioEstudiante que puede retornar el colegio almacenado en el
	 * */
	public String getColegioEstudiante() {
		return colegioEstudiante;
	}

	/**
	 * Set ColegioEstudiante que puede almacenar el colegio del estudiante
	 * */
	public void setColegioEstudiante(String colegioEstudiante) {
		this.colegioEstudiante = colegioEstudiante;
	}
	/**
	 * Get idEstudiante que puede retornar el id almacenado en el
	 * */
	public Integer getId() {
		return id;
	}
	/**
	 * Set idEstudiante que puede almacenar el id del estudiante
	 * */
	public void setIdEstudiante(Integer id) {
		this.id = id;
	}
	/**
	 * Get CorreoEstudiante que puede retornar el correo almacenado en el
	 * */
	public String getCorreoEstudiante() {
		return correoEstudiante;
	}
	/**
	 * Set CorreoEstudiante que puede almacenar el correo del estudiante
	 * */
	public void setCorreoEstudiante(String correoEstudiante) {
		this.correoEstudiante = correoEstudiante;
	}

	public int getCedula() {
		return cedula;
	}

	public void setCedula(int cedula) {
		this.cedula = cedula;
	}

	public LocalDateTime getFecha() {
		return fecha;
	}

	public void setFecha(LocalDateTime fecha) {
		this.fecha = fecha;
	}
	

}
