package daoImpl;

import dao.CuentaDao;
import entidades.Cuenta;
import entidades.Cliente;
import datos.Conexion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CuentaDaoImpl implements CuentaDao {

    @Override
    public List<Cuenta> obtenerTodas() {
        List<Cuenta> lista = new ArrayList<>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        String sql = "SELECT c.id, c.numero_cuenta, c.cbu, c.saldo, c.fecha_creacion, " +
                     "c.id_tipo_cuenta, c.id_cliente, cl.nombre, cl.apellido, c.activo " +
                     "FROM bancogrupo12.cuentas c " +
                     "JOIN bancogrupo12.clientes cl ON c.id_cliente = cl.id " +
                     "WHERE c.activo = 1";

        try {
            con = Conexion.getConexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                Cuenta c = new Cuenta();
                c.setId(rs.getInt("id"));
                c.setNumeroCuenta(rs.getString("numero_cuenta"));
                c.setCbu(rs.getString("cbu"));
                c.setSaldo(rs.getDouble("saldo"));
                c.setFechaCreacion(rs.getString("fecha_creacion"));
                c.setTipoCuenta(rs.getInt("id_tipo_cuenta"));

                Cliente cli = new Cliente();
                cli.setId(rs.getInt("id_cliente"));
                cli.setNombre(rs.getString("nombre"));
                cli.setApellido(rs.getString("apellido"));
                c.setCliente(cli);

                c.setActivo(rs.getBoolean("activo"));
                lista.add(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try { if (rs != null) rs.close(); } catch (Exception e) {}
            try { if (ps != null) ps.close(); } catch (Exception e) {}
            try { if (con != null) con.close(); } catch (Exception e) {}
        }

        return lista;
    }

    @Override
    public Cuenta obtenerPorId(int id) {
        Cuenta cuenta = null;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        String sql = "SELECT c.id, c.numero_cuenta, c.cbu, c.saldo, c.fecha_creacion, " +
                     "c.id_tipo_cuenta, c.id_cliente, cl.nombre, cl.apellido, c.activo " +
                     "FROM cuentas c " +
                     "JOIN clientes cl ON c.id_cliente = cl.id " +
                     "WHERE c.id = ?";

        try {
            con = Conexion.getConexion();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();

            if (rs.next()) {
                cuenta = new Cuenta();
                cuenta.setId(rs.getInt("id"));
                cuenta.setNumeroCuenta(rs.getString("numero_cuenta"));
                cuenta.setCbu(rs.getString("cbu"));
                cuenta.setSaldo(rs.getDouble("saldo"));
                cuenta.setFechaCreacion(rs.getString("fecha_creacion"));
                cuenta.setTipoCuenta(rs.getInt("id_tipo_cuenta"));

                Cliente cliente = new Cliente();
                cliente.setId(rs.getInt("id_cliente"));
                cliente.setNombre(rs.getString("nombre"));
                cliente.setApellido(rs.getString("apellido"));
                cuenta.setCliente(cliente);

                cuenta.setActivo(rs.getBoolean("activo"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try { if (rs != null) rs.close(); } catch (Exception e) {}
            try { if (ps != null) ps.close(); } catch (Exception e) {}
            try { if (con != null) con.close(); } catch (Exception e) {}
        }

        return cuenta;
    }

    @Override
    public boolean alta(Cuenta cuenta) {
        boolean estado = false;
        Connection con = null;
        PreparedStatement ps = null;

        String sql = "INSERT INTO cuentas " +
                     "(id_cliente, numero_cuenta, cbu, id_tipo_cuenta, fecha_creacion, saldo, activo) " +
                     "VALUES (?, ?, ?, ?, ?, ?, 1)";

        try {
            con = Conexion.getConexion();
            ps = con.prepareStatement(sql);

            ps.setInt(1, cuenta.getCliente().getId());
            ps.setString(2, cuenta.getNumeroCuenta());
            ps.setString(3, cuenta.getCbu());
            ps.setInt(4, cuenta.getTipoCuenta());
            ps.setString(5, cuenta.getFechaCreacion());
            ps.setDouble(6, cuenta.getSaldo());

            if (ps.executeUpdate() > 0) {
                estado = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try { if (ps != null) ps.close(); } catch (Exception e) {}
            try { if (con != null) con.close(); } catch (Exception e) {}
        }

        return estado;
    }

    @Override
    public boolean modificar(Cuenta cuenta) {
        boolean estado = false;
        Connection con = null;
        PreparedStatement ps = null;

        String sql = "UPDATE cuentas SET id_tipo_cuenta = ?, saldo = ?, cbu = ? WHERE id = ?";

        try {
            con = Conexion.getConexion();
            ps = con.prepareStatement(sql);

            ps.setInt(1, cuenta.getTipoCuenta());
            ps.setDouble(2, cuenta.getSaldo());
            ps.setString(3, cuenta.getCbu());
            ps.setInt(4, cuenta.getId());

            if (ps.executeUpdate() > 0) {
                estado = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try { if (ps != null) ps.close(); } catch (Exception e) {}
            try { if (con != null) con.close(); } catch (Exception e) {}
        }

        return estado;
    }

    @Override
    public boolean baja(int id) {
        boolean estado = false;
        Connection con = null;
        PreparedStatement ps = null;

        String sql = "UPDATE cuentas SET activo = 0 WHERE id = ?";

        try {
            con = Conexion.getConexion();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);

            if (ps.executeUpdate() > 0) {
                estado = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try { if (ps != null) ps.close(); } catch (Exception e) {}
            try { if (con != null) con.close(); } catch (Exception e) {}
        }

        return estado;
    }
}