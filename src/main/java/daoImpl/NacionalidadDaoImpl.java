package daoImpl;

import dao.NacionalidadDao;
import datos.Conexion;
import entidades.Nacionalidad;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NacionalidadDaoImpl implements NacionalidadDao {

    @Override
    public List<Nacionalidad> obtenerTodos() {
        List<Nacionalidad> listaNacionalidades = new ArrayList<>();
        String consultaSQL = "SELECT id, descripcion FROM nacionalidades ORDER BY descripcion";
        Connection conexion = null;
        PreparedStatement statement = null;
        ResultSet resultado = null;

        try {
            conexion = Conexion.getConexion();
            statement = conexion.prepareStatement(consultaSQL);
            resultado = statement.executeQuery();

            while (resultado.next()) {
                Nacionalidad nacionalidad = new Nacionalidad();
                nacionalidad.setId(resultado.getInt("id"));
                nacionalidad.setDescripcion(resultado.getString("descripcion"));
                listaNacionalidades.add(nacionalidad);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try { if (resultado != null) resultado.close(); } catch (Exception e) {}
            try { if (statement != null) statement.close(); } catch (Exception e) {}
            try { if (conexion != null) conexion.close(); } catch (Exception e) {}
        }

        return listaNacionalidades;
    }

    @Override
    public Nacionalidad obtenerPorId(int idNacionalidad) {
        Nacionalidad nacionalidad = null;
        String consultaSQL = "SELECT id, descripcion FROM nacionalidades WHERE id = ?";
        Connection conexion = null;
        PreparedStatement statement = null;
        ResultSet resultado = null;

        try {
            conexion = Conexion.getConexion();
            statement = conexion.prepareStatement(consultaSQL);
            statement.setInt(1, idNacionalidad);
            resultado = statement.executeQuery();

            if (resultado.next()) {
                nacionalidad = new Nacionalidad();
                nacionalidad.setId(resultado.getInt("id"));
                nacionalidad.setDescripcion(resultado.getString("descripcion"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try { if (resultado != null) resultado.close(); } catch (Exception e) {}
            try { if (statement != null) statement.close(); } catch (Exception e) {}
            try { if (conexion != null) conexion.close(); } catch (Exception e) {}
        }

        return nacionalidad;
    }
}
