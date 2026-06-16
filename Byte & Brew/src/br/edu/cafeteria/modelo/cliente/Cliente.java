package br.edu.cafeteria.modelo.cliente;

public class Cliente {
	private String nome;
	private double saldoXP;
	private String cpf;
	
	public  Cliente (String nome, double saldoXP, String cpf) {
		this.nome = nome;
		this.cpf = cpf;
		this.saldoXP = saldoXP;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public double getSaldoXP() {
		return saldoXP;
	}
	
	public void setSaldoXP(double saldoXP) {
		this.saldoXP = saldoXP;
	}
	
	public String getCpf() {
		return cpf;
	}
	
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

}
