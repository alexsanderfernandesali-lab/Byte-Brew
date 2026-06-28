package br.edu.cafeteria.app;

import br.edu.cafeteria.modelo.atendente.Atendente;
import br.edu.cafeteria.modelo.cadastros.CadastroAtendente;
import br.edu.cafeteria.modelo.cadastros.CadastroCliente;
import br.edu.cafeteria.modelo.cadastros.CadastroProduto;
import br.edu.cafeteria.modelo.cliente.Cliente;
import br.edu.cafeteria.modelo.produto.Bebidas;
import br.edu.cafeteria.modelo.produto.Comidas;
import br.edu.cafeteria.modelo.produto.Produto;
import br.edu.cafeteria.modelo.pedido.*;
import br.edu.cafeteria.servico.*;
import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CadastroProduto cardapio = new CadastroProduto();
        CadastroCliente cadastroCliente = new CadastroCliente();
        CadastroAtendente cadastroAtendente = new CadastroAtendente();

        carregarProdutosIniciais(cardapio);
        carregarAtendentesIniciais(cadastroAtendente);

        int opcao;
        do {
            System.out.println("\n--- BYTE & BREW ---");
            System.out.println("1 - Gerenciar produtos");
            System.out.println("2 - Gerenciar clientes");
            System.out.println("3 - Mostrar cardapio");
            System.out.println("4 - Atendentes");
            System.out.println("5 - Pedido");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opcao: ");

            opcao = lerInteiro(scanner);

            switch (opcao) {
                case 1:
                    menuProdutos(scanner, cardapio);
                    break;
                case 2:
                    menuClientes(scanner, cadastroCliente);
                    break;
                case 3:
                    cardapio.listarProdutos();
                    break;
                case 4:
                    menuAtendentes(scanner, cadastroAtendente);
                    break;
                case 5:
                    abrirNovoPedido(scanner, cardapio, cadastroCliente, cadastroAtendente);
                    break;
                case 0:
                    System.out.println("Sistema encerrado.");
                    break;
                default:
                    System.out.println("Opcao invalida.");
            }
        } while (opcao != 0);

        scanner.close();
    }
    

    private static void abrirNovoPedido(Scanner scanner, CadastroProduto cardapio,
                                        CadastroCliente cadastroCliente,
                                        CadastroAtendente cadastroAtendente) {

        System.out.println("\n--- NOVO PEDIDO ---");

        System.out.println("Escolha o atendente:");
        cadastroAtendente.listarAtendentes();
        System.out.print("Digite o nome do atendente: ");
        String nomeAtendente = scanner.nextLine();
        Atendente atendente = cadastroAtendente.buscarPorNome(nomeAtendente);

        if (atendente == null) {
            System.out.println("Atendente nao encontrado! Pedido cancelado.");
            return;
        }

        Cliente cliente = null;
        System.out.print("Cliente tem cadastro? (s/n): ");
        String temCadastro = scanner.nextLine();

        if (temCadastro.equalsIgnoreCase("s")) {
            System.out.print("Digite o CPF do cliente: ");
            String cpf = scanner.nextLine();
            cliente = cadastroCliente.buscarCpf(cpf);

            if (cliente == null) {
                System.out.println("Cliente nao encontrado! Pedido seguira como anonimo.");
            } else {
                System.out.println("Cliente encontrado: " + cliente.getNome());
            }
        } else {
            System.out.println("Pedido anonimo - sem acumulo de XP.");
        }

        Pedido pedido = new Pedido(cliente, atendente);

        boolean continuar = true;
        while (continuar) {
            System.out.println("\n--- CARDAPIO ---");
            cardapio.listarProdutos();

            System.out.print("Digite o codigo do produto (ou 0 para finalizar): ");
            int codigo = lerInteiro(scanner);

            if (codigo == 0) {
                if (pedido.totalItens() == 0) {
                    System.out.println("Pedido vazio! Nao e possivel finalizar.");
                    return;
                }
                continuar = false;
                break;
            }

            Produto produto = cardapio.buscarPorCodigo(codigo);
            if (produto == null) {
                System.out.println("Produto nao encontrado!");
                continue;
            }

            System.out.print("Digite a quantidade: ");
            int quantidade = lerInteiro(scanner);

            pedido.adicionarItem(produto, quantidade);
        }

        double total = pedido.calcularTotal();
        System.out.println("\nSubtotal: R$ " + String.format("%.2f", total));

        System.out.print("Hoje e Dia de Evento Geek? (s/n): ");
        String eventoGeek = scanner.nextLine();

        double desconto = 0.0;
        if (eventoGeek.equalsIgnoreCase("s")) {
            desconto = calcularDescontoBebidas(pedido);
            System.out.println("Desconto Geek aplicado: R$ " + String.format("%.2f", desconto));
            total -= desconto;
            System.out.println("Total com desconto: R$ " + String.format("%.2f", total));
        }

        System.out.println("\n--- PAGAMENTO ---");
        System.out.println("1 - Pagar com XP acumulado");
        System.out.println("2 - Pagar normalmente");
        System.out.println("3 - Desistir da compra");
        System.out.print("Escolha uma opcao: ");
        int opcaoPagamento = lerInteiro(scanner);

        if (opcaoPagamento == 3) {
            System.out.println("Compra cancelada. Voltando ao menu principal.");
            return;
        }

        if (opcaoPagamento == 1) {
            if (cliente != null && cliente.getSaldoXP() > 0) {
                double xpEmReais = cliente.getSaldoXP()/10; // 1 XP = R$ 1,00
                if (xpEmReais >= total) {
                    System.out.println("Pagamento realizado com XP!");
                    cliente.setSaldoXP(cliente.getSaldoXP() - total);
                    total = 0.0;
                } else {
                    System.out.println("Saldo XP insuficiente. Pagando com XP disponivel e complementando.");
                    total -= xpEmReais;
                    cliente.setSaldoXP(0);
                    System.out.println("Saldo restante a pagar: R$ " + String.format("%.2f", total));
                    System.out.println("Pagando o restante normalmente.");
                }
            } else {
                System.out.println("Cliente sem XP acumulado. Pagamento normal.");
            }
        }

        pedido.exibirResumo();

        System.out.println("\nTotal pago: R$ " + String.format("%.2f", total));

        if (cliente != null && opcaoPagamento != 3) {
            CalculadoraPontos calculadora = new CalculadoraPontos();
            calculadora.calcularPontos(pedido);
            System.out.println("Novo saldo XP: " + cliente.getSaldoXP());
        }

        System.out.println("\nPedido concluido! Obrigado pela preferencia.");
    }
    
    private static double calcularDescontoBebidas(Pedido pedido) {
        double totalBebidas = 0.0;
        for (var item : pedido.getListaItens()) {
            if (item.getProduto() instanceof Bebidas) {
                totalBebidas += item.getSubtotal();
            }
        }
        return totalBebidas * 0.10; 
    }


    private static void carregarProdutosIniciais(CadastroProduto cardapio) {
        cardapio.adicionarProduto(new Bebidas("Cafe do Programador", 8.50, 101, 15, "M", 120, true));
        cardapio.adicionarProduto(new Bebidas("Pocao de Mana", 7.00, 102, 10, "G", 0, false));
        cardapio.adicionarProduto(new Comidas("Lembas Bread", 12.00, 201, 8, 20, true, false));
        cardapio.adicionarProduto(new Comidas("Portal Cake", 15.00, 202, 5, 30, false, true));
    }

    private static void carregarAtendentesIniciais(CadastroAtendente cadastroAtendente) {
        cadastroAtendente.adicionarAtendente(new Atendente("Lucas"));
        cadastroAtendente.adicionarAtendente(new Atendente("Pedro"));
        cadastroAtendente.adicionarAtendente(new Atendente("Tiago"));
    }

    private static void menuProdutos(Scanner scanner, CadastroProduto cardapio) {
        int opcao;
        do {
            System.out.println("\n--- GERENCIAR PRODUTOS ---");
            System.out.println("1 - Cadastrar produto");
            System.out.println("2 - Listar produtos");
            System.out.println("3 - Buscar produto por codigo");
            System.out.println("4 - Atualizar produto");
            System.out.println("5 - Remover produto");
            System.out.println("0 - Voltar");
            System.out.print("Escolha uma opcao: ");

            opcao = lerInteiro(scanner);

            switch (opcao) {
                case 1:
                    cadastrarProduto(scanner, cardapio);
                    break;
                case 2:
                    cardapio.listarProdutos();
                    break;
                case 3:
                    buscarProduto(scanner, cardapio);
                    break;
                case 4:
                    atualizarProduto(scanner, cardapio);
                    break;
                case 5:
                    removerProduto(scanner, cardapio);
                    break;
                case 0:
                    System.out.println("Voltando ao menu principal.");
                    break;
                default:
                    System.out.println("Opcao invalida.");
            }
        } while (opcao != 0);
    }

    private static void cadastrarProduto(Scanner scanner, CadastroProduto cardapio) {
        System.out.println("\nTipo de produto:");
        System.out.println("1 - Comida");
        System.out.println("2 - Bebida");
        System.out.print("Escolha uma opcao: ");
        int tipo = lerInteiro(scanner);

        System.out.print("Codigo: ");
        int codigo = lerInteiro(scanner);

        if (cardapio.buscarPorCodigo(codigo) != null) {
            System.out.println("Ja existe um produto com esse codigo.");
            return;
        }

        System.out.print("Nome: ");
        String nome = scanner.nextLine();

        System.out.print("Preco: ");
        double preco = lerDouble(scanner);

        System.out.print("Quantidade em estoque: ");
        int estoque = lerInteiro(scanner);

        if (tipo == 1) {
            System.out.print("Tempo de preparo em minutos: ");
            int tempo = lerInteiro(scanner);

            System.out.print("E vegano? (s/n): ");
            boolean vegano = lerSimNao(scanner);

            System.out.print("E sem gluten? (s/n): ");
            boolean semGluten = lerSimNao(scanner);

            cardapio.adicionarProduto(new Comidas(nome, preco, codigo, estoque, tempo, vegano, semGluten));
        } else if (tipo == 2) {
            System.out.print("Tamanho (P/M/G): ");
            String tamanho = scanner.nextLine();

            System.out.print("Cafeina em mg: ");
            int cafeina = lerInteiro(scanner);

            System.out.print("E quente? (s/n): ");
            boolean quente = lerSimNao(scanner);

            cardapio.adicionarProduto(new Bebidas(nome, preco, codigo, estoque, tamanho, cafeina, quente));
        } else {
            System.out.println("Tipo invalido. Produto nao cadastrado.");
        }
    }

    private static void buscarProduto(Scanner scanner, CadastroProduto cardapio) {
        System.out.print("Digite o codigo do produto: ");
        int codigo = lerInteiro(scanner);
        Produto produto = cardapio.buscarPorCodigo(codigo);

        if (produto == null) {
            System.out.println("Produto nao encontrado.");
            return;
        }

        exibirProduto(produto);
    }

    private static void atualizarProduto(Scanner scanner, CadastroProduto cardapio) {
        System.out.print("Digite o codigo do produto: ");
        int codigo = lerInteiro(scanner);
        Produto produto = cardapio.buscarPorCodigo(codigo);

        if (produto == null) {
            System.out.println("Produto nao encontrado.");
            return;
        }

        System.out.println("Produto atual:");
        exibirProduto(produto);

        System.out.print("Novo nome: ");
        produto.setNome(scanner.nextLine());

        System.out.print("Novo preco: ");
        produto.setPreco(lerDouble(scanner));

        System.out.print("Novo estoque: ");
        produto.setEstoque(lerInteiro(scanner));

        if (produto instanceof Comidas) {
            Comidas comida = (Comidas) produto;
            System.out.print("Novo tempo de preparo em minutos: ");
            comida.setTempo(lerInteiro(scanner));

            System.out.print("E vegano? (s/n): ");
            comida.setVegan(lerSimNao(scanner));

            System.out.print("E sem gluten? (s/n): ");
            comida.setSgluten(lerSimNao(scanner));
        } else if (produto instanceof Bebidas) {
            Bebidas bebida = (Bebidas) produto;
            System.out.print("Novo tamanho (P/M/G): ");
            bebida.setTamanho(scanner.nextLine());

            System.out.print("Nova cafeina em mg: ");
            bebida.setCafeina(lerInteiro(scanner));

            System.out.print("E quente? (s/n): ");
            bebida.setQuente(lerSimNao(scanner));
        }

        System.out.println("Produto atualizado com sucesso.");
    }

    private static void removerProduto(Scanner scanner, CadastroProduto cardapio) {
        System.out.print("Digite o codigo do produto: ");
        int codigo = lerInteiro(scanner);
        cardapio.removerPorCodigo(codigo);
    }

    private static void menuClientes(Scanner scanner, CadastroCliente cadastroCliente) {
        int opcao;
        do {
            System.out.println("\n--- GERENCIAR CLIENTES ---");
            System.out.println("1 - Cadastrar cliente Standard");
            System.out.println("2 - Cadastrar cliente VIP");
            System.out.println("3 - Listar clientes");
            System.out.println("4 - Buscar cliente por CPF");
            System.out.println("5 - Atualizar cliente");
            System.out.println("6 - Remover cliente");
            System.out.println("0 - Voltar");
            System.out.print("Escolha uma opcao: ");

            opcao = lerInteiro(scanner);

            switch (opcao) {
                case 1:
                    cadastrarClienteStandard(scanner, cadastroCliente);
                    break;
                case 2:
                    cadastrarClienteVip(scanner, cadastroCliente);
                    break;
                case 3:
                    cadastroCliente.listaClientes();
                    break;
                case 4:
                    buscarCliente(scanner, cadastroCliente);
                    break;
                case 5:
                    atualizarCliente(scanner, cadastroCliente);
                    break;
                case 6:
                    removerCliente(scanner, cadastroCliente);
                    break;
                case 0:
                    System.out.println("Voltando ao menu principal.");
                    break;
                default:
                    System.out.println("Opcao invalida.");
            }
        } while (opcao != 0);
    }

    private static void cadastrarClienteStandard(Scanner scanner, CadastroCliente cadastroCliente) {
        System.out.print("Nome: ");
        String nome = scanner.nextLine();

        System.out.print("CPF: ");
        String cpf = scanner.nextLine();

        cadastroCliente.cadastrarStandard(nome, cpf);
    }

    private static void cadastrarClienteVip(Scanner scanner, CadastroCliente cadastroCliente) {
        System.out.print("Nome: ");
        String nome = scanner.nextLine();

        System.out.print("CPF: ");
        String cpf = scanner.nextLine();

        cadastroCliente.cadastrarVip(nome, cpf);
    }

    private static void buscarCliente(Scanner scanner, CadastroCliente cadastroCliente) {
        System.out.print("Digite o CPF: ");
        String cpf = scanner.nextLine();
        Cliente cliente = cadastroCliente.buscarCpf(cpf);

        if (cliente == null) {
            System.out.println("Cliente nao encontrado.");
            return;
        }

        exibirCliente(cliente);
    }

    private static void atualizarCliente(Scanner scanner, CadastroCliente cadastroCliente) {
        System.out.print("Digite o CPF: ");
        String cpf = scanner.nextLine();
        Cliente cliente = cadastroCliente.buscarCpf(cpf);

        if (cliente == null) {
            System.out.println("Cliente nao encontrado.");
            return;
        }

        System.out.println("Cliente atual:");
        exibirCliente(cliente);

        System.out.print("Novo nome: ");
        cliente.setNome(scanner.nextLine());

        System.out.println("Cliente atualizado com sucesso.");
    }

    private static void removerCliente(Scanner scanner, CadastroCliente cadastroCliente) {
        System.out.print("Digite o CPF: ");
        String cpf = scanner.nextLine();
        cadastroCliente.removerCpf(cpf);
    }

    private static void menuAtendentes(Scanner scanner, CadastroAtendente cadastroAtendente) {
        int opcao;
        do {
            System.out.println("\n--- ATENDENTES ---");
            System.out.println("1 - Listar atendentes");
            System.out.println("2 - Buscar atendente por nome");
            System.out.println("0 - Voltar");
            System.out.print("Escolha uma opcao: ");

            opcao = lerInteiro(scanner);

            switch (opcao) {
                case 1:
                    cadastroAtendente.listarAtendentes();
                    break;
                case 2:
                    buscarAtendente(scanner, cadastroAtendente);
                    break;
                case 0:
                    System.out.println("Voltando ao menu principal.");
                    break;
                default:
                    System.out.println("Opcao invalida.");
            }
        } while (opcao != 0);
    }

    private static void buscarAtendente(Scanner scanner, CadastroAtendente cadastroAtendente) {
        System.out.print("Digite o nome do atendente: ");
        String nome = scanner.nextLine();
        Atendente atendente = cadastroAtendente.buscarPorNome(nome);

        if (atendente == null) {
            System.out.println("Atendente nao encontrado.");
            return;
        }

        System.out.println(atendente);
    }

    private static void exibirProduto(Produto produto) {
        System.out.println("Codigo: " + produto.getCodigo());
        System.out.println("Nome: " + produto.getNome());
        System.out.println("Preco: R$ " + produto.getPreco());
        System.out.println("Estoque: " + produto.getEstoque());

        if (produto instanceof Comidas) {
            Comidas comida = (Comidas) produto;
            System.out.println("Tipo: Comida");
            System.out.println("Tempo de preparo: " + comida.getTempo() + " minutos");
            System.out.println("Vegano: " + formatarBooleano(comida.isVegan()));
            System.out.println("Sem gluten: " + formatarBooleano(comida.isSgluten()));
        } else if (produto instanceof Bebidas) {
            Bebidas bebida = (Bebidas) produto;
            System.out.println("Tipo: Bebida");
            System.out.println("Tamanho: " + bebida.getTamanho());
            System.out.println("Cafeina: " + bebida.getCafeina() + " mg");
            System.out.println("Quente: " + formatarBooleano(bebida.isQuente()));
        }
    }

    private static void exibirCliente(Cliente cliente) {
        System.out.println("Nome: " + cliente.getNome());
        System.out.println("CPF: " + cliente.getCpf());
        System.out.println("Saldo XP: " + cliente.getSaldoXP());
        System.out.println("Tipo: " + cliente.getClass().getSimpleName());
    }

    private static int lerInteiro(Scanner scanner) {
        while (!scanner.hasNextInt()) {
            System.out.print("Digite um numero inteiro valido: ");
            scanner.nextLine();
        }
        int valor = scanner.nextInt();
        scanner.nextLine();
        return valor;
    }

    private static double lerDouble(Scanner scanner) {
        while (!scanner.hasNextDouble()) {
            System.out.print("Digite um numero valido: ");
            scanner.nextLine();
        }
        double valor = scanner.nextDouble();
        scanner.nextLine();
        return valor;
    }

    private static boolean lerSimNao(Scanner scanner) {
        String resposta = scanner.nextLine();
        return resposta.equalsIgnoreCase("s") || resposta.equalsIgnoreCase("sim");
    }

    private static String formatarBooleano(boolean valor) {
        return valor ? "Sim" : "Nao";
    }
}
