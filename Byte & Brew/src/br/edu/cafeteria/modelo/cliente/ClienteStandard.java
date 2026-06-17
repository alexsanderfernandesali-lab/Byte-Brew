package br.edu.cafeteria.modelo.cliente;

public class ClienteStandard extends Cliente {
	public ClienteStandard(String nome, double saldoXP, String cpf) {
		super(nome, saldoXP, cpf);
	}
	    public void acumularXp(double valorGasto) {
		    super.acumularXp(valorGasto);
	}

}
