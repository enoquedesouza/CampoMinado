import java.util.*;

// Cria um tipo CampoMinado composto Posições 
public class CampoMinado{

 	private Posicao[][] matriz;

 	private int qtdLinhas, qtdColunas, qtdBombas;
	
	/*
	 * @param m é a quantidade de linhas da matriz que representa o campo minado
	 * @param n é a quantidade de colunas da matriz que representa o campo minado
	 * @param q é a quantidade de bombas que estarão presentes no campo.	
	*/
	
 	public CampoMinado(int m, int n, int q){

		setQTDLinhasEColunasEBombas(m, n, q);
		matriz = new Posicao[qtdLinhas][qtdColunas];
		inicializaMatriz();
		preencheBombas();
		preencheQuantasBombasNaVizinhanca();

	}

	// O campo é incializado com  com posições com o valor de 2, indicando estado fechado, todas as posições começam com valor 0 e um marca qualquer para 
	// exibição inicial.
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

	// Conta a quanntidade de bombas que há na vizinhança de posição. 
	// Obs: é muito mais elegante e computacionalmente eficiente fazer este processo de maneira recursiva, em uma segunda versão este processo de contagem
	// de bombas será feito recursivamente.

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

	// Quando  usuário resolve um abrir uma posição, todas as posições vizinhas que não estejam marcadas também serão abertas.
	// Obs. Este processo também foi feito meio na força bruta, mas é possível otimizar utilizando recursão para que fique um código mais simples, elegante e eficiente.
	// Na segunda versão deixarei este código mais eficiente.
	public void abrePosiçãoAtualEVizinhos(int i, int j){

		 if(matriz[i][j].getEstado() == 2){

			matriz[i][j].setEstado(1);

			if(matriz[i][j].getValor() == 0){

				if(i == 0){

					if((j -1) < 0){

						if(matriz[i][j+1].getValor() != -1) abrePosiçãoAtualEVizinhos(i, j+1);
						if(matriz[i+1][j].getValor() != -1) abrePosiçãoAtualEVizinhos(i+1, j);
						if(matriz[i+1][j+1].getValor() != -1) abrePosiçãoAtualEVizinhos(i+1, j+1);

					}else if((j + 1) == qtdColunas){

						if(matriz[i][j-1].getValor() != -1) abrePosiçãoAtualEVizinhos(i, j-1) ;
						if(matriz[i+1][j].getValor() != -1) abrePosiçãoAtualEVizinhos(i+1, j);
						if(matriz[i+1][j-1].getValor() != -1) abrePosiçãoAtualEVizinhos(i+1, j);
					}else{

						if(matriz[i][j+1].getValor() != -1) abrePosiçãoAtualEVizinhos(i, j+1);
						if(matriz[i][j-1].getValor() != -1) abrePosiçãoAtualEVizinhos(i, j-1);
						if(matriz[i+1][j].getValor() != -1) abrePosiçãoAtualEVizinhos(i+1, j);
						if(matriz[i+1][j-1].getValor() != -1) abrePosiçãoAtualEVizinhos(i+1, j-1);
						if(matriz[i+1][j+1].getValor() != -1) abrePosiçãoAtualEVizinhos(i+1, j+1);
					}
					
				}else if((i + 1) == qtdLinhas){


					if((j-1) < 0){

						if(matriz[i-1][j].getValor() != -1) abrePosiçãoAtualEVizinhos(i-1, j);
						if(matriz[i][j + 1].getValor() != -1) abrePosiçãoAtualEVizinhos(i, j+1);
						if(matriz[i-1][j+1].getValor() != -1) abrePosiçãoAtualEVizinhos(i-1, j+1);
				
					}else if((j + 1) == qtdColunas){
				
						if(matriz[i][j-1].getValor() != -1) abrePosiçãoAtualEVizinhos(i, j-1);
						if(matriz[i-1][j].getValor() != -1) abrePosiçãoAtualEVizinhos(i-1, j);
						if(matriz[i-1][j-1].getValor() != -1) abrePosiçãoAtualEVizinhos(i-1, j-1);
						
					}else{

						if(matriz[i][j+1].getValor() != -1) abrePosiçãoAtualEVizinhos(i, j+1);
						if(matriz[i][j-1].getValor() != -1) abrePosiçãoAtualEVizinhos(i, j-1);
						if(matriz[i-1][j].getValor() != -1) abrePosiçãoAtualEVizinhos(i - 1, j);
						if(matriz[i-1][j-1].getValor() != -1) abrePosiçãoAtualEVizinhos(i-1, j-1);
						if(matriz[i-1][j+1].getValor() != -1) abrePosiçãoAtualEVizinhos(i-1, j+1);
				        }

				}else{
					if((j-1) < 0){

						if(matriz[i-1][j].getValor() != -1) abrePosiçãoAtualEVizinhos(i - 1, j);
						if(matriz[i-1][j + 1].getValor() != -1) abrePosiçãoAtualEVizinhos(i -1, j+1);
						if(matriz[i][j+1].getValor() != -1) abrePosiçãoAtualEVizinhos(i, j+1);
						if(matriz[i+1][j+1].getValor() != -1) abrePosiçãoAtualEVizinhos(i+1, j+1);
						if(matriz[i+1][j].getValor() != -1) abrePosiçãoAtualEVizinhos(i+1, j);
					}else if((j + 1) == qtdColunas){
				
						if(matriz[i][j-1].getValor() != -1) abrePosiçãoAtualEVizinhos(i, j - 1);
						if(matriz[i+1][j].getValor() != -1) abrePosiçãoAtualEVizinhos(i + 1, j);
						if(matriz[i-1][j].getValor() != -1) abrePosiçãoAtualEVizinhos(i - 1, j);
						if(matriz[i+1][j-1].getValor() != -1) abrePosiçãoAtualEVizinhos(i + 1, j - 1);
						if(matriz[i-1][j-1 ].getValor() != -1) abrePosiçãoAtualEVizinhos(i - 1, j - 1);
					}else{

						if(matriz[i][j+1].getValor() != -1) abrePosiçãoAtualEVizinhos(i, j + 1);
						if(matriz[i][j-1].getValor() != -1) abrePosiçãoAtualEVizinhos(i, j - 1);
						if(matriz[i-1][j].getValor() != -1) abrePosiçãoAtualEVizinhos(i - 1, j);
						if(matriz[i+1][j].getValor() != -1) abrePosiçãoAtualEVizinhos(i + 1, j);
						if(matriz[i-1][j+1].getValor() != -1) abrePosiçãoAtualEVizinhos(i - 1, j + 1);
						if(matriz[i-1][j-1].getValor() != -1) abrePosiçãoAtualEVizinhos(i - 1, j - 1);
						if(matriz[i+1][j-1].getValor() != -1) abrePosiçãoAtualEVizinhos(i + 1, j - 1);
						if(matriz[i+1][j+1].getValor() != -1) abrePosiçãoAtualEVizinhos(i + 1, j + 1);
				        }
				}

			}

		}
	}

} // Fim da classe.