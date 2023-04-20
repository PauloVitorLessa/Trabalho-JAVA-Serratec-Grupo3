package contas;

import agencias.Agencia;
import enuns.ContaEnum;
import interfaces.Operacoes;
import pessoas.Pessoa;

public abstract class Conta implements Operacoes{
	
	private int numeroConta;
	private double saldo;
	private Agencia agencia;
	private Pessoa pessoa;
	private ContaEnum tipo;
	private static int contadorNumConta;
	private double totalTributo=0;
	
	public Conta(Agencia agencia, Pessoa pessoa, ContaEnum tipo) {
		
		this.agencia = agencia;
		this.pessoa = pessoa;
		this.tipo = tipo;
		contadorNumConta++;
		this.numeroConta = contadorNumConta;
	}
	public int getNumeroConta() {
		return numeroConta;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}
	public double getSaldo() {
		return saldo;
	}
	public Agencia getAgencia() {
		return agencia;
	}
	public void setAgencia(Agencia agencia) {
		this.agencia = agencia;
	}
	public Pessoa getPessoa() {
		return pessoa;
	}
	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}
	public ContaEnum getTipo() {
		return tipo;
	}
	public void setTotaltributo(double valor) {
		this.totalTributo+=valor;
	}
	
}
