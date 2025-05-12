import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    public static Connection conectar() {
        try {
            String url = "jdbc:sqlite:fruteria.db";
            return DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println("Error al conectar con la base de datos");
            e.printStackTrace();
            return null;
        }
    }
}

