package negocioimpl;

import dao.ReportesDao;
import daoimpl.ReportesDaoImpl;
import negocio.ReportesNegocio;

import java.util.Map;

public class ReportesNegocioImpl implements ReportesNegocio {

    private ReportesDao reportesDao = new ReportesDaoImpl();

    	@Override
    	public Map<String, Integer> obtenerCantidadCuentasPorTipo(java.sql.Date desde, java.sql.Date hasta) {
        return reportesDao.obtenerCantidadCuentasPorTipo(desde, hasta);
    	}
    	
    	
    	
    	@Override
    	public Map<String, Integer> obtenerCantidadPrestamosPorEstado(java.sql.Date desde, java.sql.Date hasta) {
    	    return reportesDao.obtenerCantidadPrestamosPorEstado(desde, hasta);
    	}

  
		
		@Override
		public Map<String, Integer> obtenerCantidadClientesPorProvincia() {
		return reportesDao.obtenerCantidadClientesPorProvincia(); 
        }   
        
        
    }
