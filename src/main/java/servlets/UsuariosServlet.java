package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidades.Cliente;
import entidades.Localidad;
import entidades.Nacionalidad;
import entidades.Provincia;
import entidades.TipoUsuario;
import entidades.Usuario;
import negocio.UsuarioNegocio;
import negocioImpl.UsuarioNegocioImpl;
import negocio.ClienteNegocio;
import negocio.LocalidadNegocio;
import negocio.NacionalidadNegocio;
import negocio.ProvinciaNegocio;
import negocioImpl.ClienteNegocioImpl;
import negocioImpl.LocalidadNegocioImpl;
import negocioImpl.NacionalidadNegocioImpl;
import negocioImpl.ProvinciaNegocioImpl;

/**
 * Servlet implementation class UsuariosServlet
 */
@WebServlet("/admin/UsuariosServlet")
public class UsuariosServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UsuarioNegocio usuarioNegocio = new UsuarioNegocioImpl();
	private ClienteNegocio clienteNegocio = new ClienteNegocioImpl();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UsuariosServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String accion = request.getParameter("accion");
		RequestDispatcher dispatcher;
		switch (accion) {

		case "listar":

			List<Usuario> listaUsuarios = usuarioNegocio.obtenerTodosLosUsuariosAdmin();

			request.setAttribute("listausuarios", listaUsuarios);
			dispatcher = request.getRequestDispatcher("/admin/Usuarios.jsp");
			dispatcher.forward(request, response);
			break;
		
		
		case "alta":
			
			ProvinciaNegocio provinciaNegocio = new ProvinciaNegocioImpl();
			LocalidadNegocio localidadNegocio = new LocalidadNegocioImpl();
			NacionalidadNegocio nacionalidadNegocio = new NacionalidadNegocioImpl();

			request.setAttribute("localidades", localidadNegocio.obtenerTodos());
			request.setAttribute("provincias", provinciaNegocio.obtenerTodos());
			request.setAttribute("nacionalidades", nacionalidadNegocio.obtenerTodos());

			dispatcher = request.getRequestDispatcher("/admin/AltaUsuario.jsp");
			dispatcher.forward(request, response);
			break;
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String accion = request.getParameter("accion");
		
		
		if (accion == null) {
			response.sendRedirect("UsuariosServlet?accion=listar");
			return;
		}
		
		
		switch (accion) {
		
		case "alta":
			Cliente nuevoCliente = new Cliente();
			nuevoCliente.setDni(request.getParameter("dni"));
			nuevoCliente.setCuil(request.getParameter("cuil"));
			nuevoCliente.setNombre(request.getParameter("nombre"));
			nuevoCliente.setApellido(request.getParameter("apellido"));
			nuevoCliente.setSexo(request.getParameter("sexo"));
			nuevoCliente.setFechaNacimiento(request.getParameter("fechaNacimiento"));
			nuevoCliente.setDireccion(request.getParameter("direccion"));
			nuevoCliente.setCorreoElectronico(request.getParameter("correoElectronico"));
			nuevoCliente.setTelefono(request.getParameter("telefono"));

			Nacionalidad nac = new Nacionalidad();
			nac.setId(Integer.parseInt(request.getParameter("idNacionalidad")));
			nuevoCliente.setNacionalidad(nac);

			Provincia prov = new Provincia();
			prov.setId(Integer.parseInt(request.getParameter("idProvincia")));
			nuevoCliente.setProvincia(prov);

			Localidad loc = new Localidad();
			loc.setId(Integer.parseInt(request.getParameter("idLocalidad")));
			nuevoCliente.setLocalidad(loc);

			String usuarioNombre = request.getParameter("usuario");
			String contrasena = request.getParameter("contrasena");
			String repContrasena = request.getParameter("repContrasena");

			if (!contrasena.equals(repContrasena)) {
				request.setAttribute("mensajeError", "Las contraseñas no coinciden.");
				break;
			}

			boolean clienteInsertado = clienteNegocio.insertar(nuevoCliente);

			if (clienteInsertado) {
				
				Cliente clienteRecienInsertado = clienteNegocio.obtenerPorDni(nuevoCliente.getDni());

				Usuario nuevoUsuario = new Usuario();
				nuevoUsuario.setNombreUsuario(usuarioNombre);
				nuevoUsuario.setContrasena(contrasena);
				nuevoUsuario.setActivo(true);
				
				TipoUsuario tipoCliente = new TipoUsuario();
				tipoCliente.setId(1); //siempre se crea un usuario admin
				nuevoUsuario.setTipoUsuario(tipoCliente);

				nuevoUsuario.setCliente(clienteRecienInsertado);

				if (usuarioNegocio.insertarUsuario(nuevoUsuario)) {
					request.setAttribute("mensaje", "Cliente y usuario agregados correctamente.");
				} else {
					request.setAttribute("mensajeError", "Cliente creado, pero no se pudo agregar el usuario.");
				}
			} else {
				request.setAttribute("mensajeError", "Error al agregar el cliente.");
			}
			break;

		case "eliminar":
			int idEliminar = Integer.parseInt(request.getParameter("id"));
			if (usuarioNegocio.eliminarUsuario(idEliminar)) {
				request.setAttribute("mensaje", "Usuario eliminado correctamente.");
			} else {
				request.setAttribute("mensajeError", "Error al eliminar el Usuario.");
			}
			break;

		case "altaLogica":
			int idAlta = Integer.parseInt(request.getParameter("id"));
			if (usuarioNegocio.activarUsuario(idAlta)) {
				request.setAttribute("mensaje", "Usuario dado de alta correctamente.");
			} else {
				request.setAttribute("mensajeError", "Error al dar de alta el Usuario.");
			}
			break;
		}

		List<Usuario> listaUsuarios = usuarioNegocio.obtenerTodosLosUsuariosAdmin();
		request.setAttribute("listausuarios", listaUsuarios);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/Usuarios.jsp");
		dispatcher.forward(request, response);
	}
}
