package br.edu.cafeteria.modelo.cadastros;

import conexao.ClienteDAO;
import java.util.ArrayList;
import br.edu.cafeteria.modelo.cliente.*;

public class CadastroCliente {
	private ArrayList<Cliente> listaClientes;
	private ClienteDAO dao; 
	
	public CadastroCliente(ClienteDAO dao) {
		this.listaClientes = new ArrayList<>();
		this.dao = dao;
	}
	
    public void cadastrarStandard(String nome, String cpf) {
    	if (buscarCpf(cpf) != null) {
    		System.out.println("   CPF ja cadastrado!");
    		return;
    	}

        ClienteStandard novoCliente = new ClienteStandard(nome, cpf);
        
        this.dao.salvar(novoCliente);
        
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
        
    
        this.dao.salvar(novoCliente);
        
        System.out.println("   Cliente VIP cadastrado com sucesso!");
        System.out.println("   Nome: " + nome);
        System.out.println("   CPF: " + cpf);
        System.out.println("   Saldo XP inicial: 0.0");
        System.out.println("   Cashback: 5%");
    }
	
    public Cliente buscarCpf(String cpf) {
        return this.dao.buscarPorCpf(cpf);
    }

    // Seu método de listar fica assim (repare que ele chama o banco e imprime a lista nova):
    public void listaClientes() {
        ArrayList<Cliente> clientesDoBanco = this.dao.listarTodos();
        
        if (clientesDoBanco.isEmpty()) {
            System.out.println("Nenhum cliente cadastrado.");
            return;
        }

        System.out.println("LISTA DE CLIENTES (" + clientesDoBanco.size() + "):");
        for (Cliente c : clientesDoBanco) {
            System.out.println("   " + c);
        }
    }

    // Seu método de contagem fica assim:
    public int getTotalClientes() {
        return this.dao.contarClientes();
    }
	
	public boolean removerCpf(String cpf) {
        for (Cliente c : listaClientes) {
            if (c.getCpf().equals(cpf)) {
                listaClientes.remove(c);
                System.out.println(" Cliente removido: " + c.getNome());
                return true;
            }
        }
        System.out.println(" Cliente com CPF " + cpf + " não encontrado!");
        return false;
    }
	
}
