package negocio;

import java.util.List;
import entidades.Localidad;

public interface LocalidadNegocio {
	public List<Localidad> obtenerTodos();

	public Localidad obtenerPorId(int idLocalidad);
}
