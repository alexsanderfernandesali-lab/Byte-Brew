package br.edu.cafeteria.modelo.cadastros;

import java.util.ArrayList;
import br.edu.cafeteria.modelo.cliente.Cliente;

public class CadastroCliente {
	private ArrayList<Cliente> listaClientes;
	
	public CadastroCliente() {
		this.listaClientes = new ArrayList<>();
	}
	
	public void adicionar(Cliente cliente) {
		listaClientes.add(cliente);
		System.out.println("Cliente cadastrado: " + cliente.getNome());
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
				return true;
			}
		}
		return false;
		
	}

}