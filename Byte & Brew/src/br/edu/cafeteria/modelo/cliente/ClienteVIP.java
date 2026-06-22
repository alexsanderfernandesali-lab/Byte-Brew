package br.edu.cafeteria.modelo.cliente;

import br.edu.cafeteria.excecao.PontosInsuficientesException;

public class ClienteVIP extends Cliente {

	//pessoa b: constante estática de conversão
	public static final double TAXA_CONVERSAO_XP = 0.1;
	
	public ClienteVIP(String nome, String cpf) {
		super(nome, cpf);
		this.acumularXp(getPontosAcumulados());
	}
	
	public void acumularXp(double valorGasto) {
		this.setSaldoXP(this.getSaldoXP() + valorGasto * 2);

}

	//pessoa b: método de resgatar pontos validando com a exceção
	public void resgatarPontos(double valorTotalPedidosReais) throws PontosInsuficientesException {
		double xpNecessario = valorTotalPedidosReais / TAXA_CONVERSAO_XP;

		if (this.getSaldoXP() < xpNecessario) {
			throw new PontosInsuficientesException("Saldo de XP insuficiente!" + "Necessario: " + xpNecessario + " XP. Disponivel: " + this.getSaldoXP() + " XP.");
		}

		this.setSaldoXP(this.getSaldoXP() - xpNecessario);
		System.out.println("[FIDELIDADE] Resgate efetuado com sucesso! " + xpNecessario + " XP debitados.");
	}

	public String toString() {
		return "[VIP] " + super.toString();
	}
}
