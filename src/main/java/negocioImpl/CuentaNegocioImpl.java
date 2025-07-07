package negocioImpl;

import dao.CuentaDao;
import daoImpl.CuentaDaoImpl;
import entidades.Cliente;
import entidades.Cuenta;
import negocio.CuentaNegocio;
import java.util.List;

public class CuentaNegocioImpl implements CuentaNegocio {
	private CuentaDao dao = new CuentaDaoImpl();

	@Override
	public List<Cuenta> listarCuentas() {
		return dao.obtenerTodas();
	}

	@Override
	public Cuenta obtenerCuenta(int id) throws Exception {
		Cuenta cuenta = dao.obtenerPorId(id);
		if (cuenta == null) {
			throw new Exception("Cuenta no encontrada.");
		}
		return cuenta;
	}

	@Override
	public void crearCuenta(Cuenta cuenta) throws Exception {
		// Límite máximo de 3 cuentas activas por cliente
		int activoCount = contarCuentasActivas(cuenta.getCliente());
		if (activoCount >= 3) {
			throw new Exception("El cliente ya tiene " + activoCount + " cuentas activas.");
		}

		// Validar CBU único y número de cuenta único
		
		List<Cuenta> todas = dao.obtenerTodas();
		for (Cuenta cuentarep : todas) {
			if (cuentarep.getCbu().equals(cuentarep.getCbu())) {
				generarCBUUnico();
			}
		}
		for (Cuenta cuentarep : todas) {
			if (cuentarep.getNumeroCuenta().equals(cuenta.getNumeroCuenta())) {
				generarNumeroCuentaUnico();
			}
		}
		
		// Alta
		if (!dao.alta(cuenta)) {
			throw new Exception("Ocurrió un error al crear la cuenta.");
		}
	}

	@Override
	public void modificarCuenta(Cuenta cuenta) throws Exception {
		// Validar saldo no negativo
		if (cuenta.getSaldo() < 0) {
			throw new Exception("El saldo no puede ser negativo.");
		}

		if (!dao.modificar(cuenta)) {
			throw new Exception("Ocurrió un error al modificar la cuenta.");
		}
	}

	@Override
	public void eliminarCuenta(int id) throws Exception {
		if (!dao.baja(id)) {
			throw new Exception("Ocurrió un error al eliminar la cuenta.");
		}
	}

	// Contar cuentas activas por ID de cliente
	private int contarCuentasActivas(Cliente cliente) {
		int count = 0;
		for (Cuenta cuenta : dao.obtenerTodas()) {
			if (cuenta.getCliente().getId() == cliente.getId()) {
				count++;
			}
		}
		return count;
	}
	 public  String generarNumeroCuentaUnico() {
	        String numeroCuenta;
	        do {
	            numeroCuenta = String.valueOf((int)(Math.random() * 90000000) + 10000000); // 8 dígitos
	        } while (dao.existeNumeroCuenta(numeroCuenta));

	        return numeroCuenta;
	    }

	    public  String generarCBUUnico() {
	        String cbu;

	        do {
	            StringBuilder sb = new StringBuilder();
	            for (int i = 0; i < 22; i++) {
	                sb.append((int)(Math.random() * 10)); // Genera número aleatorio del 0 al 9
	            }
	            cbu = sb.toString();
	        } while (dao.existeCBU(cbu));

	        return cbu;
	    }
	    
	    @Override
	    public void descontarSaldo(int idCuenta, double monto) throws Exception {
	        if (!dao.descontarSaldo(idCuenta, monto)) {
	            throw new Exception("No se pudo descontar el saldo. Verifique fondos suficientes.");
	        }
	    }

	    @Override
	    public void aumentarSaldo(int idCuenta, double monto) throws Exception {
	        if (!dao.aumentarSaldo(idCuenta, monto)) {
	            throw new Exception("No se pudo aumentar el saldo.");
	        }
	    }
	    
	    @Override
	    public List<Cuenta> obtenerCuentasPorCliente(int idCliente) {
	        return dao.obtenerPorCliente(idCliente);
	    }
	    

}
