package contas;

import agencias.Agencia;
import pessoas.Pessoa;

public class ContaPoupanca extends Conta{
	private double rendimento;
	
	public ContaPoupanca(Agencia agencia, Pessoa pessoa) {
		super(agencia, pessoa, ContaEnum.POUPANCA);	
	}
	@Override
	public void sacar(double valor) {
		if(this.getSaldo() >= valor && valor > 0) {
			this.setSaldo(this.getSaldo() - valor);
			System.out.println("Seu saque foi efetuado! ");
			System.out.println("O seu saldo é de: R$"+this.getSaldo());
		}else {
			System.out.println("O saque não pode ser realizado!");	
		}
	}
	@Override
	public void depositar(double valor) {
		if(valor > 0) {
			this.setSaldo(this.getSaldo()+valor);
			System.out.println("Deposito efetuado!");
		}else {
			System.out.println("Não foi possível realizar o depósito!");
		}		
	}
	@Override
	public void transferir(double valor, Conta contaDestino) {
		if(valor > 0 && this.getSaldo()>=valor) {
			setSaldo(getSaldo() - valor);
			contaDestino.depositar(valor);
			System.out.println("Transferência concluída!");
		}else {
			System.out.println("Saldo insuficiente para fazer a transferência!!");
		}	
	}
	@Override
	public void emitirExtrato() {
		
	}
}
