package br.diogo.sefaz.entidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "TELEFONE")
public class Telefone implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	@Column(name="COD_TELEFONE")
	private int codTelefone;
	
	@Column(name="DDD")
	private String ddd;
	
	@Column(name="NUMERO")
	private String numero;
	
	@Column(name="TIPO")
	private String tipo;
	
	@ManyToOne 
    @JoinColumn(name = "COD_USUARIO", referencedColumnName = "COD_USUARIO")
    private Usuario usuario;
	
	public Telefone() {}
	
	public Telefone(String ddd, String numero, String tipo, Usuario usuario) {
		super();
		this.ddd = ddd;
		this.numero = numero;
		this.tipo = tipo;
		this.usuario = usuario;
	}

	public int getCodTelefone() {
		return codTelefone;
	}

	public void setCodTelefone(int codTelefone) {
		this.codTelefone = codTelefone;
	}

	public String getDdd() {
		return ddd;
	}

	public void setDdd(String ddd) {
		this.ddd = ddd;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
