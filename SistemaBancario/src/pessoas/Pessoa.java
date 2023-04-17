package pessoas;

import enuns.Cargo;

public abstract class Pessoa {

	private String nome;
	private String cpf;
	private String senha;
	private Cargo tipo;
	
//========================================================================================
	public Pessoa(String nome, String cpf, String senha ,Cargo tipo) {
		this.nome = nome;
		this.cpf = cpf;
		this.senha = senha;
		this.tipo = tipo;
	}
//=========================================================================================

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Cargo getTipo() {
		return tipo;
	}

	public void setTipo(Cargo tipo) {
		this.tipo = tipo;
	}
	

}
