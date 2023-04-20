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
import contas.ContaCorrente;
import contas.ContaPoupanca;
import listas.Listas;
import maps.Maps;
import pessoas.Pessoa;
import relatorios.Relatorio;
import utilidades.Arred;
import utilidades.Data;

public class Menu {
	public static Scanner ler = new Scanner(System.in);

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
			System.out.println("            LOGIN       ");
			System.out.println();
			System.out.println("Digite o CPF:");
			CPF = ler.nextLine();
			System.out.println("Digite a senha:");
			senha = ler.nextLine();			

			if (Maps.mapCpfPessoa.containsKey(CPF)) {

				if (Maps.mapCpfPessoa.get(CPF).getSenha().equals(senha)) {
					
					System.out.println("\n============================");
					System.out.println("Bem vindo(a), " + Maps.mapCpfPessoa.get(CPF).getNome()+"\n");
					login = true;

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
		} while (login == false);

	}

	public static void menuCliente(Pessoa cliente) {
		int opcao;
		opcao = 0;
		do {

			System.out.println("============================");
			System.out.println("1 - Conta Corrente");
			System.out.println("2 - Conta Poupança");
			System.out.println("3 - sair");
			System.out.println("============================");

			opcao = ler.nextInt();
			switch (opcao) {

			case 1:
				
				menuOperacoesDaContaCorrente(cliente);
				break;
				
			
			case 2:
				menuOperacoesDaContaPoupanca(cliente);
				break;
			
			case 3:
				System.out.println("Sistema Encerrado");
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
				System.out.println("============================");
				System.out.println("1 - Saque");
				System.out.println("2 - Deposito");
				System.out.println("3 - Transferir");
				System.out.println("4 - Extrato");
				System.out.println("5 - Relatorio de Tributação");
				System.out.println("6 - Saldo");
				System.out.println("7 - Voltar");
				System.out.println("8 - Sair do Sistema");
				System.out.println("============================");

				opcaoCc = ler.nextInt();
				double valor;

				switch (opcaoCc) {
				case 1:
					System.out.println("Digite o valor para sacar: ");
					valor = ler.nextDouble();
					conta.sacar(valor);
					System.out.println();
					break;
				case 2:
					System.out.println("Digite o valor para depositar: ");
					valor = ler.nextDouble();
					if(conta.depositar(valor)) {
						System.out.println("Depósito efetuado!\n");
					};
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
					System.out.println();
					break;
				case 4:
					conta.emitirExtrato();
					break;
				case 5:
					Relatorio.relTributacao(conta);
						
					break;
				case 6:
					Relatorio.relSaldo(conta);
					
					break;
				case 7:
					
					break;
				case 8:
					System.out.println("Sistema Encerrado");
					System.exit(0);
					break;
				default:
					System.out.println("Opção inválida !!");
					break;
				}
				
				
				}while (opcaoCc != 7 );
				
				
			
			}
			else {
				System.out.println("Usuario não possui Conta Corrente !!");
				
	
				
			}	
		}
		
		public static void menuOperacoesDaContaPoupanca(Pessoa cliente) {
			if (Maps.mapCpfContaPoupanca.containsKey(cliente.getCpf())) {
				int opcaoPp;
				
				do {				
				
				Conta conta = Maps.mapCpfContaPoupanca.get(cliente.getCpf());				

				System.out.println("Bem vindo a Conta Poupança");
				System.out.println("==========================");
				System.out.println("1 - Saque");
				System.out.println("2 - Deposito");
				System.out.println("3 - Transferir");
				System.out.println("4 - Extrato");
				System.out.println("5 - Simulação de rendimento");
				System.out.println("6 - Sair");
				System.out.println("===========================");
				
				opcaoPp = ler.nextInt();
				double valor;

				switch (opcaoPp) {
				case 1:
					System.out.println("Digite o valor para sacar: ");
					valor = ler.nextDouble();
					conta.sacar(valor);
					System.out.println();
					break;
				case 2:
					System.out.println("Digite o valor para depositar: ");
					valor = ler.nextDouble();
					if(conta.depositar(valor)) {
						System.out.println("Depósito efetuado!\n");
					};
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
					System.out.println();
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
				
				}while(opcaoPp != 6);
					
				
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
				System.out.println("4 - sair");
				System.out.println("============================");

				opcao = ler.nextInt();
				switch (opcao) {

				case 1:
					
					menuOperacoesDaContaCorrente(gerente);
					break;
					
				
				case 2:
					menuOperacoesDaContaPoupanca(gerente);
					break;
					
				case 3:
					try {
						System.out.println("O número total de contas desta agência é: " +
					            Maps.mapCpfGerenteAgencia.get(gerente.getCpf()).getContas());
			String data = Data.dataHora(new Date());
			String dataSemEspaco = Data.dataHoraSemEspaco(new Date());
			String path = ".\\arquivos\\Relatorio-gerente-"+dataSemEspaco+".txt";
			FileWriter fw = new FileWriter(path, true);
			PrintWriter pw = new PrintWriter(fw);
			pw.println("-----------------------------------------------------------------------");
			pw.println("O número total de contas gerenciadas é: " +
		               Maps.mapCpfGerenteAgencia.get(gerente.getCpf()).getContas());
			pw.println(data);
			pw.println("-----------------------------------------------------------------------");
			pw.flush();
			pw.close();
			fw.close();
					} catch(IOException e) {
						
						e.printStackTrace();
					}					
					break;
				
				case 4:
					System.out.println("Sistema Encerrado");
					System.exit(0);
					break;
						
					}
				} while (true);
			}
		
		public static void menuDiretor(Pessoa diretor) {
			int opcao;
			opcao = 0;
			do {

				System.out.println("============================");
				System.out.println("1 - Conta Corrente");
				System.out.println("2 - Conta Poupança");
				System.out.println("3 - Relatorio de Clientes");
				System.out.println("4 - sair");
				System.out.println("============================");

				opcao = ler.nextInt();
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
					System.out.println("Sistema Encerrado");
					System.exit(0);
					break;
						
					}
				} while (true);
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
				System.out.println("5 - sair");
				System.out.println("============================");

				opcao = ler.nextInt();
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
					System.out.println("Sistema Encerrado");
					System.exit(0);
					break;
						
					}
				} while (true);
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
			List <Map.Entry<String, Agencia>> lista = new ArrayList<>(map.entrySet());
			Collections.sort(lista, new Comparator<Map.Entry<String, Agencia>>(){

				@Override
				public int compare(Entry<String, Agencia> o1, Entry<String, Agencia> o2) {
					return Maps.mapCpfPessoa.get(o1.getKey()).getNome().compareToIgnoreCase(Maps.mapCpfPessoa.get(o2.getKey()).getNome());
				}
				
			});	

		    for(Map.Entry<String, Agencia> valor: lista){
		    	
		    	System.out.println("     "+valor.getValue().getNumeroAgencia() +
		    			           "        "+valor.getKey()+ "    " +
		    			           Maps.mapCpfPessoa.get(valor.getKey()).getNome());    
		    }			    	
			
			try {
				
				String path = ".\\arquivos\\Relatorio-diretor-"+dataSemEspaco+".txt";
				FileWriter fw = new FileWriter(path, true);
				PrintWriter pw = new PrintWriter(fw);
				pw.println("-----------------------------------------------------------------------");
				pw.println("O RELATORIO DE CLIENTES\n");
				pw.println(data);
				pw.println("-----------------------------------------------------------------------");
				pw.println("  AGÊNCIA     CPF    NOME    ");
				pw.println("-----------------------------------------------------------------------");
				for(Map.Entry<String, Agencia> valor: lista) {
				pw.println("     "+valor.getValue().getNumeroAgencia() +
    			           "        "+valor.getKey()+ "    " +
    			           Maps.mapCpfPessoa.get(valor.getKey()).getNome());
				}
				pw.flush();
				pw.close();
				fw.close();
			} catch(IOException e) {
				
				e.printStackTrace();
			}					
		
		}			
			
		public static void relatorioPresidente() {
			double valorConta = 0.0;
			double valorTributo = 0.0;
			
			for(Conta conta:Listas.conta) {
				valorConta += conta.getSaldo();
				valorTributo += conta.getTotalTributo();
			}
			String data = Data.dataHora(new Date());
			String dataSemEspaco = Data.dataHoraSemEspaco(new Date());
			
			System.out.println("RELATORIO DE VALORES NO BANCO\n");
			System.out.println(data);					
			System.out.println("-----------------------------------------------------------------------");
			System.out.println("  VALOR CONTAS  ");
			System.out.println(Arred.dois(valorConta, 2));
			System.out.println("-----------------------------------------------------------------------");
			System.out.println("  VALOR TRIBUTO  ");
			System.out.println(Arred.dois(valorTributo, 2));
			System.out.println("-----------------------------------------------------------------------");
			System.out.println("  VALOR LÍQUIDO  ");
			System.out.println(Arred.dois(valorTributo+valorConta, 0));
			
		}
}
		

		

