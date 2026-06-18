package br.edu.cafeteria.app;

import br.edu.cafeteria.modelo.cadastros.*;
import br.edu.cafeteria.modelo.produto.Bebidas;
import br.edu.cafeteria.modelo.produto.Comidas;

public class Principal {

    public static void main(String[] args) {
        CadastroProduto cardapio = new CadastroProduto();
        
        cardapio.adicionarProduto(new Bebidas("Café do Programador", 8.50, 101, 15, "M", 120, true));
        cardapio.adicionarProduto(new Bebidas("Poção de Mana", 7.00, 102, 10, "G", 0, false));
        cardapio.adicionarProduto(new Comidas("Lembas Bread", 12.00, 201, 8, 20, true, false));
        cardapio.adicionarProduto(new Comidas("Portal Cake", 15.00, 202, 5, 30, false, true));
        
        
        System.out.println("--- BEM VINDO A CAFETERIA BYTE & BREW ---");
        System.out.println("Esse é o nosso cardápio:");
        cardapio.listarProdutos();
    }
}
