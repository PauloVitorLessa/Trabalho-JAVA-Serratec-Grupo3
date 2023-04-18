package pessoas;

import contas.Conta;
import contas.ContaCorrente;
import contas.ContaPoupanca;
import enuns.Cargo;
import listas.Listas;
import maps.Maps;

public abstract class Funcionario extends Pessoa {

	public Funcionario(String nome, String cpf, String senha, Cargo tipo) {
		super(nome, cpf, senha, tipo);
	}
//=====================================================================================
public Pessoa cadastraCliente(String nome, String CPF, String senha) {
		
		if(!Maps.mapCpfPessoa.containsKey(CPF)) {
			
			Pessoa pessoa = new Cliente(nome, CPF, senha);
			Listas.pessoa.add(pessoa);
			Maps.mapCpfPessoa.put(CPF, pessoa);
			return pessoa ;
		}
		return null;
	}
//=====================================================================================================
	public Conta criarContaCorrente(int numeroAgencia, String CPF) {
		
		if(Maps.mapCpfPessoa.containsKey(CPF)) {
			if(Maps.mapNumeroAgencia.containsKey(numeroAgencia)) {
				Conta contaCorrente = new ContaCorrente(Maps.mapNumeroAgencia.get(numeroAgencia),Maps.mapCpfPessoa.get(CPF));
				Listas.conta.add(contaCorrente);
				Maps.mapNumeroConta.put(contaCorrente.getNumeroConta(),contaCorrente);
				Maps.mapCpfContaCorrente.put(CPF,contaCorrente);
				return contaCorrente;	
			}
		}
		return null;
	}
	public Conta criarContaPoupanca(int numeroAgencia, String CPF) {
		
		if(Maps.mapCpfPessoa.containsKey(CPF)) {
			if(Maps.mapNumeroAgencia.containsKey(numeroAgencia)) {
				Conta contaPoupanca = new ContaPoupanca(Maps.mapNumeroAgencia.get(numeroAgencia),Maps.mapCpfPessoa.get(CPF));
				Listas.conta.add(contaPoupanca);
				Maps.mapNumeroConta.put(contaPoupanca.getNumeroConta(),contaPoupanca);
				Maps.mapCpfContaCorrente.put(CPF,contaPoupanca);
				return contaPoupanca;
			}
		}
		return null;
	}
}

