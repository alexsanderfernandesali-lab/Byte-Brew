package br.edu.cafeteria.modelo.cliente;

public abstract class Cliente {
	private String nome;
	private String cpf;
	private double saldoXP;
	private int pontosAcumulados;
	
	public  Cliente (String nome, String cpf) {
		this.nome = nome;
		this.cpf = cpf;
		this.saldoXP = 0;
		this.pontosAcumulados = 0;
	}
	
	public void acumularXp(double valorGasto) {
	    this.saldoXP += valorGasto;  
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
	
    public int getPontosAcumulados() {
        return pontosAcumulados;
	}
    
   @Override
   public String toString() {
       return "Cliente: " + nome + " | CPF: " + cpf + " | XP: " + saldoXP + " | Pontos: " + pontosAcumulados;
   }
//}

}
