package servlets;

import entidades.Cliente;
import entidades.Usuario;
import entidades.Cuenta;
import entidades.Cuota;
import entidades.Prestamo;
import negocio.CuentaNegocio;
import negocio.CuotaNegocio;
import negocio.ClienteNegocio;
import negocio.PrestamoNegocio;
import negocioimpl.ClienteNegocioImpl;
import negocioimpl.CuentaNegocioImpl;
import negocioimpl.CuotaNegocioImpl;
import negocioimpl.PrestamoNegocioImpl;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@WebServlet(urlPatterns = { "/admin/PrestamosServlet", "/cliente/PrestamosServlet" })
public class PrestamosServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PrestamoNegocio prestamoNegocio = new PrestamoNegocioImpl();
	private CuentaNegocio cuentaNegocio = new CuentaNegocioImpl();
	private ClienteNegocio clienteNegocio = new ClienteNegocioImpl();
	private CuotaNegocio cuotaNegocio = new CuotaNegocioImpl();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		Usuario usuarioLogueado = (Usuario) session.getAttribute("usuarioLogueado");

		if (usuarioLogueado == null) {
			response.sendRedirect(request.getContextPath() + "/login.jsp");
			return;
		}

		String accion = request.getParameter("accion");

		// Admin - Listo todas las solicitudes
		if ("listarSolicitudes".equals(accion)) {

			List<Prestamo> prestamos = prestamoNegocio.obtenerTodosLosPrestamos();

			for (Prestamo p : prestamos) {
				Cliente cliente = clienteNegocio.obtenerPorId(p.getCliente().getId());
				p.setCliente(cliente);

				try {
					Cuenta cuenta = cuentaNegocio.obtenerCuenta(p.getCuenta().getId());
					p.setCuenta(cuenta);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			request.setAttribute("prestamos", prestamos);
			request.getRequestDispatcher("/admin/Prestamos.jsp").forward(request, response);
			return;
		}

		// NUEVO - CLIENTE - Listar mis préstamos
		if ("listar".equals(accion)) {
			if (usuarioLogueado.getCliente() == null) {
				response.sendRedirect(request.getContextPath() + "/login.jsp");
				return;
			}

			Cliente cliente = usuarioLogueado.getCliente();
			List<Prestamo> prestamosCliente = prestamoNegocio.obtenerPrestamosPorCliente(cliente.getId());

			request.setAttribute("prestamos", prestamosCliente);

			request.getRequestDispatcher("/cliente/GestionarPrestamos.jsp").forward(request, response);
			return;
		}
		
		//Detalle prestamo
		if("detalle".equals(accion)) {
			int idPrestamo = Integer.parseInt(request.getParameter("id"));
			Prestamo prestamo = prestamoNegocio.obtenerPrestamoPorId(idPrestamo);
			List<Cuota> listaCuotas = cuotaNegocio.obtenerCuotasPorPrestamo(idPrestamo);
			
			
			request.setAttribute("cuotas", listaCuotas);
			request.setAttribute("prestamo", prestamo);
			request.getRequestDispatcher("/cliente/DetallePrestamo.jsp").forward(request, response);
		}

		// CLIENTE - Solicitar préstamo (por defecto si no hay acción o es otra)
		if (usuarioLogueado.getCliente() == null) {
			response.sendRedirect(request.getContextPath() + "/login.jsp");
			return;
		}

		Cliente cliente = usuarioLogueado.getCliente();
		List<Cuenta> cuentas = cuentaNegocio.obtenerCuentasPorCliente(cliente.getId());
		List<Prestamo> prestamosCliente = prestamoNegocio.obtenerPrestamosPorCliente(cliente.getId());

		request.setAttribute("cuentas", cuentas);
		request.setAttribute("prestamos", prestamosCliente);

		request.getRequestDispatcher("/cliente/SolicitarPrestamo.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		Usuario usuarioLogueado = (Usuario) session.getAttribute("usuarioLogueado");

		String accion = request.getParameter("accion");
		String toastMensaje = "";
		String toastTitulo = "";
		String toastTipo = "";

		if (usuarioLogueado == null) {
			response.sendRedirect(request.getContextPath() + "/login.jsp");
			return;
		}

		try {
			// ADMIN - Aprobar o rechazar
			if ("aprobar".equals(accion) || "rechazar".equals(accion)) {
				int idPrestamo = Integer.parseInt(request.getParameter("idPrestamo"));
				int nuevoEstado = "aprobar".equals(accion) ? 2 : 3;

				boolean cambio = prestamoNegocio.cambiarEstadoPrestamo(idPrestamo, nuevoEstado);

				if (cambio && nuevoEstado == 2) {
					// Si se aprobó, acreditar el préstamo a la cuenta
					prestamoNegocio.acreditarPrestamo(idPrestamo);
				}

				toastMensaje = "Operación realizada correctamente.";
				toastTitulo = "Éxito";
				toastTipo = "success";

				List<Prestamo> prestamos = prestamoNegocio.obtenerTodosLosPrestamos();

				for (Prestamo p : prestamos) {
					Cliente cliente = clienteNegocio.obtenerPorId(p.getCliente().getId());
					p.setCliente(cliente);

					try {
						Cuenta cuenta = cuentaNegocio.obtenerCuenta(p.getCuenta().getId());
						p.setCuenta(cuenta);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}

				request.setAttribute("prestamos", prestamos);
				request.setAttribute("toastMensaje", toastMensaje);
				request.setAttribute("toastTitulo", toastTitulo);
				request.setAttribute("toastTipo", toastTipo);

				request.getRequestDispatcher("/admin/Prestamos.jsp").forward(request, response);
				return;
			}

			// CLIENTE - Solicitar nuevo préstamo
			if (usuarioLogueado.getCliente() == null) {
				throw new Exception("El usuario no tiene cliente asociado.");
			}

			int idCuentaDestino = Integer.parseInt(request.getParameter("cuentaDestino"));
			double montoSolicitado = Double.parseDouble(request.getParameter("monto"));
			int cantidadCuotas = Integer.parseInt(request.getParameter("cuotas"));

			if (montoSolicitado <= 0 || cantidadCuotas <= 0) {
				throw new Exception("Monto y cuotas deben ser mayores a cero.");
			}

			double importePorCuota = montoSolicitado / cantidadCuotas;

			Prestamo prestamo = new Prestamo();
			prestamo.setCliente(usuarioLogueado.getCliente());

			Cuenta cuentaDestino = new Cuenta();
			cuentaDestino.setId(idCuentaDestino);
			prestamo.setCuenta(cuentaDestino);

			prestamo.setFechaAlta(new Date());
			prestamo.setImportePedido(montoSolicitado);
			prestamo.setCantidadCuotas(cantidadCuotas);
			prestamo.setImportePorCuota(importePorCuota);
			prestamo.setCuotasPendientes(cantidadCuotas);
			prestamo.setIdEstado(1);

			boolean resultado = prestamoNegocio.registrarSolicitudPrestamo(prestamo);

			if (resultado) {
				toastMensaje = "Préstamo solicitado correctamente. Quedó en estado Pendiente para aprobación.";
				toastTitulo = "Éxito";
				toastTipo = "success";
			} else {
				toastMensaje = "Error al solicitar el préstamo. Intente nuevamente.";
				toastTitulo = "Error";
				toastTipo = "error";
			}

		} catch (Exception ex) {
			toastMensaje = "Error: " + ex.getMessage();
			toastTitulo = "Error";
			toastTipo = "error";
			ex.printStackTrace();
		}

		request.setAttribute("toastMensaje", toastMensaje);
		request.setAttribute("toastTitulo", toastTitulo);
		request.setAttribute("toastTipo", toastTipo);
		doGet(request, response);
	}
}
