package dao;

import entidades.Cuenta;
import java.util.List;

public interface CuentaDao {

	public boolean alta(Cuenta cuenta);

	public boolean baja(int id);

	public boolean modificar(Cuenta cuenta);

	public List<Cuenta> obtenerTodas();

	public Cuenta obtenerPorId(int id);

	public boolean existeNumeroCuenta(String numeroCuenta);

	public boolean existeCBU(String cbu);

	public boolean descontarSaldo(int idCuenta, double monto);

	public boolean aumentarSaldo(int idCuenta, double monto);

	List<Cuenta> obtenerPorCliente(int idCliente);

}
