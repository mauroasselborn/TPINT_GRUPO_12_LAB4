package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import entidades.Usuario;
import negocio.UsuarioNegocio;
import negocioImpl.UsuarioNegocioImpl;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private UsuarioNegocio usuarioNegocio = new UsuarioNegocioImpl();
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String accion = request.getParameter("accion");

		if ("logout".equals(accion)) {
			HttpSession session = request.getSession(false);
			if (session != null) {
				session.invalidate();
			}
			response.sendRedirect("Login.jsp");
			return;
		}

		response.sendRedirect("Login.jsp");
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nombreUsuario = request.getParameter("usuario");
		String contrasenia = request.getParameter("password");

		if (nombreUsuario == null || contrasenia == null || nombreUsuario.isEmpty() || contrasenia.isEmpty()) {
			request.setAttribute("error", "Los campos no pueden estar vacíos.");
			request.getRequestDispatcher("Login.jsp").forward(request, response);
			return;
		}

		boolean credencialesValidas = usuarioNegocio.validarCredenciales(nombreUsuario, contrasenia);

		if (credencialesValidas) {
			Usuario usuario = usuarioNegocio.obtenerUsuarioPorNombre(nombreUsuario);
			

			if (usuario != null && usuario.isActivo()) {
				HttpSession session = request.getSession();
				session.setAttribute("usuarioLogueado", usuario);

				
				if (usuario.getTipoUsuario().getId() == 1) {
					response.sendRedirect("admin/MenuAdmin.jsp");
				} else {
					response.sendRedirect("cliente/MenuCliente.jsp");
				}
			} else {
				request.setAttribute("error", "Usuario inactivo o no encontrado.");
				request.getRequestDispatcher("Login.jsp").forward(request, response);
			}
		} else {
			request.setAttribute("error", "Usuario o contraseña incorrectos.");
			request.getRequestDispatcher("Login.jsp").forward(request, response);
		}
	}
}
