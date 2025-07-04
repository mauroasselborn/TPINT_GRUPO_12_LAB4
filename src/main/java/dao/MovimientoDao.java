package dao;
import java.util.List;

import entidades.Movimiento;

public interface MovimientoDao {
	public boolean insertarMovimiento(Movimiento mov);
	
	List<Movimiento> obtenerPorCuenta(int idCuenta);
	
}
