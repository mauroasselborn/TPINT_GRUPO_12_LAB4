package negocio;

import java.util.List;
import entidades.Prestamo;
import entidades.Cuota;

public interface PrestamoNegocio {
	List<Prestamo> obtenerPrestamosPorCliente(int idCliente);

	List<Prestamo> obtenerTodosLosPrestamos();

	Prestamo obtenerPrestamoPorId(int idPrestamo);

	List<Cuota> obtenerCuotasPorPrestamo(int idPrestamo);

	boolean registrarSolicitudPrestamo(Prestamo prestamo);

	boolean cambiarEstadoPrestamo(int idPrestamo, int nuevoEstado);

	boolean acreditarPrestamo(int idPrestamo);

}
