package br.edu.cafeteria.modelo.produto;

public class Comidas extends Produto {
	private int tempo;
	private boolean vegan;
	private boolean Sgluten;
	
	public Comidas(String nome, double preco, int codigo, int estoque,
			int tempo, boolean vegan, boolean Sgluten) {
		super(nome, preco, codigo, estoque);
		this.tempo = tempo;
		this.vegan = vegan;
		this.Sgluten = Sgluten;
	}
	
	 public int getTempo() {
	        return tempo;
	    }

	    public void setTempo(int tempo) {
	        this.tempo = tempo;
	    }

	    public boolean isVegan() {
	        return vegan;
	    }

	    public void setVegan(boolean vegan) {
	        this.vegan = vegan;
	    }

	    public boolean isSgluten() {
	        return Sgluten;
	    }

	    public void setSgluten(boolean Sgluten) {
	        this.Sgluten = Sgluten;
	    }

}
