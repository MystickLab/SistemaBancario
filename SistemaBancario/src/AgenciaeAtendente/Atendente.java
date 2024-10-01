package AgenciaeAtendente;

import ContaBancaria.ContaBancaria;
import jdbcconexao.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Atendente {
    private String nome;
    private Agencia agencia;

    public Atendente(String nome, Agencia agencia) {
        this.nome = nome;
        this.agencia = agencia;
    }

    public String getNome() {
        return nome;
    }

    public boolean verificarConta(String cpf, String numeroConta, String senha) {
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM conta WHERE cpf = ? AND numero = ? AND senha = ?")) {
            stmt.setString(1, cpf);
            stmt.setString(2, numeroConta);
            stmt.setString(3, senha);
            ResultSet rs = stmt.executeQuery();
            return rs.next(); // Retorna true se uma conta correspondente for encontrada
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Falha na verificação
        }
    }

    public boolean depositar(String tipoConta, String numeroConta, double valor) {
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement("UPDATE conta SET saldo = saldo + ? WHERE numero = ?")) {
            stmt.setDouble(1, valor);
            stmt.setString(2, numeroConta);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0; // Retorna true se o depósito foi realizado com sucesso
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Falha no depósito
        }
    }

    public ContaBancaria buscarConta(String numeroConta) {
        // Método para buscar a conta no banco de dados
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM conta WHERE numero = ?")) {
            stmt.setString(1, numeroConta);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                // Retorna uma nova conta com base nos dados do ResultSet
                int numero = rs.getInt("numero");
                double saldo = rs.getDouble("saldo");
                ContaBancaria conta = new ContaBancaria(numero);
                conta.depositar(saldo); // Define o saldo atual da conta
                return conta;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // Conta não encontrada
    }

	public Object getAgencia() {
		// TODO Auto-generated method stub
		return agencia;
	}

	
}
