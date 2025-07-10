package negocio;

import java.sql.Date;
import java.util.Map;

public interface ReportesNegocio {
    Map<String, Integer> obtenerCantidadCuentasPorTipo();
    
    Map<String, Integer> obtenerCantidadClientesPorProvincia();

	Map<String, Integer> obtenerCantidadPrestamosPorEstado(java.sql.Date desde, java.sql.Date hasta);


}
