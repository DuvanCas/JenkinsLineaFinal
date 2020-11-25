package edu.udec.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import edu.udec.dto.LibroDto;
import edu.udec.dto.LibroEditarDto;
import edu.udec.entity.Libro;
import edu.udec.exception.ArgumentRequiredException;
import edu.udec.exception.ModelNotFoundException;
import edu.udec.intefaces.ILibroService;
import edu.udec.repository.IAutorRepo;
import edu.udec.repository.ILibroRepo;

@Service
public class LibroServiceImp implements ILibroService {
	
	@Autowired
	private ILibroRepo repo;
	
	@Autowired
	private IAutorRepo repoAutor;
	/*
	 * ----------------------------------------------------------------------------------------------------------
	 */
	@Override
	public Libro listarPorId(Integer id) {
		Libro libro = repo.findById(id).orElseThrow(
				() -> new ModelNotFoundException("Autor no encontrado"));
		return libro;	
	}
	/*
	 * ----------------------------------------------------------------------------------------------------------
	 */
	@Override
	public Page<Libro> listarPaginado(Integer page, Integer size) {
		Page<Libro> listaPaginaLibro = repo.findAll(PageRequest.of(page, size));
		return listaPaginaLibro;
	}
	/*
	 * ----------------------------------------------------------------------------------------------------------
	 */	
	@Override
	public Libro guardar(LibroDto libroDto) {
		if(libroDto.getAutor().getId() == null) {
			throw new ArgumentRequiredException("Id autor es requerido");
		} else {
			if(!repoAutor.existsById(libroDto.getAutor().getId())) {
				throw new ModelNotFoundException("Autor no encontrado");
			}
		}		
		ModelMapper modelMapper = new ModelMapper();
		Libro lib = modelMapper.map(libroDto, Libro.class);
		return repo.save(lib);
	}
	/*
	 * ----------------------------------------------------------------------------------------------------------
	 */
	@Override
	public Libro editarDetalle(LibroEditarDto libroEditar) {
		boolean option = repo.existsById(libroEditar.getId());
		if(option == true) {
				ModelMapper modelmapper = new ModelMapper();
				Libro detalleedit = modelmapper.map(libroEditar, Libro.class);
				return repo.save(detalleedit);
		}else {
			throw new ModelNotFoundException("El libro que ingreso no se encuentra en el sistema...");
		}
	}
	/*
	 * ----------------------------------------------------------------------------------------------------------
	 */
	@Override
	public void eliminar(Integer id) {
		boolean option = repo.existsById(id);
		if(option == true) {
			repo.deleteById(id);	
		}else {
			throw new ModelNotFoundException("el libro que ingreso no se encuentra en el sistema");
		}
	}

	/*********************METODOS IMPLEMENTADOS SIN UTILIZAR********************/
	@Override
	public Libro guardar(Libro object) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void editar(LibroDto objEdit) {
		// TODO Auto-generated method stub
		
	}


	


}
