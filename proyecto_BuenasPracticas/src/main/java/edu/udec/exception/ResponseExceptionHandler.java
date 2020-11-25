package edu.udec.exception;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import edu.udec.dto.ErrorDto;

@ControllerAdvice
@RestController	
public class ResponseExceptionHandler extends ResponseEntityExceptionHandler{

	/*----------------------------------Excepciones Personalizadas-------------------------------------*/
	
	@ExceptionHandler(ModelNotFoundException.class)
	public final ResponseEntity<ErrorDto> ModelNotFoundExceptionHandler(ModelNotFoundException ex,
			WebRequest request){
		HttpStatus status = HttpStatus.NOT_FOUND;
		ErrorDto error = new ErrorDto(String.valueOf(status.value()), status.name(), ex.getMessage(),
				request.getDescription(false));
		ex.printStackTrace();
		return new ResponseEntity<ErrorDto>(error, status);
	}
	@ExceptionHandler(ConflictException.class)
	public final ResponseEntity<ErrorDto> ConflictExceptionHandler(ConflictException ex,
			WebRequest request){
		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
		ErrorDto error = new ErrorDto(String.valueOf(status.value()), status.name(), ex.getMessage(),
				request.getDescription(false));
		ex.printStackTrace();
		return new ResponseEntity<ErrorDto>(error, status);
	}
	@ExceptionHandler(ArgumentRequiredException.class)
	public final ResponseEntity<ErrorDto> ArgumentRequiredExceptionHandler(ArgumentRequiredException ex,
			WebRequest request){
		HttpStatus status = HttpStatus.BAD_REQUEST;
		ErrorDto error = new ErrorDto(String.valueOf(status.value()), status.name(), ex.getMessage(),
				request.getDescription(false));
		ex.printStackTrace();
		return new ResponseEntity<ErrorDto>(error, status);
	}
	@ExceptionHandler(NotContentException.class)
	public final ResponseEntity<ErrorDto> NotContentExceptionHandler(NotContentException ex,
			WebRequest request){
		HttpStatus status = HttpStatus.NO_CONTENT;
		ErrorDto error = new ErrorDto(String.valueOf(status.value()), status.name(), ex.getMessage(),
				request.getDescription(false));
		ex.printStackTrace();
		return new ResponseEntity<ErrorDto>(error, status);
	}
	@ExceptionHandler(DataIntegrityViolationException.class)
	public final ResponseEntity<ErrorDto> DataIntegrityViolationExceptionHandler(DataIntegrityViolationException ex,
			WebRequest request){
		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
		ErrorDto error = new ErrorDto(String.valueOf(status.value()), status.name(), ex.getMessage(),
				request.getDescription(false));
		ex.printStackTrace();
		return new ResponseEntity<ErrorDto>(error, status);
	}
	
	/*----------------------------------Excepciones JAVA-------------------------------------------------*/
	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		ErrorDto error = new ErrorDto(String.valueOf(status.BAD_REQUEST.value()), status.name(), "El Json va mal formado",
				request.getDescription(false));
		ex.printStackTrace();
		return new ResponseEntity<Object>(error, status);
	}
	@Override
	protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		ErrorDto error = new ErrorDto(String.valueOf(status.UNSUPPORTED_MEDIA_TYPE.value()), status.name(), "El cuerpo no es de tipo Json",
				request.getDescription(false));
		ex.printStackTrace();
		return new ResponseEntity<Object>(error, status);
	}
	@Override
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		ErrorDto error = new ErrorDto(String.valueOf(status.METHOD_NOT_ALLOWED.value()), status.name(), "Metodo Invocado Incorrecto....Intentelo Nuevamente",
				request.getDescription(false));
		ex.printStackTrace();
		return new ResponseEntity<Object>(error, status);
	}
	@Override
	protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		ErrorDto error = new ErrorDto(String.valueOf(status.NOT_FOUND.value()), status.name(), "URL invalida....Intentelo Nuevamente",
				request.getDescription(false));
		ex.printStackTrace();
		return new ResponseEntity<Object>(error, status);
	}
	@Override
	protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		ErrorDto error = new ErrorDto(String.valueOf(status.BAD_REQUEST.value()), status.name(), "Parametro Nombre es requerido o es invalido",
				request.getDescription(false));
		ex.printStackTrace();
		return new ResponseEntity<Object>(error, status);
	}
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		ErrorDto error = new ErrorDto(String.valueOf(status.BAD_REQUEST.value()), status.name(), "Faltan Campos Requeridos En El Json enviado...",
				request.getDescription(false));
		ex.printStackTrace();
		return new ResponseEntity<Object>(error, status);
	}
	
	
	
}
