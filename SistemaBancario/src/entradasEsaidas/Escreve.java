package entradasEsaidas;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import Movimentos.Movimentacao;
import agencias.Agencia;
import contas.Conta;
import contas.ContaCorrente;
import contas.ContaPoupanca;
import enuns.Cargo;
import enuns.ContaEnum;
import enuns.MovimentosEnum;
import listas.Listas;
import maps.Maps;
import pessoas.Cliente;
import pessoas.Diretor;
import pessoas.Gerente;
import pessoas.Pessoa;
import pessoas.Presidente;
import utilidades.Arred;
import utilidades.Data;

public class Escreve {

	public static void extratoCorrente(int numConta, String titular, double saldo) {

		try {
			double totalValor = 0;
			double totalTributo = 0;
			String data = Data.dataHora(new Date());
			String dataSemEspaco = Data.dataHoraSemEspaco(new Date());
			String path = ".\\arquivos\\Extrato-Corrente-" + numConta + "-" + titular + "-" + dataSemEspaco + ".txt";
			FileWriter fw = new FileWriter(path, true);
			PrintWriter pw = new PrintWriter(fw);
			pw.println("### Extrato da Conta Corrente ###\n");
			pw.println("Número da conta: " + numConta);
			pw.println("Data: " + data);
			pw.println("Saldo: R$ " + Arred.dois(saldo, 2));
			pw.println("-----------------------------------------------------------------------");
			pw.println("  TIPO         VALOR     TRIBUTO    C. DESTINO     DATA");
			pw.println("-----------------------------------------------------------------------");
			for (Movimentacao movimentacao : Listas.movimentacao) {
				if (movimentacao.getConta() == numConta) {
					switch (movimentacao.getTipo()) {
					case TRANSFERENCIA:
						pw.println(movimentacao.getTipo() + "  -" + movimentacao.getValor() + "       "
								+ movimentacao.getTributo() + "          " + movimentacao.getNumeroContaDestino()
								+ "          " + movimentacao.getDatahora() + "\n");
						totalValor -= movimentacao.getValor();
						totalTributo += movimentacao.getTributo();
						break;
					case SAQUE:
						pw.println(movimentacao.getTipo() + "          -" + movimentacao.getValor() + "       "
								+ movimentacao.getTributo() + "                     "
								+ movimentacao.getDatahora() + "\n");
						totalValor -= movimentacao.getValor();
						totalTributo += movimentacao.getTributo();
						break;

					case DEPOSITO:
						pw.println(movimentacao.getTipo() + "       +" + movimentacao.getValor() + "       "
								+ movimentacao.getTributo() + "                     "
								+ movimentacao.getDatahora() + "\n");
						totalValor += movimentacao.getValor();
						totalTributo += movimentacao.getTributo();
						break;

					default:

						break;
					}
				}
			}
			pw.println("-----------------------------------------------------------------------");
			pw.println("TOTAL:       R$ " + Arred.dois(totalValor, 2) + "    R$ " + Arred.dois(totalTributo, 2) + "\n");
			pw.println("Saldo: R$ " + Arred.dois(saldo, 2));
			pw.flush();
			pw.close();
			fw.close();

		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	public static void extratoPoupanca(int numConta, String titular, double saldo) {

		try {
			double totalValor = 0;
			String data = Data.dataHora(new Date());
			String dataSemEspaco = Data.dataHoraSemEspaco(new Date());
			String path = ".\\arquivos\\Extrato-Poupanca-" + numConta + "-" + titular + "-" + dataSemEspaco + ".txt";
			FileWriter fw = new FileWriter(path, true);
			PrintWriter pw = new PrintWriter(fw);
			pw.println("### Extrato da Conta Poupança ###\n");
			pw.println("Número da conta: " + numConta);
			pw.println("Data: " + data);
			pw.println("Saldo: R$ " + Arred.dois(saldo, 2));
			pw.println("-----------------------------------------------------------------------");
			pw.println("  TIPO         VALOR         C. DESTINO     DATA");
			pw.println("-----------------------------------------------------------------------");
			for (Movimentacao movimentacao : Listas.movimentacao) {
				if (movimentacao.getConta() == numConta) {
					switch (movimentacao.getTipo()) {
					case TRANSFERENCIA:
						pw.println(movimentacao.getTipo() + "  -" + movimentacao.getValor() + "         "
								+ movimentacao.getNumeroContaDestino() + "          "
								+ movimentacao.getDatahora() + "\n");
						totalValor -= movimentacao.getValor();
						break;
					case SAQUE:
						pw.println(movimentacao.getTipo() + "          -" + movimentacao.getValor() + "         "
								+ movimentacao.getDatahora() + "\n");
						totalValor -= movimentacao.getValor();
						break;

					case DEPOSITO:
						pw.println(movimentacao.getTipo() + "       +" + movimentacao.getValor() + "         "
								+ movimentacao.getDatahora() + "\n");
						totalValor += movimentacao.getValor();
						break;

					default:

						break;
					}
				}
			}
			pw.println("-----------------------------------------------------------------------");
			pw.println("TOTAL:       R$ " + Arred.dois(totalValor, 2) + "\n");
			pw.println("Saldo: R$ " + Arred.dois(saldo, 2));
			pw.flush();
			pw.close();
			fw.close();

		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	public static void salvaRegistros() {

		try {
			String path = ".\\arquivos\\Objetos.txt";
			FileWriter fw = new FileWriter(path);
			PrintWriter pw = new PrintWriter(fw);

			for (Agencia agencia : Listas.agencia) {
				pw.println(agencia.getTipo() + ";" + agencia.getGerente() + ";" + agencia.getContas());
			}
			for (Pessoa pessoa : Listas.pessoa) {
				if (pessoa.getTipo() == Cargo.GERENTE) {
					pw.println(pessoa.getTipo() + ";" + pessoa.getNome() + ";" + pessoa.getCpf() + ";"
							+ pessoa.getSenha() + ";" + ((Gerente) pessoa).getAgencia().getNumeroAgencia());
				} else {
					pw.println(pessoa.getTipo() + ";" + pessoa.getNome() + ";" + pessoa.getCpf() + ";"
							+ pessoa.getSenha());
				}
			}
			for (Conta conta : Listas.conta) {
				if (conta.getTipo() == ContaEnum.CORRENTE) {
					pw.println(conta.getTipo() + ";" + conta.getSaldo() + ";" + conta.getAgencia().getNumeroAgencia()
							+ ";" + conta.getPessoa().getCpf() + ";" + conta.getTotalTributo());
				} else {
					pw.println(conta.getTipo() + ";" + conta.getSaldo() + ";" + conta.getAgencia().getNumeroAgencia()
							+ ";" + conta.getPessoa().getCpf());
				}
			}

			for (Movimentacao movimento : Listas.movimentacao) {
							

				pw.println(movimento.getTipo() + ";" + movimento.getConta() + ";" + movimento.getNumeroContaDestino()
						+ ";" + movimento.getValor() + ";" + movimento.getTributo() + ";" + movimento.getDatahora());

			}

			pw.flush();
			pw.close();
			fw.close();
		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	public static void Ler() {		

		String path = ".\\arquivos\\Objetos.txt";

		try (BufferedReader br = new BufferedReader(new FileReader(path))) {
			String itens;
			while(true){
				itens = br.readLine();
				if(itens != null) {

//instancia Objetos
					
				String[] campos = itens.split(";");

				//for (String campo : campos) {

					switch (campos[0]) {

					case "PRESIDENTE":

						Pessoa p1 = new Presidente(campos[1], campos[2], campos[3]);
						Listas.pessoa.add(p1);
						Maps.mapCpfPessoa.put(campos[2], p1);

						break;

//[====p===================================
					case "AGENCIA":

						Agencia a1 = new Agencia(campos[1], Integer.parseInt(campos[2]));
						Listas.agencia.add(a1);
						Maps.mapNumeroAgencia.put(a1.getNumeroAgencia(), a1);
						break;

//=======d==================================					
					case "DIRETOR":

						Pessoa d1 = new Diretor(campos[1], campos[2], campos[3]);
						Listas.pessoa.add(d1);
						Maps.mapCpfPessoa.put(campos[2], d1);

						break;
//======g=======================================
					case "GERENTE":

						Pessoa g1 = new Gerente(campos[1], campos[2], campos[3], Maps.mapNumeroAgencia.get(Integer.parseInt(campos[4])));
						Listas.pessoa.add(g1);
						Maps.mapCpfPessoa.put(campos[2], g1);
						Maps.mapCpfGerenteAgencia.put(campos[2], Maps.mapNumeroAgencia.get(Integer.parseInt(campos[4])));
						Maps.mapAgenciaGerente.put(Integer.parseInt(campos[4]), campos[2]);

						break;

//================================================
					case "CLIENTE":

						Pessoa c1 = new Cliente(campos[1], campos[2], campos[3]);
						Listas.pessoa.add(c1);
						Maps.mapCpfPessoa.put(campos[2], c1);

						break;
//==============================================
					case "CORRENTE":
//lembrete
						Conta cc1 = new ContaCorrente(Double.parseDouble(campos[1]),
								Maps.mapNumeroAgencia.get(Integer.parseInt(campos[2])), Maps.mapCpfPessoa.get(campos[3]),
								Double.parseDouble(campos[4]));

						Listas.conta.add(cc1);
						Maps.mapNumeroConta.put(cc1.getNumeroConta(), cc1);
						Maps.mapCpfContaCorrente.put(campos[3], cc1);
						Maps.mapNumeroAgencia.put(Integer.parseInt(campos[2]), Maps.mapNumeroAgencia.get(Integer.parseInt(campos[2])));
						Maps.mapCpfPessoaAgencia.put(campos[3], Maps.mapNumeroAgencia.get(Integer.parseInt(campos[2])));

						break;
//=============================================
					case "POUPANCA":

						Conta cp1 = new ContaPoupanca(Double.parseDouble(campos[1]),
								Maps.mapNumeroAgencia.get(Integer.parseInt(campos[2])), Maps.mapCpfPessoa.get(campos[3]));
						Listas.conta.add(cp1);
						Maps.mapNumeroConta.put(cp1.getNumeroConta(), cp1);
						Maps.mapCpfContaPoupanca.put(campos[3], cp1);
						Maps.mapNumeroAgencia.put(Integer.parseInt(campos[2]), Maps.mapNumeroAgencia.get(Integer.parseInt(campos[2])));
						Maps.mapCpfPessoaAgencia.put(campos[3], Maps.mapNumeroAgencia.get(Integer.parseInt(campos[2])));

						break;

//=====================================================					

					case "SAQUE":						

						Movimentacao saque = new Movimentacao(Integer.parseInt(campos[1]), Integer.parseInt(campos[2]),
								MovimentosEnum.valueOf(campos[0]), Double.parseDouble(campos[3]),
								Double.parseDouble(campos[4]), campos[5]);
						Listas.movimentacao.add(saque);

						break;
//=====================================================						

					case "DEPOSITO":						

						Movimentacao deposito = new Movimentacao(Integer.parseInt(campos[1]),
								Integer.parseInt(campos[2]), MovimentosEnum.valueOf(campos[0]),
								Double.parseDouble(campos[3]), Double.parseDouble(campos[4]),campos[5]);
						Listas.movimentacao.add(deposito);
						break;

//=====================================================											
					case "TRANSFERENCIA":						

						Movimentacao tranferencia = new Movimentacao(Integer.parseInt(campos[1]),
								Integer.parseInt(campos[2]), MovimentosEnum.valueOf(campos[0]),
								Double.parseDouble(campos[3]), Double.parseDouble(campos[4]), campos[5]);
						Listas.movimentacao.add(tranferencia);
						break;
//=====================================================					
					default:
						break;
					}
				//}

			}else {
				break;
			}
		}

		}

		catch (IOException e) {
			System.err.format("Erro de E/S: %s%n", e);
	}
	}

}