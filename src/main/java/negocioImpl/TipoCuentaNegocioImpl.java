package negocioImpl;

import java.util.List;
import dao.TipoCuentaDao;
import daoImpl.TipoCuentaDaoImpl;
import entidades.TipoCuenta;
import negocio.TipoCuentaNegocio;


public class TipoCuentaNegocioImpl implements TipoCuentaNegocio {

	private TipoCuentaDao tipocuentaDao = new TipoCuentaDaoImpl();

	@Override
	public List<TipoCuenta> obtenerTodos() {
		return tipocuentaDao.obtenerTodos();
	}

	@Override
	public TipoCuenta obtenerPorId(int idtipocuenta) {
		return tipocuentaDao.obtenerPorId(idtipocuenta);
	}
}
