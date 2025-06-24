package dao;

import entidades.Cuenta;
import java.util.List;

public interface CuentaDao {

	public boolean alta(Cuenta cuenta); // Crear nueva cuenta

	public boolean baja(int id); // Baja lógica de una cuenta

	public boolean modificar(Cuenta cuenta); // Modificar datos de una cuenta

	public List<Cuenta> obtenerTodas(); // Listar todas las cuentas activas

	public Cuenta obtenerPorId(int id); // Obtener cuenta por ID

}
