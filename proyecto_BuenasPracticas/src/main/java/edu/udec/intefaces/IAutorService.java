package edu.udec.intefaces;
import java.util.List;

import org.springframework.data.repository.query.Param;

import edu.udec.dto.AutorLectorDto;
import edu.udec.entity.Autor;
import edu.udec.entity.AutorLector;
import edu.udec.entity.Direccion;


public interface IAutorService extends IGenerica<Autor, Autor, Integer, Integer, Integer>{
	
	/**
	 * metodo que se encarga de eliminar un estudiante
	 * @param nombre
	 * @param apellido
	 */
	public void EliminarAutor2(Integer id);
	
	/**
	 * metodo que se encarga de eliminar un estudiante
	 * @param nombre
	 * @param apellido
	 */
	public void EliminarAutor3(Integer id);
	
	/**
	 * metodo que se encarga de editar una direccion
	 * @param nombre
	 * @param apellido
	 */
	public void EditarOpcion1(Direccion obj);
	
	/**
	 * metodo que se encarga de editar una direccion
	 * @param nombre
	 * @param apellido
	 */
	public void EditarOpcion2(Direccion obj);
	
	/**
	 * metodo que se encarga de guardar un autor lector
	 * @param nombre
	 * @param apellido
	 */
	public void guardarAutorLector(AutorLectorDto dto);
	/**
	 * metodo que se encarga de guardar un autor lector
	 * @param nombre
	 * @param apellido
	 */
	public void guardarAutorLector(List<AutorLectorDto> dto);
	/**
	 * metodo que se encarga de list un autor lector
	 * @param nombre
	 * @param apellido
	 */	
	public List<AutorLector> listarLectorporAutor(Integer idAutor);
	

}
