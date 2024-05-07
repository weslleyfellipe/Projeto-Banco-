package conta.controller;

import java.util.ArrayList;

import conta.model.Conta;
import conta.repository.ContaRepository;

public class ContaController implements ContaRepository {

	
	
	private ArrayList<Conta>listaContas = new ArrayList<Conta>();
	int numero = 0;
	
	@Override
	public void procurarPorNumero(int numero) {
		var conta = buscarNaCollection(numero);
		
		if(conta !=null)
			conta.visualizar();
		else
			System.out.println("\nA conta número : " + numero + " Não foi encontrada.");
		System.out.println("Por Favor Digite outra Conta");
		// TODO Auto-generated method stub
		
	}

	@Override
	public void listarTodas() {
		for (var conta :listaContas) {
			conta.visualizar();
		}
			
		// TODO Auto-generated method stub
		
	}

	@Override
	public void cadastrar(Conta conta) {
		// TODO Auto-generated method stub
		listaContas.add(conta);
		System.out.println("\nA Conta Número : " + conta.getNumero()+ " Foi criada com Sucesso .");
			
		
		
	}

	@Override
	public void atualizar(Conta conta) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deletar(int numero) {
		var conta = buscarNaCollection(numero);
		
		if (conta != null) {
			if(listaContas.remove(conta)== true);
			System.out.println("\nA Conta numero : " + numero + " Foi deletada com sucesso !");
		}else
			System.out.println("\nA conta Numero: " + numero + "  Não foi encontrada !");
		
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sacar(int numero, float valor) {
		var conta = buscarNaCollection(numero);
		
		if (conta != null) {
		   
			if (conta.sacar(valor)== true)
				System.out.println("\nO Saque na Conta numero : " + numero + " Foi efetuado com Sucesso !");
		}else
			System.out.println("\nA Conta numero : " + numero + " Não foi encontrada !" );
		
	}

	@Override
	public void depositar(int numero, float valor) {
		var conta =buscarNaCollection(numero);
		
		if(conta !=null) {
			conta.depositar(valor);
			System.out.println("\n O Depósito na Conta numero : " + numero + " Foi efetuado com sucesso! ");
		}else
			System.out.println(
		"\nA Conta numero : " + numero + " Não foi encontrada ou a Conta destino não é uma conta corrente !");
		// TODO Auto-generated method stub
		
	}
	
	public int gerarNumero() {
		return ++ numero;
	}

	 @Override
	    public void transferir(int numeroOrigem, int numeroDestino, float valor) {
	        var contaOrigem = buscarNaCollection(numeroOrigem);
			var contaDestino = buscarNaCollection(numeroDestino);

			if (contaOrigem != null && contaDestino != null) {
								
					if (contaOrigem.sacar(valor) == true) {
						contaDestino.depositar(valor);
						System.out.println("\nA Transferência foi efetuado com sucesso!");
					}
			}else
				System.out.println("\nA Conta de Origem e/ou Destino não foram encontradas!");
	}
    
	public Conta buscarNaCollection(int numero) {
		for(var conta : listaContas) {
			if(conta.getNumero() == numero ) {
				return conta;
				
			}
		}
		return null;
	}
	
	
}
