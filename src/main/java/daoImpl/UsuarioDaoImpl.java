package daoImpl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import dao.UsuarioDao;
import entidades.Usuario;
import entidades.Cliente;
import entidades.Localidad;
import entidades.Nacionalidad;
import entidades.Provincia;
import entidades.TipoUsuario;
import datos.Conexion;

public class UsuarioDaoImpl implements UsuarioDao {

	private final String CONSULTA =

			"SELECT " + "  u.id AS id_usuario, u.nombre_usuario, u.contrasena, u.activo, u.id_tipo_usuario, u.id_cliente," + "  tu.descripcion AS desc_tipo_usuario, " + "  c.dni, " + "  c.cuil, " + "  c.nombre AS nombre_cliente, " + "  c.apellido, "
					+ "  c.sexo, " + "  c.id_nacionalidad, n.descripcion AS desc_nacionalidad, " + "  c.fecha_nacimiento, " + "  c.direccion, " + "  c.id_localidad, l.nombre AS nombre_localidad, " + "  c.id_provincia, p.nombre AS nombre_provincia, "
					+ "  c.correo_electronico, " + "  c.telefono " + "FROM usuarios u " + "JOIN tipo_usuario tu ON u.id_tipo_usuario = tu.id " + "JOIN clientes c ON u.id_cliente = c.id " + "JOIN nacionalidades n ON c.id_nacionalidad = n.id "
					+ "JOIN localidades l ON c.id_localidad = l.id " + "JOIN provincias p ON c.id_provincia = p.id ";

	private Usuario llenarUsuario(ResultSet resultado) throws SQLException {

		Usuario usuario = new Usuario();

		usuario.setId(resultado.getInt("id_usuario"));
		usuario.setNombreUsuario(resultado.getString("nombre_usuario"));
		usuario.setContrasena(resultado.getString("contrasena"));
		usuario.setActivo(resultado.getBoolean("activo"));

		// TipoUsuario
		TipoUsuario tipoUsuario = new TipoUsuario();
		tipoUsuario.setId(resultado.getInt("id_tipo_usuario"));
		tipoUsuario.setDescripcion(resultado.getString("desc_tipo_usuario"));
		usuario.setTipoUsuario(tipoUsuario);

		// Nacionalidad
		Nacionalidad nacionalidad = new Nacionalidad();
		nacionalidad.setId(resultado.getInt("id_nacionalidad"));
		nacionalidad.setDescripcion(resultado.getString("desc_nacionalidad"));

		// Localidad
		Localidad localidad = new Localidad();
		localidad.setId(resultado.getInt("id_localidad"));
		localidad.setNombre(resultado.getString("nombre_localidad"));

		// Provincia
		Provincia provincia = new Provincia();
		provincia.setId(resultado.getInt("id_provincia"));
		provincia.setNombre(resultado.getString("nombre_provincia"));

		// Cliente
		Cliente cliente = new Cliente();
		cliente.setId(resultado.getInt("id_cliente"));
		cliente.setDni(resultado.getString("dni"));
		cliente.setCuil(resultado.getString("cuil"));
		cliente.setNombre(resultado.getString("nombre_cliente"));
		cliente.setApellido(resultado.getString("apellido"));
		cliente.setSexo(resultado.getString("sexo"));
		cliente.setFechaNacimiento(resultado.getString("fecha_nacimiento"));
		cliente.setDireccion(resultado.getString("direccion"));
		cliente.setCorreoElectronico(resultado.getString("correo_electronico"));
		cliente.setTelefono(resultado.getString("telefono"));
		cliente.setNacionalidad(nacionalidad);
		cliente.setLocalidad(localidad);
		cliente.setProvincia(provincia);

		usuario.setCliente(cliente);

		return usuario;
	}

	// Eliminar usuario de forma logica
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

	// Activar usuario
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

	// Obtengo usuario por ID
	@Override
	public Usuario obtenerUsuarioPorId(int idUsuario) {
		Usuario u = null;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			conn = Conexion.getConexion();
			String sql = CONSULTA + "WHERE u.id = ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, idUsuario);
			rs = ps.executeQuery();

			if (rs.next()) {
				u = llenarUsuario(rs);
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

	// Obtengo todos los usuarios
	@Override
	public List<Usuario> obtenerTodosLosUsuarios() {
		List<Usuario> lista = new ArrayList<>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			conn = Conexion.getConexion();
			ps = conn.prepareStatement(CONSULTA + "WHERE u.id_tipo_usuario = 1");
			rs = ps.executeQuery();

			while (rs.next()) {
				Usuario u = llenarUsuario(rs);
				lista.add(u);
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

		return lista;
	}

	// Obtengo usuario por ID de cliente
	@Override
	public Usuario obtenerUsuarioPorIdCliente(int idCliente) {
		Usuario u = null;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			conn = Conexion.getConexion();
			String sql = CONSULTA + " WHERE c.id = ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, idCliente);
			rs = ps.executeQuery();

			if (rs.next()) {
				u = llenarUsuario(rs);
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

	// Obtengo usuario por nombre de usuario
	@Override
	public Usuario obtenerUsuarioPorNombre(String nombreUsuario) {
		Usuario u = null;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			conn = Conexion.getConexion();
			String sql = CONSULTA + " WHERE u.nombre_usuario = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, nombreUsuario);
			rs = ps.executeQuery();

			if (rs.next()) {
				u = llenarUsuario(rs);
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

	// Insertar usuario
	@Override
	public boolean insertarUsuario(Usuario usuario) {
		boolean resultado = false;
		Connection conn = null;
		PreparedStatement ps = null;

		try {
			conn = Conexion.getConexion();
			ps = conn.prepareStatement("INSERT INTO usuarios (id_cliente, id_tipo_usuario, nombre_usuario, contrasena, activo) VALUES (?, ?, ?, ?, 1)");

			ps.setInt(1, usuario.getCliente().getId());
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
				if (conn != null)
					conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return resultado;
	}

	// Valida credenciales
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

	// todos los usuarios admin
	@Override
	public List<Usuario> obtenerTodosLosUsuariosAdmin() {
		List<Usuario> listaAdmins = new ArrayList<>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			conn = Conexion.getConexion();
			ps = conn.prepareStatement(CONSULTA + "WHERE u.id_tipo_usuario = 1");
			rs = ps.executeQuery();

			while (rs.next()) {
				Usuario usuario = llenarUsuario(rs);
				listaAdmins.add(usuario);
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

		return listaAdmins;
	}

	@Override
	public boolean bajaPorIdCLiente(int idCliente) {
		boolean estado = false;
		Connection con = null;
		PreparedStatement ps = null;

		String sql = "UPDATE usuarios SET activo = 0 WHERE id_cliente = ?";

		try {
			con = Conexion.getConexion();
			ps = con.prepareStatement(sql);
			ps.setInt(1, idCliente);

			int filas = ps.executeUpdate();
			if (filas > 0) {
				estado = true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (ps != null)
					ps.close();
			} catch (Exception e) {
			}
			try {
				if (con != null)
					con.close();
			} catch (Exception e) {
			}
		}

		return estado;
	}

	@Override
	public boolean activarUsuarioPorIdCliente(int idCliente) {
		boolean estado = false;
		Connection con = null;
		PreparedStatement ps = null;

		String sql = "UPDATE usuarios SET activo = 1 WHERE id_cliente = ?";

		try {
			con = Conexion.getConexion();
			ps = con.prepareStatement(sql);
			ps.setInt(1, idCliente);

			int filas = ps.executeUpdate();
			if (filas > 0) {
				estado = true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (ps != null)
					ps.close();
			} catch (Exception e) {
			}
			try {
				if (con != null)
					con.close();
			} catch (Exception e) {
			}
		}

		return estado;
	}
}
