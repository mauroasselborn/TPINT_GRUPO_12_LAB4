package servlets;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import negocio.CuotaNegocio;
import negocioimpl.CuotaNegocioImpl;

/**
 * Servlet implementation class CuotasServlet
 */
@WebServlet("/cliente/CuotasServlet")
public class CuotasServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private CuotaNegocio cuotaNegocio = new CuotaNegocioImpl();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CuotasServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int idPrestamo = Integer.parseInt(request.getParameter("idPrestamo"));
		int idCuota = Integer.parseInt(request.getParameter("idCuota"));
		String fechaPago = new SimpleDateFormat("yyyy-MM-dd").format(new Date());

		String toastMensaje = cuotaNegocio.pagarCuota(idPrestamo, idCuota, fechaPago);

		boolean isError = toastMensaje.toLowerCase().contains("error")
				|| toastMensaje.toLowerCase().contains("insuficiente");
		String toastTitulo = isError ? "Error" : "Éxito";
		String toastTipo = isError ? "error" : "success";

		HttpSession session = request.getSession();
		session.setAttribute("toastMensaje", toastMensaje);
		session.setAttribute("toastTitulo", toastTitulo);
		session.setAttribute("toastTipo", toastTipo);

		response.sendRedirect(request.getContextPath() + "/cliente/PrestamosServlet?accion=detalle&id=" + idPrestamo);

		// TODO Auto-generated method stub
		doGet(request, response);
	}
}