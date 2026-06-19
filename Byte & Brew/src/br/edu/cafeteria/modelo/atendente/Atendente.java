package br.edu.cafeteria.modelo.atendente;

import java.util.ArrayList;


public class Atendente {
	private ArrayList<Atendente>listaAtendentes;
	private String nome;
	
	public Atendente(String nome){
	this.listaAtendentes = new ArrayList<>();
	this.nome = nome;
	}

	//precisa atrelar atendente ao numero da comanda
     Atendente atendente1 = (new Atendente("Lucas"));
     Atendente atendente2 = (new Atendente("Pedro"));
     Atendente atendente3 = (new Atendente("Tiago"));
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}

}
