package br.edu.cafeteria.servico;

import br.edu.cafeteria.modelo.pedido.Pedido;
import br.edu.cafeteria.modelo.cliente.Cliente;
import br.edu.cafeteria.modelo.cliente.ClienteStandard;
import br.edu.cafeteria.modelo.cliente.ClienteVIP;

public class CalculadoraPontos {
    
    public void calcularPontos(Pedido pedido) {
        double totalPedido = pedido.calcularTotal();
        Cliente cliente = pedido.getCliente();
        
        if (cliente != null) {
            if (cliente instanceof ClienteVIP) {
                calcularPontosVip((ClienteVIP) cliente, totalPedido);
            } else if (cliente instanceof ClienteStandard) {
                calcularPontosStandard((ClienteStandard) cliente, totalPedido);
            } else {
                cliente.acumularXp(totalPedido);
            }
            
            System.out.println("Pontos acumulados: " + totalPedido + " XP");
            System.out.println("Saldo total de XP: " + cliente.getSaldoXP());
        } else {
            System.out.println("Pedido anônimo (sem acúmulo de XP).");
        }
    } 

    private void calcularPontosVip(ClienteVIP cliente, double valor) {
        cliente.acumularXp(valor);
    }
    
    
    private void calcularPontosStandard(ClienteStandard cliente, double valor) {
        cliente.acumularXp(valor);
    }

} 