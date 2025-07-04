package daoImpl;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entidades.Cuenta;
import entidades.Movimiento;
import entidades.TipoMovimiento;
import datos.Conexion;
import dao.MovimientoDao;

public class MovimientoDaoImpl implements MovimientoDao {
	@Override
    public boolean insertarMovimiento(Movimiento mov) {
        boolean inserto = false;
        PreparedStatement pst = null;

        try {
            String query = "INSERT INTO movimientos (fecha, detalle, id_tipo_movimiento, importe, tipo, id_cuenta) VALUES (?, ?, ?, ?, ?, ?)";
            pst = Conexion.getConexion().prepareStatement(query); // usamos la conexión directamente

            pst.setString(1, mov.getFecha());
            pst.setString(2, mov.getDetalle());
            pst.setInt(3, mov.getTipoMovimiento().getId());
            pst.setDouble(4, mov.getImporte());
            pst.setString(5, mov.getTipo());
            pst.setInt(6, mov.getCuenta().getId());

            int filas = pst.executeUpdate();
            if (filas > 0) {
                inserto = true;
            }

        } catch (SQLException e) {
            System.out.println("[MovimientoDAOImpl] Error al insertar movimiento: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (pst != null) pst.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return inserto;
    }
	
	public List<Movimiento> obtenerPorCuenta(int idCuenta) {
	    List<Movimiento> lista = new ArrayList<>();
	    Connection cn = null;
	    PreparedStatement pst = null;
	    ResultSet rs = null;

	    try {
	        cn = Conexion.getConexion();
	        String sql = "SELECT m.id, m.fecha, m.detalle, m.importe, m.id_tipo_movimiento, tm.descripcion\r\n"
	        		+ "FROM bancogrupo12.movimientos m\r\n"
	        		+ "INNER JOIN bancogrupo12.tipo_movimiento tm ON m.id_tipo_movimiento = tm.id\r\n"
	        		+ "WHERE m.id_cuenta = ?\r\n"
	        		+ "ORDER BY m.fecha ASC;";
	        pst = cn.prepareStatement(sql);
	        pst.setInt(1, idCuenta);
	        rs = pst.executeQuery();

	        while (rs.next()) {
	            Movimiento m = new Movimiento();
	            m.setId(rs.getInt("id"));
	            m.setFecha(rs.getString("fecha"));
	            m.setDetalle(rs.getString("detalle"));
	            m.setImporte(rs.getDouble("importe")); 
	            TipoMovimiento tipomovimiento = new TipoMovimiento();
	            tipomovimiento.setId(rs.getInt("id_tipo_movimiento"));
	            tipomovimiento.setDescripcion(rs.getString("descripcion"));
	            
	            m.setTipoMovimiento(tipomovimiento);
	            

	  

	            // Cargar cuenta (mínimo id)
	            Cuenta cuenta = new Cuenta();
	            cuenta.setId(idCuenta);
	            m.setCuenta(cuenta);

	            lista.add(m);
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        try { if (rs != null) rs.close(); } catch (Exception e) { e.printStackTrace(); }
	        try { if (pst != null) pst.close(); } catch (Exception e) { e.printStackTrace(); }
	        try { if (cn != null) cn.close(); } catch (Exception e) { e.printStackTrace(); }
	    }

	    return lista;
	}

}
