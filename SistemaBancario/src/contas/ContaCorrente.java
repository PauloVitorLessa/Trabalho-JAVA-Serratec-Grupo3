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
		if(this.getSaldo() >= (valor + tributoSaque) && valor > 0) {
			this.setSaldo(this.getSaldo() - valor - tributoSaque);
			System.out.println("Seu saque foi efetuado! ");			
			Movimentacao movimento=new Movimentacao(this.getNumeroConta(), MovimentosEnum.SAQUE, valor, tributoSaque);
			Listas.movimentacao.add(movimento);
			return true;
		}else {
			System.out.println("O saque não pode ser realizado!");
			return false;
		}
	}
	public boolean depositar(double valor) {
		if(valor - tributoDeposito > 0) {
			this.setSaldo(this.getSaldo()+valor - tributoDeposito);			
			Movimentacao movimento=new Movimentacao(this.getNumeroConta(), MovimentosEnum.DEPOSITO, valor, tributoDeposito);
			Listas.movimentacao.add(movimento);
			return true;
		}else {
			System.out.println("Não foi possível realizar o depósito!");
			return false;
		}	
	}
	@Override
	public boolean transferir(double valor, Conta contaDestino) {
		if(valor - tributoTransferencia > 0 && this.getSaldo()>=valor) {
			if(this.getNumeroConta()!= contaDestino.getNumeroConta()) {
				setSaldo(getSaldo() - valor - tributoTransferencia);
				contaDestino.depositar(valor);
				System.out.println("Transferência concluída!");
				Movimentacao movimento=new Movimentacao(this.getNumeroConta(), MovimentosEnum.TRANSFERENCIA, valor, tributoTransferencia,contaDestino.getNumeroConta());
				Listas.movimentacao.add(movimento);
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
