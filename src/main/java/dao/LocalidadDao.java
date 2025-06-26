package dao;

import java.util.List;
import entidades.Localidad;

public interface LocalidadDao {
	public List<Localidad> obtenerTodos();

	public Localidad obtenerPorId(int idLocalidad);
}
