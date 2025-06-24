package daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import dao.ProvinciaDao;
import datos.Conexion;
import entidades.Provincia;

public class ProvinciaDaoImpl implements ProvinciaDao {
	
	@Override
	public ArrayList<Provincia> listarProvincias(){
		String sql="SELECT id,nombre FROM provincias";
		ArrayList<Provincia> listaProvincia = new ArrayList<>();
		
		try(Connection con = Conexion.getConexion();
				PreparedStatement ps= con.prepareStatement(sql);
				ResultSet resultSet = ps.executeQuery()){
			
			while(resultSet.next()) {
				Provincia provincia = new Provincia();
				
				provincia.setId(resultSet.getInt("id"));
				provincia.setNombre(resultSet.getString("nombre"));
				listaProvincia.add(provincia);
			}
		
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return listaProvincia;
	}
	
	@Override
	public Provincia obtenerProvinciaPorId(int id) {
		String sql="SELECT id,nombre FROM provincias WHERE id=?";
		Provincia provincia=null;
		
		try(Connection con = Conexion.getConexion();
				PreparedStatement ps = con.prepareStatement(sql)){
			
			//Establecer el parámetro del id
			ps.setInt(1,id);
			
			try(ResultSet resultSet = ps.executeQuery()){
				if(resultSet.next()) {
					provincia = new Provincia();
					provincia.setId(resultSet.getInt("id"));
					provincia.setNombre(resultSet.getString("nombre"));
				}
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return provincia;
	}
}
