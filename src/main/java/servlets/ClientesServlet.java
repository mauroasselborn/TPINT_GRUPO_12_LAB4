package servlets;

import entidades.Cliente;
import entidades.Provincia;
import entidades.TipoUsuario;
import entidades.Usuario;
import negocio.UsuarioNegocio;
import negocioimpl.ClienteNegocioImpl;
import negocioimpl.LocalidadNegocioImpl;
import negocioimpl.NacionalidadNegocioImpl;
import negocioimpl.ProvinciaNegocioImpl;
import negocioimpl.UsuarioNegocioImpl;
import entidades.Localidad;
import entidades.Nacionalidad;
import negocio.ClienteNegocio;
import negocio.ProvinciaNegocio;
import negocio.LocalidadNegocio;
import negocio.NacionalidadNegocio;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/admin/ClientesServlet")
public class ClientesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ClienteNegocio clienteNegocio = new ClienteNegocioImpl();
	private UsuarioNegocio usuarioNegocio = new UsuarioNegocioImpl();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String accion = request.getParameter("accion");
		HttpSession session = request.getSession(false);
//		Usuario user = new Usuario();

		if (session == null || session.getAttribute("usuarioLogueado") == null) {
			response.sendRedirect("../Login.jsp");
			return;
		}
//		user = (Usuario) session.getAttribute("usuarioLogueado");

//		if (user.getTipoUsuario().getDescripcion().equals("Administrador")) {
//			response.sendRedirect("../Login.jsp");
//			return;
//		}

		if ("listar".equals(accion)) {
			List<Cliente> listaClientes = clienteNegocio.obtenerTodos();
			request.setAttribute("listaClientes", listaClientes);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/Clientes.jsp");
			dispatcher.forward(request, response);
			return;
		}

		if ("editar".equals(accion)) {
			int id = Integer.parseInt(request.getParameter("id"));
			Cliente cliente = clienteNegocio.obtenerPorId(id);
			Usuario usuario = usuarioNegocio.obtenerUsuarioPorIdCliente(id);

			ProvinciaNegocio provinciaNegocio = new ProvinciaNegocioImpl();
			LocalidadNegocio localidadNegocio = new LocalidadNegocioImpl();
			NacionalidadNegocio nacionalidadNegocio = new NacionalidadNegocioImpl();

			cliente.setProvincia(provinciaNegocio.obtenerPorId(cliente.getProvincia().getId()));
			cliente.setLocalidad(localidadNegocio.obtenerPorId(cliente.getLocalidad().getId()));
			cliente.setNacionalidad(nacionalidadNegocio.obtenerPorId(cliente.getNacionalidad().getId()));

			request.setAttribute("cliente", cliente);
			request.setAttribute("provincias", provinciaNegocio.obtenerTodos());
			request.setAttribute("localidades", localidadNegocio.obtenerTodos());
			request.setAttribute("nacionalidades", nacionalidadNegocio.obtenerTodos());
			request.setAttribute("usuario", usuario);

			RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/ModificarCliente.jsp");
			dispatcher.forward(request, response);
			return;
		}

		if ("detalle".equals(accion)) {
			int id = Integer.parseInt(request.getParameter("id"));
			Cliente cliente = clienteNegocio.obtenerPorId(id);
			Usuario usuario = usuarioNegocio.obtenerUsuarioPorIdCliente(id);

			ProvinciaNegocio provinciaNegocio = new ProvinciaNegocioImpl();
			LocalidadNegocio localidadNegocio = new LocalidadNegocioImpl();
			NacionalidadNegocio nacionalidadNegocio = new NacionalidadNegocioImpl();

			cliente.setProvincia(provinciaNegocio.obtenerPorId(cliente.getProvincia().getId()));
			cliente.setLocalidad(localidadNegocio.obtenerPorId(cliente.getLocalidad().getId()));
			cliente.setNacionalidad(nacionalidadNegocio.obtenerPorId(cliente.getNacionalidad().getId()));

			request.setAttribute("cliente", cliente);
			request.setAttribute("usuario", usuario);

			RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/DetalleCliente.jsp");
			dispatcher.forward(request, response);
			return;
		}

		if ("alta".equals(accion)) {
			ProvinciaNegocio provinciaNegocio = new ProvinciaNegocioImpl();
			LocalidadNegocio localidadNegocio = new LocalidadNegocioImpl();
			NacionalidadNegocio nacionalidadNegocio = new NacionalidadNegocioImpl();

			request.setAttribute("localidades", localidadNegocio.obtenerTodos());
			request.setAttribute("provincias", provinciaNegocio.obtenerTodos());
			request.setAttribute("nacionalidades", nacionalidadNegocio.obtenerTodos());

			RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/AltaCliente.jsp");
			dispatcher.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String accion = request.getParameter("accion");
		HttpSession session = request.getSession(false);
//		Usuario user = new Usuario();
//		if (session.getAttribute("usuarioLogueado") != null) {
//			user = (Usuario) session.getAttribute("usuarioLogueado");
//		}
		if (session == null || session.getAttribute("usuarioLogueado") == null) {
			response.sendRedirect("../Login.jsp");
			return;
		} 
//		else if (user.getTipoUsuario().getDescripcion().equals("Administrador")) {
//			response.sendRedirect("../Login.jsp");
//		}

		if (accion == null) {
			response.sendRedirect("ClientesServlet?accion=listar");
			return;
		}

		String toastMensaje = "";
		String toastTitulo = "";
		String toastTipo = "";

		switch (accion) {
		
		
		
		
		/*case "alta":
			try {
				// Cliente
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

				// Usuario
				String usuarioNombre = request.getParameter("usuario");
				String contrasena = request.getParameter("contrasena");
				String repContrasena = request.getParameter("repContrasena");

				if (!contrasena.equals(repContrasena)) {
					throw new Exception("Las contraseñas no coinciden.");
				}

				Usuario nuevoUsuario = new Usuario();
				nuevoUsuario.setNombreUsuario(usuarioNombre);
				nuevoUsuario.setContrasena(contrasena);
				nuevoUsuario.setActivo(false);

				TipoUsuario tipoCliente = new TipoUsuario();
				tipoCliente.setId(2);
				nuevoUsuario.setTipoUsuario(tipoCliente);

				clienteNegocio.registrarClienteConUsuario(nuevoCliente, nuevoUsuario);

				toastMensaje = "Cliente y usuario agregados correctamente.";
				toastTitulo = "Éxito";
				toastTipo = "success";

			} catch (Exception ex) {
				toastMensaje = ex.getMessage();
				toastTitulo = "Error";
				toastTipo = "error";
			}
			break; */
		
		case "alta":
			try {
				// Cliente
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

				// Usuario
				String usuarioNombre = request.getParameter("usuario");
				String contrasena = request.getParameter("contrasena");
				String repContrasena = request.getParameter("repContrasena");

				if (!contrasena.equals(repContrasena)) {
					throw new Exception("Las contraseñas no coinciden.");
				}

				Usuario nuevoUsuario = new Usuario();
				nuevoUsuario.setNombreUsuario(usuarioNombre);
				nuevoUsuario.setContrasena(contrasena);
				nuevoUsuario.setActivo(false);

				TipoUsuario tipoCliente = new TipoUsuario();
				tipoCliente.setId(2);
				nuevoUsuario.setTipoUsuario(tipoCliente);

				clienteNegocio.registrarClienteConUsuario(nuevoCliente, nuevoUsuario);

				toastMensaje = "Cliente y usuario agregados correctamente.";
				toastTitulo = "Éxito";
				toastTipo = "success";

				// Redirige al listado
				List<Cliente> listaClientes = clienteNegocio.obtenerTodos();
				request.setAttribute("listaClientes", listaClientes);
				request.setAttribute("toastMensaje", toastMensaje);
				request.setAttribute("toastTitulo", toastTitulo);
				request.setAttribute("toastTipo", toastTipo);

				RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/Clientes.jsp");
				dispatcher.forward(request, response);
				return;

			} catch (Exception ex) {
				// Volver a AltaCliente.jsp con los valores cargados
				request.setAttribute("error", ex.getMessage());

				// Reenviar los datos del formulario
				request.setAttribute("dni", request.getParameter("dni"));
				request.setAttribute("cuil", request.getParameter("cuil"));
				request.setAttribute("nombre", request.getParameter("nombre"));
				request.setAttribute("apellido", request.getParameter("apellido"));
				request.setAttribute("sexo", request.getParameter("sexo"));
				request.setAttribute("fechaNacimiento", request.getParameter("fechaNacimiento"));
				request.setAttribute("direccion", request.getParameter("direccion"));
				request.setAttribute("correoElectronico", request.getParameter("correoElectronico"));
				request.setAttribute("telefono", request.getParameter("telefono"));
				request.setAttribute("usuario", request.getParameter("usuario"));
				request.setAttribute("idNacionalidad", request.getParameter("idNacionalidad"));
				request.setAttribute("idProvincia", request.getParameter("idProvincia"));
				request.setAttribute("idLocalidad", request.getParameter("idLocalidad"));

				// Reenviar también las listas
				ProvinciaNegocio provinciaNegocio = new ProvinciaNegocioImpl();
				LocalidadNegocio localidadNegocio = new LocalidadNegocioImpl();
				NacionalidadNegocio nacionalidadNegocio = new NacionalidadNegocioImpl();

				request.setAttribute("localidades", localidadNegocio.obtenerTodos());
				request.setAttribute("provincias", provinciaNegocio.obtenerTodos());
				request.setAttribute("nacionalidades", nacionalidadNegocio.obtenerTodos());
				
				toastMensaje = ex.getMessage();
				toastTitulo = "Error";
				toastTipo = "error";
				
				request.setAttribute("toastMensaje", toastMensaje);
				request.setAttribute("toastTitulo", toastTitulo);
				request.setAttribute("toastTipo", toastTipo);

				RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/AltaCliente.jsp");
				dispatcher.forward(request, response);
				return;
			} 

		case "modificar":
			try {
				Cliente clienteModificado = new Cliente();
				clienteModificado.setId(Integer.parseInt(request.getParameter("id")));
				clienteModificado.setDni(request.getParameter("dni"));
				clienteModificado.setCuil(request.getParameter("cuil"));
				clienteModificado.setNombre(request.getParameter("nombre"));
				clienteModificado.setApellido(request.getParameter("apellido"));
				clienteModificado.setSexo(request.getParameter("sexo"));
				clienteModificado.setFechaNacimiento(request.getParameter("fechaNacimiento"));
				clienteModificado.setDireccion(request.getParameter("direccion"));
				clienteModificado.setCorreoElectronico(request.getParameter("correoElectronico"));
				clienteModificado.setTelefono(request.getParameter("telefono"));

				Nacionalidad nacMod = new Nacionalidad();
				nacMod.setId(Integer.parseInt(request.getParameter("idNacionalidad")));
				clienteModificado.setNacionalidad(nacMod);

				Provincia provMod = new Provincia();
				provMod.setId(Integer.parseInt(request.getParameter("idProvincia")));
				clienteModificado.setProvincia(provMod);

				Localidad locMod = new Localidad();
				locMod.setId(Integer.parseInt(request.getParameter("idLocalidad")));
				clienteModificado.setLocalidad(locMod);

				if (clienteNegocio.modificar(clienteModificado)) {
					toastMensaje = "Cliente modificado correctamente.";
					toastTitulo = "Éxito";
					toastTipo = "success";
				} else {
					throw new Exception("Error al modificar el cliente.");
				}
			} catch (Exception ex) {
				toastMensaje = ex.getMessage();
				toastTitulo = "Error";
				toastTipo = "error";
			}
			break;

		case "eliminar":
			try {
				int idEliminar = Integer.parseInt(request.getParameter("id"));

				if (clienteNegocio.eliminar(idEliminar) && usuarioNegocio.eliminarPorIdCliente(idEliminar)) {
					toastMensaje = "Cliente eliminado correctamente.";
					toastTitulo = "Éxito";
					toastTipo = "success";
				} else {
					throw new Exception("Error al eliminar el cliente.");
				}
			} catch (Exception ex) {
				toastMensaje = ex.getMessage();
				toastTitulo = "Error";
				toastTipo = "error";
			}
			break;

		case "altaLogica":
			try {
				int idAlta = Integer.parseInt(request.getParameter("id"));

				if (clienteNegocio.altaLogica(idAlta) && usuarioNegocio.activarUsuarioPorIdCliente(idAlta)) {
					toastMensaje = "Cliente dado de alta correctamente.";
					toastTitulo = "Éxito";
					toastTipo = "success";
				} else {
					throw new Exception("Error al dar de alta el cliente.");
				}
			} catch (Exception ex) {
				toastMensaje = ex.getMessage();
				toastTitulo = "Error";
				toastTipo = "error";
			}
			break;
		}

		List<Cliente> listaClientes = clienteNegocio.obtenerTodos();
		request.setAttribute("listaClientes", listaClientes);
		request.setAttribute("toastMensaje", toastMensaje);
		request.setAttribute("toastTitulo", toastTitulo);
		request.setAttribute("toastTipo", toastTipo);

		RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/Clientes.jsp");
		dispatcher.forward(request, response);
	}

}
