package dao;

import entidades.Cliente;
import java.util.List;

public interface ClienteDao {

	public boolean alta(Cliente cliente) throws Exception;;

	public boolean baja(int id);

	public boolean altaLogica(int id);

	public boolean modificar(Cliente cliente);

	public List<Cliente> obtenerTodos();

	public Cliente obtenerPorId(int id);

	public Cliente obtenerPorDni(String dni);

}