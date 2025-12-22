package br.com.projetoa3.bancodedados.consurmers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConsumerAPIJBDC {
    private static final String USUARIO = "root"; // substitua
    private static final String SENHA = "gu7672017";
    private static final String URL = "jdbc:mysql://localhost:3306/projetoa3?serverTimezone=America/Bahia";

    public static Connection conectar() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(URL, USUARIO,SENHA);
        } catch (ClassNotFoundException e) {
            System.err.println("Driver JDBC não encontrado.");
        } catch (SQLException e) {
            System.err.println("Erro na conexão: " + e.getMessage());
        }
        return null;
    }
}
