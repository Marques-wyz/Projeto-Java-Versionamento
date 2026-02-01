package DAO;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexao {

    public static Connection conectar() {
        try {
            return DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/seubanco",
                "usuario",
                "senha"
            );
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
