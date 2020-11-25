package edu.udec.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import edu.udec.entity.Autor;
import io.swagger.annotations.ApiModelProperty;

public class LibroDto {

	private Integer id;
	
	@ApiModelProperty(notes = "El Nombre del libro debe ser mayor a 3 caracteres")
	@NotNull(message = "Campo nombre libro vacio")
	@Size(min = 3, message = "El Nombre del libro debe tener al menos 3 caracteres")
	private String nombre;
	
	@ApiModelProperty(notes = "La descrpcion debe ser mayor a 10 caracteres")
	@NotNull(message = "Campo descripcion vacio")
	@Size(min = 10, message = "la descripcion debe tener al menos 10 caracteres")
	private String descripcion;
	
	@ApiModelProperty(notes = "El numero de paginas es mayor a 1")
	@NotNull(message = "Campo numero de paginas vacio")
	private Integer numeroPaginas;
	
	@NotNull(message = "Objeto autor requerido")
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

	public Autor getAutor() {
		return autor;
	}

	public void setAutor(Autor autor) {
		this.autor = autor;
	}
	
	

}
