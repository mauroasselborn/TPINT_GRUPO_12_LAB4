package negocioImpl;

import java.util.ArrayList;

import dao.LocalidadDao;
import daoImpl.LocalidadDaoImpl;
import entidades.Localidad;
import negocio.LocalidadNegocio;

public class LocalidadNegocioImpl implements LocalidadNegocio {

	private LocalidadDao localidadDao;
	
	public LocalidadNegocioImpl() {
		this.localidadDao = new LocalidadDaoImpl();
	}
	
	@Override 
	public ArrayList<Localidad> listarLocalidades(){
			ArrayList<Localidad> listaLocalidades = localidadDao.listarLocalidades();
			return listaLocalidades;
	}
	
	@Override
	public Localidad obtenerLocalidadPorId(int id) {
			Localidad localidad =  localidadDao.obtenerLocalidadPorId(id);
			return localidad;
	}
}
