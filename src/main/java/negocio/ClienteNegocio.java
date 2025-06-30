package negocio;

import java.util.List;
import entidades.Cliente;

public interface ClienteNegocio {
	public List<Cliente> obtenerTodos();

	public Cliente obtenerPorId(int id);

	public Cliente obtenerPorDni(String dni);

	public boolean insertar(Cliente cliente) throws Exception;;

	public boolean modificar(Cliente cliente);

	public boolean eliminar(int id);

	public boolean altaLogica(int id);
}
