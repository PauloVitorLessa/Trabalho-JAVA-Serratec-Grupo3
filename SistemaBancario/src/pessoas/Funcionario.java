package pessoas;

import enuns.Cargo;

public abstract class Funcionario extends Pessoa {

	public Funcionario(String nome, String cpf, String senha, Cargo tipo) {
		super(nome, cpf, senha, tipo);
	}
//=====================================================================================
	
}
