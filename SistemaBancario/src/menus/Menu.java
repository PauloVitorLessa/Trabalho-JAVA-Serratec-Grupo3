package menus;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

import agencias.Agencia;
import contas.Conta;
import contas.ContaPoupanca;
import entradasEsaidas.Escreve;
import listas.Listas;
import maps.Maps;
import pessoas.Pessoa;
import relatorios.Relatorio;
import utilidades.Arred;
import utilidades.Data;

public class Menu {
	public static Scanner ler = new Scanner(System.in);

	public static void menuLogin() {

		do {
			String CPF;
			String senha;

			System.out.println("*****************************");
			System.out.println("*****************************");
			System.out.println("        BANCO GRUPO 3        ");
			System.out.println("*****************************");
			System.out.println("*****************************");
			System.out.println();
			System.out.println("            LOGIN       ");
			System.out.println();
			System.out.println("Digite o CPF:");
			CPF = ler.nextLine();
			System.out.println("Digite a senha:");
			senha = ler.nextLine();

			if (Maps.mapCpfPessoa.containsKey(CPF)) {

				if (Maps.mapCpfPessoa.get(CPF).getSenha().equals(senha)) {

					System.out.println("\n============================");
					System.out.println("Bem vindo(a), " + Maps.mapCpfPessoa.get(CPF).getNome() + "\n");

					switch (Maps.mapCpfPessoa.get(CPF).getTipo()) {
					case CLIENTE:
						System.out.println("CLIENTE");
						menuCliente(Maps.mapCpfPessoa.get(CPF));
						break;
					case GERENTE:
						System.out.println("GERENTE");
						menuGerente(Maps.mapCpfPessoa.get(CPF));
						break;
					case DIRETOR:
						System.out.println("DIRETOR");
						menuDiretor(Maps.mapCpfPessoa.get(CPF));
						break;
					case PRESIDENTE:
						System.out.println("PRESIDENTE");
						menuPresidente(Maps.mapCpfPessoa.get(CPF));
						break;

					default:
						break;
					}

				} else {
					System.out.println("CPF ou senha Inválidos");
					System.out.println("______________________________\n\n");
				}

			} else {
				System.out.println("CPF ou senha Inválidos");
				System.out.println("______________________________\n\n");
			}
		} while (true);

	}

	public static void menuCliente(Pessoa cliente) {
		int opcao;
		opcao = 0;
		do {

			System.out.println("============================");
			System.out.println("1 - Conta Corrente");
			System.out.println("2 - Conta Poupança");
			System.out.println("3 - Logout");
			System.out.println("============================");

			try {
				opcao = Integer.parseInt(ler.nextLine());
				switch (opcao) {

				case 1:

					menuOperacoesDaContaCorrente(cliente);
					break;

				case 2:
					menuOperacoesDaContaPoupanca(cliente);
					break;

				case 3:
					
					Escreve.salvaRegistros();
					break;

				default:
					System.out.println("Opção inválida !!");
					break;

				}
			} catch (NumberFormatException e) {
				System.out.println("Entrada inválida. Por favor, digite um número inteiro.");

			}
		} while (opcao != 3);
	}

	public static void menuOperacoesDaContaCorrente(Pessoa cliente) {
		if (Maps.mapCpfContaCorrente.containsKey(cliente.getCpf())) {

			int opcaoCc = 0;
			do {

				Conta conta = Maps.mapCpfContaCorrente.get(cliente.getCpf());

				System.out.println("Bem vindo a Conta Corrente");
				System.out.println("============================");
				System.out.println("1 - Saque");
				System.out.println("2 - Deposito");
				System.out.println("3 - Transferir");
				System.out.println("4 - Contratar Seguro de vida");
				System.out.println("5 - Extrato");
				System.out.println("6 - Relatorio de Tributação");
				System.out.println("7 - Saldo");
				System.out.println("8 - Voltar");
				System.out.println("9 - Sair do Sistema");
				System.out.println("============================");

				try {

					opcaoCc = Integer.parseInt(ler.nextLine());
					double valor;

					switch (opcaoCc) {
					case 1:
						System.out.println("Digite o valor para sacar: ");
						valor = Double.parseDouble(ler.nextLine());
						conta.sacar(valor);
						System.out.println();
						break;
					case 2:

						System.out.println("Digite o valor para depositar: ");
						valor = Double.parseDouble(ler.nextLine());
						if (conta.depositar(valor)) {
							System.out.println("Depósito efetuado!\n");
						}
						;
						break;
					case 3:
						System.out.println("Digite o valor para transferir: ");
						valor = Double.parseDouble(ler.nextLine());
						System.out.println("Informe o número da conta destino: ");
						int contaDestino =Integer.parseInt(ler.nextLine());
						if (Maps.mapNumeroConta.containsKey(contaDestino)) {
							Conta contaRecebe = Maps.mapNumeroConta.get(contaDestino);
							conta.transferir(valor, contaRecebe);
						} else {
							System.out.println("O número da conta não existe");
						}
						System.out.println();
						break;
					case 4:
						System.out.println("==========================");
						System.out.println("SEGURO DE VIDA: ");
						System.out.println("==========================");
						System.out.println("Digite o valor a ser segurado: ");
						double valorSeguro = Double.parseDouble(ler.nextLine());
						cliente.contrataSeguro(cliente, valorSeguro);
						break;

					case 5:
						conta.emitirExtrato();
						break;
					case 6:
						Relatorio.relTributacao(conta);

						break;
					case 7:
						Relatorio.relSaldo(conta);

						break;
					case 8:

						break;
					case 9:
						Escreve.salvaRegistros();
						System.out.println("Sistema Encerrado");
						System.exit(0);
						break;
					default:
						System.out.println("Opção inválida !!");
						break;
					}

				} catch (NumberFormatException e) {
					System.out.println("Entrada inválida. Por favor, digite um número.");

				}
			} while (opcaoCc != 8);

		} else {
			System.out.println("Usuario não possui Conta Corrente !!");

		}
	}

	public static void menuOperacoesDaContaPoupanca(Pessoa cliente) {
		if (Maps.mapCpfContaPoupanca.containsKey(cliente.getCpf())) {
			int opcaoPp = 0;

			do {

				Conta conta = Maps.mapCpfContaPoupanca.get(cliente.getCpf());

				System.out.println("Bem vindo a Conta Poupança");
				System.out.println("==========================");
				System.out.println("1 - Saque");
				System.out.println("2 - Deposito");
				System.out.println("3 - Transferir");
				System.out.println("4 - Extrato");
				System.out.println("5 - Simulação de rendimento");
				System.out.println("6 - Saldo");
				System.out.println("7 - Voltar");
				System.out.println("8 - Sair");
				System.out.println("===========================");

				try {

					opcaoPp = Integer.parseInt(ler.nextLine());
					double valor;

					switch (opcaoPp) {
					case 1:

						System.out.println("Digite o valor para sacar: ");
						valor = Double.parseDouble(ler.nextLine());
						conta.sacar(valor);
						System.out.println();

						break;
					case 2:
						System.out.println("Digite o valor para depositar: ");
						valor = Double.parseDouble(ler.nextLine());
						if (conta.depositar(valor)) {
							System.out.println("Depósito efetuado!\n");
						}
						;
						break;
					case 3:
						System.out.println("Digite o valor para transferir: ");
						valor = Double.parseDouble(ler.nextLine());
						System.out.println("Informe o número da conta destino: ");
						int contaDestino =Integer.parseInt(ler.nextLine());
						if (Maps.mapNumeroConta.containsKey(contaDestino)) {
							Conta contaRecebe = Maps.mapNumeroConta.get(contaDestino);
							conta.transferir(valor, contaRecebe);
						} else {
							System.out.println("O número da conta não existe");
						}
						System.out.println();
						break;
					case 4:
						conta.emitirExtrato();
						break;
					case 5:
						System.out.println("Digite o valor para simular: ");
						valor = Double.parseDouble(ler.nextLine());
						System.out.println("Informe quantos dias deseja simular: ");
						int dias;
						dias =Integer.parseInt(ler.nextLine());
						((ContaPoupanca) conta).simular(valor, dias);
						break;
					case 6:
						Relatorio.relSaldo(conta);
					case 7:
						break;

					case 8:
						Escreve.salvaRegistros();
						System.out.println("Sistema Encerrado");
						System.exit(0);
						break;
					default:
						System.out.println("Opção inválida !!");
						break;
					}

				} catch (NumberFormatException e) {
					System.out.println("Entrada inválida. Por favor, digite um número.");

				}

			} while (opcaoPp != 7);

		} else {
			System.out.println("Usuario não possui Conta Poupança !!");

		}
	}

	public static void menuGerente(Pessoa gerente) {
		int opcao;
		opcao = 0;
		do {

			System.out.println("============================");
			System.out.println("1 - Conta Corrente");
			System.out.println("2 - Conta Poupança");
			System.out.println("3 - Relatorio do número de contas gerenciadas");
			System.out.println("4 - Logout");
			System.out.println("============================");

			try {

				opcao = Integer.parseInt(ler.nextLine());
				switch (opcao) {

				case 1:

					menuOperacoesDaContaCorrente(gerente);
					break;

				case 2:
					menuOperacoesDaContaPoupanca(gerente);
					break;

				case 3:
					Date data = new Date();
					String dataComEspaco = Data.dataHora(data);
					System.out.println("-----------------------------------------------------------------------");
					System.out.println("### RELATÓRIO DO NÚMERO DE CONTAS GERENCIADAS ###\n");
					System.out.println("GERENTE: " + gerente.getNome());
					System.out.println("CPF: " + gerente.getCpf());
					System.out.println("NÚMERO DE CONTAS GERENCIADAS: "
							+ Maps.mapCpfGerenteAgencia.get(gerente.getCpf()).getContas());
					System.out.println("DATA: " + dataComEspaco);
					System.out.println("-----------------------------------------------------------------------");

					try {

						String dataSemEspaco = Data.dataHoraSemEspaco(data);
						String path = ".\\arquivos\\Relatorio-gerente-" + dataSemEspaco + ".txt";
						FileWriter fw = new FileWriter(path, true);
						PrintWriter pw = new PrintWriter(fw);
						pw.println("-----------------------------------------------------------------------");
						pw.println("### RELATÓRIO DO NÚMERO DE CONTAS GERENCIADAS ###\n");
						pw.println("GERENTE: " + gerente.getNome());
						pw.println("CPF: " + gerente.getCpf());
						pw.println("NÚMERO DE CONTAS GERENCIADAS: "
								+ Maps.mapCpfGerenteAgencia.get(gerente.getCpf()).getContas());
						pw.println("DATA: " + dataComEspaco);
						pw.println("-----------------------------------------------------------------------");
						pw.flush();
						pw.close();
						fw.close();
					} catch (IOException e) {

						e.printStackTrace();
					}
					break;

				case 4:
					
					Escreve.salvaRegistros();
					break;

				default:
					System.out.println("Opção inválida !!");
					break;

				}

			} catch (NumberFormatException e) {
				System.out.println("Entrada inválida. Por favor, digite um número inteiro.");

			}

		} while (opcao != 4);
	}

	public static void menuDiretor(Pessoa diretor) {
		int opcao;
		opcao = 0;
		do {

			System.out.println("============================");
			System.out.println("1 - Conta Corrente");
			System.out.println("2 - Conta Poupança");
			System.out.println("3 - Relatorio de Clientes");
			System.out.println("4 - Logout");
			System.out.println("============================");

			try {

				opcao = Integer.parseInt(ler.nextLine());
				switch (opcao) {

				case 1:

					menuOperacoesDaContaCorrente(diretor);
					break;

				case 2:
					menuOperacoesDaContaPoupanca(diretor);
					break;

				case 3:
					relatorioDiretor();
					break;

				case 4:
					
					Escreve.salvaRegistros();
					break;

				default:
					System.out.println("Opção inválida !!");
					break;

				}

			} catch (NumberFormatException e) {
				System.out.println("Entrada inválida. Por favor, digite um número inteiro.");

			}
		} while (opcao != 4);
	}

	public static void menuPresidente(Pessoa presidente) {
		int opcao;
		opcao = 0;
		do {

			System.out.println("============================");
			System.out.println("1 - Conta Corrente");
			System.out.println("2 - Conta Poupança");
			System.out.println("3 - Relatorio de Clientes");
			System.out.println("4 - Relatorio valor armazenado");
			System.out.println("5 - Logout");
			System.out.println("============================");

			try {
				opcao = Integer.parseInt(ler.nextLine());
				switch (opcao) {

				case 1:
					menuOperacoesDaContaCorrente(presidente);
					break;

				case 2:
					menuOperacoesDaContaPoupanca(presidente);
					break;

				case 3:
					relatorioDiretor();
					break;

				case 4:
					relatorioPresidente();
					break;

				case 5:
				
					Escreve.salvaRegistros();
					break;

				default:
					System.out.println("Opção inválida !!");
					break;

				}

			} catch (NumberFormatException e) {
				System.out.println("Entrada inválida. Por favor, digite um número inteiro.");

			}
		} while (opcao != 5);
	}

	public static void relatorioDiretor() {
		String data = Data.dataHora(new Date());
		String dataSemEspaco = Data.dataHoraSemEspaco(new Date());

		System.out.println("RELATORIO DE CLIENTES\n");
		System.out.println(data);
		System.out.println("-----------------------------------------------------------------------");
		System.out.println("  AGÊNCIA     CPF    NOME    ");
		System.out.println("-----------------------------------------------------------------------");
		Map<String, Agencia> map = Maps.mapCpfPessoaAgencia;
		List<Map.Entry<String, Agencia>> lista = new ArrayList<>(map.entrySet());
		Collections.sort(lista, new Comparator<Map.Entry<String, Agencia>>() {

			@Override
			public int compare(Entry<String, Agencia> o1, Entry<String, Agencia> o2) {
				return Maps.mapCpfPessoa.get(o1.getKey()).getNome()
						.compareToIgnoreCase(Maps.mapCpfPessoa.get(o2.getKey()).getNome());
			}

		});

		for (Map.Entry<String, Agencia> valor : lista) {

			System.out.println("     " + valor.getValue().getNumeroAgencia() + "        " + valor.getKey() + "    "
					+ Maps.mapCpfPessoa.get(valor.getKey()).getNome());
		}

		try {

			String path = ".\\arquivos\\Relatorio-diretor-" + dataSemEspaco + ".txt";
			FileWriter fw = new FileWriter(path, true);
			PrintWriter pw = new PrintWriter(fw);
			pw.println("-----------------------------------------------------------------------");
			pw.println("RELATORIO DE CLIENTES\n");
			pw.println(data);
			pw.println("-----------------------------------------------------------------------");
			pw.println("  AGÊNCIA     CPF    NOME    ");
			pw.println("-----------------------------------------------------------------------");
			for (Map.Entry<String, Agencia> valor : lista) {
				pw.println("     " + valor.getValue().getNumeroAgencia() + "        " + valor.getKey() + "    "
						+ Maps.mapCpfPessoa.get(valor.getKey()).getNome());
			}
			pw.flush();
			pw.close();
			fw.close();
		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	public static void relatorioPresidente() {
		String data = Data.dataHora(new Date());
		String dataSemEspaco = Data.dataHoraSemEspaco(new Date());

		double valorConta = 0.0;
		double valorTributo = 0.0;

		for (Conta conta : Listas.conta) {
			valorConta += conta.getSaldo();
			valorTributo += conta.getTotalTributo();
		}

		System.out.println("RELATORIO DE VALORES NO BANCO\n");
		System.out.println(data);
		System.out.println("-----------------------------------------------------------------------");
		System.out.println("  VALOR CONTAS  ");
		System.out.println(Arred.dois(valorConta, 2));
		System.out.println("-----------------------------------------------------------------------");
		System.out.println("  VALOR TRIBUTO  ");
		System.out.println(Arred.dois(valorTributo, 2));
		System.out.println("-----------------------------------------------------------------------");
		System.out.println("  VALOR TOTAL  ");
		System.out.println(Arred.dois(valorTributo + valorConta, 2));

		try {

			String path = ".\\arquivos\\Relatorio-Presidenter-" + dataSemEspaco + ".txt";
			FileWriter fw = new FileWriter(path, true);
			try (PrintWriter pw = new PrintWriter(fw)) {
				pw.println("RELATORIO DE VALORES NO BANCO\n");
				pw.println(data);
				pw.println("-----------------------------------------------------------------------");
				pw.println("  VALOR CONTAS  ");
				pw.println(Arred.dois(valorConta, 2));
				pw.println("-----------------------------------------------------------------------");
				pw.println("  VALOR TRIBUTO  ");
				pw.println(Arred.dois(valorTributo, 2));
				pw.println("-----------------------------------------------------------------------");
				pw.println("  VALOR TOTAL  ");
				pw.println(Arred.dois(valorTributo + valorConta, 2));
			}
		} catch (IOException e) {

			e.printStackTrace();
		}
	}
}
