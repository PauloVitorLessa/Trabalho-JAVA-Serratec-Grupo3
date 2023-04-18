package principal;

import agencias.Agencia;
import maps.Maps;
import menus.Menu;
import pessoas.Diretor;
import pessoas.Gerente;
import pessoas.Pessoa;
import pessoas.Presidente;

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
		
		((Diretor)diretor).cadastraGerente("allan", "456", "123",agencia1.getNumeroAgencia());
		
		Menu.menuLogin();	
	}
}
