package br.edu.cafeteria.servico;

import br.edu.cafeteria.modelo.pedido.Pedido;
import br.edu.cafeteria.modelo.cliente.Cliente;

public class CalculadoraPontos {
    
    public void calcularPontos(Pedido pedido) {
        
    	double totalPedido = pedido.calcularTotal();
        
                Cliente cliente = pedido.getCliente();
        
                if (cliente != null) {
  
            cliente.acumularXp(totalPedido);
            
            System.out.println("Pontos acumulados: " + totalPedido + " XP");
            System.out.println("Saldo total de XP: " + cliente.getSaldoXP());
        } else {
            System.out.println("Pedido anônimo - sem acúmulo de XP.");
        }
    }
}