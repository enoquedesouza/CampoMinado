
// Cria um tipo posição para compor o campo minado.
public class Posicao{

	private int estado; // o estado dever 1 para aberto 2 para fechado.
	private int valor; // armazena o valor do objeto Posicao.
	private String marca; // marca se a posicao é uma bomba ou nâo;

	public Posicao(int estado, int valor, String marca){

		setEstado(estado);
		setValor(valor);
		setMarca(marca);

	}
	
	public void setEstado(int estado){

		if((estado == 1) || (estado == 2)){

			this.estado = estado;
		}

	}

	public void setValor(int valor){

		this.valor = valor;

	}

	public void setMarca(String marca){

		this.marca = marca;

	}

	public int getEstado(){

		return estado;

	}

	public int getValor(){

		return valor;

	}

	public String getMarca(){

		return marca;

	}

}