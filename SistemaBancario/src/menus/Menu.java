package menus;

import java.util.Scanner;

import contas.Conta;
import contas.ContaPoupanca;
import maps.Maps;
import pessoas.Pessoa;

public class Menu {
	static Scanner ler = new Scanner(System.in);

	public static void menuLogin() {

		boolean login = false;

		do {
			String CPF;
			String senha;

			System.out.println("*****************************");
			System.out.println("*****************************");
			System.out.println("        BANCO GRUPO 3        ");
			System.out.println("*****************************");
			System.out.println("*****************************");
			System.out.println();
			System.out.println("Login:");
			System.out.println();
			System.out.println("Digite o CPF:");
			CPF = ler.nextLine();
			System.out.println("Digite a senha:");
			senha = ler.nextLine();
			System.out.println("______________________________");

			if (Maps.mapCpfPessoa.containsKey(CPF)) {

				if (Maps.mapCpfPessoa.get(CPF).getSenha().equals(senha)) {

					login = true;

					switch (Maps.mapCpfPessoa.get(CPF).getTipo()) {
					case CLIENTE:
						menuCliente(Maps.mapCpfPessoa.get(CPF));
						break;
					case GERENTE:
						System.out.println("GERENTE LOGADO");
						System.out.println("GERENTE LOGADO");
						System.out.println("GERENTE LOGADO");
						break;
					case DIRETOR:
						System.out.println("DIRETOR LOGADO");
						System.out.println("DIRETOR LOGADO");
						System.out.println("DIRETOR LOGADO");
						break;
					case PRESIDENTE:
						System.out.println("PRESIDENTE LOGADO");
						System.out.println("PRESIDENTE LOGADO");
						System.out.println("PRESIDENTE LOGADO");
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
		} while (login == false);

	}

	public static void menuCliente(Pessoa cliente) {
		int opcao;
		opcao = 0;
		do {

			System.out.println("===============");
			System.out.println("1 - Conta Corrente");
			System.out.println("2 - Conta Poupança");
			System.out.println("3 - Conta sair");
			System.out.println("===============");

			opcao = ler.nextInt();
			switch (opcao) {

			case 1:
				
				menuOperacoesDaContaCorrente(cliente);
				break;
				
			
			case 2:
				menuOperacoesDaContaPoupanca(cliente);
				break;
			
			case 3: 
				System.exit(0);
				break;
					
				}
			} while (true);
		}
		public static void menuOperacoesDaContaCorrente(Pessoa cliente) {
			if (Maps.mapCpfContaCorrente.containsKey(cliente.getCpf())) {
			
				int opcaoCc;
				do {
					
				Conta conta = Maps.mapCpfContaCorrente.get(cliente.getCpf());

				System.out.println("Bem vindo a Conta Corrente");
				System.out.println("===============");
				System.out.println("1 - Saque");
				System.out.println("2 - Deposito");
				System.out.println("3 - Transferir");
				System.out.println("4 - Extrato");
				System.out.println("5 - Sair");
				System.out.println("===============");

				opcaoCc = ler.nextInt();
				double valor;

				switch (opcaoCc) {
				case 1:
					System.out.println("Digite o valor para sacar: ");
					valor = ler.nextDouble();
					conta.sacar(valor);
					break;
				case 2:
					System.out.println("Digite o valor para depositar: ");
					valor = ler.nextDouble();
					conta.depositar(valor);
					break;
				case 3:
					System.out.println("Digite o valor para transferir: ");
					valor = ler.nextDouble();
					System.out.println("Informe o número da conta destino: ");
					int contaDestino = ler.nextInt();
					if (Maps.mapNumeroConta.containsKey(contaDestino)) {
						Conta contaRecebe = Maps.mapNumeroConta.get(contaDestino);
						conta.transferir(valor, contaRecebe);
					} else {
						System.out.println("O número da conta não existe");
					}
					break;
				case 4:
					conta.emitirExtrato();
					break;
				case 5:
						
					break;
				default:
					System.out.println("Opção inválida !!");
					break;
				}
				
				
				}while (opcaoCc != 5 );
				
				
			
			}
			else {
				System.out.println("Cliente não possui Conta Corrente !!");
				
	
				
			}	
		}
		
		public static void menuOperacoesDaContaPoupanca(Pessoa cliente) {
			if (Maps.mapCpfContaPoupanca.containsKey(cliente.getCpf())) {
				int opcaoPp;
				Conta conta = Maps.mapCpfContaPoupanca.get(cliente.getCpf());

				System.out.println("Bem vindo a Conta Poupança");
				System.out.println("===============");
				System.out.println("1 - Saque");
				System.out.println("2 - Deposito");
				System.out.println("3 - Transferir");
				System.out.println("4 - Extrato");
				System.out.println("5 - Simulação de rendimento");
				System.out.println("6 - Sair");
				System.out.println("===============");
				
				opcaoPp = ler.nextInt();
				double valor;

				switch (opcaoPp) {
				case 1:
					System.out.println("Digite o valor para sacar: ");
					valor = ler.nextDouble();
					conta.sacar(valor);
					break;
				case 2:
					System.out.println("Digite o valor para depositar: ");
					valor = ler.nextDouble();
					conta.depositar(valor);
					break;
				case 3:
					System.out.println("Digite o valor para transferir: ");
					valor = ler.nextDouble();
					System.out.println("Informe o número da conta destino: ");
					int contaDestino = ler.nextInt();
					if (Maps.mapNumeroConta.containsKey(contaDestino)) {
						Conta contaRecebe = Maps.mapNumeroConta.get(contaDestino);
						conta.transferir(valor, contaRecebe);
					} else {
						System.out.println("O número da conta não existe");
					}
					break;
				case 4:
					conta.emitirExtrato();
					break;
				case 5:
					System.out.println("Digite o valor para simular: ");
					valor = ler.nextDouble();
					System.out.println("Informe quantos dias deseja simular: ");
					int dias;
					dias = ler.nextInt();
					((ContaPoupanca) conta).simular(valor, dias);
					break;
				case 6:
					
					break;
				default:
					System.out.println("Opção inválida !!");
					break;
				}
				
				while(opcaoPp != 6);
					
				
			} else {
				System.out.println("Cliente não possui Conta Poupança !!");
				
			}
		}
	}
		

