package edu.udec.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import edu.udec.entity.Lector;

@Repository
public interface ILectorRepo extends JpaRepository<Lector, Integer>{
	
	@Query(value = "select count(*) from public.lector where cedula = :cedula", nativeQuery = true)
	Object validarExistenciaCedula(@Param("cedula")String cedula);
	
	@Query(value = "select count(*) from public.lector where cedula = :cedula and id != :id", nativeQuery = true)
	Object validarExistenciaCedulaEditar(@Param("cedula")String cedula, @Param("id")Integer id);
}
