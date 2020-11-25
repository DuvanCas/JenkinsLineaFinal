package edu.udec.exception;

public class NotContentException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;
	
	public NotContentException(String mensaje) {		
		super(mensaje);
	}
}
