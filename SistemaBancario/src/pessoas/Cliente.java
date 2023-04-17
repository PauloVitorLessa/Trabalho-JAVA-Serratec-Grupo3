package pessoas;

import enuns.Cargo;

public class Cliente extends Pessoa {

	public Cliente(String nome, String cpf, String senha) {
		super(nome, cpf, senha, Cargo.CLIENTE);

	}
}
