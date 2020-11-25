package edu.udec.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name ="usuadio")
public class Usuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idUsuario;
	
	@Column(name = "cedula", nullable = false, length = 12)
	private String cedula;
	
	@Column(name = "nombre", nullable = false, length = 20)
	private String nombre;
	
	@Column(name = "apellido", nullable = false, length = 20)
	private String apellido;
	
	@Column(name = "nick", nullable = false, length = 20, unique = true)	
	private String nick;
	
	@NotNull(message ="Contraseña es requerida")
	@Column(columnDefinition = "TEXT", name = "contrasena", nullable = false)
	private String contrasena;
	
	@ManyToOne
	@JoinColumn(name = "idRol", foreignKey = @ForeignKey(name = "FK_rol"))
	private Rol rol;

	public Integer getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	public Rol getRol() {
		return rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}
	
	
}
