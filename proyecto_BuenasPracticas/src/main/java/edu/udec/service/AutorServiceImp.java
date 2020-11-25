package edu.udec.service;

import java.math.BigInteger;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.udec.dto.AutorLectorDto;
import edu.udec.entity.Autor;
import edu.udec.entity.AutorLector;
import edu.udec.entity.Direccion;
import edu.udec.entity.Libro;
import edu.udec.exception.ArgumentRequiredException;
import edu.udec.exception.ConflictException;
import edu.udec.exception.ModelNotFoundException;
import edu.udec.exception.NotContentException;
import edu.udec.intefaces.IAutorService;
import edu.udec.repository.IAutorLectorRepo;
import edu.udec.repository.IAutorRepo;
import edu.udec.repository.IDireccionRepo;
import edu.udec.repository.ILibroRepo;


@Service
public class AutorServiceImp implements IAutorService{
	
	@Autowired
	private IAutorRepo repo;
	
	@Autowired
	private ILibroRepo lirepo;
	
	@Autowired
	private IDireccionRepo direpo;
	
	@Autowired
	private IAutorLectorRepo autorLectorRepo;
	
	/*
	 * ----------------------------------------------------------------------------------------------------------
	 */
	@Override
	public Autor listarPorId(Integer id) {
		Autor autor = repo.findById(id).orElseThrow(
				() -> new ModelNotFoundException("Autor no encontrado"));
		autor.setLibros(null);
		autor.setDireccion(null);
		return autor;	
		}
	/*
	 * ----------------------------------------------------------------------------------------------------------
	 */
	@Override
	public Page<Autor> listarPaginado(Integer page, Integer size) {
		Page<Autor> listaPaginaAutor = repo.findAll(PageRequest.of(page, size));
		for (Autor autor : listaPaginaAutor.getContent()) {
			autor.setLibros(null);
			autor.setDireccion(null);
		}
		return listaPaginaAutor;
	}
	/*
	 * ----------------------------------------------------------------------------------------------------------
	 */
	@Override
	public Autor guardar(Autor autor) {
		
		BigInteger contador = (BigInteger) repo.validarExistenciaCedula(autor.getCedula());
		if(contador.intValue()>0)
			throw new ConflictException("Autor ingresado ya esta registrado con esa cedula...");
		if(autor.getLibros() != null) {
			for(Libro libro: autor.getLibros()) {
				libro.setAutor(autor);
    		}
		}
		if(autor.getDireccion() != null) {
			autor.getDireccion().setAutor(autor);
		}
		return repo.save(autor);
	}
	/*
	 * ----------------------------------------------------------------------------------------------------------
	 */
	@Override
	public void editar(Autor obj) {
		if(obj.getId() == null) {
			throw new ArgumentRequiredException("Id Autor es requerido");
		}
		Autor autor = repo.findById(obj.getId()).orElseThrow(
				() -> new ModelNotFoundException("Autor no encontrado"));
		BigInteger contador = (BigInteger) repo.validarExistenciaCedulaEditar(obj.getCedula(), obj.getId());
		
		if(contador.intValue()>0)
			throw new ConflictException("Autor ingresado ya esta registrado con esa cedula...");
		
		autor.setCedula(obj.getCedula());
		autor.setApellido(obj.getApellido());
		autor.setNombre(obj.getNombre());
		autor.setFechaNacimiento(obj.getFechaNacimiento());
		autor.getDireccion().setDescripcion(obj.getDireccion().getDescripcion());
		autor.getDireccion().setBarrio(obj.getDireccion().getBarrio());
		repo.save(autor);
	}
	/*
	 * ----------------------------------------------------------------------------------------------------------
	 */
	@Override
	public void eliminar(Integer id) {
		Autor autor = repo.findById(id).orElseThrow(
				() -> new ModelNotFoundException("Autor que ingreso no se encuentra en el sistema"));
		repo.delete(autor);
	}
	/*
	 * ----------------------------------------------------------------------------------------------------------
	 */
	@Override
	public void EliminarAutor2(Integer id) {
		boolean option = repo.existsById(id);
		if(option == true) {
			if((lirepo.contar(id)) > 0) {
				lirepo.eliminarRegistro(id);
			}
			repo.deleteById(id);	
		}else {
			throw new ModelNotFoundException("Autor que ingreso no se encuentra en el sistema");
		}		
	}
	/*
	 * ----------------------------------------------------------------------------------------------------------
	 */
	@Override
	public void EliminarAutor3(Integer id) {
		if(repo.existsById(id)) {repo.deleteById(id);
		throw new NotContentException("Autor Eliminado...");
		}			
		else
			throw new ModelNotFoundException("Autor no encontrado");
	}
	/*
	 * ----------------------------------------------------------------------------------------------------------
	 */
	@Override
	public void EditarOpcion1(Direccion obj) {
		String barrio = obj.getBarrio();
		String desc = obj.getDescripcion();
		direpo.EditarDireccion(barrio, desc, obj.getId());		
	}
	/*
	 * ----------------------------------------------------------------------------------------------------------
	 */
	@Override
	public void EditarOpcion2(Direccion obj) {
		Direccion dir = direpo.findById(obj.getId()).orElseThrow(
				() -> new ModelNotFoundException("Autor no encontrado en el sistema..."));
		dir.setBarrio(obj.getBarrio());
		dir.setDescripcion(obj.getDescripcion());
		direpo.save(dir);
	}
	/*
	 * ----------------------------------------------------------------------------------------------------------
	 */
	@Override
	public void guardarAutorLector(AutorLectorDto dto) {
		
		autorLectorRepo.guardar(dto.getAutor().getId(), dto.getLector().getId(), dto.getInfoAdicional());		
	}
	/*
	 * ----------------------------------------------------------------------------------------------------------
	 */
	@Override
	public List<AutorLector> listarLectorporAutor(Integer idAutor) {
		
		List<AutorLector> lista= autorLectorRepo.listarLectorporAutor(idAutor);
		for (AutorLector autorLector : lista) {
			autorLector.setAutor(null);
		}
		return lista;
	}
	/*
	 * ----------------------------------------------------------------------------------------------------------
	 */
	@Transactional
	@Override
	public void guardarAutorLector(List<AutorLectorDto> dto) {
		//validar info
		for (AutorLectorDto obj : dto) {			
			autorLectorRepo.guardar(obj.getAutor().getId(), obj.getLector().getId(), obj.getInfoAdicional());			
		}
	}
}
