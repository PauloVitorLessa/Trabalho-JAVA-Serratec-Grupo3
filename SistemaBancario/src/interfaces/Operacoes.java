package interfaces;

import contas.Conta;

public interface Operacoes {
	public boolean sacar(double valor);
	public boolean depositar(double valor);
	public boolean transferir(double valor, Conta contaDestino);
	public void emitirExtrato();
}
