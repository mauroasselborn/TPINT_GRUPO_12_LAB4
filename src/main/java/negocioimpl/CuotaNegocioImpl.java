package negocioimpl;

import java.text.DecimalFormat;
import java.util.List;

import dao.CuotaDao;
import dao.PrestamoDao;
import daoimpl.CuotaDaoImpl;
import daoimpl.PrestamoDaoImpl;
import daoimpl.CuentaDaoImpl;
import entidades.Cuenta;
import entidades.Cuota;
import entidades.Movimiento;
import entidades.Prestamo;
import entidades.TipoMovimiento;
import negocio.CuotaNegocio;
import negocio.MovimientoNegocio;

public class CuotaNegocioImpl implements CuotaNegocio {
	private CuotaDao cuotaDao = new CuotaDaoImpl();
	private PrestamoDao prestamoDao = new PrestamoDaoImpl();

	@Override
	public boolean insertarCuota(Cuota cuota) {
		return cuotaDao.insertarCuota(cuota);
	}

	@Override
	public List<Cuota> obtenerCuotasPorPrestamo(int idPrestamo) {
		return cuotaDao.obtenerCuotasPorPrestamo(idPrestamo);
	}
	
	@Override
	public Cuota obtenerCuotaPorId(int idCuota) {
		return cuotaDao.obtenerProximaCuotaPorPrestamo(idCuota);
	}

	@Override
	public String pagarCuota(int idPrestamo, int idCuota, String fechaPago) {
		Prestamo prestamo = prestamoDao.obtenerPrestamoPorId(idPrestamo);
		if (prestamo == null) {
			return "Préstamo no encontrado.";
		}

		double montoCuota = prestamo.getImportePorCuota();
		int idCuenta = prestamo.getCuenta().getId();

		boolean okSaldo = new CuentaDaoImpl().descontarSaldo(idCuenta, montoCuota);
		if (!okSaldo) {
			return "Saldo insuficiente en la cuenta " + prestamo.getCuenta().getNumeroCuenta() + " para cuota de $"
					+ new DecimalFormat("#0.00").format(montoCuota) + ".";
		}
		
		//me traigo la cuota
		Cuota cuota = cuotaDao.obtenerCuotaPorId(idCuota);
		
		boolean okPago = cuotaDao.pagarCuota(idCuota, fechaPago);
		if (!okPago) {
			return "Error al registrar el pago de la cuota.";
		}

		// Registro movimiento
		Movimiento movimiento = new Movimiento();
		movimiento.setFecha(java.time.LocalDate.now().toString());
		movimiento.setDetalle("Pago de Cuota N°" + cuota.getNroCuota());
		movimiento.setTipoMovimiento(new TipoMovimiento(3, "Pago de Préstamo"));
		movimiento.setImporte(-cuota.getMonto());
		movimiento.setTipo("P");

		Cuenta cuenta = new Cuenta();
		cuenta.setId(idCuenta);
		movimiento.setCuenta(cuenta);

		MovimientoNegocio movimientoNegocio = new MovimientoNegocioImpl();
		movimientoNegocio.insertarMovimiento(movimiento);

		prestamo.setCuotasPendientes(prestamo.getCuotasPendientes() - 1);
		prestamoDao.modificarPrestamo(prestamo);

		return "Cuota $" + new DecimalFormat("#0.00").format(montoCuota) + " pagada exitosamente de la cuenta "
				+ prestamo.getCuenta().getNumeroCuenta() + ".";
	}

	@Override
	public Cuota obtenerProximaCuotaPorPrestamo(int idPrestamo) {
		return cuotaDao.obtenerProximaCuotaPorPrestamo(idPrestamo);
	}

	@Override
	public int contarCuotasPendientes(int idPrestamo) {
		return cuotaDao.contarCuotasPendientes(idPrestamo);
	}
}
