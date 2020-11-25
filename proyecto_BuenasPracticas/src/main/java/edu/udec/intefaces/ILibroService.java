package edu.udec.intefaces;


import edu.udec.dto.LibroDto;
import edu.udec.dto.LibroEditarDto;
import edu.udec.entity.Libro;

public interface ILibroService extends IGenerica<Libro, LibroDto, Integer, Integer, Integer>{
	
	public Libro guardar(LibroDto libro);
	
	public Libro editarDetalle(LibroEditarDto libroEdit);
}
