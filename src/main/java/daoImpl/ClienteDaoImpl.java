package daoImpl;

import dao.ClienteDao;
import datos.Conexion;
import entidades.Cliente;
import entidades.Nacionalidad;
import entidades.Localidad;
import entidades.Provincia;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteDaoImpl implements ClienteDao {

    @Override
    public List<Cliente> obtenerTodos() {
        List<Cliente> lista = new ArrayList<>();
        String sql = "SELECT * FROM clientes WHERE activo = 1";

        try (
            Connection con = Conexion.getConexion();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()
        ) {
            while (rs.next()) {
                Cliente c = new Cliente();
                c.setId(rs.getInt("id"));
                c.setDni(rs.getString("dni"));
                c.setCuil(rs.getString("cuil"));
                c.setNombre(rs.getString("nombre"));
                c.setApellido(rs.getString("apellido"));
                c.setSexo(rs.getString("sexo"));

                Nacionalidad nac = new Nacionalidad();
                nac.setId(rs.getInt("id_nacionalidad"));
                c.setNacionalidad(nac);

                c.setFechaNacimiento(rs.getString("fecha_nacimiento"));
                c.setDireccion(rs.getString("direccion"));

                Localidad loc = new Localidad();
                loc.setId(rs.getInt("id_localidad"));
                c.setLocalidad(loc);

                Provincia prov = new Provincia();
                prov.setId(rs.getInt("id_provincia"));
                c.setProvincia(prov);

                c.setCorreoElectronico(rs.getString("correo_electronico"));
                c.setTelefono(rs.getString("telefono"));
                c.setActivo(rs.getBoolean("activo"));

                lista.add(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    @Override
    public Cliente obtenerPorId(int id) {
        Cliente c = null;
        String sql = "SELECT * FROM clientes WHERE id = ?";

        try (
            Connection con = Conexion.getConexion();
            PreparedStatement ps = con.prepareStatement(sql)
        ) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    c = new Cliente();
                    c.setId(rs.getInt("id"));
                    c.setDni(rs.getString("dni"));
                    c.setCuil(rs.getString("cuil"));
                    c.setNombre(rs.getString("nombre"));
                    c.setApellido(rs.getString("apellido"));
                    c.setSexo(rs.getString("sexo"));

                    Nacionalidad nac = new Nacionalidad();
                    nac.setId(rs.getInt("id_nacionalidad"));
                    c.setNacionalidad(nac);

                    c.setFechaNacimiento(rs.getString("fecha_nacimiento"));
                    c.setDireccion(rs.getString("direccion"));

                    Localidad loc = new Localidad();
                    loc.setId(rs.getInt("id_localidad"));
                    c.setLocalidad(loc);

                    Provincia prov = new Provincia();
                    prov.setId(rs.getInt("id_provincia"));
                    c.setProvincia(prov);

                    c.setCorreoElectronico(rs.getString("correo_electronico"));
                    c.setTelefono(rs.getString("telefono"));
                    c.setActivo(rs.getBoolean("activo"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return c;
    }

    @Override
    public boolean alta(Cliente cliente) {
        String sql = "INSERT INTO clientes " +
                "(dni, cuil, nombre, apellido, sexo, id_nacionalidad, fecha_nacimiento, direccion, id_localidad, id_provincia, correo_electronico, telefono, activo) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, 1)";

        try (
            Connection con = Conexion.getConexion();
            PreparedStatement ps = con.prepareStatement(sql)
        ) {
            ps.setString(1, cliente.getDni());
            ps.setString(2, cliente.getCuil());
            ps.setString(3, cliente.getNombre());
            ps.setString(4, cliente.getApellido());
            ps.setString(5, cliente.getSexo());
            ps.setInt(6, cliente.getNacionalidad().getId());
            ps.setString(7, cliente.getFechaNacimiento());
            ps.setString(8, cliente.getDireccion());
            ps.setInt(9, cliente.getLocalidad().getId());
            ps.setInt(10, cliente.getProvincia().getId());
            ps.setString(11, cliente.getCorreoElectronico());
            ps.setString(12, cliente.getTelefono());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean modificar(Cliente cliente) {
        String sql = "UPDATE clientes SET " +
                "dni = ?, cuil = ?, nombre = ?, apellido = ?, sexo = ?, id_nacionalidad = ?, fecha_nacimiento = ?, " +
                "direccion = ?, id_localidad = ?, id_provincia = ?, correo_electronico = ?, telefono = ? " +
                "WHERE id = ?";

        try (
            Connection con = Conexion.getConexion();
            PreparedStatement ps = con.prepareStatement(sql)
        ) {
            ps.setString(1, cliente.getDni());
            ps.setString(2, cliente.getCuil());
            ps.setString(3, cliente.getNombre());
            ps.setString(4, cliente.getApellido());
            ps.setString(5, cliente.getSexo());
            ps.setInt(6, cliente.getNacionalidad().getId());
            ps.setString(7, cliente.getFechaNacimiento());
            ps.setString(8, cliente.getDireccion());
            ps.setInt(9, cliente.getLocalidad().getId());
            ps.setInt(10, cliente.getProvincia().getId());
            ps.setString(11, cliente.getCorreoElectronico());
            ps.setString(12, cliente.getTelefono());
            ps.setInt(13, cliente.getId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean baja(int id) {
        String sql = "UPDATE clientes SET activo = 0 WHERE id = ?";

        try (
            Connection con = Conexion.getConexion();
            PreparedStatement ps = con.prepareStatement(sql)
        ) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}
