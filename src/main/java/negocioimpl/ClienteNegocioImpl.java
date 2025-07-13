package negocioimpl;

import java.time.LocalDate;
import java.util.List;
import dao.ClienteDao;
import daoimpl.ClienteDaoImpl;
import entidades.Cliente;
import entidades.Usuario;
import excepciones.ClienteRepetidoException;
import excepciones.FechaNoValidaException;
import negocio.ClienteNegocio;
import negocio.UsuarioNegocio;
import negocioimpl.UsuarioNegocioImpl;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class ClienteNegocioImpl implements ClienteNegocio {

	private ClienteDao clienteDao = new ClienteDaoImpl();
	UsuarioNegocio usuarioNegocio = new UsuarioNegocioImpl();

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

	@Override
	public boolean registrarClienteConUsuario(Cliente cliente, Usuario usuario) throws Exception {

		// Valido que llegue Cliente
		if (cliente == null || cliente.getDni() == null || cliente.getDni().isEmpty()) {
			throw new Exception("El DNI del cliente es obligatorio.");
		}

		// Valido si ya existe el cliente en la db
		Cliente existente = clienteDao.obtenerPorDni(cliente.getDni());
		if (existente != null) {
			throw new ClienteRepetidoException("El cliente con DNI " + cliente.getDni() + " ya existe.");
		}

		// Valido el Usuario
		if (usuario == null || usuario.getNombreUsuario() == null || usuario.getNombreUsuario().isEmpty()) {
			throw new Exception("El nombre de usuario es obligatorio.");
		}

		// validacion para evitar duplicados de usuarios
		Usuario existenteUsuario = usuarioNegocio.obtenerUsuarioPorNombre(usuario.getNombreUsuario());
		if (existenteUsuario != null) {
			throw new Exception("El nombre de usuario ya existe. Por favor elija otro.");
		}

		// Inserto el cliente
		boolean clienteInsertado = clienteDao.alta(cliente);
		if (!clienteInsertado) {
			throw new Exception("No se pudo insertar el cliente.");
		}

		// Recupero el cliente recien insertado
		Cliente clienteRecienInsertado = clienteDao.obtenerPorDni(cliente.getDni());
		if (clienteRecienInsertado == null) {
			throw new Exception("Error al obtener el cliente insertado.");
		}

		usuario.setCliente(clienteRecienInsertado);

		// Inserto Usuario
		boolean usuarioInsertado = usuarioNegocio.insertarUsuario(usuario);
		if (!usuarioInsertado) {
			throw new Exception("Cliente creado, pero no se pudo insertar el usuario.");
		}

		return true;
	}

}
