package negocioImpl;

import java.time.LocalDate;
import java.util.List;
import dao.ClienteDao;
import daoImpl.ClienteDaoImpl;
import entidades.Cliente;
import excepciones.ClienteRepetidoException;
import excepciones.FechaNoValidaException;
import negocio.ClienteNegocio;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

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
	public boolean insertar(Cliente cliente) throws Exception {

		Cliente existente = clienteDao.obtenerPorDni(cliente.getDni());

		if (existente != null) {
			throw new ClienteRepetidoException("El cliente con DNI " + cliente.getDni() + " ya existe en la base de datos.");
		}

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate fechaNacimiento;
		
		try {
			fechaNacimiento = LocalDate.parse(cliente.getFechaNacimiento(), formatter);
		} catch (DateTimeParseException e) {
			throw new FechaNoValidaException();
		}

		if (fechaNacimiento.isAfter(LocalDate.now())) {
			throw new FechaNoValidaException();
		}

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
