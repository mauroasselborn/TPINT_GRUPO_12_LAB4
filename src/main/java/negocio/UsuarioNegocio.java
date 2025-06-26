package negocio;

import java.util.List;
import entidades.Usuario;

public interface UsuarioNegocio {
	// public boolean modificarUsuario(Usuario usuario);
	public boolean insertarUsuario(Usuario usuario);

	public boolean eliminarUsuario(int idUsuario); // baja lógica

	public boolean activarUsuario(int idUsuario); // alta lógica

	public Usuario obtenerUsuarioPorId(int idUsuario);

	public List<Usuario> obtenerTodosLosUsuarios();

	public boolean validarCredenciales(String nombreUsuario, String contrasenia);

	public Usuario obtenerUsuarioPorIdCliente(int idCliente);

	public Usuario obtenerUsuarioPorNombre(String nombreUsuario);
}
