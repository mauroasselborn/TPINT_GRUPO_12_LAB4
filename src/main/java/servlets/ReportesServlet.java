package servlets;

import negocio.ReportesNegocio;
import negocioImpl.ReportesNegocioImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import entidades.Usuario;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Map;


@WebServlet("/admin/reportes")
public class ReportesServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ReportesNegocio negocio = new ReportesNegocioImpl();
        HttpSession session = request.getSession(false);
	    Usuario user = new Usuario();
		if(session.getAttribute("usuarioLogueado") != null) {user = (Usuario) session.getAttribute("usuarioLogueado");}
		    if (session == null || session.getAttribute("usuarioLogueado") == null) {
		        response.sendRedirect("../Login.jsp");
		        return;
		    } else if(user.getTipoUsuario().getDescripcion().equals("Administrador")) {
		    	response.sendRedirect("../Login.jsp");
		    	}
        String desdeStr = request.getParameter("desde");
        String hastaStr = request.getParameter("hasta");

        java.sql.Date desdeDate = null;
        java.sql.Date hastaDate = null;

        try {
            if (desdeStr != null && !desdeStr.isEmpty() && hastaStr != null && !hastaStr.isEmpty()) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                java.util.Date parsedDesde = sdf.parse(desdeStr);
                java.util.Date parsedHasta = sdf.parse(hastaStr);
                desdeDate = new java.sql.Date(parsedDesde.getTime());
                hastaDate = new java.sql.Date(parsedHasta.getTime());
            } else {
                // Asignar por defecto todo el año actual
                java.time.LocalDate inicioAnio = java.time.LocalDate.now().withDayOfYear(1);
                java.time.LocalDate finAnio = java.time.LocalDate.now().withMonth(12).withDayOfMonth(31);
                desdeDate = java.sql.Date.valueOf(inicioAnio);
                hastaDate = java.sql.Date.valueOf(finAnio);
            }
        } catch (Exception e) {
            e.printStackTrace(); // opcional: redirigir o manejar el error
        }

        Map<String, Integer> cuentasPorTipo = negocio.obtenerCantidadCuentasPorTipo();
        request.setAttribute("cuentasPorTipo", cuentasPorTipo);

        Map<String, Integer> prestamosPorEstado = negocio.obtenerCantidadPrestamosPorEstado(desdeDate, hastaDate);
        request.setAttribute("prestamosPorEstado", prestamosPorEstado);

        Map<String, Integer> clientesPorProvincia = negocio.obtenerCantidadClientesPorProvincia();
        request.setAttribute("clientesPorProvincia", clientesPorProvincia);

        request.getRequestDispatcher("/admin/Reportes.jsp").forward(request, response);
    }


}

