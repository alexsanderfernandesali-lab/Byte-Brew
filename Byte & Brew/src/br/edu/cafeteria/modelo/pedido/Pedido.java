package br.edu.cafeteria.modelo.pedido;

import br.edu.cafeteria.modelo.cliente.Cliente;
import br.edu.cafeteria.modelo.produto.Produto;
import java.util.ArrayList;

public class Pedido {
	private int nrPedidos = 0;
	private static int nrComanda;
	private ArrayList<Produto> listaItens; //lista de produtos no pedido
	private Cliente cliente;
	private String atendente;
	
	public Pedido(Cliente cliente, String atendente) {
		this.cliente = cliente;
		this.atendente = atendente;
		this.listaItens = new ArrayList<>();
		//this.nrPedidos = ++nrPedidos;
	}
	
	public void adicionarItem(Produto produto) {
		if (produto.getEstoque()>0) {
			listaItens.add(produto);
			produto.setEstoque(produto.getEstoque() - 1);
			System.out.println("Item adicionado: " + produto.getNome());
		}
		}
		
		 public double calcularTotal() {
		        double total = 0.0;
		        for (Produto produto : listaItens) {
		            total += produto.getPreco();
		        }
		        return total;
	}
		 public void exibirResumo() {
		        System.out.println("\n=== RESUMO DO PEDIDO #" + nrComanda + " ==="); //talvez nrPedidos
		        System.out.println("Cliente: " + cliente.getNome());
		        System.out.println("Atendente: " + atendente);
		        System.out.println("\nItens:");
		        for (Produto produto : listaItens) {
		            System.out.println(" - " + produto.getNome() + " (R$ " + produto.getPreco() + ")");
		        }
		        System.out.println("\nTotal: R$ " + String.format("%.2f", calcularTotal()));
}
		 public int totalItens() {
		        return listaItens.size();
		    }
		 
		 public int getNumeroPedidos() {
		        return nrPedidos;
		    }
		    
		    public ArrayList<Produto> getListaItens() {
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

