package negocio;

import java.util.List;
import entidades.Prestamo;
import entidades.Cuota;

public interface PrestamoNegocio {
	List<Prestamo> obtenerPrestamosPorCliente(int idCliente);

	Prestamo obtenerPrestamoPorId(int idPrestamo);

	List<Cuota> obtenerCuotasPorPrestamo(int idPrestamo);

	boolean pagarCuota(int idCuota, String fechaPago);
}
