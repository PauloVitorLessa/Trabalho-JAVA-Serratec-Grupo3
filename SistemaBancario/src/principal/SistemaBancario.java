package principal;

import agencias.Agencia;
import contas.Conta;
import listas.Listas;
import maps.Maps;
import menus.Menu;
import pessoas.*;
import utilidades.Arred;

public class SistemaBancario {

	public static void main(String[] args) {
		
		Pessoa presidente = new Presidente("Joãozinho","122","123");
		Agencia agencia1 = ((Presidente)presidente).criarAgencia();
		
		Conta corrente1 = ((Presidente)presidente).criarContaCorrente(agencia1.getNumeroAgencia(), presidente.getCpf());
		Maps.mapCpfContaCorrente.put(presidente.getCpf(), corrente1);
		Listas.conta.add(corrente1);
		Listas.pessoa.add(presidente);
		
		Maps.mapCpfPessoa.put("122",presidente);
		Maps.mapNumeroAgencia.put(agencia1.getNumeroAgencia(),agencia1);
		
		Pessoa gerente = new Gerente("caio", "000", "000",agencia1);
		Maps.mapCpfPessoa.put("000", gerente);
		Maps.mapAgenciaGerente.put(agencia1.getNumeroAgencia(), gerente.getCpf());
		Maps.mapCpfGerenteAgencia.put(gerente.getCpf(), agencia1);
		
		
		Pessoa cliente =((Gerente)gerente).cadastraCliente("paulo", "145", "123");
		
		
		Maps.mapCpfPessoa.put(cliente.getCpf(), cliente);
		Listas.pessoa.add(cliente);
		
		Pessoa diretor = new Diretor("yago", "123", "123");
		Maps.mapCpfPessoa.put("123", diretor);
		
		
		
		Conta corrente = ((Gerente)gerente).criarContaCorrente(agencia1.getNumeroAgencia(), diretor.getCpf());
		
			
		Conta c1 = ((Gerente)gerente).criarContaCorrente(agencia1.getNumeroAgencia(), cliente.getCpf());
		Maps.mapCpfContaCorrente.put(cliente.getCpf(), c1);
		
		Conta Poupanca =  ((Gerente)gerente).criarContaPoupanca(agencia1.getNumeroAgencia(), cliente.getCpf());
        
        Maps.mapCpfContaPoupanca.put(cliente.getCpf(), Poupanca);
		
		
		
		Menu.menuLogin();
		
	}
}
