package servlets;

import entidades.Cliente;
import entidades.Usuario;
import entidades.Cuenta;
import entidades.Prestamo;
import entidades.Cuota;
import negocio.CuentaNegocio;
import negocioImpl.CuentaNegocioImpl;
import negocio.PrestamoNegocio;
import negocioImpl.PrestamoNegocioImpl;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.Date;
import java.util.List;

@WebServlet("/cliente/PrestamosServlet")
public class PrestamosServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PrestamoNegocio prestamoNegocio = new PrestamoNegocioImpl();
	private CuentaNegocio cuentaNegocio = new CuentaNegocioImpl();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		Usuario usuarioLogueado = (Usuario) session.getAttribute("usuarioLogueado");
		Cliente cliente = usuarioLogueado.getCliente();

		if (cliente == null) {
			response.sendRedirect(request.getContextPath() + "/login.jsp");
			return;
		}

		List<Cuenta> cuentas = cuentaNegocio.obtenerCuentasPorCliente(cliente.getId());

		request.setAttribute("cuentas", cuentas);

		RequestDispatcher dispatcher = request.getRequestDispatcher("/cliente/SolicitarPrestamo.jsp");
		dispatcher.forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		Usuario usuarioLogueado = (Usuario) session.getAttribute("usuarioLogueado");

		String toastMensaje = "";
		String toastTitulo = "";
		String toastTipo = "";

		if (usuarioLogueado == null || usuarioLogueado.getCliente() == null) {
			toastMensaje = "Debe volver a iniciar sesión.";
			toastTitulo = "Error";
			toastTipo = "error";

			request.setAttribute("toastMensaje", toastMensaje);
			request.setAttribute("toastTitulo", toastTitulo);
			request.setAttribute("toastTipo", toastTipo);
			request.getRequestDispatcher("/login.jsp").forward(request, response);
			return;
		}

		try {
			int idCuentaDestino = Integer.parseInt(request.getParameter("cuentaDestino"));
			double montoSolicitado = Double.parseDouble(request.getParameter("monto"));
			int cantidadCuotas = Integer.parseInt(request.getParameter("cuotas"));

			// Validaciones
			if (montoSolicitado <= 0 || cantidadCuotas <= 0) {
				throw new Exception("Monto y cuotas deben ser mayores a cero.");
			}

			// Armar el Prestamo
			Prestamo prestamo = new Prestamo();
			prestamo.setCliente(usuarioLogueado.getCliente());

			Cuenta cuentaDestino = new Cuenta();
			cuentaDestino.setId(idCuentaDestino);
			prestamo.setCuenta(cuentaDestino);

			prestamo.setFechaAlta(new java.util.Date());
			prestamo.setImportePedido(montoSolicitado);
			prestamo.setCantidadCuotas(cantidadCuotas);
			prestamo.setEstado("Pendiente");
			prestamo.setActivo(true);

			boolean resultado = prestamoNegocio.registrarSolicitudPrestamo(prestamo);

			if (resultado) {
				toastMensaje = "Préstamo solicitado correctamente. Quedó en estado Pendiente para aprobación.";
				toastTitulo = "Éxito";
				toastTipo = "success";


				request.setAttribute("toastMensaje", toastMensaje);
				request.setAttribute("toastTitulo", toastTitulo);
				request.setAttribute("toastTipo", toastTipo);
				doGet(request, response);
				return;

			} else {
				toastMensaje = "Error al solicitar el préstamo. Intente nuevamente.";
				toastTitulo = "Error";
				toastTipo = "error";

				request.setAttribute("toastMensaje", toastMensaje);
				request.setAttribute("toastTitulo", toastTitulo);
				request.setAttribute("toastTipo", toastTipo);
				doGet(request, response);
			}

		} catch (Exception ex) {
			toastMensaje = "Error: " + ex.getMessage();
			toastTitulo = "Error";
			toastTipo = "error";

			request.setAttribute("toastMensaje", toastMensaje);
			request.setAttribute("toastTitulo", toastTitulo);
			request.setAttribute("toastTipo", toastTipo);
			doGet(request, response);
		}
	}

}
