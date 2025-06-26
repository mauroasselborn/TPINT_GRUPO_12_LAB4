package negocio;

import java.util.List;
import entidades.Provincia;

public interface ProvinciaNegocio {
	public List<Provincia> obtenerTodos();

	public Provincia obtenerPorId(int id);
}
