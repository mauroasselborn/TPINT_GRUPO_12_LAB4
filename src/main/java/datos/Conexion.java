package datos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
	private static final String host = "localhost";
	private static final String port = "3306";
	private static final String db = "BancoGrupo12";
	private static final String usuario = "root";
	private static final String clave = "root";

	private static Connection conexion = null;

	private Conexion() {}

	public static Conexion getConexion() {
		if (conexion == null) {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				conexion = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + db + "?useSSL=false&serverTimezone=UTC", usuario, clave);
				conexion.setAutoCommit(false);
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
		}
		return new Conexion();
	}

	public Connection getSQLConexion() {
		return conexion;
	}
}
