package br.edu.cafeteria.modelo.cliente;

public class ClienteVIP extends Cliente {
	
	public ClienteVIP(String nome, double saldoXP, String cpf) {
		super(nome, saldoXP, cpf);
	}
	
	public void acumularXp(double valorGasto) {
		this.setSaldoXP(this.getSaldoXP() + valorGasto * 2);

}
}
