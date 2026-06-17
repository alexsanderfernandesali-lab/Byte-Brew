package br.edu.cafeteria.servico;
import br.edu.cafeteria.modelo.pedido.Pedido;

public interface Promocional {
    void aplicarDesconto(Pedido pedido);
}
