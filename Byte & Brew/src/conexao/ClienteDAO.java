package conexao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.edu.cafeteria.modelo.cliente.Cliente;
import br.edu.cafeteria.modelo.cliente.ClienteStandard;
import br.edu.cafeteria.modelo.cliente.ClienteVIP;

public class ClienteDAO {

    public void salvar(Cliente cliente) {
        String sql = "INSERT INTO cliente (nome, cpf, saldo_xp, is_vip) VALUES (?, ?, ?, ?)";

        try (Connection conn = ConexaoBanco.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getCpf());
            stmt.setDouble(3, cliente.getSaldoXP()); // (ou getPontosAcumulados(), dependendo do seu código)

            boolean ehVip = (cliente instanceof ClienteVIP); 
            stmt.setBoolean(4, ehVip);

            stmt.executeUpdate();
            
            System.out.println("\n[Banco de Dados] Cliente " + cliente.getNome() + " salvo com sucesso!");

        } catch (SQLException e) {
            System.out.println("\n[Erro no Banco] Não foi possível salvar o cliente.");
            System.out.println("Motivo: " + e.getMessage());
        }
    }
    
    public Cliente buscarPorCpf(String cpf) {
        String sql = "SELECT * FROM cliente WHERE cpf = ?";
        
        try (Connection conn = ConexaoBanco.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, cpf);
            
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String nome = rs.getString("nome");
                    double saldoXp = rs.getDouble("saldo_xp");
                    boolean isVip = rs.getBoolean("is_vip");
                    
                    Cliente clienteEncontrado;
                    if (isVip) {
                        clienteEncontrado = new ClienteVIP(nome, cpf);
                    } else {
                        clienteEncontrado = new ClienteStandard(nome, cpf);
                    }
                    
                    clienteEncontrado.setSaldoXP(saldoXp);
                    
                    return clienteEncontrado;
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar cliente: " + e.getMessage());
        }
        return null;
    }
    
    public int contarClientes() {
        String sql = "SELECT COUNT(*) AS total FROM cliente";
        
        try (Connection conn = ConexaoBanco.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            
            if (rs.next()) {
                return rs.getInt("total"); // Pega o resultado do COUNT
            }
        } catch (SQLException e) {
            System.out.println("Erro ao contar clientes: " + e.getMessage());
        }
        return 0;
    }
    
    public ArrayList<Cliente> listarTodos() {
        ArrayList<Cliente> lista = new ArrayList<>();
        String sql = "SELECT * FROM cliente";
        
        try (Connection conn = ConexaoBanco.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                String nome = rs.getString("nome");
                String cpf = rs.getString("cpf");
                boolean isVip = rs.getBoolean("is_vip");
                
                Cliente c;
                if (isVip) {
                    c = new ClienteVIP(nome, cpf);
                } else {
                    c = new ClienteStandard(nome, cpf);
                }
                lista.add(c);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar clientes: " + e.getMessage());
        }
        return lista;
    }
   //sdsdsfdsfdfsd 
}


