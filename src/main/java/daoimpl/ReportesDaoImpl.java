package daoimpl;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

import dao.ReportesDao;
import datos.Conexion;

public class ReportesDaoImpl implements ReportesDao {
	@Override
	public Map<String, Integer> obtenerCantidadCuentasPorTipo() {
	    Map<String, Integer> resultado = new HashMap<>();
	    String query = "SELECT tc.descripcion, COUNT(*) as cantidad " +
	                   "FROM cuentas c JOIN tipo_cuenta tc ON c.id_tipo_cuenta = tc.id " +
	                   "GROUP BY tc.descripcion";
	    try (Connection conn = Conexion.getConexion();
	         PreparedStatement stmt = conn.prepareStatement(query);
	         ResultSet rs = stmt.executeQuery()) {
	        while (rs.next()) {
	            resultado.put(rs.getString("descripcion"), rs.getInt("cantidad"));
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return resultado;
	}

	@Override
	public Map<String, Integer> obtenerCantidadPrestamosPorEstado(java.sql.Date desde, java.sql.Date hasta) {
	    Map<String, Integer> resultado = new HashMap<>();

	    String query = "SELECT ep.descripcion, COUNT(*) as cantidad " +
	                   "FROM prestamos p " +
	                   "JOIN estado_prestamo ep ON p.id_estado = ep.id " +
	                   "WHERE p.fecha BETWEEN ? AND ? " +
	                   "GROUP BY ep.descripcion";

	    try (Connection conn = Conexion.getConexion();
	         PreparedStatement stmt = conn.prepareStatement(query)) {

	        stmt.setDate(1, desde);
	        stmt.setDate(2, hasta);

	        ResultSet rs = stmt.executeQuery();

	        while (rs.next()) {
	            resultado.put(rs.getString("descripcion"), rs.getInt("cantidad"));
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return resultado;
	}

	
	public Map<String, Integer> obtenerCantidadClientesPorProvincia() {
	    Map<String, Integer> resultado = new HashMap<>();

	    try (Connection conn = Conexion.getConexion()) {
	        String query = "SELECT p.nombre AS provincia, COUNT(*) AS cantidad " +
	                       "FROM clientes c JOIN provincias p ON c.id_provincia = p.id " +
	                       "GROUP BY p.nombre";

	        PreparedStatement stmt = conn.prepareStatement(query);
	        ResultSet rs = stmt.executeQuery();

	        while (rs.next()) {
	            resultado.put(rs.getString("provincia"), rs.getInt("cantidad"));
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return resultado;
	}
	
	
	
	
}