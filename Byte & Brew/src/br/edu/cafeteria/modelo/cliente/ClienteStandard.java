package br.edu.cafeteria.modelo.cliente;

public class ClienteStandard extends Cliente {
	public ClienteStandard(String nome, String cpf) {
		super(nome, cpf);
		
	}

	// pessoa b: implementar método acumularXp() polimórfico
	@Override
	public void acumularXp(double valorGasto) {
		this.setSaldoXP(this.getSaldoXP() + valorGasto);
	}
	
	 public double aplicarDesconto(double valorCompra) {
	        int bonus = getPontosAcumulados() / 100;
	        double desconto = valorCompra * (bonus * 0.05);
	        return valorCompra - Math.min(desconto, valorCompra * 0.3); 
    }
	 
	 public String toString() {
	        return "[STANDARD] " + super.toString();
	    }
}
