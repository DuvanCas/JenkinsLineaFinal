package edu.udec.exception;

public class ConflictException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public ConflictException(String mensaje) {		
		super(mensaje);
	}

}
