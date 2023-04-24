package relatorios;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import Movimentos.Movimentacao;
import contas.Conta;
import listas.Listas;
import utilidades.Arred;
import utilidades.Data;

public class Relatorio {
	public static void relTributacao(Conta conta){
		
		System.out.println("### RELATÓRIO DE TRIBUTAÇÃO ###");
		System.out.println();
		System.out.println("Número da conta: " + conta.getNumeroConta());
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Date date = new Date();
		System.out.println("Data: " + sdf.format(date));
		System.out.println("-----------------------------------------------------------------------");
		System.out.println("  TIPO       TRIBUTO      DATA");
		System.out.println("-----------------------------------------------------------------------");
		
		double totalTributo = 0;
		
		for(Movimentacao movimentacao : Listas.movimentacao){
			if(movimentacao.getConta() == conta.getNumeroConta()){
				switch (movimentacao.getTipo()) {
				case TRANSFERENCIA:					
					System.out.println(movimentacao.getTipo() +"  " +
							           movimentacao.getTributo() + "        " +
							           movimentacao.getDatahora());					
					System.out.println();					
					totalTributo += movimentacao.getTributo();
					break;
					
				case SAQUE:					
					System.out.println(movimentacao.getTipo() +"          " +
					           movimentacao.getTributo() + "        " +					            
					           movimentacao.getDatahora());					
					System.out.println();					
					totalTributo += movimentacao.getTributo();
					break;
					
				case DEPOSITO:					
					System.out.println(movimentacao.getTipo() +"       " +
					           movimentacao.getTributo() + "        " +					           
					           movimentacao.getDatahora());					
					System.out.println();					
					totalTributo += movimentacao.getTributo();
					break;
					
				case SEGURO:					
					System.out.println(movimentacao.getTipo() +"          " +
					           movimentacao.getTributo() + "        " +					            
					           movimentacao.getDatahora());					
					System.out.println();					
					totalTributo += movimentacao.getTributo();
					break;

				default:
					break;
				}
				
			}
		}
		System.out.println("-----------------------------------------------------------------------");
		System.out.println("TOTAL:         " + Arred.dois(totalTributo, 2));
		System.out.println("\n");
		
		try {	    	
	    	String dataSemEspaco = Data.dataHoraSemEspaco(date);
			String path = ".\\arquivos\\Relatorio-tributacao-"+dataSemEspaco+".txt";
			FileWriter fw = new FileWriter(path, true);
			PrintWriter pw = new PrintWriter(fw);
			pw.println("### RELATÓRIO DE TRIBUTAÇÃO ###");
			pw.println();
			pw.println("Número da conta: " + conta.getNumeroConta());					
			pw.println("Data: " + sdf.format(date));
			pw.println("-----------------------------------------------------------------------");
			pw.println("  TIPO       TRIBUTO      DATA");
			pw.println("-----------------------------------------------------------------------");
						
			for(Movimentacao movimentacao : Listas.movimentacao){
				if(movimentacao.getConta() == conta.getNumeroConta()){
					switch (movimentacao.getTipo()) {
					case TRANSFERENCIA:					
						pw.println(movimentacao.getTipo() +"  " +
								           movimentacao.getTributo() + "        " +
								           movimentacao.getDatahora());					
						pw.println();					
						totalTributo += movimentacao.getTributo();
						break;
						
					case SAQUE:					
						pw.println(movimentacao.getTipo() +"          " +
						           movimentacao.getTributo() + "        " +					            
						           movimentacao.getDatahora());					
						pw.println();					
						totalTributo += movimentacao.getTributo();
						break;
						
					case DEPOSITO:					
						pw.println(movimentacao.getTipo() +"       " +
						           movimentacao.getTributo() + "        " +					           
						           movimentacao.getDatahora());					
						pw.println();					
						totalTributo += movimentacao.getTributo();
						break;
						
					case SEGURO:					
						pw.println(movimentacao.getTipo() +"          " +
						           movimentacao.getTributo() + "        " +					            
						           movimentacao.getDatahora());					
						pw.println();					
						totalTributo += movimentacao.getTributo();
						break;

					default:
						break;
					}
					
				}
			}
			pw.println("-----------------------------------------------------------------------");
			pw.println("TOTAL:         " + Arred.dois(totalTributo, 2));
			pw.println("\n");
			pw.flush();
			pw.close();
			fw.close();
		} catch(IOException e) {
			
			e.printStackTrace();
		}
				

	}
	
	public static void relSaldo(Conta conta) {
		Date data = (new Date());
		String dataComEspaco = Data.dataHora(data);
		System.out.println("-----------------------------------------------------------------------");
		System.out.println("### COMPROVANTE DE SALDO ###\n");
		System.out.println("Data: " + dataComEspaco);
		System.out.println("Número da conta: " + conta.getNumeroConta());
		System.out.printf("Saldo: R$ %.2f" , Arred.dois(conta.getSaldo(), 2));
		System.out.println("\n-----------------------------------------------------------------------");
		System.out.println("\n");
		
		try {	    	
	    	String dataSemEspaco = Data.dataHoraSemEspaco(data);
			String path = ".\\arquivos\\Comprovante-Saldo-"+dataSemEspaco+".txt";
			FileWriter fw = new FileWriter(path, true);
			PrintWriter pw = new PrintWriter(fw);
			pw.println("-----------------------------------------------------------------------");
			pw.println("### COMPROVANTE DE SALDO ###\n");
			pw.println("Data: " + data);
			pw.println("Número da conta: " + conta.getNumeroConta());
			pw.printf("Saldo: R$ %.2f" , Arred.dois(conta.getSaldo(), 2));
			pw.println("\n-----------------------------------------------------------------------");
			pw.flush();
			pw.close();
			fw.close();
		} catch(IOException e) {
			
			e.printStackTrace();
		}
	}
}
