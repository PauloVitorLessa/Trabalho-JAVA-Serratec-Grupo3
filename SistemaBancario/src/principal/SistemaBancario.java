package principal;
import agencias.Agencia;
import contas.Conta;
import listas.Listas;
import maps.Maps;
import menus.Menu;
import pessoas.Diretor;
import pessoas.Gerente;
import pessoas.Pessoa;
import pessoas.Presidente;

public class SistemaBancario {

	public static void main(String[] args) {
		
		Pessoa presidente = new Presidente("Joao","122","123");
		Listas.pessoa.add(presidente);
		Maps.mapCpfPessoa.put("122",presidente);
		
		Agencia agencia1 = ((Presidente)presidente).criarAgencia();
		Maps.mapNumeroAgencia.put(agencia1.getNumeroAgencia(),agencia1);
		
		Conta corrente1 = ((Presidente)presidente).criarContaCorrente(agencia1.getNumeroAgencia(), presidente.getCpf());
				
		Pessoa gerente = new Gerente("caio", "000", "000",agencia1);
		Maps.mapCpfPessoa.put("000", gerente);
		Maps.mapAgenciaGerente.put(agencia1.getNumeroAgencia(), gerente.getCpf());
		Maps.mapCpfGerenteAgencia.put(gerente.getCpf(), agencia1);
		
		
		Pessoa cliente =((Gerente)gerente).cadastraCliente("paulo", "145", "123");
				
		
		Pessoa diretor = new Diretor("yago", "123", "123");
		Maps.mapCpfPessoa.put("123", diretor);
				
		Conta corrente = ((Gerente)gerente).criarContaCorrente(agencia1.getNumeroAgencia(), diretor.getCpf());
		
			
		Conta c1 = ((Gerente)gerente).criarContaCorrente(agencia1.getNumeroAgencia(), cliente.getCpf());
				
		Conta Poupanca =  ((Gerente)gerente).criarContaPoupanca(agencia1.getNumeroAgencia(), cliente.getCpf());
        
        
		
		
		
		Menu.menuLogin();
		
	}
}
