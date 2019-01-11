import java.util.*;

// Cria um tipo CampoMinado composto Posi��es 
public class CampoMinado{

 	private Posicao[][] matriz;

 	private int qtdLinhas, qtdColunas, qtdBombas;
	
	/*
	 * @param m � a quantidade de linhas da matriz que representa o campo minado
	 * @param n � a quantidade de colunas da matriz que representa o campo minado
	 * @param q � a quantidade de bombas que estar�o presentes no campo.	
	*/
	
 	public CampoMinado(int m, int n, int q){

		setQTDLinhasEColunasEBombas(m, n, q);
		matriz = new Posicao[qtdLinhas][qtdColunas];
		inicializaMatriz();
		preencheBombas();
		preencheQuantasBombasNaVizinhanca();

	}

	// O campo � incializado com  com posi��es com o valor de 2, indicando estado fechado, todas as posi��es come�am com valor 0 e um marca qualquer para 
	// exibi��o inicial.
	public void inicializaMatriz(){

		for(int i = 0; i < qtdLinhas; i++)

		  for(int j = 0; j < qtdColunas; j++)

			matriz[i][j] = new Posicao(2, 0, "+");

	}

	public Posicao[][] getMatriz (){

		return matriz;
	}

	public void setQTDLinhasEColunasEBombas(int m, int n, int q){

		if((m > 0) & (n>0)){

			qtdLinhas = m;
			qtdColunas = n;

			if(q < (m*n)){

				qtdBombas = q;
			}
		}
	}

 	private void preencheBombas() {

		int i, j, k = 0;
		Random gerador = new Random();
	    	while(k < qtdBombas){

			i = gerador.nextInt(qtdLinhas-1);
			j = gerador.nextInt(qtdColunas-1);
			if (matriz[i][j].getValor() != -1){
				matriz[i][j].setValor(-1);
				k +=1;
			}
		}	
 	}

 	private void preencheQuantasBombasNaVizinhanca() {

		for(int i = 0; i < qtdLinhas; i++ ){

			for(int j = 0; j < qtdColunas; j++){
			
				if(matriz[i][j].getValor() == 0) 
					matriz[i][j].setValor(contaBombasNaVizinhanca(i, j));
			}
		}
 	}

	// Conta a quanntidade de bombas que h� na vizinhan�a de posi��o. 
	// Obs: � muito mais elegante e computacionalmente eficiente fazer este processo de maneira recursiva, em uma segunda vers�o este processo de contagem
	// de bombas ser� feito recursivamente.

 	private int contaBombasNaVizinhanca(int i, int j){
		int cont = 0;
		if(i == 0){
			 
			if((j-1) < 0){

				if(matriz[i][j+1].getValor() == -1) cont+=1;
				if(matriz[i+1][j].getValor() == -1) cont+=1;
				if(matriz[i+1][j+1].getValor() == -1) cont+=1;
				return cont;
			}else if((j + 1) == qtdColunas){
				
				if(matriz[i][j-1].getValor() == -1) cont+=1;
				if(matriz[i+1][j].getValor() == -1) cont+=1;
				if(matriz[i+1][j-1].getValor() == -1) cont+=1;
				return cont;
			}else{

				if(matriz[i][j+1].getValor() == -1) cont+=1;
				if(matriz[i][j-1].getValor() == -1) cont+=1;
				if(matriz[i+1][j].getValor() == -1) cont+=1;
				if(matriz[i+1][j-1].getValor() == -1) cont+=1;
				if(matriz[i+1][j+1].getValor() == -1) cont+=1;
				return cont;
		       }
		}else if((i + 1) == qtdLinhas){
			 
			if((j-1) < 0){

				if(matriz[i-1][j].getValor() == -1) cont+=1;
				if(matriz[i][j + 1].getValor() == -1) cont+=1;
				if(matriz[i-1][j+1].getValor() == -1) cont+=1;
				return cont;
			}else if((j + 1) == qtdColunas){
				
				if(matriz[i][j-1].getValor() == -1) cont+=1;
				if(matriz[i-1][j].getValor() == -1) cont+=1;
				if(matriz[i-1][j-1].getValor() == -1) cont+=1;
				return cont;
			}else{

				if(matriz[i][j+1].getValor() == -1) cont+=1;
				if(matriz[i][j-1].getValor() == -1) cont+=1;
				if(matriz[i-1][j].getValor() == -1) cont+=1;
				if(matriz[i-1][j-1].getValor() == -1) cont+=1;
				if(matriz[i-1][j+1].getValor() == -1) cont+=1;
				return cont;
			}
		}else{
			if((j-1) < 0){

				if(matriz[i-1][j].getValor() == -1) cont+=1;
				if(matriz[i-1][j + 1].getValor() == -1) cont+=1;
				if(matriz[i][j+1].getValor() == -1) cont+=1;
				if(matriz[i+1][j+1].getValor() == -1) cont+=1;
				if(matriz[i+1][j].getValor() == -1) cont+=1;
				return cont;
			}else if((j + 1) == qtdColunas){
				
				if(matriz[i][j-1].getValor() == -1) cont+=1;
				if(matriz[i+1][j].getValor() == -1) cont+=1;
				if(matriz[i-1][j].getValor() == -1) cont+=1;
				if(matriz[i+1][j-1].getValor() == -1) cont+=1;
				if(matriz[i-1][j-1 ].getValor() == -1) cont+=1;
				return cont;
			}else{

				if(matriz[i][j+1].getValor() == -1) cont+=1;
				if(matriz[i][j-1].getValor() == -1) cont+=1;
				if(matriz[i-1][j].getValor() == -1) cont+=1;
				if(matriz[i+1][j].getValor() == -1) cont+=1;
				if(matriz[i-1][j+1].getValor() == -1) cont+=1;
				if(matriz[i-1][j-1].getValor() == -1) cont+=1;
				if(matriz[i+1][j-1].getValor() == -1) cont+=1;
				if(matriz[i+1][j+1].getValor() == -1) cont+=1;
				return cont;
			}
		}

 	}

 	public void imprimeCampoMinado() {

		for(int i = 0; i < qtdLinhas; i++){

			for(int j = 0; j < qtdColunas; j ++){

				if(matriz[i][j].getEstado() == 2)
					System.out.print("\t" + matriz[i][j].getMarca()+ "\t");
				else if(matriz[i][j].getEstado() == 1)
					System.out.print("\t" + matriz[i][j].getValor()+ "\t");
				if(j == qtdColunas - 1) System.out.println();
			}
		}
 	}

	// Quando  usu�rio resolve um abrir uma posi��o, todas as posi��es vizinhas que n�o estejam marcadas tamb�m ser�o abertas.
	// Obs. Este processo tamb�m foi feito meio na for�a bruta, mas � poss�vel otimizar utilizando recurs�o para que fique um c�digo mais simples, elegante e eficiente.
	// Na segunda vers�o deixarei este c�digo mais eficiente.
	public void abrePosi��oAtualEVizinhos(int i, int j){

		 if(matriz[i][j].getEstado() == 2){

			matriz[i][j].setEstado(1);

			if(matriz[i][j].getValor() == 0){

				if(i == 0){

					if((j -1) < 0){

						if(matriz[i][j+1].getValor() != -1) abrePosi��oAtualEVizinhos(i, j+1);
						if(matriz[i+1][j].getValor() != -1) abrePosi��oAtualEVizinhos(i+1, j);
						if(matriz[i+1][j+1].getValor() != -1) abrePosi��oAtualEVizinhos(i+1, j+1);

					}else if((j + 1) == qtdColunas){

						if(matriz[i][j-1].getValor() != -1) abrePosi��oAtualEVizinhos(i, j-1) ;
						if(matriz[i+1][j].getValor() != -1) abrePosi��oAtualEVizinhos(i+1, j);
						if(matriz[i+1][j-1].getValor() != -1) abrePosi��oAtualEVizinhos(i+1, j);
					}else{

						if(matriz[i][j+1].getValor() != -1) abrePosi��oAtualEVizinhos(i, j+1);
						if(matriz[i][j-1].getValor() != -1) abrePosi��oAtualEVizinhos(i, j-1);
						if(matriz[i+1][j].getValor() != -1) abrePosi��oAtualEVizinhos(i+1, j);
						if(matriz[i+1][j-1].getValor() != -1) abrePosi��oAtualEVizinhos(i+1, j-1);
						if(matriz[i+1][j+1].getValor() != -1) abrePosi��oAtualEVizinhos(i+1, j+1);
					}
					
				}else if((i + 1) == qtdLinhas){


					if((j-1) < 0){

						if(matriz[i-1][j].getValor() != -1) abrePosi��oAtualEVizinhos(i-1, j);
						if(matriz[i][j + 1].getValor() != -1) abrePosi��oAtualEVizinhos(i, j+1);
						if(matriz[i-1][j+1].getValor() != -1) abrePosi��oAtualEVizinhos(i-1, j+1);
				
					}else if((j + 1) == qtdColunas){
				
						if(matriz[i][j-1].getValor() != -1) abrePosi��oAtualEVizinhos(i, j-1);
						if(matriz[i-1][j].getValor() != -1) abrePosi��oAtualEVizinhos(i-1, j);
						if(matriz[i-1][j-1].getValor() != -1) abrePosi��oAtualEVizinhos(i-1, j-1);
						
					}else{

						if(matriz[i][j+1].getValor() != -1) abrePosi��oAtualEVizinhos(i, j+1);
						if(matriz[i][j-1].getValor() != -1) abrePosi��oAtualEVizinhos(i, j-1);
						if(matriz[i-1][j].getValor() != -1) abrePosi��oAtualEVizinhos(i - 1, j);
						if(matriz[i-1][j-1].getValor() != -1) abrePosi��oAtualEVizinhos(i-1, j-1);
						if(matriz[i-1][j+1].getValor() != -1) abrePosi��oAtualEVizinhos(i-1, j+1);
				        }

				}else{
					if((j-1) < 0){

						if(matriz[i-1][j].getValor() != -1) abrePosi��oAtualEVizinhos(i - 1, j);
						if(matriz[i-1][j + 1].getValor() != -1) abrePosi��oAtualEVizinhos(i -1, j+1);
						if(matriz[i][j+1].getValor() != -1) abrePosi��oAtualEVizinhos(i, j+1);
						if(matriz[i+1][j+1].getValor() != -1) abrePosi��oAtualEVizinhos(i+1, j+1);
						if(matriz[i+1][j].getValor() != -1) abrePosi��oAtualEVizinhos(i+1, j);
					}else if((j + 1) == qtdColunas){
				
						if(matriz[i][j-1].getValor() != -1) abrePosi��oAtualEVizinhos(i, j - 1);
						if(matriz[i+1][j].getValor() != -1) abrePosi��oAtualEVizinhos(i + 1, j);
						if(matriz[i-1][j].getValor() != -1) abrePosi��oAtualEVizinhos(i - 1, j);
						if(matriz[i+1][j-1].getValor() != -1) abrePosi��oAtualEVizinhos(i + 1, j - 1);
						if(matriz[i-1][j-1 ].getValor() != -1) abrePosi��oAtualEVizinhos(i - 1, j - 1);
					}else{

						if(matriz[i][j+1].getValor() != -1) abrePosi��oAtualEVizinhos(i, j + 1);
						if(matriz[i][j-1].getValor() != -1) abrePosi��oAtualEVizinhos(i, j - 1);
						if(matriz[i-1][j].getValor() != -1) abrePosi��oAtualEVizinhos(i - 1, j);
						if(matriz[i+1][j].getValor() != -1) abrePosi��oAtualEVizinhos(i + 1, j);
						if(matriz[i-1][j+1].getValor() != -1) abrePosi��oAtualEVizinhos(i - 1, j + 1);
						if(matriz[i-1][j-1].getValor() != -1) abrePosi��oAtualEVizinhos(i - 1, j - 1);
						if(matriz[i+1][j-1].getValor() != -1) abrePosi��oAtualEVizinhos(i + 1, j - 1);
						if(matriz[i+1][j+1].getValor() != -1) abrePosi��oAtualEVizinhos(i + 1, j + 1);
				        }
				}

			}

		}
	}

} // Fim da classe.