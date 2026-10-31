package conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoBanco {

    private static final String URL = "jdbc:mysql://localhost:3306/ByteAndBrew";
    private static final String USUARIO = "root";
    private static final String SENHA = "ecstasy";

    // Método que abre a conexão e retorna ela para quem chamou
    public static Connection getConexao() {
        try {
            // Tenta conectar com o banco
            return DriverManager.getConnection(URL, USUARIO, SENHA);
        } catch (SQLException e) {
            // Se der erro (senha errada, banco não existe, etc), mostra no console
            System.out.println("Erro ao conectar com o banco de dados!");
            System.out.println("Motivo: " + e.getMessage());
            return null;
        }
    }
}