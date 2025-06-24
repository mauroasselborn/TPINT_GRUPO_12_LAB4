package negocioImpl;

import dao.DaoCuentas;
import dao.ImpDaoCuentas;
import entidades.Cliente;
import entidades.Cuenta;
import negocio.NegocioCuentas;
import java.math.BigDecimal;
import java.util.List;

public class ImpNegocioCuentas implements NegocioCuentas {
    private DaoCuentas dao = new ImpDaoCuentas();

    @Override
    public List<Cuenta> listarCuentas() {
        return dao.obtenerTodas();
    }

    @Override
    public Cuenta obtenerCuenta(int id) throws Exception {
        Cuenta c = dao.obtenerPorId(id);
        if (c == null) {
            throw new Exception("Cuenta no encontrada");
        }
        return c;
    }

    @Override
    public void crearCuenta(Cuenta cuenta) throws Exception {
        // Límite máximo de 3 cuentas activas por cliente
        int activoCount = contarCuentasActivas(cuenta.getCliente());
        if (activoCount >= 3) {
            throw new Exception("El cliente ya tiene " + activoCount + " cuentas activas.");
        }
        // Validar CBU único
        for (Cuenta c : dao.obtenerTodas()) {
            if (c.getCbu().equals(cuenta.getCbu())) {
                throw new Exception("El CBU ya está registrado para otra cuenta.");
            }
        }
        // Validar número de cuenta único
        for (Cuenta c : dao.obtenerTodas()) {
            if (c.getNumeroCuenta().equals(cuenta.getNumeroCuenta())) {
                throw new Exception("El número de cuenta ya existe.");
            }
        }
        // Asignar saldo inicial fijo de $10 000
        cuenta.setSaldo(10000);
        // Persistir alta
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

    // Helper interno
    private int contarCuentasActivas(Cliente cliente) {
        int count = 0;
        for (Cuenta c : dao.obtenerTodas()) {
            if (c.getCliente() == cliente) {
                count++;
            }
        }
        return count;
    }
}
