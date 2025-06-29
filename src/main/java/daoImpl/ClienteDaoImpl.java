package daoImpl;

import dao.ClienteDao;
import datos.Conexion;
import entidades.Cliente;
import entidades.Nacionalidad;
import entidades.Localidad;
import entidades.Provincia;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteDaoImpl implements ClienteDao {
	
	private final String CONSULTA = "SELECT C.*"
			+ " FROM clientes C"
			+ " JOIN usuarios U ON U.id_cliente = C.id"
			+ " WHERE U.id_tipo_usuario = ?";

	@Override
	public List<Cliente> obtenerTodos() {
	    List<Cliente> lista = new ArrayList<>();
	    


	    Connection con = null;
	    PreparedStatement ps = null;
	    ResultSet rs = null;
	    try {
	        con = Conexion.getConexion();
	        ps = con.prepareStatement(CONSULTA);
	        ps.setInt(1, 2);
	        rs = ps.executeQuery();

	        while (rs.next()) {
	            Cliente cliente = new Cliente();
	            cliente.setId(rs.getInt("id"));
	            cliente.setDni(rs.getString("dni"));
	            cliente.setCuil(rs.getString("cuil"));
	            cliente.setNombre(rs.getString("nombre"));
	            cliente.setApellido(rs.getString("apellido"));
	            cliente.setSexo(rs.getString("sexo"));

	            Nacionalidad nac = new Nacionalidad();
	            nac.setId(rs.getInt("id_nacionalidad"));
	            cliente.setNacionalidad(nac);

	            cliente.setFechaNacimiento(rs.getString("fecha_nacimiento"));
	            cliente.setDireccion(rs.getString("direccion"));

	            Localidad loc = new Localidad();
	            loc.setId(rs.getInt("id_localidad"));
	            cliente.setLocalidad(loc);

	            Provincia prov = new Provincia();
	            prov.setId(rs.getInt("id_provincia"));
	            cliente.setProvincia(prov);

	            cliente.setCorreoElectronico(rs.getString("correo_electronico"));
	            cliente.setTelefono(rs.getString("telefono"));
	            cliente.setActivo(rs.getBoolean("activo"));

	            lista.add(cliente);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (rs != null) rs.close();
	        } catch (Exception e) {}
	        try {
	            if (ps != null) ps.close();
	        } catch (Exception e) {}
	        try {
	            if (con != null) con.close();
	        } catch (Exception e) {}
	    }

	    return lista;
	}


	@Override
	public Cliente obtenerPorId(int id) {
		Cliente cliente = null;
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = Conexion.getConexion();
			ps = con.prepareStatement(CONSULTA + " AND C.id = ?");
			ps.setInt(1, 2);
			ps.setInt(2, id);
			rs = ps.executeQuery();

			if (rs.next()) {
				cliente = new Cliente();
				cliente.setId(rs.getInt("id"));
				cliente.setDni(rs.getString("dni"));
				cliente.setCuil(rs.getString("cuil"));
				cliente.setNombre(rs.getString("nombre"));
				cliente.setApellido(rs.getString("apellido"));
				cliente.setSexo(rs.getString("sexo"));

				Nacionalidad nac = new Nacionalidad();
				nac.setId(rs.getInt("id_nacionalidad"));
				cliente.setNacionalidad(nac);

				cliente.setFechaNacimiento(rs.getString("fecha_nacimiento"));
				cliente.setDireccion(rs.getString("direccion"));

				Localidad loc = new Localidad();
				loc.setId(rs.getInt("id_localidad"));
				cliente.setLocalidad(loc);

				Provincia prov = new Provincia();
				prov.setId(rs.getInt("id_provincia"));
				cliente.setProvincia(prov);

				cliente.setCorreoElectronico(rs.getString("correo_electronico"));
				cliente.setTelefono(rs.getString("telefono"));
				cliente.setActivo(rs.getBoolean("activo"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
			} catch (Exception e) {
			}
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

		return cliente;
	}

	@Override
	public boolean alta(Cliente cliente) {
		boolean estado = false;
		Connection con = null;
		PreparedStatement ps = null;

		String sql = "INSERT INTO clientes " + "(dni, cuil, nombre, apellido, sexo, id_nacionalidad, fecha_nacimiento, direccion, id_localidad, id_provincia, correo_electronico, telefono, activo) " + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, 1)";

		try {
			con = Conexion.getConexion();
			ps = con.prepareStatement(sql);

			ps.setString(1, cliente.getDni());
			ps.setString(2, cliente.getCuil());
			ps.setString(3, cliente.getNombre());
			ps.setString(4, cliente.getApellido());
			ps.setString(5, cliente.getSexo());
			ps.setInt(6, cliente.getNacionalidad().getId());
			ps.setString(7, cliente.getFechaNacimiento());
			ps.setString(8, cliente.getDireccion());
			ps.setInt(9, cliente.getLocalidad().getId());
			ps.setInt(10, cliente.getProvincia().getId());
			ps.setString(11, cliente.getCorreoElectronico());
			ps.setString(12, cliente.getTelefono());

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
	public boolean modificar(Cliente cliente) {
		boolean estado = false;
		Connection con = null;
		PreparedStatement ps = null;

		String sql = "UPDATE clientes SET dni = ?, cuil = ?, nombre = ?, apellido = ?, sexo = ?, id_nacionalidad = ?, fecha_nacimiento = ?, " + "direccion = ?, id_localidad = ?, id_provincia = ?, correo_electronico = ?, telefono = ? WHERE id = ?";

		try {
			con = Conexion.getConexion();
			ps = con.prepareStatement(sql);

			ps.setString(1, cliente.getDni());
			ps.setString(2, cliente.getCuil());
			ps.setString(3, cliente.getNombre());
			ps.setString(4, cliente.getApellido());
			ps.setString(5, cliente.getSexo());
			ps.setInt(6, cliente.getNacionalidad().getId());
			ps.setString(7, cliente.getFechaNacimiento());
			ps.setString(8, cliente.getDireccion());
			ps.setInt(9, cliente.getLocalidad().getId());
			ps.setInt(10, cliente.getProvincia().getId());
			ps.setString(11, cliente.getCorreoElectronico());
			ps.setString(12, cliente.getTelefono());
			ps.setInt(13, cliente.getId());

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
	public boolean baja(int id) {
		boolean estado = false;
		Connection con = null;
		PreparedStatement ps = null;

		String sql = "UPDATE clientes SET activo = 0 WHERE id = ?";

		try {
			con = Conexion.getConexion();
			ps = con.prepareStatement(sql);
			ps.setInt(1, id);

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
	public boolean altaLogica(int id) {
		boolean estado = false;
		Connection con = null;
		PreparedStatement ps = null;

		String sql = "UPDATE clientes SET activo = 1 WHERE id = ?";

		try {
			con = Conexion.getConexion();
			ps = con.prepareStatement(sql);
			ps.setInt(1, id);

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
	public Cliente obtenerPorDni(String dni) {
		Cliente cliente = null;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			conn = Conexion.getConexion();
			ps = conn.prepareStatement(CONSULTA + " AND C.dni = ?");
			ps.setInt(1, 2);
			ps.setString(2, dni);
			rs = ps.executeQuery();

			if (rs.next()) {
				cliente = new Cliente();
				cliente.setId(rs.getInt("id"));
				cliente.setDni(rs.getString("dni"));
				cliente.setCuil(rs.getString("cuil"));
				cliente.setNombre(rs.getString("nombre"));
				cliente.setApellido(rs.getString("apellido"));
				cliente.setSexo(rs.getString("sexo"));
				cliente.setFechaNacimiento(rs.getString("fecha_nacimiento"));
				cliente.setDireccion(rs.getString("direccion"));
				cliente.setCorreoElectronico(rs.getString("correo_electronico"));
				cliente.setTelefono(rs.getString("telefono"));
				cliente.setActivo(rs.getBoolean("activo"));

				
				Nacionalidad nac = new Nacionalidad();
				nac.setId(rs.getInt("id_nacionalidad"));
				cliente.setNacionalidad(nac);

				Provincia prov = new Provincia();
				prov.setId(rs.getInt("id_provincia"));
				cliente.setProvincia(prov);

				Localidad loc = new Localidad();
				loc.setId(rs.getInt("id_localidad"));
				cliente.setLocalidad(loc);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
			} catch (Exception e) {
			}
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

		return cliente;
	}

}
