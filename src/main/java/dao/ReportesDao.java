package dao;

import java.util.Map;

public interface ReportesDao {
    Map<String, Integer> obtenerCantidadCuentasPorTipo();

    Map<String, Integer> obtenerCantidadPrestamosPorEstado(java.sql.Date desde, java.sql.Date hasta);

    Map<String, Integer> obtenerCantidadClientesPorProvincia();
}
