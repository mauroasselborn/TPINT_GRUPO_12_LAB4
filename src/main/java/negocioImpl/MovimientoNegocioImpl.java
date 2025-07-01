package negocioImpl;

import dao.MovimientoDao;
import daoImpl.MovimientoDaoImpl;
import entidades.Movimiento;
import negocio.MovimientoNegocio;

public class MovimientoNegocioImpl implements MovimientoNegocio {

    private MovimientoDao movimientoDAO = new MovimientoDaoImpl();

    @Override
    public boolean insertarMovimiento(Movimiento mov) {
        return movimientoDAO.insertarMovimiento(mov);
    }
}
