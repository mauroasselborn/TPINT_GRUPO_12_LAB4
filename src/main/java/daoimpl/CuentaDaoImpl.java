package daoimpl;

import dao.CuentaDao;
import entidades.Cuenta;
import entidades.Cliente;
import negocio.TipoCuentaNegocio;
import negocioimpl.TipoCuentaNegocioImpl;
import datos.Conexion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CuentaDaoImpl implements CuentaDao {

	@Override
	public List<Cuenta> obtenerTodas() {
		List<Cuenta> lista = new ArrayList<>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		String sql = "SELECT c.id, c.numero_cuenta, c.cbu, c.saldo, c.fecha_creacion, " + "c.id_tipo_cuenta, c.id_cliente, cl.nombre, cl.apellido, c.activo " + "FROM bancogrupo12.cuentas c " + "JOIN bancogrupo12.clientes cl ON c.id_cliente = cl.id "
				+ "WHERE c.activo = 1";

		try {
			con = Conexion.getConexion();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();

			while (rs.next()) {
				Cuenta cuenta = new Cuenta();
				cuenta.setId(rs.getInt("id"));
				cuenta.setNumeroCuenta(rs.getString("numero_cuenta"));
				cuenta.setCbu(rs.getString("cbu"));
				cuenta.setSaldo(rs.getDouble("saldo"));
				cuenta.setFechaCreacion(rs.getString("fecha_creacion"));
				
				TipoCuentaNegocio tipocuenta = new TipoCuentaNegocioImpl();				
				cuenta.setTipoCuenta(tipocuenta.obtenerPorId(rs.getInt("id_tipo_cuenta")));
				
				Cliente cli = new Cliente();
				cli.setId(rs.getInt("id_cliente"));
				cli.setNombre(rs.getString("nombre"));
				cli.setApellido(rs.getString("apellido"));
				cuenta.setCliente(cli);

				cuenta.setActivo(rs.getBoolean("activo"));
				lista.add(cuenta);
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

		return lista;
	}

	@Override
	public Cuenta obtenerPorId(int id) {
		Cuenta cuenta = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		String sql = "SELECT c.id, c.numero_cuenta, c.cbu, c.saldo, c.fecha_creacion, " + "c.id_tipo_cuenta, c.id_cliente, cl.nombre, cl.apellido, c.activo " + "FROM cuentas c " + "JOIN clientes cl ON c.id_cliente = cl.id " + "WHERE c.id = ?";

		try {
			con = Conexion.getConexion();
			ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();

			if (rs.next()) {
				cuenta = new Cuenta();
				cuenta.setId(rs.getInt("id"));
				cuenta.setNumeroCuenta(rs.getString("numero_cuenta"));
				cuenta.setCbu(rs.getString("cbu"));
				cuenta.setSaldo(rs.getDouble("saldo"));
				cuenta.setFechaCreacion(rs.getString("fecha_creacion"));
				
				TipoCuentaNegocio tipocuenta = new TipoCuentaNegocioImpl();				
				cuenta.setTipoCuenta(tipocuenta.obtenerPorId(id));
				

				Cliente cliente = new Cliente();
				cliente.setId(rs.getInt("id_cliente"));
				cliente.setNombre(rs.getString("nombre"));
				cliente.setApellido(rs.getString("apellido"));
				cuenta.setCliente(cliente);

				cuenta.setActivo(rs.getBoolean("activo"));
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

		return cuenta;
	}
	
	
	@Override
	public boolean alta(Cuenta cuenta) {
	    boolean estado = false;
	    Connection con = null;
	    PreparedStatement psCuenta = null;
	    PreparedStatement psMov = null;
	    ResultSet rsKeys = null;

	    String sql = "INSERT INTO cuentas (id_cliente, numero_cuenta, cbu, id_tipo_cuenta, fecha_creacion, saldo, activo) VALUES (?, ?, ?, ?, ?, ?, 1)";
	    String sqlMovimiento = "INSERT INTO movimientos (fecha, detalle, id_tipo_movimiento, importe, tipo, id_cuenta) VALUES (NOW(), ?, ?, ?, ?, ?)";

	    try {
	        con = Conexion.getConexion();
	        con.setAutoCommit(false); // Transacción

	        psCuenta = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
	        psCuenta.setInt(1, cuenta.getCliente().getId());
	        psCuenta.setString(2, cuenta.getNumeroCuenta());
	        psCuenta.setString(3, cuenta.getCbu());
	        psCuenta.setInt(4, cuenta.getTipoCuenta().getId());
	        psCuenta.setString(5, cuenta.getFechaCreacion());
	        psCuenta.setDouble(6, cuenta.getSaldo());

	        int filasCuenta = psCuenta.executeUpdate();

	        if (filasCuenta > 0) {
	            rsKeys = psCuenta.getGeneratedKeys();
	            if (rsKeys.next()) {
	                int idCuenta = rsKeys.getInt(1);

	                psMov = con.prepareStatement(sqlMovimiento);
	                psMov.setString(1, "Alta de cuenta");
	                psMov.setInt(2, 1); // ID tipo_movimiento: "Alta de cuenta"
	                psMov.setDouble(3, cuenta.getSaldo());
	                psMov.setString(4, "ingreso");
	                psMov.setInt(5, idCuenta);

	                int filasMov = psMov.executeUpdate();

	                if (filasMov > 0) {
	                    con.commit();
	                    estado = true;
	                } else {
	                    con.rollback();
	                }
	            }
	        }

	    } catch (SQLException e) {
	        try {
	            if (con != null) con.rollback();
	        } catch (SQLException ex) {
	            ex.printStackTrace();
	        }
	        e.printStackTrace();
	    } finally {
	        try { if (rsKeys != null) rsKeys.close(); } catch (Exception e) {}
	        try { if (psMov != null) psMov.close(); } catch (Exception e) {}
	        try { if (psCuenta != null) psCuenta.close(); } catch (Exception e) {}
	        try { if (con != null) con.setAutoCommit(true); con.close(); } catch (Exception e) {}
	    }

	    return estado;
	}


	@Override
	public boolean modificar(Cuenta cuenta) {
		boolean estado = false;
		Connection con = null;
		PreparedStatement ps = null;

		String sql = "UPDATE cuentas SET id_tipo_cuenta = ?, saldo = ?, cbu = ? WHERE id = ?";

		try {
			con = Conexion.getConexion();
			ps = con.prepareStatement(sql);

			ps.setInt(1, cuenta.getTipoCuenta().getId());
			ps.setDouble(2, cuenta.getSaldo());
			ps.setString(3, cuenta.getCbu());
			ps.setInt(4, cuenta.getId());

			if (ps.executeUpdate() > 0) {
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

		String sql = "UPDATE cuentas SET activo = 0 WHERE id = ?";

		try {
			con = Conexion.getConexion();
			ps = con.prepareStatement(sql);
			ps.setInt(1, id);

			if (ps.executeUpdate() > 0) {
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
	
	public boolean existeNumeroCuenta(String numeroCuenta) {
	    Connection con = null;
	    PreparedStatement stmt = null;
	    ResultSet rs = null;
	    boolean existe = false;

	    try {
	        con = Conexion.getConexion();
	        stmt = con.prepareStatement("SELECT 1 FROM cuentas WHERE numero_cuenta = ?");
	        stmt.setString(1, numeroCuenta);
	        rs = stmt.executeQuery();
	        existe = rs.next();
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        try { if (rs != null) rs.close(); } catch (Exception e) {}
	        try { if (stmt != null) stmt.close(); } catch (Exception e) {}
	        try { if (con != null) con.close(); } catch (Exception e) {}
	    }

	    return existe;
	}
	
	public boolean existeCBU(String cbu) {
	    Connection con = null;
	    PreparedStatement stmt = null;
	    ResultSet rs = null;
	    boolean existe = false;

	    try {
	        con = Conexion.getConexion();
	        stmt = con.prepareStatement("SELECT 1 FROM cuentas WHERE cbu = ?");
	        stmt.setString(1, cbu);
	        rs = stmt.executeQuery();
	        existe = rs.next();
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        try { if (rs != null) rs.close(); } catch (Exception e) {}
	        try { if (stmt != null) stmt.close(); } catch (Exception e) {}
	        try { if (con != null) con.close(); } catch (Exception e) {}
	    }

	    return existe;
	}
	
	public boolean descontarSaldo(int idCuenta, double monto) {
	    boolean estado = false;
	    PreparedStatement ps = null;

	    String sql = "UPDATE cuentas SET saldo = saldo - ? WHERE id = ? AND saldo >= ?";

	    try {
	        ps = Conexion.getConexion().prepareStatement(sql);
	        ps.setDouble(1, monto);
	        ps.setInt(2, idCuenta);
	        ps.setDouble(3, monto);

	        if (ps.executeUpdate() > 0) {
	            estado = true;
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (ps != null) ps.close();
	        } catch (Exception e) {}
	    }

	    return estado;
	}

	@Override
	public boolean aumentarSaldo(int idCuenta, double monto) {
	    boolean estado = false;
	    PreparedStatement ps = null;

	    String sql = "UPDATE cuentas SET saldo = saldo + ? WHERE id = ?";

	    try {
	        ps = Conexion.getConexion().prepareStatement(sql);
	        ps.setDouble(1, monto);
	        ps.setInt(2, idCuenta);

	        if (ps.executeUpdate() > 0) {
	            estado = true;
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (ps != null) ps.close();
	        } catch (Exception e) {}
	    }

	    return estado;
	}
	
	@Override
	public List<Cuenta> obtenerPorCliente(int idCliente) {
	    List<Cuenta> lista = new ArrayList<>();
	    Connection cn = null;
	    PreparedStatement pst = null;
	    ResultSet rs = null;

	    try {
	        cn = Conexion.getConexion();
	        String sql = "SELECT * FROM cuentas WHERE id_cliente = ? AND activo = 1";
	        pst = cn.prepareStatement(sql);
	        pst.setInt(1, idCliente);
	        rs = pst.executeQuery();

	        while (rs.next()) {
	            Cuenta c = new Cuenta();
	            c.setId(rs.getInt("id"));
	            c.setNumeroCuenta(rs.getString("numero_cuenta"));
	            c.setCbu(rs.getString("cbu"));
	            c.setSaldo(rs.getDouble("saldo"));
	            c.setFechaCreacion(rs.getString("fecha_creacion"));
	            
	            TipoCuentaNegocio tipocuenta = new TipoCuentaNegocioImpl();
	            c.setTipoCuenta(tipocuenta.obtenerPorId(rs.getInt("id_tipo_cuenta")));
	            
	            c.setActivo(rs.getBoolean("activo"));
	            
	            // Cargar cliente (opcional si lo necesitás completo)
	            Cliente cliente = new Cliente();
	            cliente.setId(rs.getInt("id_cliente"));
	            c.setCliente(cliente);
	            
	            lista.add(c);
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        try { if (rs != null) rs.close(); } catch (Exception e) { e.printStackTrace(); }
	        try { if (pst != null) pst.close(); } catch (Exception e) { e.printStackTrace(); }
	        try { if (cn != null) cn.close(); } catch (Exception e) { e.printStackTrace(); }
	    }

	    return lista;
	}
}