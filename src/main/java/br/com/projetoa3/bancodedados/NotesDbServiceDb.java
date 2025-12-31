package br.com.projetoa3.bancodedados;

import br.com.projetoa3.bancodedados.consurmers.ConsumerAPIJBDC;
import br.com.projetoa3.bancodedados.interfacedb.INotesDb;
import br.com.projetoa3.modelo.records.Notes;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class NotesDbServiceDb implements INotesDb {

    @Override
    public void createTable() {
        String sql = """
            CREATE TABLE IF NOT EXISTS notas (
                id int AUTO_INCREMENT PRIMARY KEY,
                ra int,
                A1 int,
                A2 int,
                A3 int,
                soma VARCHAR(100),
                status VARCHAR(100)
                );
        """;

        try (Connection conn = ConsumerAPIJBDC.conectar()){
            if (conn != null) {
                try(Statement stmt = conn.createStatement()){
                    stmt.execute(sql);
                    System.out.println("Tabela 'notas' criada com sucesso.");
                }
            }
            else{
                System.err.println("Erro ao conectar ao banco de dados.");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao criar tabela: " + e.getMessage());
        }
    }

    @Override
    public void insert(String ra, int A1, int A2, int A3, int soma, String status) {
        String inserirNota = "INSERT INTO notas (ra, A1, A2, A3, soma,status) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = ConsumerAPIJBDC.conectar()){
            assert conn != null;
             try (PreparedStatement psInsere = conn.prepareStatement(inserirNota)) {

                 psInsere.setString(1, ra);
                 psInsere.setInt(2, A1);
                 psInsere.setInt(3, A2);
                 psInsere.setInt(4, A3);
                 psInsere.setInt(5, soma);
                 psInsere.setString(6, status);
                 psInsere.executeUpdate();

                 System.out.println("Notas inseridas com sucesso.");
             }
        } catch (SQLException e) {
            System.err.println("Erro ao inserir notas: " + e.getMessage());
        }
    }

    @Override
    public Map<String, Notes> listNotes() {
        Map<String, Notes> notasMap = new HashMap<>();
        String sql = "SELECT * FROM notas";

        try (Connection conn = ConsumerAPIJBDC.conectar()){
            assert conn != null;
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                ResultSet rs = stmt.executeQuery();

                while (rs.next()) {
                    Notes notes = new Notes(
                            rs.getInt("A1"),
                            rs.getInt("A2"),
                            rs.getInt("A3")
                    );
                    notasMap.put(rs.getString("ra"), notes);
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar notas por RA: " + e.getMessage());
        }
        return notasMap;
    }
/*
public void buscarNotasPorId(int idNota) {
    String sql = """
            SELECT n.id, a.nome, a.ra, n.A1, n.A2, n.A3, n.soma
            FROM notas n
            JOIN alunos a ON n.aluno_id = a.id
            WHERE n.id = ?
        """;

    try (Connection conn = DriverManager.getConnection(URL, USUARIO, SENHA);
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setInt(1, idNota);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            System.out.printf("ID Nota: %d | Aluno: %s | RA: %s | A1: %.2f | A2: %.2f | A3: %.2f | Soma: %.2f%n",
                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getString("ra"),
                    rs.getDouble("A1"),
                    rs.getDouble("A2"),
                    rs.getDouble("A3"),
                    rs.getDouble("soma")
            );
        } else {
            System.out.println("Nenhuma nota encontrada com ID: " + idNota);
        }

    } catch (SQLException e) {
        System.err.println("Erro ao buscar nota por ID: " + e.getMessage());
    }
}
*/
    @Override
    public void updateNotes(String idNota, int novaA1, int novaA2, int novaA3, int soma, String status)  {
    String sql = "UPDATE notas SET A1 = ?, A2 = ?, A3 = ?, soma = ?, status = ? WHERE id = ?";

    try (Connection conn = ConsumerAPIJBDC.conectar()){
        assert conn != null;
        try(PreparedStatement stmt = conn.prepareStatement(sql)) {

             stmt.setDouble(1, novaA1);
             stmt.setDouble(2, novaA2);
             stmt.setDouble(3, novaA3);
             stmt.setInt(4, soma);
             stmt.setString(5, status);
             stmt.setString(4, idNota);

             int afetados = stmt.executeUpdate();
             if (afetados > 0) {
                 System.out.println("Notas atualizadas com sucesso.");
             } else {
                 System.out.println("Nota com ID " + idNota + " não encontrada.");
             }
         }
    } catch (SQLException e) {
        System.err.println("Erro ao conectar atualizar notas: " + e.getMessage());
    }
}

@Override
public void delete(String ra) {
    String sql = "DELETE FROM notas WHERE ra = ?";

    try (Connection conn = ConsumerAPIJBDC.conectar()){
             assert conn != null;
             try (PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, ra);
            int afetados = stmt.executeUpdate();

            if (afetados > 0) {
                System.out.println("Notas excluídas com sucesso.");
            } else {
                System.out.println("Nota com ID " + ra + " não encontrada.");
            }

        }
         } catch (SQLException e) {
        System.err.println("Erro ao excluir notas: " + e.getMessage());
    }
}
}

