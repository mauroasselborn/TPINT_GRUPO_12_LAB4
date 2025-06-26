package servlets;

import entidades.Cuenta;
import entidades.Cliente;
import negocioImpl.CuentaNegocioImpl;
import negocioImpl.ClienteNegocioImpl;
import negocio.CuentaNegocio;
import negocio.ClienteNegocio;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/admin/CuentasServlet")
public class CuentasServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private final CuentaNegocio cuentaNegocio = new CuentaNegocioImpl();
	private final ClienteNegocio clienteNegocio = new ClienteNegocioImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String accion = req.getParameter("accion");

		try {
			switch (accion) {
			case "nuevo":
				List<Cliente> clientes = clienteNegocio.listarClientes();
				req.setAttribute("clientes", clientes);
				req.getRequestDispatcher("/admin/AltaCuenta.jsp").forward(req, resp);
				break;

			case "editar":
				int idEd = Integer.parseInt(req.getParameter("id"));
				Cuenta cuentaEd = cuentaNegocio.obtenerCuenta(idEd);
				req.setAttribute("cuenta", cuentaEd);
				req.getRequestDispatcher("/admin/ModificarCuenta.jsp").forward(req, resp);
				break;

			case "borrar":
				int idB = Integer.parseInt(req.getParameter("id"));
				cuentaNegocio.eliminarCuenta(idB);
				resp.sendRedirect("CuentasServlet?accion=listar");
				break;

			case "listar":
			default:
				List<Cuenta> cuentas = cuentaNegocio.listarCuentas();
				req.setAttribute("cuentas", cuentas);
				req.getRequestDispatcher("/admin/Cuentas.jsp").forward(req, resp);
				break;
			}
		} catch (Exception e) {
			req.setAttribute("error", e.getMessage());
			req.getRequestDispatcher("/admin/Error.jsp").forward(req, resp);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String accion = req.getParameter("accion");

		try {
			if ("crear".equals(accion)) {
				Cuenta nueva = new Cuenta();
				nueva.setCliente(new Cliente(Integer.parseInt(req.getParameter("clienteId"))));
				nueva.setNumeroCuenta(req.getParameter("numero"));
				nueva.setCbu(req.getParameter("cbu"));
				nueva.setTipoCuenta(Integer.parseInt(req.getParameter("tipo")));
				nueva.setFechaCreacion(java.time.LocalDate.now().toString());
				nueva.setSaldo(10000);
				cuentaNegocio.crearCuenta(nueva);

			} else if ("guardarModificacion".equals(accion)) {
				Cuenta cuentamodificar = new Cuenta();
				cuentamodificar.setId(Integer.parseInt(req.getParameter("id")));
				cuentamodificar.setTipoCuenta(Integer.parseInt(req.getParameter("tipo")));
				cuentamodificar.setCbu(req.getParameter("cbu"));
				cuentamodificar.setSaldo(Double.parseDouble(req.getParameter("saldo")));
				cuentaNegocio.modificarCuenta(cuentamodificar);
			}

			resp.sendRedirect("CuentasServlet?accion=listar");

		} catch (Exception e) {
			req.setAttribute("error", e.getMessage());
			req.getRequestDispatcher("/admin/Error.jsp").forward(req, resp);
		}
	}
}