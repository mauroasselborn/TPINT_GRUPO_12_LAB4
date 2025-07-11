package dao;

import java.util.List;
import entidades.Usuario;

public interface UsuarioDao {
	boolean insertarUsuario(Usuario usuario);

	boolean eliminarUsuario(int idUsuario);

	boolean bajaPorIdCLiente(int idCliente);

	boolean activarUsuario(int idUsuario);

	boolean activarUsuarioPorIdCliente(int idCliente);

	Usuario obtenerUsuarioPorId(int idUsuario);

	Usuario obtenerUsuarioPorIdCliente(int idCliente);

	Usuario obtenerUsuarioPorNombre(String nombreUsuario);

	List<Usuario> obtenerTodosLosUsuarios();

	List<Usuario> obtenerTodosLosUsuariosAdmin();

	boolean validarCredenciales(String nombreUsuario, String contrasenia);
}
