package edu.udec.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import edu.udec.dto.ErrorDto;

@ControllerAdvice
@RestController
public class MethodArgumentTypeMismatchExceptionHandler {
	@org.springframework.web.bind.annotation.ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public final ResponseEntity<ErrorDto>HandlerTypeMismatchException(Exception ex, WebRequest request){
		HttpStatus status = HttpStatus.METHOD_NOT_ALLOWED;
		ErrorDto error = new ErrorDto(String.valueOf(status.value()), status.name(), "Metodo Invocado Incorrecto....Intentelo Nuevamente",
				request.getDescription(false));
		return new ResponseEntity<ErrorDto>(error, status);
	}
}
