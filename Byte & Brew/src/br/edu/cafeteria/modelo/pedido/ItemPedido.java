package br.edu.cafeteria.modelo.pedido;

import br.edu.cafeteria.modelo.produto.Produto;

public class ItemPedido {
	private Produto produto;    
    private int quantidade;
    private double subtotal;

    public ItemPedido(Produto produto, int quantidade) {
        this.produto = produto;
        this.quantidade = quantidade;
        this.subtotal = produto.getPreco() * quantidade;
    }
    
    public double getSubtotal() {
        return subtotal;
    }
    
    public void setSubtotal(int quantidade) {
        this.subtotal = produto.getPreco() * quantidade;
    }
    
    public Produto getProduto() {
        return produto;
    }
    
    public void setProduto(Produto produto) {
        this.produto = produto;
    }
    
    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
        
    }

}