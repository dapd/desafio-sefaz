package br.diogo.sefaz.dao;

import java.util.List;

import br.diogo.sefaz.entidades.Telefone;
import br.diogo.sefaz.entidades.Usuario;

public class TelefoneDAO extends GenericDAO<Telefone> {
	
	private Telefone telefone;
	public TelefoneDAO(Telefone telefone) {
		super(telefone);
	}
	
	public Integer cadastrar(){
		return (Integer) new GenericDAO<Telefone>(telefone).create();
	}
	
	public void atualizar(){
		new GenericDAO<Telefone>(new Telefone()).saveOrUpdate(telefone);
	}
	
	public Telefone consultarPorCodigo(Integer codTelefone){
		return (Telefone) new GenericDAO<Telefone>(new Telefone())
	    		.findByFieldEq("codTelefone", codTelefone);
	}
	
	public void deletar(Telefone telefone){
		new GenericDAO<Telefone>(new Telefone()).delete(telefone);
	}
	
	public List<Telefone> listarTodos(){
		return new GenericDAO<Telefone>(new Telefone()).findALL();
	}
	
	public List<Telefone> listarPorUsuario(Usuario usuario){
		return new GenericDAO<Telefone>(new Telefone())
	    		.findListByFieldEq("usuario", usuario);
	}
	
}
