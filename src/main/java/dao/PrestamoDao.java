package dao;

import java.util.List;
import entidades.Prestamo;
import entidades.Cuota;

public interface PrestamoDao {
	List<Prestamo> obtenerPrestamosPorCliente(int idCliente);

	Prestamo obtenerPrestamoPorId(int idPrestamo);

	List<Cuota> obtenerCuotasPorPrestamo(int idPrestamo);

	boolean pagarCuota(int idCuota, String fechaPago);
}
