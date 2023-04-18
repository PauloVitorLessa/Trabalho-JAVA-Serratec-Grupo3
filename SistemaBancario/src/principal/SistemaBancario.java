package principal;

import maps.Maps;
import menus.Menu;
import pessoas.Diretor;
import pessoas.Gerente;
import pessoas.Pessoa;

public class SistemaBancario {

	public static void main(String[] args) {
		
		Pessoa gerente = new Gerente("caio", "123456789", "123456789");
		Maps.mapCpfPessoa.put("123456789", gerente);
		
		((Gerente)gerente).cadastraCliente("paulo", "145", "123");
		
		Pessoa diretor = new Diretor("yago", "123", "123");
		Maps.mapCpfPessoa.put("123", diretor);
		
		((Diretor)diretor).cadastraGerente("allan", "456", "123");
		
		Menu.menuLogin();
		
	}

}
