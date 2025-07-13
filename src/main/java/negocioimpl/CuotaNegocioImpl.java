package negocioimpl;

import java.util.List;
import dao.CuotaDao;
import daoimpl.CuotaDaoImpl;
import entidades.Cuota;
import negocio.CuotaNegocio;

public class CuotaNegocioImpl implements CuotaNegocio {
    private CuotaDao cuotaDao = new CuotaDaoImpl();

    @Override
    public boolean insertarCuota(Cuota cuota) {
        return cuotaDao.insertarCuota(cuota);
    }

    @Override
    public List<Cuota> obtenerCuotasPorPrestamo(int idPrestamo) {
        return cuotaDao.obtenerCuotasPorPrestamo(idPrestamo);
    }

    @Override
    public boolean pagarCuota(int idCuota, String fechaPago) {
        return cuotaDao.pagarCuota(idCuota, fechaPago);
    }
}
