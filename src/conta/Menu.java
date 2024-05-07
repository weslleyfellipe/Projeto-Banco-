package conta;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

import conta.controller.ContaController;
import conta.model.Conta;
import conta.model.ContaCorrente;
import conta.model.ContaPoupanca;
import conta.util.Cores;

public class Menu {

	public static Scanner leia = new Scanner(System.in);
	
	public static void main(String[] args) {
		
		ContaController Contas = new ContaController();

		

       int opcao,numero,agencia,tipo,aniversario;
       String titular;
       float saldo,limite;
       
       System.out.println("\nCriar Contas\n");
       
       ContaCorrente cc1 =new ContaCorrente(Contas.gerarNumero(), 123,1 ,"Weslley Fellipe", 15000f,25000f); 
       Contas.cadastrar(cc1);
       
       ContaCorrente cc2 =new ContaCorrente(Contas.gerarNumero(), 124,1 ,"Maria Da Silva", 5000f,2000f); 
       Contas.cadastrar(cc2);
       
       ContaPoupanca cp1 = new ContaPoupanca (Contas.gerarNumero(),125 , 2 , "Mariana dos Santos",4000f,12);
       Contas.cadastrar(cp1);
       
       ContaPoupanca cp2 = new ContaPoupanca (Contas.gerarNumero(),125 , 2 , "Julia Ramos",14000f,15);
       Contas.cadastrar(cp2);
       
       Contas.listarTodas();
	
		
		while (true) {
			
			System.out.println(Cores.TEXT_GREEN+ Cores.ANSI_BLACK_BACKGROUND + "****************************************************************************");
			System.out.println("****************************************************************************");
			System.out.println("                      BANCO DO BRAZIL COM Z                                 ");
			System.out.println("                                                                            ");
			System.out.println("****************************************************************************");
			System.out.println("                                                                            ");
			System.out.println("               1 - Criar Conta                                              ");
			System.out.println("               2 - Listar todas as Contas                                   ");
			System.out.println("               3 - Buscar Conta por Numero                                  ");
			System.out.println("               4 - Atualizar Dados da Conta                                 ");
			System.out.println("               5 - Apagar Conta                                             ");
			System.out.println("               6 - Sacar                                                    ");
			System.out.println("               7 - Depositar                                                ");
			System.out.println("               8 - Transferir valores entre Contas                          ");
			System.out.println("               9 - Sair                                                     ");
			System.out.println("                                                                            "); 
			System.out.println("****************************************************************************");
			System.out.println("Entre com a opção desejada:                                                 ");
			System.out.println("                                                                            ");
			
			
			
			try {
			opcao = leia.nextInt();
			} catch (InputMismatchException e ) {
				System.out.println("\nDigite Valores Inteiros !");
				leia.nextLine();
				opcao = 0;
			}
			if(opcao == 9) {
				System.out.println("\nBanco do Brazil com Z - O seu futuro começa aqui!");
                 sobre();
				leia.close();
				System.exit(0);
			}
			
			float valor;
			int numeroDestino;
			switch(opcao) {
			case 1:
				System.out.println("\n Criar Conta");
				
				System.out.println("Digite o numero da Agência: ");
				agencia=leia.nextInt();
				System.out.println("Digite o nome do Titular da Conta");
				leia.skip("\\R?");
				titular = leia.nextLine();
				
				do {
					System.out.println("Digite o tipo da Conta (1-CC OU 2 -CP) : ");
					tipo = leia.nextInt();
				}while(tipo < 1 && tipo > 2 );
				
				System.out.println("Digite o Saldo da Conta (R$): ");
				saldo = leia.nextFloat();
				 
				switch(tipo){
				case 1 -> {
					System.out.println("Digite um Limite de Crédito (R$) : ");
					limite = leia.nextFloat();
					Contas.cadastrar(new ContaCorrente(Contas.gerarNumero(),agencia,tipo,titular,saldo,limite));
									}
				case 2 -> {
					System.out.println("Digite o Dia do Aniversario da Conta : ");
					aniversario =leia.nextInt();
				Contas.cadastrar(new ContaPoupanca(Contas.gerarNumero(), agencia,tipo,titular,saldo,aniversario));
				}
			}
				
				keypress();
                 break;
			case 2:
				System.out.println("\n Listar todas as Contas");
				Contas.listarTodas();
				keypress();
                 break;
			case 3:
				System.out.println("\n Consultar Dados da Conta - por número");
				System.out.println("Digite número da conta : ");
				numero = leia.nextInt();
				
				Contas.procurarPorNumero(numero);
				
				keypress();
				break;
			case 4:
				System.out.println("\n Atualizar dados da Conta");
				

				System.out.println("Digite o número da conta: ");
				numero = leia.nextInt();
				
				var buscaConta = Contas.buscarNaCollection(numero);

				if (buscaConta != null) {
					
					System.out.println("Digite o Numero da Agência: ");
					agencia = leia.nextInt();
					System.out.println("Digite o Nome do Titular: ");
					leia.skip("\\R?");
					titular = leia.nextLine();
						
					System.out.println("Digite o Saldo da Conta (R$): ");
					saldo = leia.nextFloat();
					
					tipo = buscaConta.getTipo();
					
					switch(tipo) {
						case 1 -> {
							System.out.println("Digite o Limite de Crédito (R$): ");
							limite = leia.nextFloat();
							Contas.atualizar(new ContaCorrente(numero, agencia, tipo, titular, saldo, limite));
						}
						case 2 -> {
							System.out.println("Digite o dia do Aniversario da Conta: ");
							aniversario = leia.nextInt();
							Contas.atualizar(new ContaPoupanca(numero, agencia, tipo, titular, saldo, aniversario));
						}
						default ->{
							System.out.println("Tipo de conta inválido!");
						}
					}
					
				}else
					System.out.println("\nConta não encontrada!");

                keypress();
				break;
			case 5:
				System.out.println("\n Apagar Conta");
				System.out.println("Digite o número da conta : ");
				numero =leia.nextInt();
				Contas.deletar(numero);
				
				keypress();
                 break;
			case 6:
				System.out.println("\n Sacar");
				System.out.println("Digite o numero da conta : ");
				numero =leia.nextInt();
				do {
				System.out.println("Digite o Valor do saque (R$) : ");
				valor = leia.nextFloat();
				}while(valor <= 0);
				
				Contas.sacar(numero,valor);
				keypress();
				break;
             case 7:
				System.out.println("\n Depositar");
				System.out.println("Digite o Numero da conta : ");
				numero = leia.nextInt();
				
				do {
					System.out.println("Digite o valor do depósito (R$): ");
					valor = leia.nextFloat();
				}while(valor <= 0);
				Contas.depositar(numero,valor);
				
				
				keypress();
				break;
             case 8:
				System.out.println("\n Transferência entre Contas\n");
				
				System.out.println("Digite o Numero da conta de origem : ");
				numero = leia.nextInt();
				System.out.println("Digite o numero da Conta de Destino: ");
			   numeroDestino = leia.nextInt();
				
				do {
					System.out.println("Digite o Valor da Transferência (R$): ");
					valor = leia.nextFloat();
				}while(valor <= 0);
				 
				Contas.transferir(numero,numeroDestino,valor);
				
				keypress();
		       break;
			  default:
				System.out.println("\nOpção Inválida\n");
                 break;
			}
        }
	}
	public static void sobre() {
		System.out.println("\n*********************************************************");
		System.out.println("Projeto Desenvolvido por: ");
		System.out.println("Weslley Fellipe - Weslleyfellipe1@gmail.com");
		System.out.println("https://github.com/weslleyfellipe");
		System.out.println("*********************************************************");

	}
	
	   public static void keypress() {
	      try {
		   

				System.out.println(Cores.TEXT_RESET + "\n\nPressione Enter para Continuar...");
				System.in.read();
	      }catch(IOException e ) {	
	    	  System.out.println("Você Pressionou uma tecla diferente de Enter !!");

		   }
	   
	}
	
}