package daoImpl;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import dao.ReportesDao;
import dao.UsuarioDao;
import entidades.Usuario;
import entidades.Cliente;
import entidades.Localidad;
import entidades.Nacionalidad;
import entidades.Provincia;
import entidades.TipoUsuario;
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
	public Map<String, Integer> obtenerCantidadPrestamosPorEstado() {
	    Map<String, Integer> resultado = new HashMap<>();
	    String query = "SELECT ep.descripcion, COUNT(*) as cantidad " +
	                   "FROM prestamos p " +
	                   "JOIN estado_prestamo ep ON p.id_estado = ep.id " +
	                   "GROUP BY ep.descripcion";

	    try (Connection conn = Conexion.getConexion();
	         PreparedStatement stmt = conn.prepareStatement(query);
	         ResultSet rs = stmt.executeQuery()) {

	        while (rs.next()) {
	            resultado.put(rs.getString("descripcion"), rs.getInt("cantidad"));
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    System.out.println("Préstamos por estado: " + resultado); // 
	    return resultado;
	}
	
	
}