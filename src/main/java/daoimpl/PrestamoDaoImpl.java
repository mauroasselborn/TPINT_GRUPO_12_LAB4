package daoimpl;

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
		String sql = "SELECT * " + "FROM prestamos " + "WHERE id_cliente = ?";

		try (Connection con = Conexion.getConexion(); PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setInt(1, idCliente);
			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					Prestamo p = new Prestamo();
					p.setId(rs.getInt("id"));
					p.setFechaAlta(rs.getDate("fecha"));
					p.setImportePedido(rs.getDouble("importe_pedido"));
					p.setCantidadCuotas(rs.getInt("cantidad_cuotas"));
					p.setImportePorCuota(rs.getDouble("importe_cuota"));
					p.setCuotasPendientes(rs.getInt("cuotas_pendientes"));
					p.setIdEstado(rs.getInt("id_estado"));

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
		String sql = "SELECT * " + "FROM prestamos " + "WHERE id = ?";

		try (Connection con = Conexion.getConexion(); PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setInt(1, idPrestamo);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					prestamo = new Prestamo();
					prestamo.setId(rs.getInt("id"));
					prestamo.setFechaAlta(rs.getDate("fecha"));
					prestamo.setImportePedido(rs.getDouble("importe_pedido"));
					prestamo.setCantidadCuotas(rs.getInt("cantidad_cuotas"));
					prestamo.setImportePorCuota(rs.getDouble("importe_cuota"));
					prestamo.setCuotasPendientes(rs.getInt("cuotas_pendientes"));
					prestamo.setIdEstado(rs.getInt("id_estado"));

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
		String sql = "SELECT * " + "FROM cuotas " + "WHERE id_prestamo = ? " + "ORDER BY nro_cuota ASC";

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
	public int insertarPrestamo(Prestamo prestamo) {
		int idGenerado = -1;
		String sqlPrestamo = "INSERT INTO prestamos " + "(id_cliente, id_cuenta, fecha, importe_pedido, cantidad_cuotas, importe_cuota, cuotas_pendientes, id_estado) " + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

		try (Connection con = Conexion.getConexion(); PreparedStatement ps = con.prepareStatement(sqlPrestamo, Statement.RETURN_GENERATED_KEYS)) {

			ps.setInt(1, prestamo.getCliente().getId());
			ps.setInt(2, prestamo.getCuenta().getId());

			java.sql.Date sqlDate = new java.sql.Date(prestamo.getFechaAlta().getTime());
			ps.setDate(3, sqlDate);

			ps.setDouble(4, prestamo.getImportePedido());
			ps.setInt(5, prestamo.getCantidadCuotas());
			ps.setDouble(6, prestamo.getImportePorCuota());
			ps.setInt(7, prestamo.getCuotasPendientes());
			ps.setInt(8, prestamo.getIdEstado());

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

	@Override
	public List<Prestamo> obtenerTodosLosPrestamos() {
		List<Prestamo> lista = new ArrayList<>();
		String sql = "SELECT * FROM prestamos";

		try (Connection con = Conexion.getConexion(); PreparedStatement ps = con.prepareStatement(sql)) {

			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					Prestamo p = new Prestamo();
					p.setId(rs.getInt("id"));
					p.setFechaAlta(rs.getDate("fecha"));
					p.setImportePedido(rs.getDouble("importe_pedido"));
					p.setCantidadCuotas(rs.getInt("cantidad_cuotas"));
					p.setImportePorCuota(rs.getDouble("importe_cuota"));
					p.setCuotasPendientes(rs.getInt("cuotas_pendientes"));
					p.setIdEstado(rs.getInt("id_estado"));

					Cuenta c = new Cuenta();
					c.setId(rs.getInt("id_cuenta"));
					p.setCuenta(c);

					Cliente cl = new Cliente();
					cl.setId(rs.getInt("id_cliente"));
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
	public boolean cambiarEstadoPrestamo(int idPrestamo, int nuevoEstado) {
		String sql = "UPDATE prestamos SET id_estado = ? WHERE id = ?";
		try (Connection con = Conexion.getConexion(); PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setInt(1, nuevoEstado);
			ps.setInt(2, idPrestamo);
			return ps.executeUpdate() > 0;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

}
