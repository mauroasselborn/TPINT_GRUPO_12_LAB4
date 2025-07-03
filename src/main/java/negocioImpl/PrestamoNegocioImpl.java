package negocioImpl;

import java.util.List;
import dao.PrestamoDao;
import daoImpl.PrestamoDaoImpl;
import entidades.Prestamo;
import entidades.Cuota;
import negocio.PrestamoNegocio;

public class PrestamoNegocioImpl implements PrestamoNegocio {
	private PrestamoDao prestamoDao = new PrestamoDaoImpl();

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
}
