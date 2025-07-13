package servlets;

import entidades.Cuenta;
import entidades.Cliente;
import entidades.TipoCuenta;
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

//		Usuario user = new Usuario();
//		if (session.getAttribute("usuarioLogueado") != null) {
//			user = (Usuario) session.getAttribute("usuarioLogueado");
//		}
		if (session == null || session.getAttribute("usuarioLogueado") == null) {
			resp.sendRedirect("../Login.jsp");
			return;
		}
//		} else if (user.getTipoUsuario().getDescripcion().equals("Administrador")) {
//			resp.sendRedirect("../Login.jsp");
//		}

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
//		Usuario user = new Usuario();
//		if (session.getAttribute("usuarioLogueado") != null) {
//			user = (Usuario) session.getAttribute("usuarioLogueado");
//		}
		if (session == null || session.getAttribute("usuarioLogueado") == null) {
			resp.sendRedirect("../Login.jsp");
			return;
		}
//		} else if (user.getTipoUsuario().getDescripcion().equals("Administrador")) {
//			resp.sendRedirect("../Login.jsp");
//		}

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
				cuentaNegocio.crearCuenta(nueva);
				clienteNegocio.altaLogica(idCliente);
				usuarioNegocio.activarUsuarioPorIdCliente(idCliente);

			} else if ("guardarModificacion".equals(accion)) {
				Cuenta cuentamodificar = new Cuenta();
				cuentamodificar.setId(Integer.parseInt(req.getParameter("id")));

				TipoCuenta tipocuenta = new TipoCuenta();
				tipocuenta.setId(Integer.parseInt(req.getParameter("tipo")));
				cuentamodificar.setTipoCuenta(tipocuenta);

				cuentamodificar.setCbu(req.getParameter("cbu"));
				cuentamodificar.setSaldo(Double.parseDouble(req.getParameter("saldo")));
				cuentaNegocio.modificarCuenta(cuentamodificar);
			} else if ("borrar".equals(accion)) {
				int idB = Integer.parseInt(req.getParameter("id"));
				cuentaNegocio.eliminarCuenta(idB);
			}

			resp.sendRedirect("CuentasServlet?accion=listar");

		} catch (Exception e) {
			req.setAttribute("error", e.getMessage());
			req.getRequestDispatcher("/admin/Error.jsp").forward(req, resp);
		}
	}
}