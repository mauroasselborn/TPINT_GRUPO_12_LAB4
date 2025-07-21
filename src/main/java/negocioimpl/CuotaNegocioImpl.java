package negocioimpl;

import java.text.DecimalFormat;
import java.util.List;

import dao.CuotaDao;
import dao.PrestamoDao;
import daoimpl.CuotaDaoImpl;
import daoimpl.PrestamoDaoImpl;
import daoimpl.CuentaDaoImpl;
import entidades.Cuota;
import entidades.Prestamo;
import negocio.CuotaNegocio;

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

		boolean okPago = cuotaDao.pagarCuota(idCuota, fechaPago);
		if (!okPago) {
			return "Error al registrar el pago de la cuota.";
		}

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
