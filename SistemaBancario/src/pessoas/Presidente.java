package pessoas;

import enuns.Cargo;
import listas.Listas;
import maps.Maps;

public class Presidente extends Diretor {

	public Presidente(String nome, String cpf, String senha) {

		super(nome, cpf, senha);
		this.setTipo(Cargo.PRESIDENTE);
	}
	
public boolean cadastraDiretor(String nome, String CPF, String senha) {
		
		if(!Maps.mapCpfPessoa.containsKey(CPF)) {
			
			Pessoa pessoa = new Diretor(nome, CPF, senha);
			Listas.pessoa.add(pessoa);
			Maps.mapCpfPessoa.put(CPF, pessoa);
			return true;
		}
		
		return false;
	}
}
