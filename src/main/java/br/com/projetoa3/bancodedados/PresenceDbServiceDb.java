package br.com.projetoa3.bancodedados;

import br.com.projetoa3.bancodedados.consurmers.ConsumerAPIJBDC;
import br.com.projetoa3.bancodedados.interfacedb.IPresenceDb;
import br.com.projetoa3.modelo.records.PresenceList;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

import java.sql.*;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class PresenceDbServiceDb implements IPresenceDb {

    @Override
    public void createTable() {
        try (Connection conexao = ConsumerAPIJBDC.conectar()){

            assert conexao != null;
            try(Statement stmt = conexao.createStatement()) {

            String sqlPresenca = """
                CREATE TABLE IF NOT EXISTS presenca (
                    idP BIGINT AUTO_INCREMENT PRIMARY KEY,
                    id INT,
                    data DATE NOT NULL,
                    presente BOOLEAN NOT NULL,
                    turma VARCHAR(255) NOT NULL,
                    professor VARCHAR(255) NOT NULL
                );
                """;


            stmt.executeUpdate(sqlPresenca);

            System.out.println("Tabelas 'presenca' e 'presencaaluno' criadas com sucesso!");
    }
        } catch (SQLException e) {
            System.err.println("Erro ao criar tabelas: " + e.getMessage());
        }
    }

    @Override
    public void insertPresence(Long id, LocalDate data, boolean presente, String turma, String professor) {
        String sql = "INSERT INTO presenca (id, data, presente, turma, professor) VALUES (?, ?, ?, ?, ?)";

        try (Connection conexao = ConsumerAPIJBDC.conectar()){
                assert conexao != null;
                try(PreparedStatement pstmt = conexao.prepareStatement(sql)) {

                pstmt.setLong(1, id);
                pstmt.setDate(2, Date.valueOf(data));
                pstmt.setBoolean(3, presente);
                pstmt.setString(4, turma);
                pstmt.setString(5, professor);

                pstmt.executeUpdate();
                System.out.println("Presença inserida com sucesso!");

            }
            } catch (SQLException e) {
            System.err.println("Erro ao inserir presença: " + e.getMessage());
        }
    }
    @Override
    public Map<LocalDate, Map<Long, PresenceList>> listPresence() {
        Map<LocalDate, Map<Long, PresenceList>> presencas = new HashMap<>();
        String sql = "SELECT * FROM presenca";

        try (Connection conexao = ConsumerAPIJBDC.conectar()){
                assert conexao != null;
                try(Statement stmt = conexao.createStatement()){
                 ResultSet rs = stmt.executeQuery(sql);{

                        while (rs.next()) {
                            LocalDate data = rs.getDate("data").toLocalDate();
                            Long id = rs.getLong("id");
                            String turma = rs.getString("turma");
                            String professor = rs.getString("professor");
                            rs.getBoolean("presente");
                            PresenceList presenceList = new PresenceList(new SimpleBooleanProperty(rs.getBoolean("presente")), turma, professor);
                            presencas.computeIfAbsent(data, k -> new HashMap<>()).put(id, presenceList);
                        }
                    }
            }
            } catch (SQLException e) {
            System.err.println("Erro ao listar presenças: " + e.getMessage());
        }
        return presencas;
    }
        @Override
        public void delete(String id) {
        String sql = "DELETE FROM presenca WHERE id = ?";

        try (Connection conexao = ConsumerAPIJBDC.conectar()){
            assert conexao != null;
            try(PreparedStatement pstmt = conexao.prepareStatement(sql)) {
                pstmt.setString(1, id);
                pstmt.executeUpdate();
            }
        }catch (SQLException e) {
            System.err.println("Erro ao excluir presenças: " + e.getMessage());
        }
    }

    @Override
    public void updatePresence(Long id, LocalDate data, boolean presente, String professor) {
        String sql = "UPDATE presenca SET presente = ? WHERE id = ? AND data = ? AND professor = ?";

        try (Connection conexao = ConsumerAPIJBDC.conectar()){
            assert conexao != null;
            try(PreparedStatement pstmt = conexao.prepareStatement(sql)) {

                 pstmt.setBoolean(1, presente);
                 pstmt.setLong(2, id);
                 pstmt.setDate(3, Date.valueOf(data));
                 pstmt.setString(4, professor);

                 int linhasAfetadas = pstmt.executeUpdate();
                 if (linhasAfetadas > 0) {
                     System.out.println("Presença atualizada com sucesso!");
                 } else {
                     System.out.println("Nenhuma presença encontrada para o ID e data informados.");
                 }
             }
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar presença: " + e.getMessage());
        }
    }
}



