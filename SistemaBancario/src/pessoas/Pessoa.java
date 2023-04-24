package pessoas;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import Movimentos.Movimentacao;
import contas.Conta;
import enuns.Cargo;
import enuns.MovimentosEnum;
import listas.Listas;
import maps.Maps;
import seguros.Seguro;
import utilidades.Arred;
import utilidades.Data;

public abstract class Pessoa {

	private String nome;
	private String cpf;
	private String senha;
	private Cargo tipo;
	private Seguro seguro;
	
//========================================================================================
	public Pessoa(String nome, String cpf, String senha ,Cargo tipo) {
		this.nome = nome;
		this.cpf = cpf;
		this.senha = senha;
		this.tipo = tipo;
		
	}
//=========================================================================================

	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public Cargo getTipo() {
		return tipo;
	}
	public void setTipo(Cargo tipo) {
		this.tipo = tipo;
	}
	
	
	public Seguro getSeguro() {
		return seguro;
	}

	public void setSeguro(Seguro seguro) {
		this.seguro = seguro;
	}

	public void contrataSeguro(Pessoa cliente, double valor) {
		Date data = new Date();
		if(!Maps.mapCpfSeguro.containsKey(cliente.getCpf())) {
			if(valor>=0.01) {
				if(Maps.mapCpfContaCorrente.get(cliente.getCpf()).getSaldo()>=valor*0.2) {
					Conta conta = Maps.mapCpfContaCorrente.get(cliente.getCpf());
					conta.setSaldo(conta.getSaldo()-valor*0.2);
					conta.setTotaltributo(valor*0.2);
					Seguro seg = new Seguro(cliente, valor);
					Listas.seguro.add(seg);
					this.setSeguro(seg);
					Maps.mapCpfSeguro.put(cliente.getCpf(), seg);
					Movimentacao mov = new Movimentacao(conta.getNumeroConta(), MovimentosEnum.SEGURO, valor, valor*0.2);
					Listas.movimentacao.add(mov);					
					String dataComEspaco = Data.dataHora(data);
					System.out.println("-----------------------------------------------------------------------");
					System.out.println("SEGURO CONTRATADO COM SUCESSO");
					System.out.println("VALOR SEGURADO: R$ " + valor);
					System.out.println("VALOR TRIBUTO: R$ " + valor*0.2);
					System.out.println("TITULAR: "+cliente.getNome());
					System.out.println("CONTA: " + conta.getNumeroConta());
					System.out.println("Data: " + dataComEspaco);
					System.out.println("-----------------------------------------------------------------------");
					
					try {
						String dataSemEspaco = Data.dataHoraSemEspaco(data);
						String path = ".\\arquivos\\Seguro-comprovante-"+cliente.getNome()+"-"+dataSemEspaco+".txt";
						FileWriter fw = new FileWriter(path, true);
						PrintWriter pw = new PrintWriter(fw);
						pw.println("-----------------------------------------------------------------------");
						pw.println("COMPROVANTE DE CONTRATAÇÃO DE SEGURO DE VIDA");
						pw.println("VALOR SEGURADO: R$ " + valor);
						pw.println("VALOR TRIBUTO: R$ " + valor*0.2);
						pw.println("TITULAR: "+cliente.getNome());
						pw.println("CONTA: " + conta.getNumeroConta());
						pw.println("Data: " + dataComEspaco);
						pw.println("-----------------------------------------------------------------------");
						pw.flush();
						pw.close();
						fw.close();
					} catch(IOException e) {
						
						e.printStackTrace();
					}
					
				}else {
					System.out.println("Operação não realizada!");
					System.out.println("Saldo insuficiente para "+
					                   "pagar o tributo do seguro.");
				}
			}else {
				System.out.println("Operação não realizada!");
				System.out.println("O valor deve ser maior ou!"+
				                   "igual a R$ 0,01");
			}
			
		}else {
			System.out.println("Operação não realizada!");
			System.out.println("O cliente já possui seguro de vida!");
		}
		
		
		
		
	};
	

}
