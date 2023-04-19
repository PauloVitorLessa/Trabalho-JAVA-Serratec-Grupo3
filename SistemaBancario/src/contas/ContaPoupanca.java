package contas;

import java.text.SimpleDateFormat;
import java.util.Date;

import Movimentos.Movimentacao;
import agencias.Agencia;
import entradasEsaidas.Escreve;
import enuns.ContaEnum;
import listas.Listas;
import pessoas.Pessoa;
import utilidades.Arred;
import utilidades.Data;

public class ContaPoupanca extends Conta{
	
	private double rendimento = 0.022;
	
	public ContaPoupanca(Agencia agencia, Pessoa pessoa) {
		super(agencia, pessoa, ContaEnum.POUPANCA);	
	}
	public boolean sacar(double valor) {
		if(this.getSaldo() >= valor && valor > 0) {
			this.setSaldo(this.getSaldo() - valor);
			System.out.println("Seu saque foi efetuado! ");
			System.out.println("O seu saldo é de: R$"+this.getSaldo());
			return true;
		}else {
			System.out.println("O saque não pode ser realizado!");
			return false;
		}
	}
	public boolean depositar(double valor) {
		if(valor > 0) {
			this.setSaldo(this.getSaldo()+valor);			
			return true;
		}else {
			System.out.println("Não foi possível realizar o depósito!");
			return false;
		}	
	}
	@Override
	public boolean transferir(double valor, Conta contaDestino) {
		if(valor > 0 && this.getSaldo()>=valor) {
			if(this.getNumeroConta()!= contaDestino.getNumeroConta()) {
				setSaldo(getSaldo() - valor);
				contaDestino.depositar(valor);
				System.out.println("Transferência concluída!");
				return true;
			}
			else {
				System.out.println("Transferência não realizada.");
				System.out.println("A conta de destino deve ser diferente da conta de origem.");
				return false;
			}
			
		}else {
			System.out.println("Saldo insuficiente para fazer a transferência!!");
			return false;
		}
	}
	public void emitirExtrato() {
		System.out.println("### Extrato da Conta Poupança ###\n");
		System.out.println("Número da conta: " + this.getNumeroConta());
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Date date = new Date();
		System.out.println("Data: " + sdf.format(date));
		System.out.println("Saldo: R$ " + Arred.dois(this.getSaldo(), 2));
		System.out.println("-----------------------------------------------------------------------");
		System.out.println("  TIPO         VALOR         C. DESTINO     DATA");
		System.out.println("-----------------------------------------------------------------------");
		
		double totalValor = 0;		
		
		for(Movimentacao movimentacao : Listas.movimentacao) {
			if(movimentacao.getConta() == this.getNumeroConta()) {
				switch (movimentacao.getTipo()) {
				case TRANSFERENCIA:					
					System.out.println(movimentacao.getTipo() +"  -" +
							           movimentacao.getValor() + "       " +
							           movimentacao.getNumeroContaDestino() + "         " + 
							           Data.dataHora(movimentacao.getDatahora()));					
					System.out.println();
					totalValor -= movimentacao.getValor();					
					break;
					
				case SAQUE:					
					System.out.println(movimentacao.getTipo() +"          -" +
					           movimentacao.getValor() + "       " +
					           Data.dataHora(movimentacao.getDatahora()));					
					System.out.println();
					totalValor -= movimentacao.getValor();					
					break;
					
				case DEPOSITO:					
					System.out.println(movimentacao.getTipo() +"       +" +
					           movimentacao.getValor() + "       " +
					           Data.dataHora(movimentacao.getDatahora()));					
					System.out.println();
					totalValor += movimentacao.getValor();					
					break;

				default:
					break;
				}
				
			}
		}
		System.out.println("-----------------------------------------------------------------------");
		System.out.println("TOTAL:          " + Arred.dois(totalValor, 2));
		System.out.println("\n");
		Escreve.extratoPoupanca(this.getNumeroConta(), this.getPessoa().getNome(), this.getSaldo());
	}
	
	public void simular(double valor, int dias) {
		Double capital = valor;
		Double montante;
	    int tempo = dias;
	    montante = capital * Math.pow(1 + this.rendimento, tempo); 
	    System.out.println("Total Simulado em " + dias + " dias de : " + montante);
	    
	}
}
