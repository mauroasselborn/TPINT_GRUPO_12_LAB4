package negocioimpl;

import java.util.List;
import dao.LocalidadDao;
import daoimpl.LocalidadDaoImpl;
import entidades.Localidad;
import negocio.LocalidadNegocio;

public class LocalidadNegocioImpl implements LocalidadNegocio {

	private LocalidadDao localidadDao = new LocalidadDaoImpl();

	@Override
	public List<Localidad> obtenerTodos() {
		return localidadDao.obtenerTodos();
	}
	
	@Override
    public List<Localidad> obtenerTodos(int idProvincia) {
        return localidadDao.obtenerTodos(idProvincia);
    }
	@Override
	public Localidad obtenerPorId(int idLocalidad) {
		return localidadDao.obtenerPorId(idLocalidad);
	}
}
