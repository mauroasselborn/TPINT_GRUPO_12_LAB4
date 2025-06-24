package datos;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexion {
    private static final String host = "localhost";
    private static final String port = "3306";
    private static final String db = "bancogrupo12";
    private static final String usuario = "root";
    private static final String clave = "root";

    private static Connection conexion = null;

    private Conexion() {}

    public static Connection getConexion() {
        if (conexion == null) {
            try {
            	Class.forName("com.mysql.jdbc.Driver");

                String url = "jdbc:mysql://" + host + ":" + port + "/" + db + "?useSSL=false&serverTimezone=UTC";
                conexion = DriverManager.getConnection(url, usuario, clave);

                System.out.println("[Conexion]  Conexión a la base de datos establecida correctamente.");
            } catch (Exception e) {
                System.out.println("[Conexion]  Error al conectar con la base de datos: " + e.getMessage());
                e.printStackTrace();
            }
        }
        return conexion;
    }
}
