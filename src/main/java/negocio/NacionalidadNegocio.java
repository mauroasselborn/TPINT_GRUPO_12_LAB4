package negocio;

import java.util.ArrayList;
import entidades.Nacionalidad;

public interface NacionalidadNegocio {

	ArrayList<Nacionalidad> listarNacionalidades();
	public Nacionalidad obtenerNacionalidadPorId(int id);
}
