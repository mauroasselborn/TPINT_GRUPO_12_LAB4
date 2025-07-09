package daoImpl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import dao.CuotaDao;
import datos.Conexion;
import entidades.Cuota;
import entidades.Prestamo;

public class CuotaDaoImpl implements CuotaDao {

    @Override
    public boolean insertarCuota(Cuota cuota) {
        String sql = "INSERT INTO cuota (id_prestamo, nro_cuota, monto, fecha_pago) VALUES (?, ?, ?, NULL)";
        
        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
             
            ps.setInt(1, cuota.getPrestamo().getId());
            ps.setInt(2, cuota.getNroCuota());
            ps.setDouble(3, cuota.getMonto());
            
            int filas = ps.executeUpdate();
            return filas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Cuota> obtenerCuotasPorPrestamo(int idPrestamo) {
        List<Cuota> cuotas = new ArrayList<>();
        String sql = "SELECT id, nro_cuota, monto, fecha_pago FROM cuota WHERE id_prestamo = ? ORDER BY nro_cuota ASC";

        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
             
            ps.setInt(1, idPrestamo);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Cuota cuota = new Cuota();
                    cuota.setId(rs.getInt("id"));
                    cuota.setNroCuota(rs.getInt("nro_cuota"));
                    cuota.setMonto(rs.getDouble("monto"));
                    cuota.setFechaPago(rs.getDate("fecha_pago"));

                    Prestamo p = new Prestamo();
                    p.setId(idPrestamo);
                    cuota.setPrestamo(p);

                    cuotas.add(cuota);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cuotas;
    }

    @Override
    public boolean pagarCuota(int idCuota, String fechaPago) {
        String sql = "UPDATE cuota SET fecha_pago = ? WHERE id = ? AND fecha_pago IS NULL";
        
        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
             
            ps.setString(1, fechaPago);
            ps.setInt(2, idCuota);
            
            int filas = ps.executeUpdate();
            return filas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
