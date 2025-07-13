package daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import datos.Conexion;
import entidades.TipoCuenta;
import dao.TipoCuentaDao;

public class TipoCuentaDaoImpl implements TipoCuentaDao {
	@Override
	public List<TipoCuenta> obtenerTodos() {
		List<TipoCuenta> listaTipoCuenta = new ArrayList<>();
		String consultaSQL = "SELECT * FROM tipo_cuenta  ORDER BY nombre";
		Connection conexion = null;
		PreparedStatement statement = null;
		ResultSet resultado = null;

		try {
			conexion = Conexion.getConexion();
			statement = conexion.prepareStatement(consultaSQL);
			resultado = statement.executeQuery();

			while (resultado.next()) {
				TipoCuenta tipocuenta = new TipoCuenta();
				tipocuenta.setId(resultado.getInt("id"));
				tipocuenta.setDescripcion(resultado.getString("descripcion"));
				listaTipoCuenta.add(tipocuenta);
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

		return listaTipoCuenta;
	}

	@Override
	public TipoCuenta obtenerPorId(int idtipocuenta) {
		TipoCuenta tipocuenta = null;
		String consultaSQL = "SELECT * FROM tipo_cuenta WHERE id = ?";
		Connection conexion = null;
		PreparedStatement statement = null;
		ResultSet resultado = null;

		try {
			conexion = Conexion.getConexion();
			statement = conexion.prepareStatement(consultaSQL);
			statement.setInt(1, idtipocuenta);
			resultado = statement.executeQuery();

			if (resultado.next()) {
				tipocuenta = new TipoCuenta();
				tipocuenta.setId(resultado.getInt("id"));
				tipocuenta.setDescripcion(resultado.getString("descripcion"));
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

		return tipocuenta;
	}
}
