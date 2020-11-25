package edu.udec.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.udec.dto.EstudianteDto;
import edu.udec.entity.Estudiante;
import edu.udec.intefaces.IEstudianteService;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@ApiModel(value="Controller para la clase Estudiante - Acá Genero los metodos Restful")
@RequestMapping("/estudiantes")
public class EstudianteController {

	
	/*
	 * Annotation that allows the injection to access getters y setters
 	 */
	@Autowired
	IEstudianteService estudianteService;

	@GetMapping("/listarNombre/{Id}")
	@ApiOperation(value="Metodo Listar que proporciona los datos de un estudiante por medio "
			+ " del envio del parametro NOMBRE", response=EstudianteDto.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Lista encontrada Correctamente"),
			@ApiResponse(code = 401, message = "Necesita algun inicio de sesion"),
			@ApiResponse(code = 403, message = "Contraseña Invalida"),
			@ApiResponse(code = 404, message = "No se encuentra disponible una lista con ese nombre")
			
	})
	public ResponseEntity<Estudiante> listarEstudiantePorNombre(@Valid @PathVariable Integer Id){
		Estudiante estudiante = estudianteService.listarPorId(Id);
			return new ResponseEntity<Estudiante>(estudiante, HttpStatus.OK);

	}
	/*
	 * ----------------------------------------------------------------------------------------------------------
	 */
	@GetMapping("/listar")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Lista encontrada Correctamente"),
			@ApiResponse(code = 204, message = "Lista Vacia"),
			@ApiResponse(code = 403, message = "Contraseña Invalida"),
			@ApiResponse(code = 404, message = "No se encuentra disponible una lista")
			
	})
	@ApiOperation(value="Metodo Listar que proporciona una lista"
			+ " completa de datos existentes", response=List.class)
	public ResponseEntity<List<Estudiante>> listarEstudiantesCompletos() throws Exception{
		List<Estudiante> estudiante = estudianteService.ObtenerListaEstudiante();
		return new ResponseEntity<List<Estudiante>>(estudiante, HttpStatus.OK);

	}
	/*
	 * ----------------------------------------------------------------------------------------------------------
	 */
	@GetMapping("/listarPageable/{page}/{size}")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Lista encontrada Correctamente"),
			@ApiResponse(code = 204, message = "Lista Vacia"),
			@ApiResponse(code = 403, message = "Contraseña Invalida"),
			@ApiResponse(code = 404, message = "No se encuentra disponible una lista")
			
	})
	@ApiOperation(value="Metodo Listar que proporciona una lista"
			+ " completa de datos existentes", response=List.class)
	public ResponseEntity<Page<Estudiante>> listarPageable(@PathVariable int page, @PathVariable int size) throws Exception{
		Page<Estudiante> estudiante = estudianteService.listarPaginado(page, size);
		return new ResponseEntity<Page<Estudiante>>(estudiante, HttpStatus.OK);

	}
	/*
	 * ----------------------------------------------------------------------------------------------------------
	 */
	@GetMapping("/listarPageablePorApellido/{page}/{size}/{apellido}")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Lista encontrada Correctamente"),
			@ApiResponse(code = 204, message = "Lista Vacia"),
			@ApiResponse(code = 403, message = "Contraseña Invalida"),
			@ApiResponse(code = 404, message = "No se encuentra disponible una lista")
			
	})
	@ApiOperation(value="Metodo Listar que proporciona una lista"
			+ " completa de datos existentes", response=List.class)
	public ResponseEntity<Page<Estudiante>> listarPageablePorApellido(@PathVariable int page, @PathVariable int size, @PathVariable String apellido) throws Exception{
		Page<Estudiante> estudiante = estudianteService.listarPorNombre(page, size, apellido);
		return new ResponseEntity<Page<Estudiante>>(estudiante, HttpStatus.OK);

	}
	/*
	 * ----------------------------------------------------------------------------------------------------------
	 */
	@PostMapping("/guardar")
	@ApiOperation(value="Metodo Guardar que proporciona la creacion"
			+ " para un nuevo estudiante segun los parametros solicitados", response=EstudianteDto.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Lista encontrada Correctamente"),
			@ApiResponse(code = 201, message = "Usuario Agregado Correctamente"),
			@ApiResponse(code = 401, message = "Necesita algun inicio de sesion"),
			@ApiResponse(code = 403, message = "Contraseña Invalida"),
			@ApiResponse(code = 404, message = "No se encuentra disponible una lista")			
	}) 
	public ResponseEntity<Estudiante> guardarEstudiante(@Valid @RequestBody Estudiante estudiante) {
		Estudiante estudianteNuevo = estudianteService.guardar(estudiante);
		HttpHeaders header = new HttpHeaders();
		header.add("Custome-Header", "Estudiante Guardado Con exito");
		return new ResponseEntity<Estudiante>(estudianteNuevo, header, HttpStatus.CREATED);
	}
	/*
	 * ----------------------------------------------------------------------------------------------------------
	 */
	@PutMapping("/editar")
	@ApiOperation(value="Metodo Editar que permite la edicion"
			+ " de un campo, siendo parametros in editables nombre y apellido", response=EstudianteDto.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Lista encontrada Correctamente"),
			@ApiResponse(code = 201, message = "Usuario Agregado Correctamente"),
			@ApiResponse(code = 401, message = "Necesita algun inicio de sesion"),
			@ApiResponse(code = 403, message = "Contraseña Invalida"),
			@ApiResponse(code = 404, message = "No se encuentra disponible una lista")			
	})
	public ResponseEntity<Object> editarPersona(@RequestBody Estudiante estudiante) {
		estudianteService.editar(estudiante);
		HttpHeaders header = new HttpHeaders();
		header.add("Custome-Header", "Estudiante actualizado con exito");
		return new ResponseEntity<Object>(header, HttpStatus.OK);
	}
	/*
	 * ----------------------------------------------------------------------------------------------------------
	 */
	@DeleteMapping("/borrar/{id}")
	@ApiOperation(value="Metodo Borrar que permite eliminar un"
			+ " estudiante enviando como parametro el id")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Lista encontrada Correctamente"),
			@ApiResponse(code = 204, message = "Usuario Eliminado Correctamente"),
			@ApiResponse(code = 401, message = "Necesita algun inicio de sesion"),
			@ApiResponse(code = 403, message = "Contraseña Invalida"),
			@ApiResponse(code = 404, message = "No se encuentra disponible una lista")		
	})
	public ResponseEntity<Object> borrarPersona(@PathVariable Integer id) {
		estudianteService.eliminar(id);
		return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
	}
	/*
	 * ----------------------------------------------------------------------------------------------------------
	 */
	@GetMapping("/listarConsulta/{nombre}/{colegio}")
	@ApiOperation(value="Metodo Listar que proporciona los datos de un estudiante por medio "
			+ " del envio del parametro NOMBRE y COLEGIO", response=EstudianteDto.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Lista encontrada Correctamente"),
			@ApiResponse(code = 401, message = "Necesita algun inicio de sesion"),
			@ApiResponse(code = 403, message = "Contraseña Invalida"),
			@ApiResponse(code = 404, message = "No se encuentra disponible una lista con ese nombre")			
	})
	public ResponseEntity<EstudianteDto> listarConsultaJpql(@Valid @PathVariable String nombre, @PathVariable String colegio){
		EstudianteDto estudiante = estudianteService.findByNombreEstudianteAndColegioEstudiante(nombre, colegio);
			return new ResponseEntity<EstudianteDto>(estudiante, HttpStatus.OK);

	}
	
}
