package dao;

import java.util.List;
import entidades.Nacionalidad;

public interface NacionalidadDao {
    public List<Nacionalidad> obtenerTodos();
    public Nacionalidad obtenerPorId(int idNacionalidad);
}
