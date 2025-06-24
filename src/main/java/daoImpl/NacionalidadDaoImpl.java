package daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import dao.NacionalidadDao;
import datos.Conexion;
import entidades.Nacionalidad;

public class NacionalidadDaoImpl implements NacionalidadDao {

	@Override
	public ArrayList<Nacionalidad> listarNacionalidades(){
		String sql="SELECT id,Descripcion FROM nacionalidades";
		ArrayList<Nacionalidad> listaNacionalidades= new ArrayList<>();
		
		try(Connection con = Conexion.getConexion();
				PreparedStatement ps=con.prepareStatement(sql);
				ResultSet resultSet = ps.executeQuery()){
			
			while(resultSet.next()) {
				Nacionalidad nacionalidad = new Nacionalidad();
				
				nacionalidad.setId(resultSet.getInt("id"));
				nacionalidad.setDescripcion(resultSet.getString("descripcion"));
				listaNacionalidades.add(nacionalidad);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return listaNacionalidades;
	}
	
	@Override
	public Nacionalidad obtenerNacionalidadPorId(int id) {
		String sql="SELECT id,Descripcion FROM nacionalidades WHERE id=?";
		Nacionalidad nacionalidad=null;
		
		try(Connection con = Conexion.getConexion();
				PreparedStatement ps= con.prepareStatement(sql)){
			
			ps.setInt(1, id);
			
			try(ResultSet resultSet = ps.executeQuery()){
				if(resultSet.next()) {
					nacionalidad = new Nacionalidad();
					nacionalidad.setId(resultSet.getInt("id"));
					nacionalidad.setDescripcion(resultSet.getString("descripcion"));
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return nacionalidad;
	}
}
