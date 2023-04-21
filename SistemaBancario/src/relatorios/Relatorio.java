package relatorios;

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

				default:
					break;
				}
				
			}
		}
		System.out.println("-----------------------------------------------------------------------");
		System.out.println("TOTAL:         " + Arred.dois(totalTributo, 2));
		System.out.println("\n");
				

	}
	
	public static void relSaldo(Conta conta) {
		Date agora = new Date();		
		System.out.printf("Seu saldo é de: R$ " + Arred.dois(conta.getSaldo(), 2) + "    " + Data.dataHora(agora));
		System.out.println("\n\n");
	}
}
