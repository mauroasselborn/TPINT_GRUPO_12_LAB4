package negocioimpl;

import java.util.List;

import dao.PrestamoDao;
import daoimpl.PrestamoDaoImpl;
import negocio.PrestamoNegocio;
import negocio.CuentaNegocio;
import negocio.MovimientoNegocio;
import entidades.Prestamo;
import entidades.TipoMovimiento;
import entidades.Cuenta;
import entidades.Cuota;
import entidades.Movimiento;
import negocio.CuotaNegocio;

public class PrestamoNegocioImpl implements PrestamoNegocio {
	private PrestamoDao prestamoDao = new PrestamoDaoImpl();
	private CuotaNegocio cuotaNegocio = new CuotaNegocioImpl();
	private CuentaNegocio cuentaNegocio = new CuentaNegocioImpl();

	@Override
	public List<Prestamo> obtenerPrestamosPorCliente(int idCliente) {
		return prestamoDao.obtenerPrestamosPorCliente(idCliente);
	}

	@Override
	public Prestamo obtenerPrestamoPorId(int idPrestamo) {
		return prestamoDao.obtenerPrestamoPorId(idPrestamo);
	}

	@Override
	public List<Cuota> obtenerCuotasPorPrestamo(int idPrestamo) {
		return prestamoDao.obtenerCuotasPorPrestamo(idPrestamo);
	}

	@Override
	public boolean registrarSolicitudPrestamo(Prestamo prestamo) {
		if (prestamo == null || prestamo.getCliente() == null || prestamo.getCuenta() == null) {
			return false;
		}

		if (prestamo.getImportePedido() <= 0 || prestamo.getCantidadCuotas() <= 0) {
			return false;
		}

		// Insertar el prestamo
		int idPrestamoGenerado = prestamoDao.insertarPrestamo(prestamo);
		if (idPrestamoGenerado <= 0) {
			return false;
		}

		return true;
	}

	@Override
	public List<Prestamo> obtenerTodosLosPrestamos() {
		return prestamoDao.obtenerTodosLosPrestamos();
	}

	@Override
	public boolean cambiarEstadoPrestamo(int idPrestamo, int nuevoEstado) {
		return prestamoDao.cambiarEstadoPrestamo(idPrestamo, nuevoEstado);
	}

	@Override
	public boolean acreditarPrestamo(int idPrestamo) {
		// Obtener el préstamo
		Prestamo prestamo = prestamoDao.obtenerPrestamoPorId(idPrestamo);
		if (prestamo == null) {
			return false;
		}

		double monto = prestamo.getImportePedido();
		int idCuenta = prestamo.getCuenta().getId();

		// Aumentar saldo en la cuenta destino
		try {
			cuentaNegocio.aumentarSaldo(idCuenta, monto);
		} catch (Exception e) {
			e.printStackTrace();
		}

		double importePorCuota = Math.round((prestamo.getImportePedido() / prestamo.getCantidadCuotas()) * 100.0) / 100.0;
		if (importePorCuota <= 0) {
			return false;
		}

		prestamo.setImportePorCuota(importePorCuota);

		// Insertar cuotas
		for (int i = 1; i <= prestamo.getCantidadCuotas(); i++) {
			Cuota cuota = new Cuota();
			cuota.setNroCuota(i);
			cuota.setMonto(importePorCuota);

			Prestamo pRef = new Prestamo();
			pRef.setId(idPrestamo);
			cuota.setPrestamo(pRef);

			cuotaNegocio.insertarCuota(cuota);
		}

		// Registrar movimiento de Alta de Préstamo
		Movimiento movimiento = new Movimiento();
		movimiento.setFecha(java.time.LocalDate.now().toString());
		movimiento.setDetalle("Alta de préstamo");
		movimiento.setTipoMovimiento(new TipoMovimiento(2, "Alta de Préstamo"));
		movimiento.setImporte(monto);
		movimiento.setTipo("C"); // Crédito

		Cuenta cuenta = new Cuenta();
		cuenta.setId(idCuenta);
		movimiento.setCuenta(cuenta);

		MovimientoNegocio movimientoNegocio = new MovimientoNegocioImpl();
		movimientoNegocio.insertarMovimiento(movimiento);

		return true;
	}

}
