package servlets;

import entidades.Cliente;
import entidades.Prestamo;
import entidades.Cuota;
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

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String accion = request.getParameter("accion");
		HttpSession session = request.getSession();
		Cliente clienteLogueado = (Cliente) session.getAttribute("clienteLogueado");

		if (clienteLogueado == null) {
			response.sendRedirect("../login.jsp");
			return;
		}

		if ("detalle".equals(accion)) {
			// Mostrar detalle del préstamo con cuotas
			String idParam = request.getParameter("id");
			if (idParam == null) {
				response.sendRedirect("PrestamosServlet");
				return;
			}

			int idPrestamo = Integer.parseInt(idParam);
			Prestamo prestamo = prestamoNegocio.obtenerPrestamoPorId(idPrestamo);
			List<Cuota> cuotas = prestamoNegocio.obtenerCuotasPorPrestamo(idPrestamo);

			request.setAttribute("prestamo", prestamo);
			request.setAttribute("listaCuotas", cuotas);

			// Mensaje de pago exitoso o fallido
			String pago = request.getParameter("pago");
			if (pago != null) {
				request.setAttribute("pago", pago);
			}

			RequestDispatcher rd = request.getRequestDispatcher("/cliente/DetallePrestamo.jsp");
			rd.forward(request, response);

		} else if ("pagar".equals(accion)) {
			// Procesar pago de cuota
			String idCuotaParam = request.getParameter("idCuota");
			String idPrestamoParam = request.getParameter("idPrestamo");

			if (idCuotaParam == null || idPrestamoParam == null) {
				response.sendRedirect("PrestamosServlet");
				return;
			}

			int idCuota = Integer.parseInt(idCuotaParam);
			String fechaPago = new Date(System.currentTimeMillis()).toString();

			boolean exito = prestamoNegocio.pagarCuota(idCuota, fechaPago);

			// Volver al detalle con resultado
			response.sendRedirect("PrestamosServlet?accion=detalle&id=" + idPrestamoParam + "&pago=" + exito);

		} else {
			// Listado general de préstamos
			List<Prestamo> listaPrestamos = prestamoNegocio.obtenerPrestamosPorCliente(clienteLogueado.getId());
			request.setAttribute("listaPrestamos", listaPrestamos);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/cliente/MisPrestamos.jsp");
			dispatcher.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
