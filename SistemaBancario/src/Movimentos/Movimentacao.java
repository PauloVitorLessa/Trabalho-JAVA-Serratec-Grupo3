package Movimentos;

import java.util.Date;

import enuns.MovimentosEnum;

public class Movimentacao {
	
	private int numeroConta;
	private int numeroContaDestino;
	private MovimentosEnum tipo;
	private double valor;
	private double tributo;
	private Date datahora;
	
	
	
public Movimentacao(int numeroConta, int numeroContaDestino, MovimentosEnum tipo, double valor, double tributo,
			Date datahora) {
		
		this.numeroConta = numeroConta;
		this.numeroContaDestino = numeroContaDestino;
		this.tipo = tipo;
		this.valor = valor;
		this.tributo = tributo;
		this.datahora = datahora;
	}
public Movimentacao(int numeroConta, MovimentosEnum tipo, double valor, double tributo) {
	this.numeroConta = numeroConta;
	this.tipo = tipo;
	this.valor = valor;
	this.tributo = tributo;
	this.datahora = new Date();
	}
public Movimentacao(int numeroConta, MovimentosEnum tipo, double valor, double tributo, int numeroContaDestino) {
	this.numeroConta = numeroConta;
	this.numeroContaDestino = numeroContaDestino;
	this.tipo = tipo;
	this.valor = valor;
	this.tributo = tributo;
	this.datahora = new Date();
	}

public int getConta() {
	return numeroConta;
}

public MovimentosEnum getTipo() {
	return tipo;
}

public double getValor() {
	return valor;
}

public double getTributo() {
	return tributo;
}

public Date getDatahora() {
	return datahora;
}
public int getNumeroContaDestino() {
	return numeroContaDestino;
}

	
	
	

}
