package daoImpl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import dao.PrestamoDao;
import datos.Conexion;
import entidades.Cliente;
import entidades.Cuenta;
import entidades.Prestamo;
import entidades.Cuota;

public class PrestamoDaoImpl implements PrestamoDao {

	@Override
	public List<Prestamo> obtenerPrestamosPorCliente(int idCliente) {
		List<Prestamo> lista = new ArrayList<>();
		String sql = "SELECT id, fecha_alta, importe_pedido, cantidad_cuotas, importe_por_cuota, estado, id_cuenta"
				+ " FROM prestamo" + " WHERE id_cliente = ? AND activo = 1";

		try (Connection con = Conexion.getConexion(); PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setInt(1, idCliente);
			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					Prestamo p = new Prestamo();
					p.setId(rs.getInt("id"));
					p.setFechaAlta(rs.getDate("fecha_alta"));
					p.setImportePedido(rs.getDouble("importe_pedido"));
					p.setCantidadCuotas(rs.getInt("cantidad_cuotas"));
					p.setImportePorCuota(rs.getDouble("importe_por_cuota"));
					p.setEstado(rs.getString("estado"));

					Cuenta c = new Cuenta();
					c.setId(rs.getInt("id_cuenta"));
					p.setCuenta(c);

					Cliente cl = new Cliente();
					cl.setId(idCliente);
					p.setCliente(cl);

					lista.add(p);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;
	}

	@Override
	public Prestamo obtenerPrestamoPorId(int idPrestamo) {
		Prestamo prestamo = null;
		String sql = "SELECT id, fecha_alta, importe_pedido, cantidad_cuotas, importe_por_cuota, estado, id_cuenta, id_cliente"
				+ " FROM prestamo" + " WHERE id = ? AND activo = 1";

		try (Connection con = Conexion.getConexion(); PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setInt(1, idPrestamo);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					prestamo = new Prestamo();
					prestamo.setId(rs.getInt("id"));
					prestamo.setFechaAlta(rs.getDate("fecha_alta"));
					prestamo.setImportePedido(rs.getDouble("importe_pedido"));
					prestamo.setCantidadCuotas(rs.getInt("cantidad_cuotas"));
					prestamo.setImportePorCuota(rs.getDouble("importe_por_cuota"));
					prestamo.setEstado(rs.getString("estado"));

					Cuenta c = new Cuenta();
					c.setId(rs.getInt("id_cuenta"));
					prestamo.setCuenta(c);

					Cliente cl = new Cliente();
					cl.setId(rs.getInt("id_cliente"));
					prestamo.setCliente(cl);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return prestamo;
	}

	@Override
	public List<Cuota> obtenerCuotasPorPrestamo(int idPrestamo) {
		List<Cuota> cuotas = new ArrayList<>();
		String sql = "SELECT id, nro_cuota, monto, fecha_pago" + " FROM cuota"
				+ " WHERE id_prestamo = ? AND activo = 1 ORDER BY nro_cuota ASC";

		try (Connection con = Conexion.getConexion(); PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setInt(1, idPrestamo);
			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					Cuota cuota = new Cuota();
					cuota.setId(rs.getInt("id"));
					cuota.setNroCuota(rs.getInt("nro_cuota"));
					cuota.setMonto(rs.getDouble("monto"));
					cuota.setFechaPago(rs.getDate("fecha_pago"));

					Prestamo p = new Prestamo();
					p.setId(idPrestamo);
					cuota.setPrestamo(p);

					cuotas.add(cuota);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cuotas;
	}

	@Override
	public boolean pagarCuota(int idCuota, String fechaPago) {
		String sql = "UPDATE cuota SET fecha_pago = ? WHERE id = ? AND activo = 1 AND fecha_pago IS NULL";

		try (Connection con = Conexion.getConexion(); PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setString(1, fechaPago);
			ps.setInt(2, idCuota);

			int filas = ps.executeUpdate();
			return filas > 0;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public int insertarPrestamo(Prestamo prestamo) {
		int idGenerado = -1;
		String sql = "INSERT INTO prestamo (id_cliente, id_cuenta, fecha, importe_pedido, cantidad_cuotas, importe_cuota, cuotas_pendientes, id_estado, activo) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

		try (Connection con = Conexion.getConexion();
				PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

			ps.setInt(1, prestamo.getCliente().getId());
			ps.setInt(2, prestamo.getCuenta().getId());

			// fecha
			java.sql.Date sqlDate = new java.sql.Date(prestamo.getFechaAlta().getTime());
			ps.setDate(3, sqlDate);

			ps.setDouble(4, prestamo.getImportePedido());
			ps.setInt(5, prestamo.getCantidadCuotas());
			ps.setDouble(6, prestamo.getImportePorCuota());

			// cuotas_pendientes = NULL al crear
			ps.setNull(7, java.sql.Types.INTEGER);

			// id_estado = 1 (Pendiente)
			ps.setInt(8, 1);

			// activo = 1
			ps.setInt(9, 1);

			int filas = ps.executeUpdate();

			if (filas > 0) {
				try (ResultSet rs = ps.getGeneratedKeys()) {
					if (rs.next()) {
						idGenerado = rs.getInt(1);
					}
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return idGenerado;
	}

}
