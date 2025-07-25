package negocio;

import java.util.List;
import entidades.Cuota;

public interface CuotaNegocio {
	boolean insertarCuota(Cuota cuota);

	List<Cuota> obtenerCuotasPorPrestamo(int idPrestamo);

	String pagarCuota(int idPrestamo, int idCuota, String fechaPago);

	Cuota obtenerProximaCuotaPorPrestamo(int idPrestamo);

	int contarCuotasPendientes(int idPrestamo);
	
	Cuota obtenerCuotaPorId(int idCuota);
}
