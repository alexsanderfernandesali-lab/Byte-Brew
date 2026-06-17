package br.edu.cafeteria.servico;

import br.edu.cafeteria.modelo.pedido.Pedido;
import br.edu.cafeteria.modelo.pedido.ItemPedido;
import br.edu.cafeteria.modelo.produto.Bebidas;

public class EventoGeekPromotion implements Promocional {
    @Override
    public void aplicarDesconto(Pedido pedido) {
        for (ItemPedido item : pedido.getListaItens()) {
            if (item.getProduto() instanceof Bebidas) {
                double precoOriginal = item.getProduto().getPreco();
                double desconto = precoOriginal * 0.10;
                double novoPreco = precoOriginal - desconto;

                item.getProduto().setPreco(novoPreco);
                item.setSubtotal(item.getQuantidade());
            }
        }
    }
}
   

