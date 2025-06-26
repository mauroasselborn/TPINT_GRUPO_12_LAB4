package daoImpl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import dao.UsuarioDao;
import entidades.Usuario;
import entidades.TipoUsuario;
import datos.Conexion;

public class UsuarioDaoImpl implements UsuarioDao {

	@Override
	public boolean eliminarUsuario(int idUsuario) {
		boolean estado = false;
		Connection conn = null;
		PreparedStatement ps = null;

		try {
			conn = Conexion.getConexion();
			ps = conn.prepareStatement("UPDATE usuarios SET activo = 0 WHERE id = ?");
			ps.setInt(1, idUsuario);
			estado = ps.executeUpdate() > 0;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (ps != null)
					ps.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return estado;
	}

	@Override
	public boolean activarUsuario(int idUsuario) {
		boolean estado = false;
		Connection conn = null;
		PreparedStatement ps = null;

		try {
			conn = Conexion.getConexion();
			ps = conn.prepareStatement("UPDATE usuarios SET activo = 1 WHERE id = ?");
			ps.setInt(1, idUsuario);
			estado = ps.executeUpdate() > 0;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (ps != null)
					ps.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return estado;
	}

	@Override
	public Usuario obtenerUsuarioPorId(int idUsuario) {
		Usuario u = null;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			conn = Conexion.getConexion();
			ps = conn.prepareStatement("SELECT * FROM usuarios WHERE id = ?");
			ps.setInt(1, idUsuario);
			rs = ps.executeQuery();

			if (rs.next()) {
				u = new Usuario();
				u.setId(rs.getInt("id"));
				u.setNombreUsuario(rs.getString("nombre_usuario"));
				u.setContrasena(rs.getString("contrasena"));
				u.setActivo(rs.getBoolean("activo"));

				TipoUsuario tipo = new TipoUsuario();
				tipo.setId(rs.getInt("id_tipo_usuario"));
				u.setTipoUsuario(tipo);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (ps != null)
					ps.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return u;
	}

	@Override
	public List<Usuario> obtenerTodosLosUsuarios() {
		List<Usuario> lista = new ArrayList<>();
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;

		try {
			conn = Conexion.getConexion();
			st = conn.createStatement();
			rs = st.executeQuery("SELECT * FROM usuarios");

			while (rs.next()) {
				Usuario u = new Usuario();
				u.setId(rs.getInt("id"));
				u.setNombreUsuario(rs.getString("nombre_usuario"));
				u.setContrasena(rs.getString("contrasena"));
				u.setActivo(rs.getBoolean("activo"));

				TipoUsuario tipo = new TipoUsuario();
				tipo.setId(rs.getInt("id_tipo_usuario"));
				u.setTipoUsuario(tipo);

				lista.add(u);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (st != null)
					st.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return lista;
	}

	@Override
	public boolean validarCredenciales(String nombreUsuario, String contrasenia) {
		boolean valido = false;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			conn = Conexion.getConexion();
			ps = conn.prepareStatement("SELECT * FROM usuarios WHERE nombre_usuario = ? AND contrasena = ? AND activo = 1");
			ps.setString(1, nombreUsuario);
			ps.setString(2, contrasenia);
			rs = ps.executeQuery();

			if (rs.next()) {
				valido = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (ps != null)
					ps.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return valido;
	}

	@Override
	public Usuario obtenerUsuarioPorIdCliente(int idCliente) {
		Usuario u = null;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			conn = Conexion.getConexion();
			ps = conn.prepareStatement("SELECT * FROM usuarios WHERE id_cliente = ?");
			ps.setInt(1, idCliente);
			rs = ps.executeQuery();

			if (rs.next()) {
				u = new Usuario();
				u.setId(rs.getInt("id"));
				u.setNombreUsuario(rs.getString("nombre_usuario"));
				u.setContrasena(rs.getString("contrasena"));
				u.setActivo(rs.getBoolean("activo"));

				TipoUsuario tipo = new TipoUsuario();
				tipo.setId(rs.getInt("id_tipo_usuario"));
				u.setTipoUsuario(tipo);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (ps != null)
					ps.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return u;
	}

	@Override
	public Usuario obtenerUsuarioPorNombre(String nombreUsuario) {
		Usuario u = null;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			conn = Conexion.getConexion();
			ps = conn.prepareStatement("SELECT * FROM usuarios WHERE nombre_usuario = ?");
			ps.setString(1, nombreUsuario);
			rs = ps.executeQuery();

			if (rs.next()) {
				u = new Usuario();
				u.setId(rs.getInt("id"));
				u.setNombreUsuario(rs.getString("nombre_usuario"));
				u.setContrasena(rs.getString("contrasena"));
				u.setActivo(rs.getBoolean("activo"));

				TipoUsuario tipo = new TipoUsuario();
				tipo.setId(rs.getInt("id_tipo_usuario"));
				u.setTipoUsuario(tipo);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (ps != null)
					ps.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return u;
	}

	@Override
	public boolean insertarUsuario(Usuario usuario) {
		boolean resultado = false;
		Connection conn = null;
		PreparedStatement ps = null;

		try {
			conn = Conexion.getConexion();
			ps = conn.prepareStatement("INSERT INTO usuarios (id_cliente, id_tipo_usuario, nombre_usuario, contrasena, activo) VALUES (?, ?, ?, ?, 1)");

			ps.setInt(1, usuario.getIdCliente());
			ps.setInt(2, usuario.getTipoUsuario().getId());
			ps.setString(3, usuario.getNombreUsuario());
			ps.setString(4, usuario.getContrasena());

			resultado = ps.executeUpdate() > 0;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (ps != null)
					ps.close();
			} catch (Exception e) {
			}
			try {
				if (conn != null)
					conn.close();
			} catch (Exception e) {
			}
		}

		return resultado;
	}

}
