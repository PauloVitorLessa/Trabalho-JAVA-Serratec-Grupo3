package contas;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
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
import utilidades.Data;

public class ContaPoupanca extends Conta{
	
	private double rendimento = 0.0002;
	
	
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
					Movimentacao movimento=new Movimentacao(contaDestino.getNumeroConta(), MovimentosEnum.RECEBIMENTO, valor, 0,this.getNumeroConta());
					Listas.movimentacao.add(movimento);
					System.out.println("Transferência concluída!");
					Movimentacao movimento1=new Movimentacao(this.getNumeroConta(), MovimentosEnum.TRANSFERENCIA, valor, 0,contaDestino.getNumeroConta());
					Listas.movimentacao.add(movimento1);
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
		System.out.println("                             C. ORIGEM");
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
					
				case RECEBIMENTO:					
					System.out.println("TRANSFERENCIA  +" +
							           movimentacao.getValor() + "              " +
							           movimentacao.getNumeroContaDestino() + "         " + 
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
	    double rendimento = montante-capital;
	    String data = Data.dataHora(new Date());
	    System.out.println("-----------------------------------------------------------------------");
	    System.out.println("### SIMULAÇÃO DE RENDIMENTO DA POUPANÇA###\n");
		System.out.println("Número da conta: " + this.getNumeroConta());				
		System.out.println("Data: " + data);
	    System.out.printf("Valor Simulado: R$ %.2f" , Arred.dois(valor, 2));
	    System.out.println("\nPrazo simulado: "+ dias + " dias");
	    System.out.println("Taxa de rendimento ao dia: "+ Arred.dois(this.rendimento*100, 2) + "%");
	    System.out.printf("Total do rendimento: R$ %.2f",Arred.dois(rendimento, 2));
	    System.out.printf("\nMontante final: R$ %.2f",Arred.dois(montante, 2));
	    System.out.println("\n\n");
	    
	    try {	    	
	    	String dataSemEspaco = Data.dataHoraSemEspaco(new Date());
			String path = ".\\arquivos\\Simulacao-rendimento-"+dataSemEspaco+".txt";
			FileWriter fw = new FileWriter(path, true);
			PrintWriter pw = new PrintWriter(fw);
			pw.println("-----------------------------------------------------------------------");
			pw.println("### SIMULAÇÃO DE RENDIMENTO DA POUPANÇA###\n");
			pw.println("Número da conta: " + this.getNumeroConta());
			pw.println("Data: " + data);
			pw.printf("Valor Simulado: R$ %.2f", Arred.dois(valor, 2));
			pw.println("\nPrazo simulado: "+ dias + " dias");
			pw.println("Taxa de rendimento ao dia: "+ Arred.dois(this.rendimento*100, 2) + "%");
			pw.printf("Total do rendimento: R$ %.2f",Arred.dois(rendimento, 2));
			pw.printf("\nMontante final: R$ %.2f",Arred.dois(montante, 2));
			pw.flush();
			pw.close();
			fw.close();
		} catch(IOException e) {
			
			e.printStackTrace();
		}
	    
	    
	}
}
