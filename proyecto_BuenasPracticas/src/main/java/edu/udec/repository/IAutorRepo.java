package edu.udec.repository;

import java.math.BigInteger;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import edu.udec.entity.Autor;

@Repository
public interface IAutorRepo extends JpaRepository<Autor, Integer>{
	
	@Query(value = "select count(*) from public.autor where cedula = :cedula", nativeQuery = true)
	Object validarExistenciaCedula(@Param("cedula")String cedula);
	
	@Query(value = "select count(*) from public.autor where cedula = :cedula and id != :id", nativeQuery = true)
	Object validarExistenciaCedulaEditar(@Param("cedula")String cedula, @Param("id")Integer id);
	
	@Query(value = "select count(c) from Autor c where c.id = :id")
	BigInteger validarExistenciaPorId(@Param("id")Integer id);	
}
