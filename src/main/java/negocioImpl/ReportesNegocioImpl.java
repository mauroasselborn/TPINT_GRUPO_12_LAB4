package negocioImpl;

import dao.ReportesDao;
import daoImpl.ReportesDaoImpl;
import negocio.ReportesNegocio;
import java.util.Map;

public class ReportesNegocioImpl implements ReportesNegocio {

    private ReportesDao reportesDao = new ReportesDaoImpl();

    	@Override
    	public Map<String, Integer> obtenerCantidadCuentasPorTipo() {
        return reportesDao.obtenerCantidadCuentasPorTipo();
    	}
    	
    	
		@Override
		public Map<String, Integer> obtenerCantidadPrestamosPorEstado() {
		return reportesDao.obtenerCantidadPrestamosPorEstado(); 
        }   
        
        
    }
