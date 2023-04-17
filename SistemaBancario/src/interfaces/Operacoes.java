package interfaces;

import contas.Conta;

public interface Operacoes {
	public void sacar(double valor);
	public void depositar(double valor);
	public void transferir(double valor, Conta contaDestino);
	public void emitirExtrato();
}
