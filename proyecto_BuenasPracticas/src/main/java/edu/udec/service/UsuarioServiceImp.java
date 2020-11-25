package edu.udec.service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import edu.udec.entity.Lector;
import edu.udec.entity.Usuario;
import edu.udec.exception.ConflictException;
import edu.udec.exception.ModelNotFoundException;
import edu.udec.intefaces.IUsuarioService;
import edu.udec.repository.IUsuarioRepo;

@Service
public class UsuarioServiceImp implements IUsuarioService, UserDetailsService{
	
	@Autowired
	private IUsuarioRepo repo;
	
	@Autowired
	private BCryptPasswordEncoder encriptar;
	/*
	 * ----------------------------------------------------------------------------------------------------------
	 */
	@Override
	public Usuario listarPorId(Integer id) {
		Usuario usuario = repo.findById(id).orElseThrow(
				() -> new ModelNotFoundException("Usuario no encontrado"));
		return usuario;	
	}
	/*
	 * ----------------------------------------------------------------------------------------------------------
	 */
	@Override
	public Page<Usuario> listarPaginado(Integer page, Integer size) {
		Page<Usuario> listaPaginaUsuario = repo.findAll(PageRequest.of(page, size));
		return listaPaginaUsuario;
	}
	/*
	 * ----------------------------------------------------------------------------------------------------------
	 */
	@Override
	public Usuario guardar(Usuario object) {
		
		String clave = encriptar.encode(object.getContrasena());
		object.setContrasena(clave);
		BigInteger contador = (BigInteger) repo.validarExistenciaCedula(object.getCedula());
		if(contador.intValue()>0)
			throw new ConflictException("Usuario ingresado ya esta registrado con esa cedula...");
		BigInteger contador1 = (BigInteger) repo.validarExistenciaNick(object.getNick());
		if(contador1.intValue()>0)
			throw new ConflictException("Usuario ingresado ya esta registrado con ese Nick...");
		if(object.getRol().getIdRol() != 1 & object.getRol().getIdRol() != 2)
			throw new ConflictException("El rol ingresado no existe en el sistema...");		
		return repo.save(object);
	}
	/*
	 * ----------------------------------------------------------------------------------------------------------
	 */
	@Override
	public void editar(Usuario objEdit) {
		// TODO Auto-generated method stub
		
	}
	/*
	 * ----------------------------------------------------------------------------------------------------------
	 */
	@Override
	public void eliminar(Integer id_object) {
		Usuario usuario = repo.findById(id_object).orElseThrow(
				() -> new ModelNotFoundException("Usuario que ingreso no se encuentra en el sistema"));
		repo.delete(usuario);
		
	}
	/*
	 * ----------------------------------------------------------------------------------------------------------
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario = repo.findOneByNick(username);
		if(usuario == null)
			throw new ModelNotFoundException("Usuario no encontrado");		
		List<GrantedAuthority> roles = new ArrayList<>();
		roles.add(new SimpleGrantedAuthority(usuario.getRol().getNombre()));		
		UserDetails ud = new User(usuario.getNick(), usuario.getContrasena(), roles);		
		return ud;
	}
}
