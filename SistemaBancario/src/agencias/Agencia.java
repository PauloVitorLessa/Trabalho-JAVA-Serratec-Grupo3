package agencias;

import enuns.AgenciaEnum;

public class Agencia {
	
	private int numeroAgencia;
	private String gerente;
	private int contas=0;
	private static int contador = 0;
	private AgenciaEnum tipo = AgenciaEnum.AGENCIA;
	
	
	public Agencia() {
		contador++;
		this.numeroAgencia = contador;
	}
	public AgenciaEnum getTipo() {
		
		return tipo;
	}
	public Agencia(String gerente) {
		this.gerente = gerente;
		contador++;
		this.numeroAgencia = contador;
	}
	public int getNumeroAgencia() {
		return numeroAgencia;
	}
	public String getGerente() {
		return gerente;
	}
	public void setGerente(String gerente) {
		this.gerente = gerente;
	}
	
	public void addConta() {
		this.contas += 1;
		
	}
	public int getContas() {
		return contas;
	}
	public void setContas(int contas) {
		this.contas = contas;
	}
		
		
	
}
