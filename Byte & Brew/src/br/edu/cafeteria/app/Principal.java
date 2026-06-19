package br.edu.cafeteria.app;


import br.edu.cafeteria.modelo.cadastros.*;
import br.edu.cafeteria.modelo.atendente.*;
import br.edu.cafeteria.modelo.produto.*;
import br.edu.cafeteria.modelo.cliente.*;
import java.util.Scanner;

public class Principal {

    public static void main(String[] args) {
    	Scanner scanner = new Scanner(System.in);
        CadastroProduto cardapio = new CadastroProduto();
        
        cardapio.adicionarProduto(new Bebidas("Café do Programador", 8.50, 101, 15, "M", 120, true));
        cardapio.adicionarProduto(new Bebidas("Poção de Mana", 7.00, 102, 10, "G", 0, false));
        cardapio.adicionarProduto(new Comidas("Lembas Bread", 12.00, 201, 8, 20, true, false));
        cardapio.adicionarProduto(new Comidas("Portal Cake", 15.00, 202, 5, 30, false, true));
        
        
        System.out.println("--- BEM VINDO A CAFETERIA BYTE & BREW ! ---");
        System.out.println("Escolha seu Atendente:");
        System.out.println("1 - Lucas");
        System.out.println("2 - Pedro");
        System.out.println("3 - Tiago");
        System.out.print("Digite o número do atendente: ");
        int opcaoAtendente = scanner.nextInt();
        scanner.nextLine();
        
        //Precisa organizar atendente
        Atendente atendenteEscolhido = null;
       
       
        
        System.out.println("Olá meu nome é e serei seu atendente");
        System.out.println("Aqui na Byte & Brew possuímos um sistema de acumulo de pontos"
        		+ " que a cada compra realizada você acumula pontos que poderá usar em proximas compras sempre que desejar"
        		+ "bastara me informar seu nome e CPF, tem interesse?"); 
       
        String escolhaCadastro = scanner.nextLine();
        
      //  CadastroCliente cliente = new CadastroCliente();
        
        if (escolhaCadastro.equalsIgnoreCase("sim")) {
        	
        	System.out.print("Digite seu nome: ");
            String nome = scanner.nextLine();
            
            System.out.print("Digite seu CPF: ");
            String cpf = scanner.nextLine();
            
            CadastroCliente cadastroCliente = new CadastroCliente();
			cadastroCliente.cadastrarStandard(nome, cpf);
            
            
            
        
        } else { 
            System.out.println("Tudo bem! Caso mude de ideia, é só nos avisar.");
        
        }
       
        
        System.out.println("\nEsse é o nosso cardápio:");
        System.out.println("---CARDÁPIO---");
        cardapio.listarProdutos();
        
        System.out.println("\nComo deseja pesquisar?");
        System.out.println("1 - Por código");
        System.out.println("2 - Por nome");
        System.out.print("Digite a opção: ");
        int opcaoPesquisa = scanner.nextInt();
        scanner.nextLine(); // consumir quebra de linha
        
        switch (opcaoPesquisa) {
            case 1:
                System.out.print("Digite o código do produto: ");
                int codigo = scanner.nextInt();
                Produto produtoPorCodigo = cardapio.buscarPorCodigo(codigo);
                if (produtoPorCodigo != null) {
                    System.out.println("Produto encontrado: " + produtoPorCodigo);
                } else {
                    System.out.println("Produto não encontrado!");
                }
                break;
            case 2:
                System.out.print("Digite o nome do produto: ");
              //  String nomeProduto = scanner.nextLine();
                //ArrayList<Produto> produtosPorNome = cardapio.buscarPorNome(nomeProduto);
                //if (!produtosPorNome.isEmpty()) {
                    System.out.println("Produtos encontrados:");
                  //  for (Produto p : produtosPorNome) {
                    //    System.out.println(p);
                //    }
                //} else {
                  //  System.out.println("Nenhum produto encontrado com esse nome!");
                //}
                //break;
            //default:
                System.out.println("Opção inválida!");
                break;
        }
        
        scanner.close();
    }
}