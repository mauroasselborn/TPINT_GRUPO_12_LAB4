package negocio;

import java.util.ArrayList;
import entidades.Provincia;

public interface ProvinciaNegocio {

	ArrayList<Provincia> listarProvincias();
	public Provincia obtenerProvinciaPorId(int id);
}
