package edu.udec.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import edu.udec.entity.AutorLector;

@Repository
public interface IAutorLectorRepo extends JpaRepository<AutorLector, Integer>{
	
	@Transactional
	@Modifying
	@Query(value = "INSERT INTO autor_lector(id_autor, id_lector, info_adicional) "
			+ "VALUES(:idAutor, :idLector, :infoAdicional)", nativeQuery = true)
	public void guardar(@Param("idAutor") Integer idAutor,@Param("idLector") Integer idLector,@Param("infoAdicional") String infoAdicional);
	
	@Query("from AutorLector al where al.autor.id =:idAutor")
	List<AutorLector> listarLectorporAutor(@Param("idAutor") Integer idAutor);

}
