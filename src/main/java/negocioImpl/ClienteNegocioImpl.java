package negocioImpl;

import java.util.List;
import dao.ClienteDao;
import daoImpl.ClienteDaoImpl;
import entidades.Cliente;
import negocio.ClienteNegocio;

public class ClienteNegocioImpl implements ClienteNegocio {

	private ClienteDao clienteDao = new ClienteDaoImpl();

	@Override
	public List<Cliente> obtenerTodos() {
		return clienteDao.obtenerTodos();
	}

	@Override
	public Cliente obtenerPorId(int id) {
		return clienteDao.obtenerPorId(id);
	}

	@Override
	public boolean insertar(Cliente cliente) {
		return clienteDao.alta(cliente);
	}

	@Override
	public boolean modificar(Cliente cliente) {
		return clienteDao.modificar(cliente);
	}

	@Override
	public boolean eliminar(int id) {
		return clienteDao.baja(id);
	}

	@Override
	public boolean altaLogica(int id) {
		return clienteDao.altaLogica(id);
	}

	@Override
	public Cliente obtenerPorDni(String dni) {
		return clienteDao.obtenerPorDni(dni);
	}

}
