package br.edu.cafeteria.modelo.produto;

public class Bebidas extends Produto {
	private String tamanho;
	private int cafeina;
	private boolean quente;
	
	public Bebidas(String nome, double preco, int codigo, int estoque,
			String tamanho, int cafeina, boolean quente) {
		super(nome, preco, codigo, estoque);
		this.tamanho = tamanho;
		this.cafeina = cafeina;
		this.quente = quente;
	}
	
	public String getTamanho() {
        return tamanho;
    }

    public void setTamanho(String tamanho) {
        this.tamanho = tamanho;
    }

    public int getCafeina() {
        return cafeina;
    }

    public void setCafeina(int cafeina) {
        this.cafeina = cafeina;
    }

    public boolean isQuente() {
        return quente;
    }

    public void setQuente(boolean quente) {
        this.quente = quente;
    }
	

}
