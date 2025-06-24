package negocio;

import java.util.ArrayList;

import entidades.Localidad;

public interface LocalidadNegocio {

	ArrayList<Localidad> listarLocalidades();
	public Localidad obtenerLocalidadPorId(int id);
}
