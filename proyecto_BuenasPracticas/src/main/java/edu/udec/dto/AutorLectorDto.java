package edu.udec.dto;

import java.io.Serializable;

import edu.udec.entity.Autor;
import edu.udec.entity.Lector;

public class AutorLectorDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private Autor autor;

	private Lector lector;
	
	private String infoAdicional;

	
	public Autor getAutor() {
		return autor;
	}

	public void setAutor(Autor autor) {
		this.autor = autor;
	}

	public Lector getLector() {
		return lector;
	}

	public void setLector(Lector lector) {
		this.lector = lector;
	}

	public String getInfoAdicional() {
		return infoAdicional;
	}

	public void setInfoAdicional(String infoAdicional) {
		this.infoAdicional = infoAdicional;
	}

}
