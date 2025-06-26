package negocio;

import java.util.List;
import entidades.Nacionalidad;

public interface NacionalidadNegocio {
	public List<Nacionalidad> obtenerTodos();

	public Nacionalidad obtenerPorId(int idNacionalidad);
}
