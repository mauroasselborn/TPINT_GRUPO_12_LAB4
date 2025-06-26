package servlets;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import entidades.Usuario;
import daoImpl.UsuarioDaoImpl;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public LoginServlet() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String usuario = request.getParameter("usuario");
		String contrasena = request.getParameter("password");

		System.out.println("[Login] Usuario ingresado: " + usuario);
		System.out.println("[Login] Contraseña ingresada: " + contrasena);

		UsuarioDaoImpl usuarioDao = new UsuarioDaoImpl();
		Usuario u = usuarioDao.obtenerUsuario(usuario, contrasena);

		if (u != null) {
			System.out.println("[Login] Login exitoso");
			HttpSession session = request.getSession();
			session.setAttribute("usuarioLogueado", u);
			response.sendRedirect("admin/MenuAdmin.jsp"); // O redireccionar a MenuAdmin
		} else {
			System.out.println("[Login] Login fallido");
			request.setAttribute("mensajeError", "Usuario o contraseña incorrectos");
			RequestDispatcher rd = request.getRequestDispatcher("/Login.jsp");
			rd.forward(request, response);
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String accion = request.getParameter("accion");

		if ("logout".equals(accion)) {
			System.out.println("[Login] Cerrando sesión...");
			HttpSession session = request.getSession(false);
			if (session != null) {
				session.invalidate();
			}
			response.sendRedirect("Login.jsp?logout=1");
		} else {
			response.sendRedirect("Login.jsp");
		}
	}
}
