package negocioImpl;

import java.util.ArrayList;

import dao.ProvinciaDao;
import daoImpl.ProvinciaDaoImpl;
import entidades.Provincia;
import negocio.ProvinciaNegocio;

public class ProvinciaNegocioImpl implements ProvinciaNegocio {

	private ProvinciaDao provinciaDao;
	
	public ProvinciaNegocioImpl() {
		this.provinciaDao = new ProvinciaDaoImpl();
	}
	
	@Override
	public ArrayList<Provincia> listarProvincias(){
			ArrayList<Provincia> listaProvincias = provinciaDao.listarProvincias();
			return listaProvincias;
	}
	
	@Override
	public Provincia obtenerProvinciaPorId(int id) {
			Provincia provincia = provinciaDao.obtenerProvinciaPorId(id);
			return provincia;
	}
}
