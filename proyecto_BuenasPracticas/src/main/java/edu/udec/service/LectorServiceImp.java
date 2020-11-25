package edu.udec.service;

import java.math.BigInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import edu.udec.entity.Lector;
import edu.udec.exception.ArgumentRequiredException;
import edu.udec.exception.ConflictException;
import edu.udec.exception.ModelNotFoundException;
import edu.udec.intefaces.ILectorService;
import edu.udec.repository.ILectorRepo;

@Service
public class LectorServiceImp implements ILectorService{
	
	@Autowired
	private ILectorRepo repo;
	/*
	 * ----------------------------------------------------------------------------------------------------------
	 */
	@Override
	public Lector listarPorId(Integer id) {
		Lector lector = repo.findById(id).orElseThrow(
				() -> new ModelNotFoundException("Lector no encontrado"));
		return lector;	
	}
	/*
	 * ----------------------------------------------------------------------------------------------------------
	 */	
	@Override
	public Page<Lector> listarPaginado(Integer page, Integer size) {
		Page<Lector> listaPaginaLector = repo.findAll(PageRequest.of(page, size));
		return listaPaginaLector;
	}
	/*
	 * ----------------------------------------------------------------------------------------------------------
	 */
	@Override
	public Lector guardar(Lector object) {
		
		BigInteger contador = (BigInteger) repo.validarExistenciaCedula(object.getCedula());
		if(contador.intValue()>0)
			throw new ConflictException("Autor ingresado ya esta registrado con esa cedula...");
		
		return repo.save(object);
	}
	/*
	 * ----------------------------------------------------------------------------------------------------------
	 */
	@Override
	public void editar(Lector obj) {
		if(obj.getId() == null) {
			throw new ArgumentRequiredException("Id Autor es requerido");
		}
		Lector lector = repo.findById(obj.getId()).orElseThrow(
				() -> new ModelNotFoundException("Autor no encontrado"));
		BigInteger contador = (BigInteger) repo.validarExistenciaCedulaEditar(obj.getCedula(), obj.getId());
		
		if(contador.intValue()>0)
			throw new ConflictException("Autor ingresado ya esta registrado con esa cedula...");
		
		lector.setCedula(obj.getCedula());
		lector.setApellido(obj.getApellido());
		lector.setNombre(obj.getNombre());
		repo.save(lector);	
	}
	/*
	 * ----------------------------------------------------------------------------------------------------------
	 */
	@Override
	public void eliminar(Integer id) {
		Lector lector = repo.findById(id).orElseThrow(
				() -> new ModelNotFoundException("Lector que ingreso no se encuentra en el sistema"));
		repo.delete(lector);
	}
}
