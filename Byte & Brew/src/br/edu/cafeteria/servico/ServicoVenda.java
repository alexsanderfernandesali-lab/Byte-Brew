package br.edu.cafeteria.servico;

import br.edu.cafeteria.modelo.pedido.Pedido;

public interface ServicoVenda {
    void realizarVenda(Pedido pedido);
    void cancelarVenda(Pedido pedido);   
}
