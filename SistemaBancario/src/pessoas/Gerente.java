package pessoas;

import enuns.Cargo;

public class Gerente extends Funcionario{

	public Gerente(String nome, String cpf, String senha, Cargo tipo) {
		super(nome, cpf, senha, Cargo.GERENTE);
	}
}
