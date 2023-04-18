package principal;

import agencias.Agencia;
import contas.Conta;
import maps.Maps;
import menus.Menu;
import pessoas.*;

public class SistemaBancario {

	public static void main(String[] args) {
		
		Pessoa presidente = new Presidente("Joãozinho","122","12354");
		Agencia agencia1 = ((Presidente)presidente).criarAgencia();
		Maps.mapCpfPessoa.put("122",presidente);
		Maps.mapNumeroAgencia.put(agencia1.getNumeroAgencia(),agencia1);
		
		Pessoa gerente = new Gerente("caio", "123456789", "123456789",agencia1);
		Maps.mapCpfPessoa.put("123456789", gerente);
		Maps.mapAgenciaGerente.put(agencia1.getNumeroAgencia(), gerente.getCpf());
		
		((Gerente)gerente).cadastraCliente("paulo", "145", "123");
		
		Pessoa diretor = new Diretor("yago", "123", "123");
		Maps.mapCpfPessoa.put("123", diretor);
		
		Conta corrente = ((Gerente)gerente).criarContaCorrente(agencia1.getNumeroAgencia(), diretor.getCpf());
		System.out.println(corrente.getNumeroConta() + " / " + corrente.getSaldo());
		corrente.depositar(10);
		System.out.println(corrente.getNumeroConta() + " / " + corrente.getSaldo());
		
		Menu.menuLogin();
		
	}
}
