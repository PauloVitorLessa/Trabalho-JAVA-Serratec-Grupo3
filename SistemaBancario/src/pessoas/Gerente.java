package pessoas;

import agencias.Agencia;
import enuns.Cargo;

public class Gerente extends Funcionario{
	
	private Agencia agencia;
	public Gerente(String nome, String cpf, String senha, Agencia agencia) {
		super(nome, cpf, senha, Cargo.GERENTE);
		this.agencia = agencia;
	}
	
	public int getContasAgencia() {
		return this.agencia.getContas().size();
	}
	
}
