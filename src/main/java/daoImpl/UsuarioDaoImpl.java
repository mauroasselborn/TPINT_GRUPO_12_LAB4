package daoImpl;

import java.sql.*;
import dao.UsuarioDao;
import entidades.Usuario;
import entidades.TipoUsuario;
import datos.Conexion;

public class UsuarioDaoImpl implements UsuarioDao {

	@Override
	public Usuario obtenerUsuario(String nombreUsuario, String contrasena) {
	    Usuario usuario = null;
	    Connection conexion = null;
	    PreparedStatement stmt = null;
	    ResultSet rs = null;

	    try {
	        conexion = Conexion.getConexion();
	        System.out.println("[DAO] Obteniendo usuario con: " + nombreUsuario + " / " + contrasena);

	        String query = "SELECT * FROM usuarios WHERE nombre_usuario = ? AND contrasena = ? AND activo = 1";

	        stmt = conexion.prepareStatement(query);
	        stmt.setString(1, nombreUsuario);
	        stmt.setString(2, contrasena);

	        rs = stmt.executeQuery();

	        if (rs.next()) {
	            usuario = new Usuario();
	            usuario.setId(rs.getInt("id"));
	            usuario.setNombreUsuario(rs.getString("nombre_usuario"));
	            usuario.setContrasena(rs.getString("contrasena"));
	            usuario.setActivo(rs.getBoolean("activo"));

	           
	            TipoUsuario tipo = new TipoUsuario();
	            tipo.setId(rs.getInt("id_tipo_usuario"));
	            tipo.setDescripcion(""); 
	            usuario.setTipoUsuario(tipo);
	        }
	    } catch (Exception e) {
	        System.out.println("[DAO] Error al obtener usuario: " + e.getMessage());
	        e.printStackTrace();
	    }

	    return usuario;
	}
}