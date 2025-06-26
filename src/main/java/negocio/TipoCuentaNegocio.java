package negocio;
import java.util.List;
import entidades.TipoCuenta;


public interface TipoCuentaNegocio {
	public List<TipoCuenta> obtenerTodos();

	public TipoCuenta obtenerPorId(int idtipocuenta);
}
