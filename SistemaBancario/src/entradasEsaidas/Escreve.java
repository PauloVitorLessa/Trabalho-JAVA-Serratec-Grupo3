package entradasEsaidas;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import Movimentos.Movimentacao;
import agencias.Agencia;
import contas.Conta;
import enuns.Cargo;
import enuns.ContaEnum;
import enuns.MovimentosEnum;
import listas.Listas;
import pessoas.Gerente;
import pessoas.Pessoa;
import utilidades.Arred;
import utilidades.Data;

public class Escreve {
	
	public static void extratoCorrente(int numConta, String titular, double saldo) {
		
		try {
			double totalValor = 0;
			double totalTributo = 0;
			String data = Data.dataHora(new Date());
			String dataSemEspaco = Data.dataHoraSemEspaco(new Date());
			String path = ".\\arquivos\\Extrato-Corrente-" + numConta +"-"+titular+"-"+dataSemEspaco+".txt";
			FileWriter fw = new FileWriter(path, true);
			PrintWriter pw = new PrintWriter(fw);
			pw.println("### Extrato da Conta Corrente ###\n");
			pw.println("Número da conta: " + numConta);
			pw.println("Data: " + data);
			pw.println("Saldo: R$ " + Arred.dois(saldo, 2));
			pw.println("-----------------------------------------------------------------------");
			pw.println("  TIPO         VALOR     TRIBUTO    C. DESTINO     DATA");
			pw.println("-----------------------------------------------------------------------");
			for(Movimentacao movimentacao : Listas.movimentacao) {
				if(movimentacao.getConta() == numConta) {
					switch (movimentacao.getTipo()) {
					case TRANSFERENCIA:
						pw.println(movimentacao.getTipo() +"  -" +
						           movimentacao.getValor() + "       " +
						           movimentacao.getTributo() + "          " +
						           movimentacao.getNumeroContaDestino() + "          " + 
						           Data.dataHora(movimentacao.getDatahora())+"\n");						
						totalValor -= movimentacao.getValor();
						totalTributo += movimentacao.getTributo();
						break;
						case SAQUE:					
						pw.println(movimentacao.getTipo() +"          -" +
						           movimentacao.getValor() + "       " +
						           movimentacao.getTributo() + "                     " +					            
						           Data.dataHora(movimentacao.getDatahora())+"\n");						
						totalValor -= movimentacao.getValor();
						totalTributo += movimentacao.getTributo();
						break;
						
					case DEPOSITO:					
						pw.println(movimentacao.getTipo() +"       +" +
						           movimentacao.getValor() + "       " +
						           movimentacao.getTributo() + "                     " +					           
						           Data.dataHora(movimentacao.getDatahora())+"\n");						
						totalValor += movimentacao.getValor();
						totalTributo += movimentacao.getTributo();
						break;

					default:
						
					break;
					}					
				}
			}
			pw.println("-----------------------------------------------------------------------");
			pw.println("TOTAL:       R$ " + Arred.dois(totalValor, 2)+
					           "    R$ "+ Arred.dois(totalTributo, 2)+"\n");
			pw.println("Saldo: R$ " + Arred.dois(saldo, 2));
			pw.flush();
			pw.close();
			fw.close();
			
		} catch(IOException e) {
			
			e.printStackTrace();
		}		
	}
public static void extratoPoupanca(int numConta, String titular, double saldo) {
		
		try {
			double totalValor = 0;			
			String data = Data.dataHora(new Date());
			String dataSemEspaco = Data.dataHoraSemEspaco(new Date());
			String path = ".\\arquivos\\Extrato-Poupanca-" + numConta +"-"+titular+"-"+dataSemEspaco+".txt";
			FileWriter fw = new FileWriter(path, true);
			PrintWriter pw = new PrintWriter(fw);
			pw.println("### Extrato da Conta Poupança ###\n");
			pw.println("Número da conta: " + numConta);
			pw.println("Data: " + data);
			pw.println("Saldo: R$ " + Arred.dois(saldo, 2));
			pw.println("-----------------------------------------------------------------------");
			pw.println("  TIPO         VALOR         C. DESTINO     DATA");
			pw.println("-----------------------------------------------------------------------");
			for(Movimentacao movimentacao : Listas.movimentacao) {
				if(movimentacao.getConta() == numConta) {
					switch (movimentacao.getTipo()) {
					case TRANSFERENCIA:
						pw.println(movimentacao.getTipo() +"  -" +
						           movimentacao.getValor() + "         " +
						           movimentacao.getNumeroContaDestino() + "          " + 
						           Data.dataHora(movimentacao.getDatahora())+"\n");						
						totalValor -= movimentacao.getValor();						
						break;
						case SAQUE:					
						pw.println(movimentacao.getTipo() +"          -" +
						           movimentacao.getValor() + "         " +						            
						           Data.dataHora(movimentacao.getDatahora())+"\n");						
						totalValor -= movimentacao.getValor();						
						break;
						
					case DEPOSITO:					
						pw.println(movimentacao.getTipo() +"       +" +
						           movimentacao.getValor() + "         " +						           				           
						           Data.dataHora(movimentacao.getDatahora())+"\n");						
						totalValor += movimentacao.getValor();						
						break;

					default:
						
					break;
					}					
				}
			}
			pw.println("-----------------------------------------------------------------------");
			pw.println("TOTAL:       R$ " + Arred.dois(totalValor, 2)+"\n");
			pw.println("Saldo: R$ " + Arred.dois(saldo, 2));
			pw.flush();
			pw.close();
			fw.close();
			
		} catch(IOException e) {
			
			e.printStackTrace();
		}		
	}

	public static void salvaRegistros() {
		
		try {
			String path = ".\\arquivos\\Objetos.txt";
			FileWriter fw = new FileWriter(path);
			PrintWriter pw = new PrintWriter(fw);
			
			
			for(Agencia agencia : Listas.agencia) {
				pw.println(agencia.getTipo() + ";"+agencia.getGerente() +";"+agencia.getContas());
			}
			for(Pessoa pessoa : Listas.pessoa) {
				if(pessoa.getTipo()==Cargo.GERENTE){
					pw.println(pessoa.getTipo() +";"+pessoa.getNome()+";"+pessoa.getCpf()+
							   ";"+pessoa.getSenha()+";"+((Gerente) pessoa).getAgencia().getGerente());
				}else {
					pw.println(pessoa.getTipo() +";"+pessoa.getNome()+";"+pessoa.getCpf()+
							   ";"+pessoa.getSenha());
				}				
			}
			for(Conta conta : Listas.conta) {
				if(conta.getTipo()==ContaEnum.CORRENTE){
					pw.println(conta.getTipo()+";"+conta.getSaldo() +";"+conta.getAgencia().getNumeroAgencia()+";"+
				               conta.getPessoa().getCpf()+";"+conta.getTotalTributo());
				}else {
					pw.println(conta.getTipo()+";"+conta.getSaldo() +";"+conta.getAgencia().getNumeroAgencia()+";"+
				               conta.getPessoa().getCpf());
				}				
			}
			
			for(Movimentacao movimento : Listas.movimentacao) {
			
					pw.println(movimento.getTipo()+";"+movimento.getConta()+";"+movimento.getNumeroContaDestino()+
							   ";"+movimento.getValor()+";"+movimento.getTributo()+";"+movimento.getDatahora());
				
				
			}	
			
			pw.flush();
			pw.close();
			fw.close();
		} catch(IOException e) {
			
			e.printStackTrace();
		}
		
	}
	
}




