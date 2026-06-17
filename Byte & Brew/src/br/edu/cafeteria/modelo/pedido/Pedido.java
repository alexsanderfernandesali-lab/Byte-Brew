package br.edu.cafeteria.modelo.pedido;

import br.edu.cafeteria.modelo.cliente.Cliente;
import br.edu.cafeteria.modelo.produto.Produto;
import java.util.ArrayList;

public class Pedido {
    private static int contadorPedidos = 0;
    private int numeroPedido;
    private ArrayList<ItemPedido> listaItens;  
    private Cliente cliente;
    private String atendente;

    public Pedido(Cliente cliente, String atendente) {
        this.cliente = cliente;
        this.atendente = atendente;
        this.listaItens = new ArrayList<>();
        this.numeroPedido = ++contadorPedidos;
    }

    public void adicionarItem(Produto produto, int quantidade) {
        if (produto.getEstoque() >= quantidade) {
            ItemPedido item = new ItemPedido(produto, quantidade);
            listaItens.add(item);
            produto.setEstoque(produto.getEstoque() - quantidade);
            System.out.println(quantidade + "x " + produto.getNome() + " adicionado!");
        } else {
            System.out.println("Estoque insuficiente! Disponível: " + produto.getEstoque());
        }
    }

    public double calcularTotal() {
        double total = 0.0;
        for (ItemPedido item : listaItens) {  
            total += item.getSubtotal();
        }
        return total;
    }

    public void exibirResumo() {
        System.out.println("\n=== RESUMO DO PEDIDO #" + numeroPedido + " ===");
        System.out.println("Cliente: " + cliente.getNome());
        System.out.println("Atendente: " + atendente);
        System.out.println("\nItens:");
        for (ItemPedido item : listaItens) {  // ← MUDOU AQUI!
            System.out.println(" - " + item.getProduto().getNome() 
                + " (R$ " + item.getProduto().getPreco() + ") x " 
                + item.getQuantidade() + " = R$ " + item.getSubtotal());
        }
        System.out.println("\nTotal: R$ " + String.format("%.2f", calcularTotal()));
    }

    public int totalItens() {
        return listaItens.size();
    }

    public int getNumeroPedido() {
        return numeroPedido;
    }

    public ArrayList<ItemPedido> getListaItens() {
        return listaItens;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public String getAtendente() {
        return atendente;
    }

    public void setAtendente(String atendente) {
        this.atendente = atendente;
    }
}
