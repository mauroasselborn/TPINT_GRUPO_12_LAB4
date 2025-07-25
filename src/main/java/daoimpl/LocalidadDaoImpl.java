package daoimpl;

import dao.LocalidadDao;
import datos.Conexion;
import entidades.Localidad;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LocalidadDaoImpl implements LocalidadDao {

	@Override
	public List<Localidad> obtenerTodos() {
		List<Localidad> listaLocalidades = new ArrayList<>();
		String consultaSQL = "SELECT * FROM localidades ORDER BY nombre";
		Connection conexion = null;
		PreparedStatement statement = null;
		ResultSet resultado = null;

		try {
			conexion = Conexion.getConexion();
			statement = conexion.prepareStatement(consultaSQL);
			resultado = statement.executeQuery();

			while (resultado.next()) {
				Localidad localidad = new Localidad();
				localidad.setId(resultado.getInt("id"));
				localidad.setNombre(resultado.getString("nombre"));
				localidad.setProvincia(resultado.getInt("id_provincia"));
				listaLocalidades.add(localidad);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (resultado != null)
					resultado.close();
			} catch (Exception e) {
			}
			try {
				if (statement != null)
					statement.close();
			} catch (Exception e) {
			}
			try {
				if (conexion != null)
					conexion.close();
			} catch (Exception e) {
			}
		}

		return listaLocalidades;
	}

	public List<Localidad> obtenerTodos(int idProvincia) {
	    List<Localidad> listaLocalidades = new ArrayList<>();
	    String consultaSQL = "SELECT * FROM localidades WHERE id_provincia = ? ORDER BY nombre";
	    Connection conexion = null;
	    PreparedStatement statement = null;
	    ResultSet resultado = null;

	    try {
	        conexion = Conexion.getConexion();
	        statement = conexion.prepareStatement(consultaSQL);
	        statement.setInt(1, idProvincia);
	        resultado = statement.executeQuery();

	        while (resultado.next()) {
	            Localidad localidad = new Localidad();
	            localidad.setId(resultado.getInt("id"));
	            localidad.setNombre(resultado.getString("nombre"));
	            localidad.setProvincia(resultado.getInt("id_provincia"));
	            listaLocalidades.add(localidad);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (resultado != null) resultado.close();
	        } catch (Exception e) {}
	        try {
	            if (statement != null) statement.close();
	        } catch (Exception e) {}
	        try {
	            if (conexion != null) conexion.close();
	        } catch (Exception e) {}
	    }

	    return listaLocalidades;
	}

	
	@Override
	public Localidad obtenerPorId(int idLocalidad) {
		Localidad localidad = null;
		String consultaSQL = "SELECT * FROM localidades WHERE id = ?";
		Connection conexion = null;
		PreparedStatement statement = null;
		ResultSet resultado = null;

		try {
			conexion = Conexion.getConexion();
			statement = conexion.prepareStatement(consultaSQL);
			statement.setInt(1, idLocalidad);
			resultado = statement.executeQuery();

			if (resultado.next()) {
				localidad = new Localidad();
				localidad.setId(resultado.getInt("id"));
				localidad.setNombre(resultado.getString("nombre"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (resultado != null)
					resultado.close();
			} catch (Exception e) {
			}
			try {
				if (statement != null)
					statement.close();
			} catch (Exception e) {
			}
			try {
				if (conexion != null)
					conexion.close();
			} catch (Exception e) {
			}
		}

		return localidad;
	}
}
