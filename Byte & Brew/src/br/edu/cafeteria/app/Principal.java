package br.edu.cafeteria.app;


import br.edu.cafeteria.modelo.cadastros.*;
import br.edu.cafeteria.modelo.atendente.*;
import br.edu.cafeteria.modelo.produto.*;
import java.util.Scanner;

public class Principal {

    public static void main(String[] args) {
    	Scanner scanner = new Scanner(System.in);
        CadastroProduto cardapio = new CadastroProduto();
        Atendente atendente = new Atendente("Lucas");
        Atendente atendente2 = new Atendente("Pedro");
        Atendente atendente3 = new Atendente("Tiago");
        
        cardapio.adicionarProduto(new Bebidas("Café do Programador", 8.50, 101, 15, "M", 120, true));
        cardapio.adicionarProduto(new Bebidas("Poção de Mana", 7.00, 102, 10, "G", 0, false));
        cardapio.adicionarProduto(new Comidas("Lembas Bread", 12.00, 201, 8, 20, true, false));
        cardapio.adicionarProduto(new Comidas("Portal Cake", 15.00, 202, 5, 30, false, true));
        
        
        System.out.println("--- BEM VINDO A CAFETERIA BYTE & BREW ! ---");
        System.out.println("Escolha seu Atendente");
        String escolha = scanner.next();
        
        System.out.println("Olá meu nome é "+ escolha +" e serei seu atendente");
        
        System.out.println("Esse é o nosso cardápio:");
        System.out.println("---CARDÁPIO---");
        cardapio.listarProdutos();
        
        System.out.println("Digite o codigo do produto");
        int codigo = scanner.nextInt();
    }
}
