package servlets;

import entidades.Cuenta;
import entidades.Cliente;
import negocio.CuentaNegocio;
import negocio.ClienteNegocio;
import negocio.TipoCuentaNegocio;
import negocio.UsuarioNegocio;
import negocioimpl.ClienteNegocioImpl;
import negocioimpl.CuentaNegocioImpl;
import negocioimpl.TipoCuentaNegocioImpl;
import negocioimpl.UsuarioNegocioImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/admin/CuentasServlet")
public class CuentasServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private CuentaNegocio cuentaNegocio = new CuentaNegocioImpl();
	private ClienteNegocio clienteNegocio = new ClienteNegocioImpl();
	private TipoCuentaNegocio tipocuenta = new TipoCuentaNegocioImpl();
	private UsuarioNegocio usuarioNegocio = new UsuarioNegocioImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String accion = req.getParameter("accion");
		HttpSession session = req.getSession(false);
		if (session != null &&
			    session.getAttribute("toastMensaje") != null &&
			    session.getAttribute("toastTitulo") != null &&
			    session.getAttribute("toastTipo") != null) {

			    req.setAttribute("toastMensaje", session.getAttribute("toastMensaje"));
			    req.setAttribute("toastTitulo", session.getAttribute("toastTitulo"));
			    req.setAttribute("toastTipo", session.getAttribute("toastTipo"));

			    session.removeAttribute("toastMensaje");
			    session.removeAttribute("toastTitulo");
			    session.removeAttribute("toastTipo");
			}

		if (session == null || session.getAttribute("usuarioLogueado") == null) {
			resp.sendRedirect("../Login.jsp");
			return;
		}

		try {
			switch (accion) {
			case "nuevo":
				List<Cliente> clientes = clienteNegocio.obtenerTodos();

				// Generar CBU y número de cuenta únicos
				String cbu = cuentaNegocio.generarCBUUnico();
				String numeroCuenta = cuentaNegocio.generarNumeroCuentaUnico();
				double saldoInicial = 10000;

				// Enviar al JSP
				req.setAttribute("clientes", clientes);
				req.setAttribute("cbu", cbu);
				req.setAttribute("numeroCuenta", numeroCuenta);
				req.setAttribute("saldo", saldoInicial);
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

		HttpSession session = req.getSession(false);
		if (session == null || session.getAttribute("usuarioLogueado") == null) {
			resp.sendRedirect("../Login.jsp");
			return;
		}

		try {
			if ("crear".equals(accion)) {
				Cuenta nueva = new Cuenta();
				int idCliente = Integer.parseInt(req.getParameter("clienteId"));
				nueva.setCliente(new Cliente(idCliente));
				nueva.setNumeroCuenta(req.getParameter("numero"));
				nueva.setCbu(req.getParameter("cbu"));
				nueva.setTipoCuenta(tipocuenta.obtenerPorId(Integer.parseInt(req.getParameter("tipo"))));
				nueva.setFechaCreacion(java.time.LocalDate.now().toString());
				nueva.setSaldo(Double.parseDouble(req.getParameter("saldo")));
				 String toastMensaje = "";
				    String toastTitulo = "";
				    String toastTipo = "";

				    try {
				        cuentaNegocio.crearCuenta(nueva);

				        clienteNegocio.altaLogica(idCliente);
				        usuarioNegocio.activarUsuarioPorIdCliente(idCliente);

				        toastMensaje = "Cuenta creada correctamente.";
				        toastTitulo = "Éxito";
				        toastTipo = "success";
				    } catch (Exception e) {
				        toastMensaje = e.getMessage();
				        toastTitulo = "Error";
				        toastTipo = "error";
				    }

				    session = req.getSession();
				    session.setAttribute("toastMensaje", toastMensaje);
				    session.setAttribute("toastTitulo", toastTitulo);
				    session.setAttribute("toastTipo", toastTipo);
				    session.setAttribute("mostrarToast", "true");
				    resp.sendRedirect("CuentasServlet?accion=nuevo");
			        return;

			} else if (accion.equals("guardarModificacion")) {
			    int id = Integer.parseInt(req.getParameter("id"));
			    double saldo = Double.parseDouble(req.getParameter("saldo"));

			    Cuenta cuenta = cuentaNegocio.obtenerCuenta(id); 
			    if (cuenta != null) {
			    	cuenta.setSaldo(saldo); //  
			        String toastMensaje =  cuentaNegocio.modificarCuenta(cuenta);
			        
			        boolean isError = toastMensaje.toLowerCase().contains("error")
			        		|| toastMensaje.toLowerCase().contains("Error");
			        String toastTitulo = isError ? "Error" : "Éxito";
			        String toastTipo = isError ? "error" : "success";
			        
			        session = req.getSession();
			        session.setAttribute("toastMensaje", toastMensaje);
			        session.setAttribute("toastTitulo", toastTitulo);
			        session.setAttribute("toastTipo", toastTipo);
			        if(toastTipo.equals("error")) {
			        	System.out.println("mostrartoast");
			        	session.setAttribute("toastMensaje", toastMensaje);
			        	session.setAttribute("toastTitulo", toastTitulo);
			        	session.setAttribute("toastTipo", toastTipo);
			        	resp.sendRedirect("CuentasServlet?accion=editar&id="+cuenta.getId());
			        return;
			        }
			        	
			    }

			  
			} else if ("eliminar".equals(accion)) {
				try {
	        		int id = Integer.parseInt(req.getParameter("id"));
	        	    String mensaje = cuentaNegocio.eliminarCuenta(id);

	        	    session = req.getSession();
	        	    session.setAttribute("toastMensaje", mensaje);
	        	    session.setAttribute("toastTitulo", "Éxito");
	        	    session.setAttribute("toastTipo", "success");
	        	    session.setAttribute("mostrarToast", "true");	        	    	        	
	        	} catch (Exception e) {
	        	    session = req.getSession();
	        	    session.setAttribute("toastMensaje", e.getMessage());
	        	    session.setAttribute("toastTitulo", "Error");
	        	    session.setAttribute("toastTipo", "error");
	        	    session.setAttribute("mostrarToast", "true");	        	   
	        	}
	        }
	        else if ("altaLogica".equals(accion)) {
	        	try {
	        		int id = Integer.parseInt(req.getParameter("id"));
	        	    String mensaje = cuentaNegocio.altaLogica(id);

	        	    session = req.getSession();
	        	    session.setAttribute("toastMensaje", mensaje);
	        	    session.setAttribute("toastTitulo", "Éxito");
	        	    session.setAttribute("toastTipo", "success");
	        	    session.setAttribute("mostrarToast", "true");	        	    	        	
	        	} catch (Exception e) {
	        	    session = req.getSession();
	        	    session.setAttribute("toastMensaje", e.getMessage());
	        	    session.setAttribute("toastTitulo", "Error");
	        	    session.setAttribute("toastTipo", "error");
	        	    session.setAttribute("mostrarToast", "true");	        	   
	        	}
	        }

			resp.sendRedirect("CuentasServlet?accion=listar");
			return;

		} catch (Exception e) {
			req.setAttribute("error", e.getMessage());
			req.getRequestDispatcher("/admin/Error.jsp").forward(req, resp);
		}
	}
}