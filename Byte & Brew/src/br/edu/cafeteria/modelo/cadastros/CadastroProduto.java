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
            // Imprime as informações básicas de todo Produto
            System.out.print("Código: " + p.getCodigo() 
                + " | Nome: " + p.getNome() 
                + " | Preço: R$ " + p.getPreco());

            // Verifica se o produto é uma Bebida
            if (p instanceof br.edu.cafeteria.modelo.produto.Bebidas) {
                br.edu.cafeteria.modelo.produto.Bebidas b = (br.edu.cafeteria.modelo.produto.Bebidas) p;
                
                System.out.println(" | Tamanho: " + b.getTamanho()
                    + " | Cafeína: " + b.getCafeina() + "mg");
            } 
            // Verifica se o produto é uma Comida
            else if (p instanceof br.edu.cafeteria.modelo.produto.Comidas) {
                br.edu.cafeteria.modelo.produto.Comidas c = (br.edu.cafeteria.modelo.produto.Comidas) p;
                
                System.out.println(" | Tempo de Preparo: " + c.getTempo() + " min"
                    + " | Vegano: Sim"
                    + " | Sem Glúten: Sim" );
            } 
            // Caso seja um produto base
            else {
                System.out.println();
            }
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
