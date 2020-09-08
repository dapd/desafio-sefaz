package br.diogo.sefaz.dao;

import java.util.List;

import br.diogo.sefaz.entidades.Usuario;

public class UsuarioDAO extends GenericDAO<Usuario> {

	private Usuario usuario;
	public UsuarioDAO(Usuario usuario) {
		super(usuario);
	}
	
	public Integer cadastrar(){
		return (Integer) new GenericDAO<Usuario>(usuario).create();
	}
	
	public void atualizar(){
		new GenericDAO<Usuario>(new Usuario()).update(usuario);
	}
	
	public Usuario consultarPorEmail(String email){
		return (Usuario) new GenericDAO<Usuario>(new Usuario())
	    		.findByFieldEq("email", email);
	}
	
	public Usuario consultarPorCodigo(Integer codUsuario){
		return (Usuario) new GenericDAO<Usuario>(new Usuario())
	    		.findByFieldEq("codUsuario", codUsuario);
	}
	
	public void deletar(Usuario usuario){
		new GenericDAO<Usuario>(new Usuario()).delete(usuario);
	}
	
	public List<Usuario> listarTodos(){
		return new GenericDAO<Usuario>(new Usuario()).findALL();
	}

}
