package daoimpl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import dao.CuotaDao;
import datos.Conexion;
import entidades.Cuota;
import entidades.Prestamo;

public class CuotaDaoImpl implements CuotaDao {

	@Override
	public boolean insertarCuota(Cuota cuota) {
		String sql = "INSERT INTO cuotas (id_prestamo, nro_cuota, monto, fecha_pago) VALUES (?, ?, ?, NULL)";

		try (Connection con = Conexion.getConexion(); PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setInt(1, cuota.getPrestamo().getId());
			ps.setInt(2, cuota.getNroCuota());
			ps.setDouble(3, cuota.getMonto());

			int filas = ps.executeUpdate();
			return filas > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<Cuota> obtenerCuotasPorPrestamo(int idPrestamo) {
		List<Cuota> cuotas = new ArrayList<>();
		String sql = "SELECT id, nro_cuota, monto, fecha_pago FROM cuotas WHERE id_prestamo = ? ORDER BY nro_cuota ASC";

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
		String sql = "UPDATE cuotas SET fecha_pago = ? WHERE id = ? AND fecha_pago IS NULL";

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
	public Cuota obtenerProximaCuotaPorPrestamo(int idPrestamo) {
		String sql = "SELECT id, nro_cuota, monto " + "  FROM cuotas "
				+ " WHERE id_prestamo = ? AND fecha_pago IS NULL " + " ORDER BY nro_cuota ASC " + " LIMIT 1";
		try (Connection con = Conexion.getConexion(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, idPrestamo);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					Cuota c = new Cuota();
					c.setId(rs.getInt("id"));
					c.setNroCuota(rs.getInt("nro_cuota"));
					c.setMonto(rs.getDouble("monto"));
					return c;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int contarCuotasPendientes(int idPrestamo) {
		String sql = "SELECT COUNT(*) AS total " + "  FROM cuotas " + " WHERE id_prestamo = ? AND fecha_pago IS NULL";
		try (Connection con = Conexion.getConexion(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, idPrestamo);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					return rs.getInt("total");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public Cuota obtenerCuotaPorId(int idCuota) {
		Cuota cuota = null;
		Connection conexion = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conexion = Conexion.getConexion();
			String sql = "SELECT id, id_prestamo, nro_cuota, monto, fecha_pago "
					+ "FROM cuotas "
					+ "WHERE id = ?";
			
			ps = conexion.prepareStatement(sql);
			ps.setInt(1, idCuota);
			rs = ps.executeQuery();
			if (rs.next()) {
				cuota = new Cuota();
				cuota.setId(rs.getInt("id"));

				Prestamo prestamo = new Prestamo();
				prestamo.setId(rs.getInt("id_prestamo"));
				cuota.setPrestamo(prestamo);

				cuota.setNroCuota(rs.getInt("nro_cuota"));
				cuota.setMonto(rs.getDouble("monto"));

				Date fechaSql = rs.getDate("fecha_pago");
				if (fechaSql != null) {
					cuota.setFechaPago(new java.util.Date(fechaSql.getTime()));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
			try {
				if (ps != null)
					ps.close();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
			try {
				if (conexion != null)
					conexion.close();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
		return cuota;
	}
}
