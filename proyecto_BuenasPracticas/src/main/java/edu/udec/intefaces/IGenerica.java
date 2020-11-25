package edu.udec.intefaces;

import org.springframework.data.domain.Page;

/**
 * 
 * @author duvan
 *
 * @param <E> Class Entity origin
 * @param <E2> Class Entity to edit
 * @param <E3> Param id_object
 * @param <E4> Param to page
 * @param <E5> Param to size page
 */
public interface IGenerica <E, E2, E3, E4, E5>{
	
	 public default Page<E> listarPaginado(E4 page, E5 size){return null;};
	 
	 public default E listarPorId(E3 id_object){return null;};
	 
	 public E guardar(E object);
	 
	 public void editar(E2 objEdit);
	 
	 public void eliminar(E3 id_object);
}
