package negocioImpl;

import java.util.List;
import dao.ProvinciaDao;
import daoImpl.ProvinciaDaoImpl;
import entidades.Provincia;
import negocio.ProvinciaNegocio;

public class ProvinciaNegocioImpl implements ProvinciaNegocio {

    private ProvinciaDao provinciaDao = new ProvinciaDaoImpl();

    @Override
    public List<Provincia> obtenerTodos() {
        return provinciaDao.obtenerTodos();
    }

    @Override
    public Provincia obtenerPorId(int id) {
        return provinciaDao.obtenerPorId(id);
    }
}
