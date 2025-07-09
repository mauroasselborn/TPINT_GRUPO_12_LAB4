package dao;

import java.util.List;
import entidades.Cuota;

public interface CuotaDao {
    boolean insertarCuota(Cuota cuota);
    List<Cuota> obtenerCuotasPorPrestamo(int idPrestamo);
    boolean pagarCuota(int idCuota, String fechaPago);
}
