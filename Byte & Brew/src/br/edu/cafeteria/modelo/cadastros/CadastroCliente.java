package br.edu.cafeteria.modelo.cadastros;

import br.edu.cafeteria.modelo.cliente.Cliente;
import java.util.ArrayList;

public class CadastroCliente {
	private ArrayList<Cliente> listaClientes;
	
	public CadastroCliente() {
		this.listaClientes = new ArrayList<>();
	}
	
	public void adicionar(Cliente cliente) {
		listaClientes.add(cliente);
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
