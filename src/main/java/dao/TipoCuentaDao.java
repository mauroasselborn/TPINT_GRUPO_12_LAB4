package dao;

import java.util.List;

import entidades.TipoCuenta;

public interface TipoCuentaDao {
	public List<TipoCuenta> obtenerTodos();

	public TipoCuenta obtenerPorId(int idtipocuenta);

}
