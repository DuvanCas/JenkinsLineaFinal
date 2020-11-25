package edu.udec.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import edu.udec.entity.Libro;

@Repository
public interface ILibroRepo extends JpaRepository<Libro, Integer>{

	@Query("SELECT count(*) FROM Libro ex WHERE ex.autor = ?1")
	public Integer contar(@Param("id_autor") Integer id_autor);
	
	@Transactional
	@Modifying
	@Query("DELETE FROM Libro ce WHERE ce.autor = ?1")
	public void eliminarRegistro(@Param("id_autor") Integer id_autor);
}
