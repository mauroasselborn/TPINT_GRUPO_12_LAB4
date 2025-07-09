package negocio;

import java.util.List;
import entidades.Cuota;

public interface CuotaNegocio {
    boolean insertarCuota(Cuota cuota);
    List<Cuota> obtenerCuotasPorPrestamo(int idPrestamo);
    boolean pagarCuota(int idCuota, String fechaPago);
}
