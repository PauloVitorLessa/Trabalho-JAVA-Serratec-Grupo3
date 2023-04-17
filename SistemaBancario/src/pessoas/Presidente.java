package pessoas;

import enuns.Cargo;

public class Presidente extends Diretor {

	public Presidente(String nome, String cpf, String senha) {

		super(nome, cpf, senha);
		this.setTipo(Cargo.PRESIDENTE);
	}
}
