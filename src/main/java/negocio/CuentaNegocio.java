package negocio;

import entidades.Cliente;
import entidades.Cuenta;
import java.util.List;

public interface CuentaNegocio {
	List<Cuenta> listarCuentas(); // Listar todas las cuentas activas
	
	public int contarCuentasActivas(Cliente cliente);

	Cuenta obtenerCuenta(int id) throws Exception; // Obtener cuenta por ID

	void crearCuenta(Cuenta cuenta) throws Exception; // Alta de cuenta (saldo inicial $10 000, límite 3, CBU y número únicos)

	String modificarCuenta(Cuenta cuenta) throws Exception;// Modificación (saldo ≥ 0)

	String eliminarCuenta(int id) throws Exception; // Baja lógica (estado = 0)
	
	public  String generarNumeroCuentaUnico(); //Genera nro aleatorio
	
	public  String generarCBUUnico();
	
	public void descontarSaldo(int idCuenta, double monto) throws Exception;
	
    public void aumentarSaldo(int idCuenta, double monto) throws Exception;
    
    List<Cuenta> obtenerCuentasPorCliente(int idCliente);
    
    public String altaLogica(int id) throws Exception;
}
