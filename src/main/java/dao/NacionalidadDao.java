package dao;

import java.util.ArrayList;
import entidades.Nacionalidad;

public interface NacionalidadDao {
	public ArrayList<Nacionalidad> listarNacionalidades();
	public Nacionalidad obtenerNacionalidadPorId(int id);
}
