package dao;

import java.util.List;
import entidades.Cuota;

public interface CuotaDao {
	boolean insertarCuota(Cuota cuota);

	List<Cuota> obtenerCuotasPorPrestamo(int idPrestamo);

	boolean pagarCuota(int idCuota, String fechaPago);

	Cuota obtenerProximaCuotaPorPrestamo(int idPrestamo);

	int contarCuotasPendientes(int idPrestamo);
	
	Cuota obtenerCuotaPorId(int idCuota);

}
