package negocioimpl;

import java.util.List;

import dao.MovimientoDao;
import daoimpl.MovimientoDaoImpl;
import entidades.Movimiento;
import negocio.MovimientoNegocio;

public class MovimientoNegocioImpl implements MovimientoNegocio {

    private MovimientoDao movimientoDAO = new MovimientoDaoImpl();

    @Override
    public boolean insertarMovimiento(Movimiento mov) {
        return movimientoDAO.insertarMovimiento(mov);
    }
    
    @Override
    public List<Movimiento> obtenerMovimientosPorCuenta(int idCuenta) {
        MovimientoDao dao = new MovimientoDaoImpl();
        return dao.obtenerPorCuenta(idCuenta);
    }
}
