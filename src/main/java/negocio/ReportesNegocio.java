package negocio;

import java.util.Map;

public interface ReportesNegocio {
    Map<String, Integer> obtenerCantidadCuentasPorTipo(java.sql.Date desde, java.sql.Date hasta);
    
    Map<String, Integer> obtenerCantidadClientesPorProvincia();

	Map<String, Integer> obtenerCantidadPrestamosPorEstado(java.sql.Date desde, java.sql.Date hasta);

}
