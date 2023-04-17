package contas;

import java.text.SimpleDateFormat;
import java.util.Date;

import agencias.Agencia;
import pessoas.Pessoa;

public class ContaCorrente extends Conta{
	
	private double tributo;
	
	public ContaCorrente(Agencia agencia, Pessoa pessoa) {
		super(agencia, pessoa, ContaEnum.CORRENTE);
		
	}
	public void sacar(double valor) {
		if(this.getSaldo() >= valor && valor > 0) {
			this.setSaldo(this.getSaldo() - valor);
			System.out.println("Seu saque foi efetuado! ");
			System.out.println("O seu saldo é de: R$"+this.getSaldo());
		}else {
			System.out.println("O saque não pode ser realizado!");	
		}
	}
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
		System.out.println("### Extrato da Conta Corrente ###");
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Date date = new Date();
		System.out.println("Data: " + sdf.format(date));
		System.out.println("Saldo: " + this.getSaldo());
	}
}
