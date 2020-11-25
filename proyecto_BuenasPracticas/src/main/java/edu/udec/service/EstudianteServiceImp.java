package edu.udec.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import edu.udec.dto.EstudianteDto;
import edu.udec.entity.Estudiante;
import edu.udec.exception.ArgumentRequiredException;
import edu.udec.exception.ConflictException;
import edu.udec.exception.ModelNotFoundException;
import edu.udec.exception.NotContentException;
import edu.udec.intefaces.IEstudianteService;
import edu.udec.repository.IEstudianteRepo;
import edu.udec.util.MHelpers;

/**
 * Clase EstudianteServiceImp el cual es encarga de implementar la interfaz de 
 * IEstudianteService
 * */

@Service
public class EstudianteServiceImp implements IEstudianteService {

	@Autowired
	private IEstudianteRepo repo;
	
	/************************************************************************************************************
	 * metodo que se encarga de retornar la lista de los estudiantes filtrado por un nombre
	 * especifico solicitado 
	 * 
	 * @param Recive String nombre el cual es el nomnbre del estuadiante
	 * @return Retorna objeto Estudiante con la informacion respectiva encontrada por el nombre
	 * */
	@Override
	public Estudiante listarPorId(Integer id){
		Estudiante estudiante = repo.findById(id).orElseThrow(
				() -> new ModelNotFoundException("Estudiante no encontrado"));
		return estudiante;
	}

	
	/************************************************************************************************************
	 * <p>
	 * metodo que se encargado de retornar la lista completa de estudiantes
	 * </p>
	 * @return Retorna una lista de obajeto estudiante
	 * */
	@Override
	public List<Estudiante> ObtenerListaEstudiante() {
		return repo.findAll();
	}
	

	
	/************************************************************************************************************
	 * <p>
	 * metodo encargado de la creacion de los estudiantes nuevos y encargado de añadirlo a la lista
	 * </p>
	 * @param Recive objeto Estudiante 
	 * @return Retorna objeto Estudiante con la información cargada
	 * */
	@Override
	public Estudiante guardar(Estudiante estudiante) {

	       List<Estudiante> option = repo.findAll();
	       for(Estudiante est : option ) {
	    	   if(est.getCedula() == (estudiante.getCedula())) {
	    		   throw new ConflictException("Cedula ingresada ya esta registrada");
	    	   }
	       }
			return repo.save(estudiante);
	}

	
	/************************************************************************************************************
	 * <p>
	 * metodo que encargado de editar y actualizar los datos del estudiante
	 * </p>
	 * @param Recive Objeto estudiante
	 * @return Retorna Objeto estudiante con la informacion actualizada
	 * */
	@Override
	public void editar(Estudiante estudiante) {		
		
		if(estudiante.getId() == null) {
			throw new ArgumentRequiredException("Id Profesor es requerido");
		}
		//if(repo.existsByCedula){}
		this.listarPorId(estudiante.getId());		
		repo.save(estudiante);
	}


	/************************************************************************************************************
	 * <p>
	 * metodo encargado de eliminar un estudiante de la lista
	 * </p>
	 * @param Recive String nombre y String apellido
	 * @return void estatus 204 not data y en el header el mesnaje de Estudiante eliminado
	 * */	
	@Override
	public void eliminar(Integer id) {
		if(repo.existsById(id)) {repo.deleteById(id);
		throw new NotContentException("Estudiante Eliminado...");
		}			
		else
			throw new ModelNotFoundException("Estudiante no encontrado");
		
	}

	/************************************************************************************************************
	 * Metodo que retorna estudiante por jpql
	 */
	@Override
	public EstudianteDto findByNombreEstudianteAndColegioEstudiante(String nombre, String colegio) {
		Optional<Estudiante> estu = this.repo.findByNombreEstudianteAndColegioEstudiante(nombre, colegio);
		if(!estu.isPresent()) {
			throw new ModelNotFoundException("Estudiante no encontrado");
		}
		return MHelpers.modelMapper().map(estu.get(), EstudianteDto.class);
	}
	
	/************************************************************************************************************
	 * Metodo para listar paginado
	 */	
	@Override
	public Page<Estudiante> listarPaginado(Integer page, Integer size) {
		return repo.findAll(PageRequest.of(page, size));
	}
	
	/************************************************************************************************************
	 * Metodo para listar paginado por apellido
	 */
	@Override
	public Page<Estudiante> listarPorNombre(int page, int size, String nombre) {
		return repo.findByNombreEstudiante(nombre, PageRequest.of(page, size, Sort.by("nombreEstudiante").ascending()));
	}

	/*************************************************************************************************************
	 * Metodo que convierte una clase necesaria 
	 * @param estudiante
	 * @return
	 */
	private EstudianteDto convertToEstudianteDto(final Estudiante estudiante) {
		return MHelpers.modelMapper().map(estudiante, EstudianteDto.class);
	}

}
