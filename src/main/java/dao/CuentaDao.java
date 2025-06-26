package dao;

import entidades.Cuenta;
import java.util.List;

public interface CuentaDao {

	public boolean alta(Cuenta cuenta);

	public boolean baja(int id);

	public boolean modificar(Cuenta cuenta);

	public List<Cuenta> obtenerTodas();

	public Cuenta obtenerPorId(int id);

}
