package br.com.projetoa3.src.bancodedados;
import br.com.projetoa3.src.bancodedados.consurmers.ConsumerAPIJBDC;
import br.com.projetoa3.src.bancodedados.interfacedb.IClassSchoolDb;
import br.com.projetoa3.src.modelo.records.ClassSchool;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class ClassSchServiceDbDb implements IClassSchoolDb {
    @Override
    public void createTable() {
        String sql = """
            CREATE TABLE IF NOT EXISTS turmas (
                id VARCHAR(100) PRIMARY KEY,
                nomeDaTurma VARCHAR(100) NOT NULL,
                professores_ra VARCHAR(50),
                FOREIGN KEY (professores_ra) REFERENCES professores(ra)
            );
        """;

        try (Connection conn = ConsumerAPIJBDC.conectar()) {
            if (conn != null) {
                try (Statement stmt = conn.createStatement()) {
                    stmt.execute(sql);
                    System.out.println("Tabela 'turmas' criada com sucesso.");
                }
            }

        } catch (SQLException e) {
            System.err.println("Erro ao criar a tabela: " + e.getMessage());
        }
    }

    @Override
    public void insertClass(String id, String nomeDaTurma, String professores_ra) {
        String sql = "INSERT INTO turmas (id, nomeDaTurma, professores_ra) VALUES (?, ?, ?)";

        try (Connection conn = ConsumerAPIJBDC.conectar()) {
            assert conn != null;
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {

                pstmt.setString(1, id);
                pstmt.setString(2, nomeDaTurma);
                pstmt.setString(3, professores_ra);
                pstmt.executeUpdate();
                System.out.println("Turma inserida com sucesso.");

            }
        } catch (SQLException e) {
            System.err.println("Erro ao inserir turma: " + e.getMessage());
        }
    }

    @Override
    public Map<String, ClassSchool> listClass() {
        Map<String, ClassSchool> turmas = new HashMap<>();
        String sql = "SELECT * FROM turmas";

        try (Connection conn = ConsumerAPIJBDC.conectar()) {
            assert conn != null;
            try (Statement stmt = conn.createStatement()) {


                ResultSet rs = stmt.executeQuery(sql);
                {

                    while (rs.next()) {
                        String id = rs.getString("id");
                        String nome = rs.getString("nomeDaTurma");
                        String professoresRa = rs.getString("professores_ra");
                        ClassSchool turma = new ClassSchool(nome, professoresRa);
                        turmas.put(id, turma);
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar turmas: " + e.getMessage());
        }
        return turmas;
    }
    @Override
    public void delete(String id) {
        String sql = "DELETE FROM turmas WHERE id = ?";

        try (Connection conn = ConsumerAPIJBDC.conectar()){
            assert conn != null;
            try(PreparedStatement pstmt = conn.prepareStatement(sql)) {

                 pstmt.setString(1, id);
                 int linhasAfetadas = pstmt.executeUpdate();

                 if (linhasAfetadas > 0) {
                     System.out.println("Turma excluída com sucesso.");
                 } else {
                     System.out.println("Nenhuma turma encontrada com ID " + id);
                 }
             }
        } catch (SQLException e) {
            System.err.println("Erro ao excluir turma: " + e.getMessage());
        }
    }
}
