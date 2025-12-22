package br.com.projetoa3.bancodedados;

import br.com.projetoa3.bancodedados.interfacedb.ITeacher;
import br.com.projetoa3.modelo.records.Teach;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

import static br.com.projetoa3.bancodedados.consurmers.ConsumerAPIJBDC.conectar;

public class TeacherServiceDb implements ITeacher {

    @Override
    public void createTable() {
        String sql = "CREATE TABLE IF NOT EXISTS professores (" +
                "ra VARCHAR(50) PRIMARY KEY," +
                "nome VARCHAR(255) NOT NULL," +
                "email VARCHAR(255) UNIQUE NOT NULL," +
                "senha VARCHAR(255) NOT NULL" +
                ");";

        try (Connection conexao = conectar()) {
            if (conexao != null) {
                try (Statement stmt = conexao.createStatement()) {
                    stmt.execute(sql);
                    System.out.println("Tabela 'professores' criada/verificada com sucesso!");
                }
            } else {
                System.err.println("Conexão falhou. Verifique as credenciais ou se o MySQL está rodando.");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao criar a tabela: " + e.getMessage());
        }
    }

    @Override
    public void insert(String ra, String nome, String email, String senha) {
        String sql = "INSERT INTO professores (ra, nome, email, senha) VALUES (?, ?, ?, ?)";

        try (Connection conexao = conectar()) {
            assert conexao != null;
            try (PreparedStatement pstmt = conexao.prepareStatement(sql)) {

                pstmt.setString(1, ra);
                pstmt.setString(2, nome);
                pstmt.setString(3, email);
                pstmt.setString(4, senha);

                pstmt.executeUpdate();
                System.out.println("Professor '" + nome + "' inserido com sucesso!");

            }
        } catch (SQLException e) {
            System.err.println("Erro ao inserir professor: " + e.getMessage());
        }
    }

    @Override
    public Map<String, Teach> listTeachers() {
        Map<String, Teach> professoresMap = new HashMap<>();
        String sql = "SELECT * FROM professores";

        try (Connection conexao = conectar()) {
            assert conexao != null;
            try (Statement stmt = conexao.createStatement();
                 ResultSet rs = stmt.executeQuery(sql)) {

                while (rs.next()) {

                    Teach professor = new Teach(
                            rs.getString("nome"),
                            rs.getString("ra"),
                            rs.getString("email"),
                            rs.getString("senha"));
                    professoresMap.put(rs.getString("ra"), professor);
                }

            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar professores: " + e.getMessage());
        }
        return professoresMap;
    }
/*
    public void buscarProfessorPorRa(String ra) {
        String sql = "SELECT * FROM professores WHERE ra = ?";

        try (Connection conexao = conectar()) {
            assert conexao != null;
            try (PreparedStatement pstmt = conexao.prepareStatement(sql)) {

                pstmt.setString(1, ra);
                try (ResultSet rs = pstmt.executeQuery()) {
                    if (rs.next()) {
                        System.out.println("Professor encontrado:");
                        System.out.println("RA: " + rs.getString("ra") +
                                ", Nome: " + rs.getString("nome") +
                                ", Email: " + rs.getString("email"));
                    } else {
                        System.out.println("Professor não encontrado com RA:" + ra);
                    }
                }

            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar professor: " + e.getMessage());
        }
    }

    public void atualizarProfessor(String ra, String nome, String email, String senha) {
        String sql = "UPDATE professores SET nome = ?, email = ?, senha = ? WHERE ra = ?";

        try (Connection conexao = ConsumerAPIJBDC.conectar()) {
            assert conexao != null;
            try (PreparedStatement pstmt = conexao.prepareStatement(sql)) {

                pstmt.setString(1, nome);
                pstmt.setString(2, email);
                pstmt.setString(3, senha);
                pstmt.setString(4, ra);

                int linhas = pstmt.executeUpdate();

                if (linhas > 0) {
                    System.out.println("Professor atualizado com sucesso!");
                } else {
                    System.out.println("Nenhum professor encontrado com RA: " + ra);
                }

            }
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar professor: " + e.getMessage());
        }
    }
*/
    @Override
    public void delete(String ra) {
        String sql = "DELETE FROM professores WHERE ra = ?";

        try (Connection conexao = conectar()) {
            assert conexao != null;
            try (PreparedStatement pstmt = conexao.prepareStatement(sql)) {

                pstmt.setString(1, ra);
                int linhas = pstmt.executeUpdate();

                if (linhas > 0) {
                    System.out.println("Professor excluído com sucesso!");
                } else {
                    System.out.println("Nenhum professor encontrado com RA: " + ra);
                }

            }
        } catch (SQLException e) {
            System.err.println("Erro ao excluir professor: " + e.getMessage());
        }
    }
}
