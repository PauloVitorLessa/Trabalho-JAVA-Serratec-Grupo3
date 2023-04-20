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
				if(!Maps.mapCpfContaCorrente.containsKey(CPF)) {
					Conta contaCorrente = new ContaCorrente(Maps.mapNumeroAgencia.get(numeroAgencia),Maps.mapCpfPessoa.get(CPF));
					Listas.conta.add(contaCorrente);
					Maps.mapNumeroConta.put(contaCorrente.getNumeroConta(),contaCorrente);
					Maps.mapCpfContaCorrente.put(CPF,contaCorrente);
					Maps.mapNumeroAgencia.get(numeroAgencia).addConta();
					Maps.mapCpfPessoaAgencia.put(CPF, Maps.mapNumeroAgencia.get(numeroAgencia));
					return contaCorrente;
				}else {
					System.out.println("Operacao nao realizada,\n"
					           + " pois o cliente ja possui conta corrente");
					return null;
				}
					
			}else {
				System.out.println("Operacao nao realizada,\n"
				           + " pois a agencia informada nao existe no sistema");
				return null;
			}
		}
		System.out.println("Operacao nao realizada,\n"
		           + " pois o cliente informado nao esta cadastrado no sistema");
		return null;
	}
	public Conta criarContaPoupanca(int numeroAgencia, String CPF) {
		
		if(Maps.mapCpfPessoa.containsKey(CPF)) {
			if(Maps.mapNumeroAgencia.containsKey(numeroAgencia)) {
				if(!Maps.mapCpfContaPoupanca.containsKey(CPF)) {
					Conta contaPoupanca = new ContaPoupanca(Maps.mapNumeroAgencia.get(numeroAgencia),Maps.mapCpfPessoa.get(CPF));
					Listas.conta.add(contaPoupanca);
					Maps.mapNumeroConta.put(contaPoupanca.getNumeroConta(),contaPoupanca);
					Maps.mapCpfContaPoupanca.put(CPF,contaPoupanca);
					Maps.mapNumeroAgencia.get(numeroAgencia).addConta();
					Maps.mapCpfPessoaAgencia.put(CPF, Maps.mapNumeroAgencia.get(numeroAgencia));
					return contaPoupanca;
				}else {
					System.out.println("Operacao nao realizada,\n"
							           + " pois o cliente ja possui conta poupanca");
					return null;
				}	
				
			}else {
				System.out.println("Operacao nao realizada,\n"
				           + " pois a agencia informada nao existe no sistema");
				return null;
			}
		}
		System.out.println("Operacao nao realizada,\n"
		           + " pois o cliente informado nao esta cadastrado no sistema");
		return null;
	}
}

