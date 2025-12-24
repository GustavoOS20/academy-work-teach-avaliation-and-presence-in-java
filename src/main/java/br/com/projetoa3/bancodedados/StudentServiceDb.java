package br.com.projetoa3.bancodedados;

import br.com.projetoa3.bancodedados.consurmers.ConsumerAPIJBDC;
import br.com.projetoa3.bancodedados.interfacedb.IDBStudent;
import br.com.projetoa3.modelo.Alunos;
import br.com.projetoa3.modelo.records.Student;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class StudentServiceDb implements IDBStudent {
    @Override
    public void createTable() {
        String sql = "CREATE TABLE IF NOT EXISTS alunos (" +
                "id BIGINT AUTO_INCREMENT PRIMARY KEY," +
                "RA VARCHAR(100)," +
                "nome VARCHAR(100) NOT NULL," +
                "turmaId VARCHAR(100)," +
                "professor_ra VARCHAR(50)" +
                ") ENGINE=InnoDB;";

        try (Connection conn = ConsumerAPIJBDC.conectar()) {
            if (conn != null) {
                try (Statement stmt = conn.createStatement()) {
                    stmt.executeUpdate(sql);
                }
            } else {
                System.err.println("Erro ao conectar ao banco de dados.");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao criar a tabela alunos: " + e.getMessage());
        }
    }
    @Override
    public void insert(Long ra, String nome, String turmaId, String professor_ra) {
        String sql = "INSERT INTO alunos (RA, nome, turmaId, professor_ra) VALUES (?, ?, ?, ?)";
        try (Connection conn = ConsumerAPIJBDC.conectar()){
            assert conn != null;
            PreparedStatement stmt = conn.prepareStatement(sql); {

            stmt.setLong(1, ra);
            stmt.setString(2, nome);
            stmt.setString(3, turmaId);
            stmt.setString(4, professor_ra);

            stmt.executeUpdate();
            System.out.println("Aluno inserido com sucesso!");
}
        } catch (SQLException e) {
            System.err.println("Erro ao inserir aluno: " + e.getMessage());
        }
    }

    @Override
    public Map<String,Student>listTables() {
        Map<String, Student> alunosMap = new HashMap<>(); // Recebe o mapa de alunos
        String sql = "SELECT * FROM alunos";
        try (Connection conn = ConsumerAPIJBDC.conectar()){
            assert conn != null;
            Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql);{
                while (rs.next()) {
                    long ra = Long.parseLong(rs.getString(rs.findColumn("RA")));
                            Student aluno = new Student(
                                    rs.getString("nome"),
                                    ra,
                                    rs.getString("turmaId"),
                                    rs.getString("professor_ra"));

                    String raTurma = rs.getString("RA") + "-" + rs.getString("turmaId");
                    alunosMap.put(raTurma, aluno);
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar alunos: " + e.getMessage());
        }
        return alunosMap;
    }

   /* public void buscarAlunoPorRA(int ra) {
        String sql = "SELECT * FROM alunos WHERE RA = ?";
        try (Connection conn = DriverManager.getConnection(url, usuario, senha);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, ra);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                System.out.println("Aluno encontrado:");
                System.out.println("RA: " + rs.getInt("RA") +
                        ", Nome: " + rs.getString("nome") +
                        ", Presença ID: " + rs.getInt("presenca_id") +
                        ", Turma ID: " + rs.getInt("turma_id") +
                        ", Notas ID: " + rs.getInt("notas_id"));
            } else {
                System.out.println("Aluno com RA " + ra + " não encontrado.");
            }

        } catch (SQLException e) {
            System.err.println("Erro ao buscar aluno por RA: " + e.getMessage());
        }
    }

    public void buscarAlunoPorNome(String nome) {
        String sql = "SELECT * FROM alunos WHERE nome LIKE ?";
        try (Connection conn = DriverManager.getConnection(url, usuario, senha);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, "%" + nome + "%");
            ResultSet rs = stmt.executeQuery();

            System.out.println("Resultados da busca por nome:");
            while (rs.next()) {
                System.out.println("RA: " + rs.getInt("RA") +
                        ", Nome: " + rs.getString("nome") +
                        ", Presença ID: " + rs.getInt("presenca_id") +
                        ", Turma ID: " + rs.getInt("turma_id") +
                        ", Notas ID: " + rs.getInt("notas_id"));
            }

        } catch (SQLException e) {
            System.err.println("Erro ao buscar aluno por nome: " + e.getMessage());
        }
    }

    public void atualizarAluno(int ra, String novoNome, Integer novaPresencaId, Integer novaTurmaId, Integer novaNotasId) {
        String sql = "UPDATE alunos SET nome = ?, presenca_id = ?, turma_id = ?, notas_id = ? WHERE RA = ?";
        try (Connection conn = DriverManager.getConnection(url, usuario, senha);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, novoNome);
            stmt.setObject(2, novaPresencaId, Types.INTEGER);
            stmt.setObject(3, novaTurmaId, Types.INTEGER);
            stmt.setObject(4, novaNotasId, Types.INTEGER);
            stmt.setInt(5, ra);

            int rows = stmt.executeUpdate();
            if (rows > 0) {
                System.out.println("Aluno atualizado com sucesso.");
            } else {
                System.out.println("Aluno com RA " + ra + " não encontrado.");
            }

        } catch (SQLException e) {
            System.err.println("Erro ao atualizar aluno: " + e.getMessage());
        }
    }

    */
    @Override
   public void delete(String ra) {
        String sql = "DELETE FROM alunos WHERE RA = ?";
        try (Connection conn = ConsumerAPIJBDC.conectar()){
            assert conn != null;
            PreparedStatement stmt = conn.prepareStatement(sql);{
                stmt.setString(1, ra);
                int rows = stmt.executeUpdate();

                if (rows > 0) {
                    System.out.println("Aluno excluído com sucesso.");
                } else {
                    System.out.println("Aluno com RA " + ra + " não encontrado.");
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao excluir aluno: " + e.getMessage());
        }
    }
}
