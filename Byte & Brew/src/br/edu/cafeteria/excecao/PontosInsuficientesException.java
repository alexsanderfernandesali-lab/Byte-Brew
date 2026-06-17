package br.edu.cafeteria.excecao;

    //justificativa: Unchecked Exception (RuntimeException). Utilizada para monitorar o saldo de pontos do cliente sem engessar as assinaturas dos meétodos de servico.

public class PontosInsuficientesException extends RuntimeException {
    public PontosInsuficientesException(String message) {
        super(message);
    }
}