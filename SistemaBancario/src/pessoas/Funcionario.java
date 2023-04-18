package pessoas;

import enuns.Cargo;
import listas.Listas;
import maps.Maps;

public abstract class Funcionario extends Pessoa {

	public Funcionario(String nome, String cpf, String senha, Cargo tipo) {
		super(nome, cpf, senha, tipo);
	}
//=====================================================================================
public boolean cadastraCliente(String nome, String CPF, String senha) {
		
		if(!Maps.mapCpfPessoa.containsKey(CPF)) {
			
			Pessoa pessoa = new Cliente(nome, CPF, senha);
			Listas.pessoa.add(pessoa);
			Maps.mapCpfPessoa.put(CPF, pessoa);
			return true;
		}
		
		return false;
	}
	
}
