package br.com.mateus;

public class TesteMain {
	private static Teste1 teste;
	
	public static void main(String[] args) {
		
		if(teste.isStatus()){
			System.out.println("Legal");
		}else{
			System.out.println("Não funfa");
		}
	}
}
