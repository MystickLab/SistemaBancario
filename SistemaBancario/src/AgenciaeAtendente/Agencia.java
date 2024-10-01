package AgenciaeAtendente;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import Banco.Banco;
import jdbcconexao.ConnectionFactory;

public class Agencia {
    private String nome;
    private Banco banco;
    private int bancoId; // Armazena o banco_id
    private ArrayList<Atendente> atendentes;

    public Agencia(String nome, Banco banco, int bancoId) {
        this.nome = nome;
        this.banco = banco;
        this.bancoId = bancoId;
        this.atendentes = carregarAtendentes();
    }

    public ArrayList<Atendente> carregarAtendentes() {
        ArrayList<Atendente> listaAtendentes = new ArrayList<>();
        try (Connection conn = ConnectionFactory.getConnection();
             Statement stmt = conn.createStatement()) {

            String query = "SELECT * FROM atendente WHERE agencia_id = (SELECT id FROM agencia WHERE nome = '" + nome + "')";
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                String nomeAtendente = rs.getString("nome");
                Atendente atendente = new Atendente(nomeAtendente, this);
                listaAtendentes.add(atendente);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaAtendentes;
    }

    public String getNome() {
        return nome;
    }

    public void adicionarAtendente(Atendente atendente) {
        atendentes.add(atendente);
        JOptionPane.showMessageDialog(null,"Atendente " + atendente.getNome() + " adicionado com sucesso!");
    }

    public Atendente acessarAtendente(int index) {
        if (index >= 0 && index < atendentes.size()) {
            return atendentes.get(index);
        } else {
            System.out.println("Atendente não encontrado.");
            return null;
        }
    }

    public int getBancoId() {
        return bancoId;
    }
}
