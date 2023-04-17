package pessoas;

import enuns.Cargo;

public class Diretor extends Funcionario{
	
	public Diretor(String nome, String cpf, String senha) {
		super(nome, cpf, senha, Cargo.DIRETOR);	
	}

}
