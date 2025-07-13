package servlets;

import entidades.Cuenta;
import entidades.Cliente;
import entidades.Movimiento;
import entidades.Usuario;
import negocio.CuentaNegocio;
import negocio.MovimientoNegocio;
import negocioimpl.CuentaNegocioImpl;
import negocioimpl.MovimientoNegocioImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/cliente/MenuClienteServlet")
public class MenuClienteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	CuentaNegocio cuentaNeg = new CuentaNegocioImpl();
	MovimientoNegocio movimientoNeg = new MovimientoNegocioImpl();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Obtener sesión y cliente logueado
		HttpSession session = request.getSession(false);
	    if (session == null || session.getAttribute("usuarioLogueado") == null) {
	        response.sendRedirect("../Login.jsp");
	        return;
	    }
		    
		Cuenta cuentaSeleccionada = null;
		String idCuentaParam = request.getParameter("idCuenta");
		
		if (idCuentaParam != null && !idCuentaParam.isEmpty()) {
		    int idCuenta = Integer.parseInt(idCuentaParam);
		     try {
		    	 cuentaSeleccionada = cuentaNeg.obtenerCuenta(idCuenta);
			} catch (Exception e) {
				
				e.printStackTrace();
			}
	
		}
		
		
		Usuario usuario = (Usuario) session.getAttribute("usuarioLogueado");
		Cliente clienteLogueado = usuario.getCliente();

		if (clienteLogueado == null) {
		    response.sendRedirect("Login.jsp");
		    return;
		}

		List<Cuenta> cuentas = cuentaNeg.obtenerCuentasPorCliente(clienteLogueado.getId());
		request.setAttribute("cuentas", cuentas);
		request.setAttribute("cuentaSeleccionada", cuentaSeleccionada);
		try {
		    if (cuentaSeleccionada == null && cuentas.size() > 0) {
		        cuentaSeleccionada = cuentas.get(0); 
		    }

		    if (cuentaSeleccionada != null) {
		        List<Movimiento> movimientos = movimientoNeg.obtenerMovimientosPorCuenta(cuentaSeleccionada.getId());
		        request.setAttribute("movimientos", movimientos);
		    }

		    request.setAttribute("cuentaSeleccionada", cuentaSeleccionada);
		    

		} catch (Exception e) {
		    e.printStackTrace();
		}

		request.getRequestDispatcher("MenuCliente.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
		HttpSession session = request.getSession(false);
	    if (session == null || session.getAttribute("usuarioLogueado") == null) {
	        response.sendRedirect("../Login.jsp");
	        return;
	    }
		String accion = request.getParameter("accion");

		    if ("copiarCBU".equals(accion)) {
		        String cbuCopiado = request.getParameter("cbu");
		        System.out.println("El usuario copió el CBU: " + cbuCopiado);		        		       
		        return;
		    }
	}

}
