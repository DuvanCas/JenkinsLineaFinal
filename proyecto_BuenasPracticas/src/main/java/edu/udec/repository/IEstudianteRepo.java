package edu.udec.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import edu.udec.entity.Estudiante;


@Repository
public interface IEstudianteRepo extends JpaRepository<Estudiante, Integer>{
	
	@Transactional(readOnly = true)
	Optional<Estudiante> findByNombreEstudianteAndColegioEstudiante(String nombreEstudiante, String colegio);
	
	//@Query("SELECT p FROM estudiante p WHERE UPPER(p.nombre) LIKE  %?#{[0].toUpperCase()}%")
	@Query("SELECT p FROM Estudiante p WHERE upper(p.nombreEstudiante) = upper(?1)")
	public Page<Estudiante> findByNombreEstudiante(@Param("nombre") String nombre, Pageable pageable);
}
