package br.edu.cafeteria.modelo.cadastros;

import br.edu.cafeteria.modelo.produto.Produto;

import java.util.ArrayList;

public class CadastroProduto {
    private ArrayList<Produto> listaProdutos;

    public CadastroProduto() {
        this.listaProdutos = new ArrayList<>();
    }

    public void adicionarProduto(Produto produto) {
        listaProdutos.add(produto);
        System.out.println("Produto adicionado: " + produto.getNome());
    }

    public void listarProdutos() {
        if (listaProdutos.isEmpty()) {
            System.out.println("Nenhum produto cadastrado.");
            return;
        }
        System.out.println("\n=== CARDÁPIO ===");
        for (Produto p : listaProdutos) {
            System.out.println("Código: " + p.getCodigo() 
                + " | Nome: " + p.getNome() 
                + " | Preço: R$ " + p.getPreco() 
                + " | Estoque: " + p.getEstoque());
        }
    }

    public Produto buscarPorCodigo(int codigo) {
        for (Produto p : listaProdutos) {
            if (p.getCodigo() == codigo) {
                return p;
            }
        }
        return null;
    }

    public Produto buscarPorNome(String nome) {
        for (Produto p : listaProdutos) {
            if (p.getNome().equalsIgnoreCase(nome)) {
                return p;
            }
        }
        return null;
    }

    public boolean removerPorCodigo(int codigo) {
        Produto encontrado = buscarPorCodigo(codigo);
        if (encontrado != null) {
            listaProdutos.remove(encontrado);
            System.out.println("Produto removido: " + encontrado.getNome());
            return true;
        }
        System.out.println("Produto não encontrado.");
        return false;
    }

    public ArrayList<Produto> getListaProdutos() {
        return listaProdutos;
    }

    public int totalProdutos() {
        return listaProdutos.size();
    }
}
