package dao;

import java.util.List;
import entidades.Provincia;

public interface ProvinciaDao {
	public List<Provincia> obtenerTodos();

	public Provincia obtenerPorId(int id);
}
