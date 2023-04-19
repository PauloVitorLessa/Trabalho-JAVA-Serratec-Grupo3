package pessoas;

import agencias.Agencia;
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
	public Agencia criarAgencia() {
		Agencia agencia = new Agencia();
		Maps.mapNumeroAgencia.put(agencia.getNumeroAgencia(),agencia);
		Listas.agencia.add(agencia);
		return agencia;
	}
	public boolean criarAgencia(String CPF) {
		
		if(Maps.mapCpfGerente.containsKey(CPF)) {
			Agencia agencia = new Agencia(CPF);
			Listas.agencia.add(agencia);
			Maps.mapNumeroAgencia.put(agencia.getNumeroAgencia(),agencia);
			return true;
		}
		return false;
	}	
}
