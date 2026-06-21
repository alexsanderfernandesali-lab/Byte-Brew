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
    
    // Métodos para acumular pontos (controle interno)
  //  public void adicionarPontos(int pontos) {
    //    this.pontosAcumulados += pontos;
     //   this.saldoXP += pontos; // cada ponto = 1 XP
  //  }
    
   // public void usarPontos(int pontos) {
    //    if (pontos <= this.pontosAcumulados) {
          //  this.pontosAcumulados -= pontos;
     //       this.saldoXP -= pontos;
      //  } else {
       //     System.out.println("Saldo de pontos insuficiente!");
       // }
   // }
 // Método abstrato - cada tipo de cliente tem benefício diferente
   // public abstract double aplicarDesconto(double valorCompra);
    
   @Override
   public String toString() {
       return "Cliente: " + nome + " | CPF: " + cpf + " | XP: " + saldoXP + " | Pontos: " + pontosAcumulados;
   }
//}

}
