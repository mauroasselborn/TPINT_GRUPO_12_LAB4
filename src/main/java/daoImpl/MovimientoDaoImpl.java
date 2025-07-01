package daoImpl;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import entidades.Movimiento;
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

}
