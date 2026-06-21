package br.edu.cafeteria.modelo.atendente;


public class Atendente {
	private String nome;
	private int id;
	private static int contadorId = 1;
	
	public Atendente(String nome) {
	this.nome = nome;
	this.id = contadorId++;
	}
	
	 public String getNome() {
	        return nome;
	    }
	    
	    public void setNome(String nome) {
	        this.nome = nome;
	    }
	    
	    public int getId() {
	        return id;
	    }
	    
	    public void setId(int id) {
	        this.id = id;
	    }
	    
	    public String toString() {
	        return "Atendente #" + id + ": " + nome;
	    }
	}

