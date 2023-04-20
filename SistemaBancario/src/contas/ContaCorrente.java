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
import utilidades.Data;

public class ContaCorrente extends Conta{
	
	private double tributoSaque = 0.1;
	private double tributoDeposito = 0.1;
	private double tributoTransferencia = 0.2;

	
	public ContaCorrente(Agencia agencia, Pessoa pessoa) {
		super(agencia, pessoa, ContaEnum.CORRENTE);
		
	}
	public boolean sacar(double valor) {
		if(valor > 0.009999999999999999999999999999999999999999999999) {
			if(this.getSaldo() >= (valor + tributoSaque)) {
				this.setSaldo(this.getSaldo() - valor - tributoSaque);
				System.out.println("Seu saque foi efetuado! ");			
				Movimentacao movimento=new Movimentacao(this.getNumeroConta(), MovimentosEnum.SAQUE, valor, tributoSaque);
				Listas.movimentacao.add(movimento);
				this.setTotaltributo(tributoSaque);
				return true;
			}else {
				System.out.println("Saque não realizado.");
				System.out.println("Saldo insuficiente para "
						           + "fazer o saque e pagar o tributo!");
				return false;
			}
		}else {
			System.out.println("Saque não realizado.");
			System.out.println("O valor deve ser \n"
					           + "maior do que 1 centavo");
			return false;
		}
		
	}
	public boolean depositar(double valor) {
		if(valor > 0.009999999999999999999999999999999999999999999999) {
			if(this.getSaldo() > tributoDeposito || valor >=tributoDeposito) {
				this.setSaldo(this.getSaldo()+valor - tributoDeposito);			
				Movimentacao movimento=new Movimentacao(this.getNumeroConta(), MovimentosEnum.DEPOSITO, valor, tributoDeposito);
				Listas.movimentacao.add(movimento);
				this.setTotaltributo(tributoDeposito);
				return true;
			}else {
				System.out.println("Deposito não realizado.");
				System.out.println("Saldo ou valor de deposito\n"
						           + " insuficiente para pagar o tributo!");
				return false;
			}
				
			}else {
				System.out.println("Deposito não realizado.");
				System.out.println("O valor deve ser \n"
						            + "maior ou igual a 1 centavo.");
				return false;
			}
				
	}
			
	
	@Override
	public boolean transferir(double valor, Conta contaDestino) {
		if(valor >0.009999999999999999999999999999999999999999999999) {
			if(this.getSaldo() + tributoTransferencia >= valor) {
				if(this.getNumeroConta()!= contaDestino.getNumeroConta()) {
					setSaldo(getSaldo() - valor - tributoTransferencia);
					contaDestino.depositar(valor);
					System.out.println("Transferência concluída!");
					Movimentacao movimento=new Movimentacao(this.getNumeroConta(), MovimentosEnum.TRANSFERENCIA, valor, tributoTransferencia,contaDestino.getNumeroConta());
					Listas.movimentacao.add(movimento);
					this.setTotaltributo(tributoTransferencia);
					return true;
				}
				else {
					System.out.println("Transferência não realizada.");
					System.out.println("A conta de destino deve ser \n"
							           + "diferente da conta de origem.");
					return false;
				}
				
				
			} else {
				System.out.println("Transferência não realizada.");
				System.out.println("Saldo insuficiente para \n"
						           + "fazer a transferência e pagar o tributo!");
				return false;
			}
			
			
		}else {
			System.out.println("Transferência não realizada.");
			System.out.println("O valor deve ser \n"
					           + "maior ou igual a 1 centavo");
			return false;
		}
	}
	@Override
	public void emitirExtrato() {
		System.out.println("### Extrato da Conta Corrente ###\n");
		System.out.println("Número da conta: " + this.getNumeroConta());
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Date date = new Date();
		System.out.println("Data: " + sdf.format(date));
		System.out.println("Saldo: R$ " + Arred.dois(this.getSaldo(), 2));
		System.out.println("-----------------------------------------------------------------------");
		System.out.println("  TIPO         VALOR     TRIBUTO    C. DESTINO     DATA");
		System.out.println("-----------------------------------------------------------------------");
		
		double totalValor = 0;
		double totalTributo = 0;
		
		for(Movimentacao movimentacao : Listas.movimentacao) {
			if(movimentacao.getConta() == this.getNumeroConta()) {
				switch (movimentacao.getTipo()) {
				case TRANSFERENCIA:					
					System.out.println(movimentacao.getTipo() +"  -" +
							           movimentacao.getValor() + "       " +
							           movimentacao.getTributo() + "          " +
							           movimentacao.getNumeroContaDestino() + "          " + 
							           Data.dataHora(movimentacao.getDatahora()));					
					System.out.println();
					totalValor -= movimentacao.getValor();
					totalTributo += movimentacao.getTributo();
					break;
					
				case SAQUE:					
					System.out.println(movimentacao.getTipo() +"          -" +
					           movimentacao.getValor() + "       " +
					           movimentacao.getTributo() + "                     " +					            
					           Data.dataHora(movimentacao.getDatahora()));					
					System.out.println();
					totalValor -= movimentacao.getValor();
					totalTributo += movimentacao.getTributo();
					break;
					
				case DEPOSITO:					
					System.out.println(movimentacao.getTipo() +"       +" +
					           movimentacao.getValor() + "       " +
					           movimentacao.getTributo() + "                     " +					           
					           Data.dataHora(movimentacao.getDatahora()));					
					System.out.println();
					totalValor += movimentacao.getValor();
					totalTributo += movimentacao.getTributo();
					break;

				default:
					break;
				}
				
			}
		}
		System.out.println("-----------------------------------------------------------------------");
		System.out.println("TOTAL:       R$ " + Arred.dois(totalValor, 2)+
				           "    R$ "+ Arred.dois(totalTributo, 2));
		System.out.println("\n");
		Escreve.extratoCorrente(this.getNumeroConta(), this.getPessoa().getNome(), this.getSaldo());
	}	
}
