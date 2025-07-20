package dao;

import java.util.List;
import entidades.Prestamo;
import entidades.Cuota;

public interface PrestamoDao {

	List<Prestamo> obtenerPrestamosPorCliente(int idCliente);

	List<Prestamo> obtenerTodosLosPrestamos();

	Prestamo obtenerPrestamoPorId(int idPrestamo);

	List<Cuota> obtenerCuotasPorPrestamo(int idPrestamo);

	int insertarPrestamo(Prestamo prestamo);

	boolean cambiarEstadoPrestamo(int idPrestamo, int nuevoEstado);

	boolean modificarPrestamo(Prestamo prestamo);

}
