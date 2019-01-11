import java.util.*;


public class ClassePrincipalCampoMinado{
	
        private static CampoMinado campo;
	private enum Status{ GANHOU, PERDEU, RODANDO};
	private static Status statusJogo;
	private static int qtdLinhas, qtdColunas, qtdBombas;
	

	public static void main (String args[]){

		Scanner entrada = new Scanner(System.in);
		qtdLinhas = 9; qtdColunas = 9; qtdBombas = 10;
		campo = new CampoMinado(9, 9, 10);
		campo.imprimeCampoMinado();
		statusJogo = Status.RODANDO;
		int linha, coluna, operacao;
		while(statusJogo == Status.RODANDO){

			System.out.print("Escolha a operação (1 - Marcar / 2 Abrir): ");
			operacao = entrada.nextInt();

			if(operacao == 1){
		
				System.out.print("Digite a linha (começando do zero): ");
				linha = entrada.nextInt();
				System.out.print("Digite a coluna (começando do zero): ");
				coluna = entrada.nextInt();
				campo.getMatriz()[linha][coluna].setMarca("B");
				campo.imprimeCampoMinado();
	
			}else if( operacao == 2){

				System.out.print("Digite a linha (começando do zero): ");
				linha = entrada.nextInt();
				System.out.print("Digite a coluna (começando do zero): ");
				coluna = entrada.nextInt();

				if((campo.getMatriz()[linha][coluna].getValor()) == -1){
				
					statusJogo = Status.PERDEU;

				}
				campo.abrePosiçãoAtualEVizinhos(linha, coluna);
				campo.imprimeCampoMinado();
				if(ClassePrincipalCampoMinado.verificaSeGanhou()) statusJogo = Status.GANHOU;
		       }else{

		              System.out.println("Valor Inválido ");

		       }	

		}
		
		if(statusJogo == Status.GANHOU){

			System.out.println("Você Ganhou!! ");

		}else{

			System.out.println("Você Perdeu!! ");
		}

	}

	// Varre a matriz verificando se todas as posições foram abertas e das que foram abertas não há alguma bomba. Se todas as posições foram abertas e dentre 
	// elas não alguma bomba então o usuário venceu o jogo.

	public static boolean verificaSeGanhou(){
		
		int cont = 0;
		for(Posicao[]  posicao: campo.getMatriz()){
			
			for(Posicao pos: posicao){

				if(pos.getEstado() == 1){

					cont+=1;
				}

			}
		}

		if(cont >= (qtdLinhas*qtdColunas) - qtdBombas ) return true;

		else return false;
	}
}