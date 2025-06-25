package datos;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexion {
    private static final String host = "localhost";
    //private static final String port = "3306";
    private static final String port = "3307"; //Configuracion para que ande la bbdd de MARIANO
    private static final String db = "bancogrupo12";
    private static final String usuario = "root";
    private static final String clave = "root";

    //private static Connection conexion = null;

    private Conexion() {}

    public static Connection getConexion() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://" + host + ":" + port + "/" + db + "?useSSL=false&serverTimezone=UTC";
            System.out.println("[Conexion]  Conexión a la base de datos establecida correctamente.");
            return DriverManager.getConnection(url, usuario, clave);
        } catch (Exception e) {
            System.out.println("[Conexion]  Error al conectar con la base de datos: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
}
