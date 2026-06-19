package br.edu.cafeteria.modelo.cliente;

public class ClienteVIP extends Cliente {
	
	public ClienteVIP(String nome, String cpf) {
		super(nome, cpf);
		this.acumularXp(getPontosAcumulados());
	}
	
	public void acumularXp(double valorGasto) {
		this.setSaldoXP(this.getSaldoXP() + valorGasto * 2);

}
}
