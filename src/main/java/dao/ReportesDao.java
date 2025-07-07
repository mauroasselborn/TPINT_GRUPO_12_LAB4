package dao;

import java.util.List;
import java.util.Map;
import entidades.Usuario;

public interface ReportesDao {
    Map<String, Integer> obtenerCantidadCuentasPorTipo();
    Map<String, Integer> obtenerCantidadPrestamosPorEstado();

}
