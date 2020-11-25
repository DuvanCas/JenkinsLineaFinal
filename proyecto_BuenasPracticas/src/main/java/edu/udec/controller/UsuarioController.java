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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.udec.entity.Usuario;
import edu.udec.intefaces.IUsuarioService;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@PreAuthorize("hasAuthority('Admin') or hasAuthority('SuperAdmin')")
@RestController
@ApiModel(value="Controller para la clase Lector - Acá Genero los metodos Restful")
@RequestMapping("/usuarios")
public class UsuarioController {
	
	/*
	 * Annotation that allows the injection to access getters y setters
 	 */
	@Autowired
	IUsuarioService usuarioService;
	
	/*
	 * ----------------------------------------------------------------------------------------------------------
	 */	
	@GetMapping("/listarPorId/{Id}")
	@ApiOperation(value="Metodo Listar que proporciona los datos de un autor por medio "
			+ " del envio del parametro ID")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Lista encontrada Correctamente"),
			@ApiResponse(code = 401, message = "Necesita algun inicio de sesion"),
			@ApiResponse(code = 403, message = "Contraseña Invalida"),
			@ApiResponse(code = 404, message = "No se encuentra disponible una lista con ese nombre")
			
	})
	public ResponseEntity<Usuario> listarEstudiantePorNombre(@Valid @PathVariable Integer Id){
		Usuario usuario = usuarioService.listarPorId(Id);
			return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);

	}
	/*
	 * ----------------------------------------------------------------------------------------------------------
	 */
	@GetMapping("/listarTodosPageable/{page}/{size}")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Lista encontrada Correctamente"),
			@ApiResponse(code = 204, message = "Lista Vacia"),
			@ApiResponse(code = 403, message = "Contraseña Invalida"),
			@ApiResponse(code = 404, message = "No se encuentra disponible una lista")
			
	})
	@ApiOperation(value="Metodo Listar que proporciona una lista"
			+ " completa de datos existentes", response=List.class)
	public ResponseEntity<Page<Usuario>> listarPageable(@PathVariable int page, @PathVariable int size) throws Exception{
		Page<Usuario> usuario = usuarioService.listarPaginado(page, size);
		return new ResponseEntity<Page<Usuario>>(usuario, HttpStatus.OK);

	}	
	/*
	 * ----------------------------------------------------------------------------------------------------------
	 */
	@PreAuthorize("hasAuthority('SuperAdmin')")
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
	public ResponseEntity<Usuario> guardarEstudiante(@Valid @RequestBody Usuario usuario) {
		Usuario usuarioNuevo = usuarioService.guardar(usuario);
		HttpHeaders header = new HttpHeaders();
		header.add("Custome-Header", "Estudiante Guardado Con exito");
		return new ResponseEntity<Usuario>(usuarioNuevo, header, HttpStatus.CREATED);
	}
	/*
	 * ----------------------------------------------------------------------------------------------------------
	 */
	@PreAuthorize("hasAuthority('SuperAdmin')")
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
		usuarioService.eliminar(id);
		return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
	}

}
