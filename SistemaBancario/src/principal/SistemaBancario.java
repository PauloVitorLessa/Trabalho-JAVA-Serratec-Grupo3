package principal;
import agencias.Agencia;
import contas.Conta;
import contas.ContaCorrente;
import enuns.ContaEnum;
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
		Listas.pessoa.add(gerente);
		Maps.mapCpfPessoa.put("000", gerente);
		Maps.mapAgenciaGerente.put(agencia1.getNumeroAgencia(), gerente.getCpf());
		Maps.mapCpfGerenteAgencia.put(gerente.getCpf(), agencia1);
		agencia1.setGerente(gerente.getCpf());
		
		
		Pessoa cliente =((Gerente)gerente).cadastraCliente("paulo", "145", "123");
				
		
		Pessoa diretor = new Diretor("yago", "123", "123");
		Listas.pessoa.add(diretor);
		Maps.mapCpfPessoa.put("123", diretor);
				
		Conta corrente = ((Gerente)gerente).criarContaCorrente(agencia1.getNumeroAgencia(), diretor.getCpf());
		Conta teste = new ContaCorrente (100, agencia1, gerente,0.80);
		System.out.println(teste.getSaldo());
		System.out.println(((ContaCorrente) teste).tributo());
		
		
		
			
		Conta c1 = ((Gerente)gerente).criarContaCorrente(agencia1.getNumeroAgencia(), cliente.getCpf());
				
		Conta Poupanca =  ((Gerente)gerente).criarContaPoupanca(agencia1.getNumeroAgencia(), cliente.getCpf());
        
        
		
		
		
		Menu.menuLogin();
		
	}
}
