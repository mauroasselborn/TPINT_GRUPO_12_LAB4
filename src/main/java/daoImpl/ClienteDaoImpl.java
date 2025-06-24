package daoImpl;

import dao.ClienteDao;
import entidades.Cliente;
import datos.Conexion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class ClienteDaoImpl implements ClienteDao {

	@Override
	public boolean AltaCliente(Cliente cliente) {
		String sql="INSERT INTO clientes(id,dni,cuil,nombre,apellido,sexo,id_nacionalidad,fecha_nacimiento,direccion,id_localidad,id_provincia,correo_electronico,telefono)"
				   +"VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?);";
	
		try(
				Connection con = Conexion.getConexion();
	            PreparedStatement ps = con.prepareStatement(sql)
		){
			ps.setInt(1,cliente.getUsuario().getId());
			ps.setString(2, cliente.getDni());
			ps.setString(3, cliente.getCuil());
			ps.setString(4, cliente.getNombre());
			ps.setString(5, cliente.getApellido());
			ps.setString(6, String.valueOf(cliente.getSexo()));
			ps.setInt(7, cliente.getNacionalidad().getId());
			ps.setDate(8, java.sql.Date.valueOf(cliente.getFechaNacimiento()));
			ps.setString(9, cliente.getDireccion());
			ps.setInt(10, cliente.getLocalidad().getId());
			ps.setInt(11, cliente.getProvincia().getId());
			ps.setString(12, cliente.getCorreoElectronico());
			ps.setString(13, cliente.getTelefono());
			
			//Ejecuta la actualización y devuelve si al menos una de las filas fue afectada
			int filas= ps.executeUpdate();
			return filas>0;
			
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
