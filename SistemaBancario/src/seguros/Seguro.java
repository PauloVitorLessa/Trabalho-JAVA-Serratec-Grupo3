package seguros;

import enuns.MovimentosEnum;
import pessoas.Pessoa;

public class Seguro {
	
	private MovimentosEnum tipo = MovimentosEnum.SEGURO;
	private int numero;
	private static int contador;
	private Pessoa cliente;
	private double valorSeguro;
	private double valorTributo;
	
	public Seguro(Pessoa cliente, double valorSeguro) {
		contador++;
		this.numero = contador;
		this.cliente = cliente;
		this.valorSeguro = valorSeguro;
		this.valorTributo = 0.2*valorSeguro;
	}

	public Pessoa getCliente() {
		return cliente;
	}

	public void setCliente(Pessoa cliente) {
		this.cliente = cliente;
	}

	public double getValorSeguro() {
		return valorSeguro;
	}

	public void setValorSeguro(double valorSeguro) {
		this.valorSeguro = valorSeguro;
	}

	public double getValorTributo() {
		return valorTributo;
	}

	public void setValorTributo(double valorTributo) {
		this.valorTributo = valorTributo;
	}

	public int getNumero() {
		return numero;
	}

	public MovimentosEnum getTipo() {
		return tipo;
	}
	
	
	
	
	
	
	
	

}