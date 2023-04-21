package contas;

import java.text.SimpleDateFormat;
import java.util.Date;

import Movimentos.Movimentacao;
import agencias.Agencia;
import entradasEsaidas.Escreve;
import enuns.ContaEnum;
import enuns.MovimentosEnum;
import listas.Listas;
import pessoas.Pessoa;
import utilidades.Arred;

public class ContaPoupanca extends Conta{
	
	private double rendimento = 0.00017;
	
	
	public ContaPoupanca(double saldo, Agencia agencia, Pessoa pessoa) {
		super(saldo, agencia, pessoa, ContaEnum.POUPANCA, 0);
		
	}
	public ContaPoupanca(Agencia agencia, Pessoa pessoa) {
		super(agencia, pessoa, ContaEnum.POUPANCA);	
	}
	public boolean sacar(double valor) {
		if(valor >= 0.01) {
			if(this.getSaldo() >= valor) {
				this.setSaldo(this.getSaldo() - valor);
				System.out.println("Seu saque foi efetuado! ");				
				Movimentacao movimento=new Movimentacao(this.getNumeroConta(), MovimentosEnum.SAQUE, valor, 0);
				Listas.movimentacao.add(movimento);
				return true;
			}else {
				System.out.println("Saldo insuficinte para realizar o saque!");
				return false;
			}
		}else {
			System.out.println("Saque não realizado.");
			System.out.println("O valor deve ser \n"
					           + "maior ou igual a R$ 0,01");
			return false;
		}
		
	}
	public boolean depositar(double valor) {
		if(valor >= 0.01) {
			this.setSaldo(this.getSaldo()+valor);
			Movimentacao movimento=new Movimentacao(this.getNumeroConta(), MovimentosEnum.DEPOSITO, valor, 0);
			Listas.movimentacao.add(movimento);
			return true;
		}else {
			System.out.println("Deposito não realizado.");
			System.out.println("O valor deve ser \n"
					            + "maior ou igual a R$ 0,01");
			return false;
		}	
	}
	@Override
	public boolean transferir(double valor, Conta contaDestino) {
		if(valor >= 0.01) {
			if(this.getSaldo()>=valor) {
				if(this.getNumeroConta()!= contaDestino.getNumeroConta()) {
					setSaldo(getSaldo() - valor);
					contaDestino.recebeTransferencia(valor);
					System.out.println("Transferência concluída!");
					Movimentacao movimento=new Movimentacao(this.getNumeroConta(), MovimentosEnum.TRANSFERENCIA, valor, 0,contaDestino.getNumeroConta());
					Listas.movimentacao.add(movimento);
					return true;
				}
				else {
					System.out.println("Transferência não realizada.");
					System.out.println("A conta de destino deve ser \n"
							          + "diferente da conta de origem.");
					return false;
				}
				
			}else {
				System.out.println("Transferência não realizada.");
				System.out.println("Saldo insuficiente para fazer a transferência!");
				return false;
			
			}
			
		}else {
			System.out.println("Transferência não realizada.");
			System.out.println("O valor deve ser \n"
					           + "maior ou igual a R$ 0,01");
			return false;
		}
	}
	
	public void recebeTransferencia(double valor) {
		this.setSaldo(this.getSaldo()+valor);
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
							           movimentacao.getValor() + "              " +
							           movimentacao.getNumeroContaDestino() + "         " + 
							           movimentacao.getDatahora());					
					System.out.println();
					totalValor -= movimentacao.getValor();					
					break;
					
				case SAQUE:					
					System.out.println(movimentacao.getTipo() +"          -" +
					           movimentacao.getValor() + "                      " +
					           movimentacao.getDatahora());					
					System.out.println();
					totalValor -= movimentacao.getValor();					
					break;
					
				case DEPOSITO:					
					System.out.println(movimentacao.getTipo() +"       +" +
					           movimentacao.getValor() + "                      " +
					           movimentacao.getDatahora());					
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
	    System.out.println("Total Simulado em " + dias + " dias de : " + Arred.dois(montante, 2));
	    
	}
}
