package daoimpl;

import dao.ProvinciaDao;
import datos.Conexion;
import entidades.Provincia;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProvinciaDaoImpl implements ProvinciaDao {

	@Override
	public List<Provincia> obtenerTodos() {
		List<Provincia> listaProvincias = new ArrayList<>();
		String consultaSQL = "SELECT * FROM provincias ORDER BY nombre";
		Connection conexion = null;
		PreparedStatement statement = null;
		ResultSet resultado = null;

		try {
			conexion = Conexion.getConexion();
			statement = conexion.prepareStatement(consultaSQL);
			resultado = statement.executeQuery();

			while (resultado.next()) {
				Provincia provincia = new Provincia();
				provincia.setId(resultado.getInt("id"));
				provincia.setNombre(resultado.getString("nombre"));
				listaProvincias.add(provincia);
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

		return listaProvincias;
	}

	@Override
	public Provincia obtenerPorId(int idProvincia) {
		Provincia provincia = null;
		String consultaSQL = "SELECT * FROM provincias WHERE id = ?";
		Connection conexion = null;
		PreparedStatement statement = null;
		ResultSet resultado = null;

		try {
			conexion = Conexion.getConexion();
			statement = conexion.prepareStatement(consultaSQL);
			statement.setInt(1, idProvincia);
			resultado = statement.executeQuery();

			if (resultado.next()) {
				provincia = new Provincia();
				provincia.setId(resultado.getInt("id"));
				provincia.setNombre(resultado.getString("nombre"));
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

		return provincia;
	}
}