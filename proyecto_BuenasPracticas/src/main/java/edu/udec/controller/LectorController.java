package edu.udec.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.udec.entity.Lector;
import edu.udec.intefaces.ILectorService;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@PreAuthorize("hasAuthority('Admin') or hasAuthority('SuperAdmin')")
@RestController
@ApiModel(value="Controller para la clase Lector - Acá Genero los metodos Restful")
@RequestMapping("/lectores")
public class LectorController {
	
	/*
	 * Annotation that allows the injection to access getters y setters
 	 */
	@Autowired
	ILectorService lectorService;
	
	/*
	 * ----------------------------------------------------------------------------------------------------------
	 */
	@PreAuthorize("hasAuthority('Invitado') or hasAuthority('Admin') or hasAuthority('SuperAdmin')")
	@GetMapping("/listarPorId/{Id}")
	@ApiOperation(value="Metodo Listar que proporciona los datos de un autor por medio "
			+ " del envio del parametro ID")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Lista encontrada Correctamente"),
			@ApiResponse(code = 401, message = "Necesita algun inicio de sesion"),
			@ApiResponse(code = 403, message = "Contraseña Invalida"),
			@ApiResponse(code = 404, message = "No se encuentra disponible una lista con ese nombre")
			
	})
	public ResponseEntity<Lector> listarEstudiantePorNombre(@Valid @PathVariable Integer Id){
		Lector lector = lectorService.listarPorId(Id);
			return new ResponseEntity<Lector>(lector, HttpStatus.OK);

	}
	/*
	 * ----------------------------------------------------------------------------------------------------------
	 */
	@PreAuthorize("hasAuthority('Invitado') or hasAuthority('Admin') or hasAuthority('SuperAdmin')")
	@GetMapping("/listarTodosPageable/{page}/{size}")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Lista encontrada Correctamente"),
			@ApiResponse(code = 204, message = "Lista Vacia"),
			@ApiResponse(code = 403, message = "Contraseña Invalida"),
			@ApiResponse(code = 404, message = "No se encuentra disponible una lista")
			
	})
	@ApiOperation(value="Metodo Listar que proporciona una lista"
			+ " completa de datos existentes", response=List.class)
	public ResponseEntity<Page<Lector>> listarPageable(@PathVariable int page, @PathVariable int size) throws Exception{
		Page<Lector> lector = lectorService.listarPaginado(page, size);
		return new ResponseEntity<Page<Lector>>(lector, HttpStatus.OK);

	}	
	/*
	 * ----------------------------------------------------------------------------------------------------------
	 */
	@PostMapping("/guardar")
	@ApiOperation(value="Metodo Guardar que proporciona la creacion"
			+ " para un nuevo estudiante segun los parametros solicitados")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Lista encontrada Correctamente"),
			@ApiResponse(code = 201, message = "Usuario Agregado Correctamente"),
			@ApiResponse(code = 401, message = "Necesita algun inicio de sesion"),
			@ApiResponse(code = 403, message = "Contraseña Invalida"),
			@ApiResponse(code = 404, message = "No se encuentra disponible una lista")			
	}) 
	public ResponseEntity<Lector> guardarEstudiante(@Valid @RequestBody Lector lector) {
		Lector lectorNuevo = lectorService.guardar(lector);
		HttpHeaders header = new HttpHeaders();
		header.add("Custome-Header", "Estudiante Guardado Con exito");
		return new ResponseEntity<Lector>(lectorNuevo, header, HttpStatus.CREATED);
	}
	/*
	 * ----------------------------------------------------------------------------------------------------------
	 */
	@PutMapping("/editar")
	@ApiOperation(value="Metodo Editar que permite la edicion"
			+ " de un campo, siendo parametros in editables nombre y apellido")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Lista encontrada Correctamente"),
			@ApiResponse(code = 201, message = "Usuario Agregado Correctamente"),
			@ApiResponse(code = 401, message = "Necesita algun inicio de sesion"),
			@ApiResponse(code = 403, message = "Contraseña Invalida"),
			@ApiResponse(code = 404, message = "No se encuentra disponible una lista")			
	})
	public ResponseEntity<Object> editarPersona(@RequestBody Lector lector) {
		lectorService.editar(lector);
		HttpHeaders header = new HttpHeaders();
		header.add("Custome-Header", "Lector actualizado con exito");
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
		lectorService.eliminar(id);
		return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
	}
}
