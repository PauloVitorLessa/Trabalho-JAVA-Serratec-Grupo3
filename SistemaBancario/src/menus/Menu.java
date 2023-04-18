package menus;

import java.util.Scanner;

import maps.Maps;

public class Menu {
	
	public static void menuLogin() {
		
		boolean login=false;
		
		do {			
			String CPF;
			String senha;
			Scanner ler = new Scanner(System.in);
			
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
			
			if(Maps.mapCpfPessoa.containsKey(CPF)) {
				
				if(Maps.mapCpfPessoa.get(CPF).getSenha().equals(senha)){
					
					login = true;
					ler.close();
					
					switch (Maps.mapCpfPessoa.get(CPF).getTipo()) {
					case CLIENTE :				
						System.out.println("CLIENTE LOGADO");
						System.out.println("CLIENTE LOGADO");
						System.out.println("CLIENTE LOGADO");				
						break;			
					case GERENTE :
						System.out.println("GERENTE LOGADO");
						System.out.println("GERENTE LOGADO");
						System.out.println("GERENTE LOGADO");
						break;
					case DIRETOR :
						System.out.println("DIRETOR LOGADO");
						System.out.println("DIRETOR LOGADO");
						System.out.println("DIRETOR LOGADO");
						break;
					case PRESIDENTE :
						System.out.println("PRESIDENTE LOGADO");
						System.out.println("PRESIDENTE LOGADO");
						System.out.println("PRESIDENTE LOGADO");
						break;

					default:
						break;
					}	
					
				}
				else {
					System.out.println("CPF ou senha Inválidos");
					System.out.println("______________________________\n\n");
				}
			
			}
			else {
				System.out.println("CPF ou senha Inválidos");
				System.out.println("______________________________\n\n");
			}
						
			
		}
		while(login==false);
		
	}
	
}