
package negocioImpl;

import negocio.ClienteNegocio;
import dao.ClienteDao;
import daoImpl.ClienteDaoImpl;
import entidades.Cliente;

import java.util.List;

public class ClienteNegocioImpl implements ClienteNegocio {

	private final ClienteDao clienteDao = new ClienteDaoImpl();

	@Override
	public List<Cliente> listarClientes() {
		return clienteDao.obtenerTodos();
	}
}
