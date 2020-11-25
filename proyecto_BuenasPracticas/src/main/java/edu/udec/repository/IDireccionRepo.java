package edu.udec.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import edu.udec.entity.Direccion;

public interface IDireccionRepo extends JpaRepository<Direccion, Integer>{
	
	@Transactional
	@Modifying
	@Query(value = "update public.direccion set barrio =:barrio , descripcion =:desc where autor_id = :id", nativeQuery = true)
	public void EditarDireccion(@Param("barrio")String barrio, @Param("desc")String desc, @Param("id")Integer id);
}
