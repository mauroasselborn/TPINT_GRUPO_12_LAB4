package negocio;

import java.util.List;
import entidades.Localidad;

public interface LocalidadNegocio {
	public List<Localidad> obtenerTodos();
	public List<Localidad> obtenerTodos(int idProvincia); // con filtro
	public Localidad obtenerPorId(int idLocalidad);
}
