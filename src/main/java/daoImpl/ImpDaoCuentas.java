package daoImpl;

import dao.DaoCuentas;
import entidades.Cuenta;
import entidades.TipoCuenta;
import entidades.Cliente;
import datos.Conexion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ImpDaoCuentas implements DaoCuentas {

    @Override
    public List<Cuenta> obtenerTodas() {
        List<Cuenta> lista = new ArrayList<>();
        String sql =
            "SELECT c.id, c.numero_cuenta, c.cbu, c.saldo, c.fecha_creacion, " +
            "       c.id_tipo_cuenta, tc.descripcion, " +
            "       c.id_cliente, cl.nombre, cl.apellido, c.activo " +
            "FROM cuentas c " +
            "JOIN tipo_cuenta tc ON c.id_tipo_cuenta = tc.id " +
            "JOIN clientes cl   ON c.id_cliente     = cl.id " +
            "WHERE c.activo = 1";
        try (
            Connection con = Conexion.getConexion().getSQLConexion();
            PreparedStatement ps   = con.prepareStatement(sql);
            ResultSet rs           = ps.executeQuery()
        ) {
            while (rs.next()) {
                Cuenta c = new Cuenta();
                c.setId(rs.getInt("id"));
                c.setNumeroCuenta(rs.getString("numero_cuenta"));
                c.setCbu(rs.getString("cbu"));
                c.setSaldo(rs.getDouble("saldo"));
                c.setFechaCreacion(rs.getString("fecha_creacion"));
                // Tipo de cuenta
                TipoCuenta tc = new TipoCuenta(
                    rs.getInt("id_tipo_cuenta"),
                    rs.getString("descripcion")
                );
                c.setTipoCuenta(tc);
                // Cliente
                Cliente cli = new Cliente();
                cli.setId(rs.getInt("id_cliente"));
                cli.setNombre(rs.getString("nombre"));
                cli.setApellido(rs.getString("apellido"));
                c.setCliente(cli);
                // Activo
                c.setActivo(rs.getBoolean("activo"));
                lista.add(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    @Override
    public Cuenta obtenerPorId(int id) {
        Cuenta c = null;
        String sql =
            "SELECT c.id, c.numero_cuenta, c.cbu, c.saldo, c.fecha_creacion, " +
            "       c.id_tipo_cuenta, tc.descripcion, " +
            "       c.id_cliente, cl.nombre, cl.apellido, c.activo " +
            "FROM cuentas c " +
            "JOIN tipo_cuenta tc ON c.id_tipo_cuenta = tc.id " +
            "JOIN clientes cl   ON c.id_cliente     = cl.id " +
            "WHERE c.id = ?";
        try (
            Connection con = Conexion.getConexion().getSQLConexion();
            PreparedStatement ps = con.prepareStatement(sql)
        ) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    c = new Cuenta();
                    c.setId(rs.getInt("id"));
                    c.setNumeroCuenta(rs.getString("numero_cuenta"));
                    c.setCbu(rs.getString("cbu"));
                    c.setSaldo(rs.getDouble("saldo"));
                    c.setFechaCreacion(rs.getString("fecha_creacion"));
                    TipoCuenta tc = new TipoCuenta(
                        rs.getInt("id_tipo_cuenta"),
                        rs.getString("descripcion")
                    );
                    c.setTipoCuenta(tc);
                    Cliente cli = new Cliente();
                    cli.setId(rs.getInt("id_cliente"));
                    cli.setNombre(rs.getString("nombre"));
                    cli.setApellido(rs.getString("apellido"));
                    c.setCliente(cli);
                    c.setActivo(rs.getBoolean("activo"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return c;
    }

    @Override
    public boolean alta(Cuenta cuenta) {
        String sql =
            "INSERT INTO cuentas " +
            "(id_cliente, numero_cuenta, cbu, id_tipo_cuenta, fecha_creacion, saldo, activo) " +
            "VALUES (?, ?, ?, ?, ?, ?, 1)";
        try (
            Connection con = Conexion.getConexion().getSQLConexion();
            PreparedStatement ps = con.prepareStatement(sql)
        ) {
            ps.setInt(1, cuenta.getCliente().getId());
            ps.setString(2, cuenta.getNumeroCuenta());
            ps.setString(3, cuenta.getCbu());
            ps.setInt(4, cuenta.getTipoCuenta().getId());
            ps.setString(5, cuenta.getFechaCreacion());
            ps.setDouble(6, cuenta.getSaldo());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean modificar(Cuenta cuenta) {
        String sql =
            "UPDATE cuentas " +
            "SET id_tipo_cuenta = ?, saldo = ?, cbu = ? " +
            "WHERE id = ?";
        try (
            Connection con = Conexion.getConexion().getSQLConexion();
            PreparedStatement ps = con.prepareStatement(sql)
        ) {
            ps.setInt(1, cuenta.getTipoCuenta().getId());
            ps.setDouble(2, cuenta.getSaldo());
            ps.setString(3, cuenta.getCbu());
            ps.setInt(4, cuenta.getId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean baja(int id) {
        String sql = "UPDATE cuentas SET activo = 0 WHERE id = ?";
        try (
            Connection con = Conexion.getConexion().getSQLConexion();
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