package dao;

import entidades.Cuenta;
import java.util.List;

public interface DaoCuentas {
    List<Cuenta> obtenerTodas();             // Listar todas las cuentas activas
    Cuenta obtenerPorId(int id);             // Obtener cuenta por ID
    boolean alta(Cuenta cuenta);             // Crear nueva cuenta
    boolean modificar(Cuenta cuenta);        // Modificar datos de una cuenta
    boolean baja(int id);                    // Baja lógica de una cuenta
}
