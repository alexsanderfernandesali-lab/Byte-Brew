package br.edu.cafeteria.modelo.pedido;

import br.edu.cafeteria.modelo.cliente.Cliente;
import br.edu.cafeteria.modelo.produto.*;
import br.edu.cafeteria.modelo.atendente.Atendente;
import java.util.ArrayList;

public class Pedido {
    private static int contadorPedidos = 0;
    private int numeroPedido;
    private ArrayList<ItemPedido> listaItens;  
    private Cliente cliente;
    private Atendente atendente;
    private double valorTotal;
    private String status;

    public Pedido(Cliente cliente, Atendente atendente) {
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
    
    
    //pessoa b: método atualizado p/ receber o tipo de pagamento e a interface Promocional
    public void finalizarPedido(String tipoPagamento, br.edu.cafeteria.servico.Promocional promocao) {
        this.valorTotal = calcularTotal();

        if (promocao != null) {
            promocao.aplicarDesconto(this);
        }

        if (tipoPagamento.equalsIgnoreCase("pontosVIP")) {
            if (this.cliente instanceof br.edu.cafeteria.modelo.cliente.ClienteVIP) {
                try {
                    ((br.edu.cafeteria.modelo.cliente.ClienteVIP) this.cliente).resgatarPontos(this.valorTotal);
                    this.status = "Pago com pontos XP!";
                } catch (br.edu.cafeteria.excecao.PontosInsuficientesException e) {
                    System.out.println("[ERRO NO PAGAMENTO]" + e.getMessage());
                    this.status = "Falha no pagamento! Saldo insuficiente.";
                    return;
                }
            } else {
                System.out.println("ERRO! Apenas clientes VIP podem pagar com pontos XP.");
                this.status = "Falha no pagamento!";
                return;
            }
        } else {
            if (this.cliente != null) {
                this.cliente.acumularXp(this.valorTotal);
            }
            this.status = "Finalizado!";
        }

        System.out.println("Pedido #" + numeroPedido + " finalizado!");
        System.out.println(" Atendente: " + atendente.getNome());
        System.out.println(" Cliente: " + (cliente != null ? cliente.getNome() : "Anônnimo"));
        System.out.println(" Valor Total: R$ " + String.format("%.2f", this.valorTotal));
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
        for (ItemPedido item : listaItens) {  
            System.out.println(" - " + item.getProduto());
        }
        System.out.println("\nTotal: R$ " + String.format("%.2f", getValorTotal()));
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
    
    public double getValorTotal() {
        return valorTotal;
    }

    //pessoa b: setter criado para que a interface Promocional consiga aplicar o desconto de fora da classe 
    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }
    
    public String getStatus() {
        return status;
    }

    public Atendente getAtendente() {
        return atendente;
    }

    public void setAtendente(Atendente atendente) {
        this.atendente = atendente;
    }
}
