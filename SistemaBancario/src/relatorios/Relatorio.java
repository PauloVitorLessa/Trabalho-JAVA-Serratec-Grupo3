package relatorios;

import java.time.LocalDateTime;

import Movimentos.Movimentacao;
import contas.Conta;
import listas.Listas;

public class Relatorio {
	public static void relTributacao(Conta conta){
		for(Movimentacao movimento : Listas.movimentacao){
			if(movimento.getConta() == conta.getNumeroConta()){
				System.out.println(movimento.getDatahora()+ " " 
			+ movimento.getTipo() + " tributo: " + movimento.getTributo());
			} 
				
		}
	
	}
	public static void relSaldo(Conta conta) {
		LocalDateTime agora = LocalDateTime.now();
		System.out.printf("Seu saldo é: %.2f" , conta.getSaldo() , " " , agora);
		System.out.println();
	}
}
