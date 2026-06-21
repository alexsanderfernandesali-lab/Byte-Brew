package br.edu.cafeteria.modelo.cadastros;

import java.util.ArrayList;
import br.edu.cafeteria.modelo.cliente.*;

public class CadastroCliente {
	private ArrayList<Cliente> listaClientes;
	private int totalClientes;
	
	public CadastroCliente() {
		this.listaClientes = new ArrayList<>();
		this.totalClientes = 0;
	}
	
	 // Método para cadastrar cliente STANDARD (apenas nome e cpf)
    public void cadastrarStandard(String nome, String cpf) {
    	if (buscarCpf(cpf) != null) {
    		System.out.println("   CPF ja cadastrado!");
    		return;
    	}

        ClienteStandard novoCliente = new ClienteStandard(nome, cpf);
        listaClientes.add(novoCliente);
        totalClientes++;
        System.out.println("   Cliente STANDARD cadastrado com sucesso!");
        System.out.println("   Nome: " + nome);
        System.out.println("   CPF: " + cpf);
        System.out.println("   Saldo XP inicial: 0.0");
	}
    
    public void cadastrarVip(String nome, String cpf) {
    	if (buscarCpf(cpf) != null) {
    		System.out.println("   CPF ja cadastrado!");
    		return;
    	}

        ClienteVIP novoCliente = new ClienteVIP(nome, cpf);
        listaClientes.add(novoCliente);
        totalClientes++;
        System.out.println("   Cliente VIP cadastrado com sucesso!");
        System.out.println("   Nome: " + nome);
        System.out.println("   CPF: " + cpf);
        System.out.println("   Saldo XP inicial: 0.0");
        System.out.println("   Cashback: 5%");
        
    }
	
	public Cliente buscarCpf(String cpf) {
		for (Cliente c : listaClientes) {
			if (c.getCpf().equals(cpf)){
				return c;
			}
		}
		return null;

	}
	
	public boolean removerCpf(String cpf) {
        for (Cliente c : listaClientes) {
            if (c.getCpf().equals(cpf)) {
                listaClientes.remove(c);
                totalClientes--;
                System.out.println(" Cliente removido: " + c.getNome());
                return true;
            }
        }
        System.out.println(" Cliente com CPF " + cpf + " não encontrado!");
        return false;
    }
	
	public int getTotalClientes() {
        return totalClientes;
	}
	public void listaClientes() {
	 if (listaClientes.isEmpty()) {
		 System.out.println("Nenhum cliente cadastrado.");
		 return;
	 }

	 System.out.println("LISTA DE CLIENTES (" + totalClientes + "):");
     for (Cliente c : listaClientes) 
         System.out.println("   " + c);
     }

}
