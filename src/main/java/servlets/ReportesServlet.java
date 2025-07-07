package servlets;

import negocio.ReportesNegocio;
import negocioImpl.ReportesNegocioImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.Map;

@WebServlet("/admin/reportes")
public class ReportesServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ReportesNegocio negocio = new ReportesNegocioImpl();

        Map<String, Integer> cuentasPorTipo = negocio.obtenerCantidadCuentasPorTipo();
        request.setAttribute("cuentasPorTipo", cuentasPorTipo);

        Map<String, Integer> prestamosPorEstado = negocio.obtenerCantidadPrestamosPorEstado();
        request.setAttribute("prestamosPorEstado", prestamosPorEstado);

        request.getRequestDispatcher("/admin/Reportes.jsp").forward(request, response);
    }
}
