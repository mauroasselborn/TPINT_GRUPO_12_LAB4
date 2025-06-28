package dao;

import java.util.List;
import entidades.Usuario;

public interface UsuarioDao {

	public boolean insertarUsuario(Usuario usuario);

	public boolean eliminarUsuario(int idUsuario); // baja lógica

	public boolean activarUsuario(int idUsuario); // alta lógica

	public Usuario obtenerUsuarioPorId(int idUsuario);

	public List<Usuario> obtenerTodosLosUsuarios();

	public List<Usuario> obtenerTodosLosUsuariosAdmin();

	public boolean validarCredenciales(String nombreUsuario, String contrasenia);

	Usuario obtenerUsuarioPorIdCliente(int idCliente);

	Usuario obtenerUsuarioPorNombre(String nombreUsuario);
}
