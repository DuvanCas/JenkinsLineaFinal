package edu.udec.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import edu.udec.entity.Usuario;

@Repository
public interface IUsuarioRepo extends JpaRepository<Usuario, Integer>{

	Usuario findOneByNick(String nick);
	
	@Query(value = "select count(*) from public.usuadio where cedula = :cedula", nativeQuery = true)
	Object validarExistenciaCedula(@Param("cedula")String cedula);
	
	@Query(value = "select count(*) from public.usuadio where nick = :nick", nativeQuery = true)
	Object validarExistenciaNick(@Param("nick")String nick);
}
