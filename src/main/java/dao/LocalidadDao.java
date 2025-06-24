package dao;

import java.util.ArrayList;

import entidades.Localidad;

public interface LocalidadDao {

	public ArrayList<Localidad> listarLocalidades();
	public Localidad obtenerLocalidadPorId (int id);
}
