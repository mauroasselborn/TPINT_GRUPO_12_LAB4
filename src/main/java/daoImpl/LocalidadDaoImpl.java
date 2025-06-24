package daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import dao.LocalidadDao;
import datos.Conexion;
import entidades.Localidad;
import entidades.Provincia;

public class LocalidadDaoImpl implements LocalidadDao {

	@Override
	public ArrayList<Localidad> listarLocalidades(){
		String sql="SELECT id,nombre,id_provincia FROM localidades";
		ArrayList<Localidad> listaLocalidades = new ArrayList<>();
		
		try(Connection con = Conexion.getConexion();
				PreparedStatement ps = con.prepareStatement(sql);
				ResultSet resultSet = ps.executeQuery()){
					
			
				while(resultSet.next()) {
					Localidad localidad = new Localidad();
					localidad.setId(resultSet.getInt("id"));
					localidad.setNombre(resultSet.getString("nombre"));
					
					 Provincia provincia = new Provincia();
					 provincia.setId(resultSet.getInt("id_provincia"));
					 localidad.setProvincia(provincia);
					
					listaLocalidades.add(localidad);
					
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
		return listaLocalidades;
	}
	
	@Override
	public Localidad obtenerLocalidadPorId(int id) {
	    String sql = "SELECT id, nombre, id_provincia FROM localidades WHERE id = ?";
	    Localidad localidad = null;

	    try (Connection con = Conexion.getConexion();
	         PreparedStatement ps = con.prepareStatement(sql)) {

	        ps.setInt(1, id);

	        try (ResultSet resultSet = ps.executeQuery()) {
	            if (resultSet.next()) {
	                localidad = new Localidad();
	                localidad.setId(resultSet.getInt("id"));
	                localidad.setNombre(resultSet.getString("nombre"));

	                Provincia provincia = new Provincia();
	                provincia.setId(resultSet.getInt("id_provincia"));
	                localidad.setProvincia(provincia);
	            }
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return localidad;
	}

}
