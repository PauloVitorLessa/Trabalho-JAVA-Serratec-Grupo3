package pessoas;

import enuns.Cargo;
import listas.Listas;
import maps.Maps;

public class Diretor extends Funcionario{
	
	public Diretor(String nome, String cpf, String senha) {
		super(nome, cpf, senha, Cargo.DIRETOR);	
	}

 public boolean cadastraGerente(String nome, String CPF, String senha, int numeroAgencia) {
	 
		if(!Maps.mapCpfPessoa.containsKey(CPF)) {
			if(Maps.mapNumeroAgencia.containsKey(numeroAgencia)) {
				if(!Maps.mapAgenciaGerente.containsKey(numeroAgencia)) {
				Pessoa pessoa = new Gerente(nome, CPF, senha, Maps.mapNumeroAgencia.get(numeroAgencia));
				Listas.pessoa.add(pessoa);
				Maps.mapCpfPessoa.put(CPF, pessoa);
				Maps.mapAgenciaGerente.put(numeroAgencia, CPF);
				System.out.println("Deu bom!!");
				return true;
				}
			}	
		}	
		System.out.println("Deu ruim!");
		return false;
	}
}
