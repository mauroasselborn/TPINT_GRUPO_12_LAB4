package negocio;

import java.util.List;
import entidades.Cliente;

public interface ClienteNegocio {
	public List<Cliente> obtenerTodos();

	public Cliente obtenerPorId(int id);

	public boolean insertar(Cliente cliente);

	public boolean modificar(Cliente cliente);

	public boolean eliminar(int id);
}
