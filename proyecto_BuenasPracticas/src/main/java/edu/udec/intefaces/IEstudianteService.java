package edu.udec.intefaces;

import java.util.List;

import org.springframework.data.domain.Page;

import edu.udec.dto.EstudianteDto;
//import edu.udec.dto.EstudianteDto;
import edu.udec.entity.Estudiante;



/**
 * Interfaz con metodos necesarios para ser implementados por alguna clase si lo requiere
 * */

public interface IEstudianteService extends IGenerica<Estudiante, Estudiante, Integer, Integer, Integer>{

	/**
	 * metodo que retorna la lista completa no paginada de estudiantes
	 * @return
	 */
	public List<Estudiante> ObtenerListaEstudiante();
	/**
	 * metodo que retorna la lista por nombre paginado
	 * @param page
	 * @param size
	 * @param nombre
	 * @return
	 */
	public Page<Estudiante> listarPorNombre(int page, int size, String nombre);
	
	/**
	 * Metodo JPQL 
	 * @param nombre
	 * @param colegio
	 * @return
	 */
	public EstudianteDto findByNombreEstudianteAndColegioEstudiante(String nombre, String colegio);


}
