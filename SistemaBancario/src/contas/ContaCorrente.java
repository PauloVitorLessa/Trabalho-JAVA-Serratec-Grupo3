package contas;

import java.text.SimpleDateFormat;
import java.util.Date;

import Movimentos.Movimentacao;
import agencias.Agencia;
import enuns.MovimentosEnum;
import listas.Listas;
import pessoas.Pessoa;

public class ContaCorrente extends Conta{
	
	private double tributoSaque = 0.1;
	private double tributoDeposito = 0.1;
	private double tributoTransferencia = 0.2;
	
	public ContaCorrente(Agencia agencia, Pessoa pessoa) {
		super(agencia, pessoa, ContaEnum.CORRENTE);
		
	}
	public void sacar(double valor) {
		if(this.getSaldo() >= (valor + tributoSaque) && valor > 0) {
			this.setSaldo(this.getSaldo() - valor - tributoSaque);
			System.out.println("Seu saque foi efetuado! ");
			System.out.println("O seu saldo é de: R$"+this.getSaldo());
			Movimentacao movimento=new Movimentacao(this.getNumeroConta(), MovimentosEnum.SAQUE, valor, tributoSaque);
			Listas.movimentacao.add(movimento);
		}else {
			System.out.println("O saque não pode ser realizado!");
		}
	}
	public void depositar(double valor) {
		if(valor - tributoDeposito > 0) {
			this.setSaldo(this.getSaldo()+valor - tributoDeposito);
			System.out.println("Deposito efetuado!");
			Movimentacao movimento=new Movimentacao(this.getNumeroConta(), MovimentosEnum.DEPOSITO, valor, tributoDeposito);
			Listas.movimentacao.add(movimento);
		}else {
			System.out.println("Não foi possível realizar o depósito!");
		}	
	}
	@Override
	public void transferir(double valor, Conta contaDestino) {
		if(valor - tributoTransferencia > 0 && this.getSaldo()>=valor) {
			setSaldo(getSaldo() - valor - tributoTransferencia);
			contaDestino.depositar(valor);
			System.out.println("Transferência concluída!");
			Movimentacao movimento=new Movimentacao(this.getNumeroConta(), MovimentosEnum.TRANSFERENCIA, valor, tributoTransferencia,contaDestino.getNumeroConta());
			Listas.movimentacao.add(movimento);
		}else {
			System.out.println("Saldo insuficiente para fazer a transferência!!");
		}
	}
	@Override
	public void emitirExtrato() {
		System.out.println("### Extrato da Conta Corrente ###");
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Date date = new Date();
		System.out.println("Data: " + sdf.format(date));
		System.out.println("Saldo: " + this.getSaldo());
		for(Movimentacao movimentacao : Listas.movimentacao) {
			if(movimentacao.getConta() == this.getNumeroConta()) {
				switch (movimentacao.getTipo()) {
				case TRANSFERENCIA:
					System.out.println(movimentacao.getConta());
					System.out.println(movimentacao.getNumeroContaDestino());
					System.out.println(movimentacao.getTributo());
					System.out.println(movimentacao.getValor());
					System.out.println(movimentacao.getDatahora());
					System.out.println(movimentacao.getTipo());
					break;
					
				case SAQUE:
					System.out.println(movimentacao.getConta());
					System.out.println(movimentacao.getTributo());
					System.out.println(movimentacao.getValor());
					System.out.println(movimentacao.getDatahora());
					System.out.println(movimentacao.getTipo());
					break;
					
				case DEPOSITO:
					System.out.println(movimentacao.getConta());
					System.out.println(movimentacao.getTributo());
					System.out.println(movimentacao.getValor());
					System.out.println(movimentacao.getDatahora());
					System.out.println(movimentacao.getTipo());
					break;

				default:
					break;
				}
			}
		}
	}
}
