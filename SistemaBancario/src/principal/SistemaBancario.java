package principal;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import entradasEsaidas.Escreve;
import listas.Listas;
import maps.Maps;
import menus.Menu;

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
		
		
		//System.out.println(Maps.mapNumeroAgencia.toString());	
System.out.println(Maps.mapCpfPessoaAgencia);



System.out.println(Listas.movimentacao.toString());
		
		
		
		Menu.menuLogin();
		
	}
}
