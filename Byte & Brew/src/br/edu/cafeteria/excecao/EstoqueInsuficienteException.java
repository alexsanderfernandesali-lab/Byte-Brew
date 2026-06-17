package br.edu.cafeteria.excecao;


    //justificativa: Unchecked Exception (RuntimeException). Utilizada para regras de negócio, evitando a obrigatoriedade de blocos try-catch por todo o código.

public class EstoqueInsuficienteException extends RuntimeException {
    public EstoqueInsuficienteException(String message) {
        super(message);
    }
    
}
