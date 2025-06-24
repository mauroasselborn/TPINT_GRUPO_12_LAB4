package negocioImpl;

import java.util.ArrayList;

import dao.NacionalidadDao;
import daoImpl.NacionalidadDaoImpl;
import entidades.Nacionalidad;
import negocio.NacionalidadNegocio;

public class NacionalidadNegocioImpl implements NacionalidadNegocio {
	private NacionalidadDao nacionalidadDao;
	
	public NacionalidadNegocioImpl() {
		this.nacionalidadDao = new NacionalidadDaoImpl();
	}
	
	@Override
	public ArrayList<Nacionalidad> listarNacionalidades(){
			ArrayList<Nacionalidad> listaNacionalidades = nacionalidadDao.listarNacionalidades();
			return listaNacionalidades;
	}
	
	
	@Override
	public Nacionalidad obtenerNacionalidadPorId(int id) {
		Nacionalidad nacionalidad=nacionalidadDao.obtenerNacionalidadPorId(id);
		return nacionalidad;
	}
}
