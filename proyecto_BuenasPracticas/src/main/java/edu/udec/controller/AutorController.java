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

import edu.udec.dto.AutorLectorDto;
import edu.udec.entity.Autor;
import edu.udec.entity.AutorLector;
import edu.udec.entity.Direccion;
import edu.udec.intefaces.IAutorService;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@PreAuthorize("hasAuthority('Admin') or hasAuthority('SuperAdmin')")
@RestController
@ApiModel(value="Controller para la clase Autor - Acá Genero los metodos Restful")
@RequestMapping("/autores")
public class AutorController {
	
	/*
	 * Annotation that allows the injection to access getters y setters
 	 */
	@Autowired
	IAutorService autorService;
	
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
	public ResponseEntity<Autor> listarEstudiantePorNombre(@Valid @PathVariable Integer Id){
		Autor autor = autorService.listarPorId(Id);
			return new ResponseEntity<Autor>(autor, HttpStatus.OK);

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
	public ResponseEntity<Page<Autor>> listarPageable(@PathVariable int page, @PathVariable int size) throws Exception{
		Page<Autor> autor = autorService.listarPaginado(page, size);
		return new ResponseEntity<Page<Autor>>(autor, HttpStatus.OK);

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
	public ResponseEntity<Autor> guardarEstudiante(@Valid @RequestBody Autor autor) {
		Autor autorNuevo = autorService.guardar(autor);
		HttpHeaders header = new HttpHeaders();
		header.add("Custome-Header", "Estudiante Guardado Con exito");
		return new ResponseEntity<Autor>(autorNuevo, header, HttpStatus.CREATED);
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
	public ResponseEntity<Object> editarPersona(@RequestBody Autor autor) {
		autorService.editar(autor);
		HttpHeaders header = new HttpHeaders();
		header.add("Custome-Header", "Autor actualizado con exito");
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
		autorService.eliminar(id);
		return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
	}
	/*
	 * ----------------------------------------------------------------------------------------------------------
	 */
	@DeleteMapping("/borrar2/{id}")
	@ApiOperation(value="Metodo Borrar que permite eliminar un"
			+ " estudiante enviando como parametro el id")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Lista encontrada Correctamente"),
			@ApiResponse(code = 204, message = "Usuario Eliminado Correctamente"),
			@ApiResponse(code = 401, message = "Necesita algun inicio de sesion"),
			@ApiResponse(code = 403, message = "Contraseña Invalida"),
			@ApiResponse(code = 404, message = "No se encuentra disponible una lista")		
	})
	public ResponseEntity<Object> borrarPersona2(@PathVariable Integer id) {
		autorService.EliminarAutor2(id);
		return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
	}
	/*
	 * ----------------------------------------------------------------------------------------------------------
	 */
	@DeleteMapping("/borrar3/{id}")
	@ApiOperation(value="Metodo Borrar que permite eliminar un"
			+ " estudiante enviando como parametro el id")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Lista encontrada Correctamente"),
			@ApiResponse(code = 204, message = "Usuario Eliminado Correctamente"),
			@ApiResponse(code = 401, message = "Necesita algun inicio de sesion"),
			@ApiResponse(code = 403, message = "Contraseña Invalida"),
			@ApiResponse(code = 404, message = "No se encuentra disponible una lista")		
	})
	public ResponseEntity<Object> borrarPersona3(@PathVariable Integer id) {
		autorService.EliminarAutor3(id);
		return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
	}	
	/*
	 * ----------------------------------------------------------------------------------------------------------
	 */
	@PutMapping("/editarDireccion1")
	@ApiOperation(value="Metodo Editar que permite la edicion"
			+ " de un campo, siendo parametros in editables nombre y apellido")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Lista encontrada Correctamente"),
			@ApiResponse(code = 201, message = "Usuario Agregado Correctamente"),
			@ApiResponse(code = 401, message = "Necesita algun inicio de sesion"),
			@ApiResponse(code = 403, message = "Contraseña Invalida"),
			@ApiResponse(code = 404, message = "No se encuentra disponible una lista")			
	})
	public ResponseEntity<Object> editarDireccion1(@RequestBody Direccion direccion) {
		autorService.EditarOpcion1(direccion);
		HttpHeaders header = new HttpHeaders();
		header.add("Custome-Header", "Direccion actualizada con exito");
		return new ResponseEntity<Object>(header, HttpStatus.OK);
	}
	/*
	 * ----------------------------------------------------------------------------------------------------------
	 */
	@PutMapping("/editarDireccion2")
	@ApiOperation(value="Metodo Editar que permite la edicion"
			+ " de un campo, siendo parametros in editables nombre y apellido")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Lista encontrada Correctamente"),
			@ApiResponse(code = 201, message = "Usuario Agregado Correctamente"),
			@ApiResponse(code = 401, message = "Necesita algun inicio de sesion"),
			@ApiResponse(code = 403, message = "Contraseña Invalida"),
			@ApiResponse(code = 404, message = "No se encuentra disponible una lista")			
	})
	public ResponseEntity<Object> editarDireccion2(@RequestBody Direccion direccion) {
		autorService.EditarOpcion2(direccion);
		HttpHeaders header = new HttpHeaders();
		header.add("Custome-Header", "Direccion actualizada con exito");
		return new ResponseEntity<Object>(header, HttpStatus.OK);
	}
	/*
	 * ----------------------------------------------------------------------------------------------------------
	 */
	@PostMapping("/asociarLector")
	@ApiOperation(value="Metodo Guardar que proporciona la creacion"
			+ " para un nuevo estudiante segun los parametros solicitados")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Lista encontrada Correctamente"),
			@ApiResponse(code = 201, message = "Usuario Agregado Correctamente"),
			@ApiResponse(code = 401, message = "Necesita algun inicio de sesion"),
			@ApiResponse(code = 403, message = "Contraseña Invalida"),
			@ApiResponse(code = 404, message = "No se encuentra disponible una lista")			
	}) 
	public ResponseEntity<Object> guardarAutorLector(@RequestBody AutorLectorDto autor) {
		autorService.guardarAutorLector(autor);
		HttpHeaders header = new HttpHeaders();
		header.add("Custome-Header", "Asociasion Guardada Con exito");
		return new ResponseEntity<Object>(header, HttpStatus.CREATED);
	}
	/*
	 * ----------------------------------------------------------------------------------------------------------
	 */	
	@GetMapping("/listarAutorLector/{idAutor}")
	@ApiOperation(value="Metodo Listar que proporciona los datos de un autor por medio "
			+ " del envio del parametro ID")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Lista encontrada Correctamente"),
			@ApiResponse(code = 401, message = "Necesita algun inicio de sesion"),
			@ApiResponse(code = 403, message = "Contraseña Invalida"),
			@ApiResponse(code = 404, message = "No se encuentra disponible una lista con ese nombre")
			
	})
	public ResponseEntity<List<AutorLector>> listarAutorLector(@PathVariable Integer idAutor){
		List<AutorLector> autorLector= autorService.listarLectorporAutor(idAutor);
			return new ResponseEntity<List<AutorLector>>(autorLector, HttpStatus.OK);

	}
	/*
	 * ----------------------------------------------------------------------------------------------------------
	 */
	@PostMapping("/asociarLectorLista")
	@ApiOperation(value="Metodo Guardar que proporciona la creacion"
			+ " para un nuevo estudiante segun los parametros solicitados")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Lista encontrada Correctamente"),
			@ApiResponse(code = 201, message = "Usuario Agregado Correctamente"),
			@ApiResponse(code = 401, message = "Necesita algun inicio de sesion"),
			@ApiResponse(code = 403, message = "Contraseña Invalida"),
			@ApiResponse(code = 404, message = "No se encuentra disponible una lista")			
	}) 
	public ResponseEntity<Object> guardarAutorLectorLista(@RequestBody List<AutorLectorDto> autor) {
		autorService.guardarAutorLector(autor);
		HttpHeaders header = new HttpHeaders();
		header.add("Custome-Header", "Asociasion Guardada Con exito");
		return new ResponseEntity<Object>(header, HttpStatus.CREATED);
	}


}
