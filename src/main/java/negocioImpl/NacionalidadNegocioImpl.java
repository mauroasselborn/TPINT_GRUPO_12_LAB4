package negocioImpl;

import java.util.List;
import dao.NacionalidadDao;
import daoImpl.NacionalidadDaoImpl;
import entidades.Nacionalidad;
import negocio.NacionalidadNegocio;

public class NacionalidadNegocioImpl implements NacionalidadNegocio {

    private NacionalidadDao nacionalidadDao = new NacionalidadDaoImpl();

    @Override
    public List<Nacionalidad> obtenerTodos() {
        return nacionalidadDao.obtenerTodos();
    }

    @Override
    public Nacionalidad obtenerPorId(int idNacionalidad) {
        return nacionalidadDao.obtenerPorId(idNacionalidad);
    }
}
