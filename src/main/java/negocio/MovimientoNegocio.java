package negocio;

import java.util.List;

import entidades.Movimiento;

public interface MovimientoNegocio {
    public boolean insertarMovimiento(Movimiento mov);
    
    List<Movimiento> obtenerMovimientosPorCuenta(int idCuenta);
}