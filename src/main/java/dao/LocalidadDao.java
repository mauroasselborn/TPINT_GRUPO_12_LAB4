package dao;

import java.util.List;
import entidades.Localidad;

public interface LocalidadDao {
	public List<Localidad> obtenerTodos();
	public List<Localidad> obtenerTodos(int idProvincia); // con filtro por provincia
	public Localidad obtenerPorId(int idLocalidad);
}
