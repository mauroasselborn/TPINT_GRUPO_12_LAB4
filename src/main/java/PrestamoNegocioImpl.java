package negocioImpl;

import java.util.List;

import dao.PrestamoDao;
import daoImpl.PrestamoDaoImpl;
import negocio.PrestamoNegocio;

import entidades.Prestamo;
import entidades.Cuota;

import negocio.CuotaNegocio;
import negocioImpl.CuotaNegocioImpl;

public class PrestamoNegocioImpl implements PrestamoNegocio {
	private PrestamoDao prestamoDao = new PrestamoDaoImpl();
	private CuotaNegocio cuotaNegocio = new CuotaNegocioImpl();

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
	public boolean pagarCuota(int idCuota, String fechaPago) {
		return prestamoDao.pagarCuota(idCuota, fechaPago);
	}

	@Override
	public boolean registrarSolicitudPrestamo(Prestamo prestamo) {
		if (prestamo == null || prestamo.getCliente() == null || prestamo.getCuenta() == null) {
			return false;
		}

		// Calcular importe_por_cuota redondeado
		double importePorCuota = Math.round((prestamo.getImportePedido() / prestamo.getCantidadCuotas()) * 100.0)
				/ 100.0;
		prestamo.setImportePorCuota(importePorCuota);

		// Insertar el préstamo
		int idPrestamoGenerado = prestamoDao.insertarPrestamo(prestamo);
		if (idPrestamoGenerado <= 0) {
			return false;
		}

		// Insertar cuotas usando CuotaNegocio
		for (int i = 1; i <= prestamo.getCantidadCuotas(); i++) {
			Cuota cuota = new Cuota();
			cuota.setNroCuota(i);
			cuota.setMonto(importePorCuota);

			Prestamo pRef = new Prestamo();
			pRef.setId(idPrestamoGenerado);
			cuota.setPrestamo(pRef);

			cuotaNegocio.insertarCuota(cuota);
		}

		return true;
	}
}
