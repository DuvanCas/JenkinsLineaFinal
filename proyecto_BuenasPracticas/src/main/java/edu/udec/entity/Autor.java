package edu.udec.entity;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name = "autor")
public class Autor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ApiModelProperty(notes = "El Nombre del autor debe ser mayor a 3 caracteres")
	@NotNull(message = "Campo nombre vacio")
	@Size(min = 3, message = "El Nombre debe tener al menos 3 caracteres")
	@Column(name = "nombre", nullable = false, length = 25)
	private String nombre;
	
	@ApiModelProperty(notes = "El apellido del estudiante debe ser mayor a 3 caracteres")
	@NotNull(message = "Campo apelido vacio")
	@Size(min = 3, message = "El apellido debe tener al menos 3 caracteres")
	@Column(name = "apellido", nullable = false, length = 25)
	private String apellido;
	
	@NotNull(message = "Por favor ingrese una fecha")
	@Column(name = "fecha_nacimiento", nullable = false)
	private LocalDate fechaNacimiento;
	
	@ApiModelProperty(notes = "cedula del autor debe ser formato valido")
	@NotNull(message = "Campo cedula vacio")
	@Size(min = 7, max = 11, message = "La cedula debe ser de al menos entre 7 y 11 caracteres")
	@Column(name = "cedula", nullable = false)
	private String cedula;
	
	@OneToMany(mappedBy = "autor", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	private List<Libro> libros;
	
	@NotNull(message = "Por favor ingrese una direccion")
	@OneToOne(mappedBy = "autor", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	private Direccion direccion;
	
	/* 
	 ******************************GETTERS AND SETTERS******************************************************* 
	 */
	
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
	public List<Libro> getLibros() {
		return libros;
	}
	public void setLibros(List<Libro> libros) {
		this.libros = libros;
	}
	public Direccion getDireccion() {
		return direccion;
	}
	public void setDireccion(Direccion direccion) {
		this.direccion = direccion;
	}
	
}
