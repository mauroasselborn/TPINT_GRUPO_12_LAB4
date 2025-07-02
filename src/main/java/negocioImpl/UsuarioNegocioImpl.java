package negocioImpl;

import java.util.List;

import dao.UsuarioDao;
import daoImpl.UsuarioDaoImpl;
import entidades.Usuario;
import negocio.UsuarioNegocio;

public class UsuarioNegocioImpl implements UsuarioNegocio {

	private UsuarioDao usuarioDao = new UsuarioDaoImpl();

	@Override
	public boolean eliminarUsuario(int idUsuario) {
		return usuarioDao.eliminarUsuario(idUsuario);
	}

	@Override
	public boolean activarUsuario(int idUsuario) {
		return usuarioDao.activarUsuario(idUsuario);
	}

	@Override
	public Usuario obtenerUsuarioPorId(int idUsuario) {
		return usuarioDao.obtenerUsuarioPorId(idUsuario);
	}

	@Override
	public List<Usuario> obtenerTodosLosUsuarios() {
		return usuarioDao.obtenerTodosLosUsuarios();
	}

	@Override
	public boolean validarCredenciales(String nombreUsuario, String contrasenia) {
		return usuarioDao.validarCredenciales(nombreUsuario, contrasenia);
	}

	@Override
	public Usuario obtenerUsuarioPorIdCliente(int idCliente) {
		return usuarioDao.obtenerUsuarioPorIdCliente(idCliente);
	}

	@Override
	public boolean eliminarPorIdCliente(int idCliente) {
		return usuarioDao.bajaPorIdCLiente(idCliente);
	}

	@Override
	public Usuario obtenerUsuarioPorNombre(String nombreUsuario) {
		return usuarioDao.obtenerUsuarioPorNombre(nombreUsuario);
	}

	@Override
	public boolean insertarUsuario(Usuario usuario) {
		return usuarioDao.insertarUsuario(usuario);
	}

	@Override
	public List<Usuario> obtenerTodosLosUsuariosAdmin() {
		return usuarioDao.obtenerTodosLosUsuariosAdmin();
	}

	@Override
	public boolean activarUsuarioPorIdCliente(int idCliente) {
		return usuarioDao.activarUsuarioPorIdCliente(idCliente);
	}

}
