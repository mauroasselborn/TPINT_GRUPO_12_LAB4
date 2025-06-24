package dao;

import entidades.Usuario;

public interface UsuarioDao {
    public Usuario obtenerUsuario(String nombreUsuario, String contrasena);
}