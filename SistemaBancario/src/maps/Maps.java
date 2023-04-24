package maps;

import java.util.HashMap;
import java.util.Map;

import agencias.Agencia;
import contas.Conta;
import pessoas.Gerente;
import pessoas.Pessoa;
import seguros.Seguro;

public class Maps {
	
	public static Map<String, Pessoa> mapCpfPessoa = new HashMap<>();
	public static Map<Integer, Agencia> mapNumeroAgencia = new HashMap<>();
	public static Map<String, Gerente> mapCpfGerente = new HashMap<>();
	public static Map<Integer, Conta> mapNumeroConta = new HashMap<>();
	public static Map<Integer, String> mapAgenciaGerente = new HashMap<>();
	public static Map<String, Conta> mapCpfContaCorrente = new HashMap<>();
	public static Map<String, Conta> mapCpfContaPoupanca = new HashMap<>();
	public static Map<String, Agencia> mapCpfGerenteAgencia = new HashMap<>();
	public static Map<String, Agencia> mapCpfPessoaAgencia = new HashMap<>();
	public static Map<String, Seguro> mapCpfSeguro = new HashMap<>();
	
}
