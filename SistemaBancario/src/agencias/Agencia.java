package agencias;

import java.util.ArrayList;
import java.util.List;

import contas.Conta;
import enuns.AgenciaEnum;
import pessoas.Gerente;

public class Agencia {
	
	private int numeroAgencia;
	private Gerente gerente;
	private List<Conta> contas = new ArrayList<>();
	private static int contador = 0;
	private AgenciaEnum tipo = AgenciaEnum.AGENCIA;
	
	public Agencia(Gerente gerente, List<Conta> contas) {
		this.gerente = gerente;
		this.contas = contas;
		contador++;
	}
	public Agencia() {
		contador++;
		this.numeroAgencia = contador;
	}
	public AgenciaEnum getTipo() {
		return tipo;
	}
	public Agencia(Gerente gerente) {
		this.gerente = gerente;
		contador++;
		this.numeroAgencia = contador;
	}
	public int getNumeroAgencia() {
		return numeroAgencia;
	}
	public Gerente getGerente() {
		return gerente;
	}
	public void setGerente(Gerente gerente) {
		this.gerente = gerente;
	}
	public List<Conta> getContas() {
		return contas;
	}
	public void addConta(Conta conta){
		this.contas.add(conta);
	}
	
}
