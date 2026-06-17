package br.edu.cafeteria.servico;

import br.edu.cafeteria.modelo.pedido.Pedido;

public class CalculadoraPontos {
    public void calcularPontos(Pedido pedido) {
        double totalPedido = pedido.calcularTotal();
        pedido.getCliente().acumularXp(totalPedido);
    }
}  