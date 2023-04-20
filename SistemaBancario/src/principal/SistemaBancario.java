package principal;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import agencias.Agencia;
import contas.Conta;
import contas.ContaCorrente;
import entradasEsaidas.Escreve;
import listas.Listas;
import maps.Maps;
import menus.Menu;
import pessoas.Diretor;
import pessoas.Gerente;
import pessoas.Pessoa;
import pessoas.Presidente;

public class SistemaBancario {

	public static void main(String[] args) {
		
		try {
			String path = ".\\arquivos\\Objetos.txt";
			FileWriter fw = new FileWriter(path,true);
			PrintWriter pw = new PrintWriter(fw);

			pw.flush();
			pw.close();
			fw.close();
		} catch (IOException e) {

			e.printStackTrace();
		}
			
		Escreve.Ler();
		
		
		Menu.menuLogin();
		
	}
}
