package br.edu.cafeteria.servico;

import br.edu.cafeteria.excecao.EstoqueInsuficienteException;
import br.edu.cafeteria.modelo.produto.Produto;

public class GerenciadorEstoque {
    public void validarEstoque(Produto produto, int quantidadeDesejada) {
        if (produto.getEstoque() < quantidadeDesejada) {
            throw new EstoqueInsuficienteException("Estoque insuficiente para o produto: " + produto.getNome() 
                                                    + "Disponível: " + produto.getEstoque());
        }
    }

    public void baixarEstoque(Produto produto, int quantidade) {
        validarEstoque(produto, quantidade);
        produto.setEstoque(produto.getEstoque() - quantidade);
    }
    
}
