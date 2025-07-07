package negocio;

import java.util.Map;

public interface ReportesNegocio {
    Map<String, Integer> obtenerCantidadCuentasPorTipo();
    
    Map<String, Integer> obtenerCantidadPrestamosPorEstado();

}
