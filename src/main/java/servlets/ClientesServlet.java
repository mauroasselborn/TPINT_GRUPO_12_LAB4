package servlets;

import entidades.Cliente;
import entidades.Provincia;
import entidades.TipoUsuario;
import entidades.Usuario;
import entidades.Localidad;
import entidades.Nacionalidad;
import negocio.UsuarioNegocio;
import negocio.ClienteNegocio;
import negocio.CuentaNegocio;
import negocio.ProvinciaNegocio;
import negocio.LocalidadNegocio;
import negocio.NacionalidadNegocio;
import negocioimpl.ClienteNegocioImpl;
import negocioimpl.CuentaNegocioImpl;
import negocioimpl.LocalidadNegocioImpl;
import negocioimpl.NacionalidadNegocioImpl;
import negocioimpl.ProvinciaNegocioImpl;
import negocioimpl.UsuarioNegocioImpl;

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
	private CuentaNegocio cuentaNegocio = new CuentaNegocioImpl();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String accion = request.getParameter("accion");
		HttpSession session = request.getSession(false);
		if (session == null || session.getAttribute("usuarioLogueado") == null) {
			response.sendRedirect("../Login.jsp");
			return;
		}

		ProvinciaNegocio provinciaNegocio = new ProvinciaNegocioImpl();
		LocalidadNegocio localidadNegocio = new LocalidadNegocioImpl();
		NacionalidadNegocio nacionalidadNegocio = new NacionalidadNegocioImpl();

		switch (accion) {
			case "listar": {
				List<Cliente> listaClientes = clienteNegocio.obtenerTodos();
				request.setAttribute("listaClientes", listaClientes);
				request.getRequestDispatcher("/admin/Clientes.jsp").forward(request, response);
				return;
			}
			case "editar": {
				int id = Integer.parseInt(request.getParameter("id"));
				Cliente cliente = clienteNegocio.obtenerPorId(id);
				Usuario usuario = usuarioNegocio.obtenerUsuarioPorIdCliente(id);
				cliente.setProvincia(provinciaNegocio.obtenerPorId(cliente.getProvincia().getId()));
				cliente.setLocalidad(localidadNegocio.obtenerPorId(cliente.getLocalidad().getId()));
				cliente.setNacionalidad(nacionalidadNegocio.obtenerPorId(cliente.getNacionalidad().getId()));
				request.setAttribute("cliente", cliente);
				request.setAttribute("usuario", usuario);
				request.setAttribute("provincias", provinciaNegocio.obtenerTodos());
				request.setAttribute("localidades", localidadNegocio.obtenerTodos());
				request.setAttribute("nacionalidades", nacionalidadNegocio.obtenerTodos());
				request.getRequestDispatcher("/admin/ModificarCliente.jsp").forward(request, response);
				return;
			}
			case "detalle": {
				int id = Integer.parseInt(request.getParameter("id"));
				Cliente cliente = clienteNegocio.obtenerPorId(id);
				Usuario usuario = usuarioNegocio.obtenerUsuarioPorIdCliente(id);
				cliente.setProvincia(provinciaNegocio.obtenerPorId(cliente.getProvincia().getId()));
				cliente.setLocalidad(localidadNegocio.obtenerPorId(cliente.getLocalidad().getId()));
				cliente.setNacionalidad(nacionalidadNegocio.obtenerPorId(cliente.getNacionalidad().getId()));
				request.setAttribute("cliente", cliente);
				request.setAttribute("usuario", usuario);
				request.getRequestDispatcher("/admin/DetalleCliente.jsp").forward(request, response);
				return;
			}
			case "alta": {
				request.setAttribute("provincias", provinciaNegocio.obtenerTodos());
				request.setAttribute("localidades", localidadNegocio.obtenerTodos());
				request.setAttribute("nacionalidades", nacionalidadNegocio.obtenerTodos());
				request.getRequestDispatcher("/admin/AltaCliente.jsp").forward(request, response);
				return;
			}
			default: {
				response.sendRedirect("ClientesServlet?accion=listar");
			}
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if (session == null || session.getAttribute("usuarioLogueado") == null) {
			response.sendRedirect("../Login.jsp");
			return;
		}

		String accion = request.getParameter("accion");
		if (accion == null) {
			response.sendRedirect("ClientesServlet?accion=listar");
			return;
		}

		ProvinciaNegocio provinciaNegocio = new ProvinciaNegocioImpl();
		LocalidadNegocio localidadNegocio = new LocalidadNegocioImpl();
		NacionalidadNegocio nacionalidadNegocio = new NacionalidadNegocioImpl();

		switch (accion) {
			case "alta": {
				String dni = request.getParameter("dni");
				String cuil = request.getParameter("cuil");
				String nombre = request.getParameter("nombre");
				String apellido = request.getParameter("apellido");
				String sexo = request.getParameter("sexo");
				String fechaNac = request.getParameter("fechaNacimiento");
				String direccion = request.getParameter("direccion");
				String correo = request.getParameter("correoElectronico");
				String telefono = request.getParameter("telefono");
				String usuarioNombre = request.getParameter("usuario");
				String contrasena = request.getParameter("contrasena");
				String repContrasena = request.getParameter("repContrasena");
				String idNacionalidad = request.getParameter("idNacionalidad");
				String idProvincia = request.getParameter("idProvincia");
				String idLocalidad = request.getParameter("idLocalidad");

				String error = null;
				if (!dni.matches("\\d{7,8}")) {
					error = "El DNI debe tener 7 u 8 dígitos.";
				} else if (!cuil.matches("\\d{2}-\\d{7,8}-\\d")) {
					error = "El CUIL debe tener el formato XX-12345678-X.";
				} else if (!nombre.matches("[A-Za-zÁÉÍÓÚáéíóúñÑ ]+")) {
					error = "El nombre solo puede contener letras.";
				} else if (!apellido.matches("[A-Za-zÁÉÍÓÚáéíóúñÑ ]+")) {
					error = "El apellido solo puede contener letras.";
				} else if (!correo.matches("^[\\w.-]+@[\\w.-]+\\.[A-Za-z]{2,6}$")) {
					error = "El correo electrónico no es válido.";
				} else if (!telefono.isEmpty() && !telefono.matches("\\d{10}")) {
					error = "El teléfono debe tener 10 dígitos.";
				} else if (!usuarioNombre.matches("[A-Za-z0-9]{4,20}")) {
					error = "El usuario debe tener 4 a 20 caracteres alfanuméricos.";
				} else if (!contrasena.equals(repContrasena)) {
					error = "Las contraseñas no coinciden.";
				}

				try {
					Cliente cliente = new Cliente();
					cliente.setDni(dni);
					cliente.setCuil(cuil);
					cliente.setNombre(nombre);
					cliente.setApellido(apellido);
					cliente.setSexo(sexo);
					cliente.setFechaNacimiento(fechaNac);
					cliente.setDireccion(direccion);
					cliente.setCorreoElectronico(correo);
					cliente.setTelefono(telefono);

					Nacionalidad nacionalidad = new Nacionalidad();
					nacionalidad.setId(Integer.parseInt(idNacionalidad));
					cliente.setNacionalidad(nacionalidad);

					Provincia provincia = new Provincia();
					provincia.setId(Integer.parseInt(idProvincia));
					cliente.setProvincia(provincia);

					Localidad localidad = new Localidad();
					localidad.setId(Integer.parseInt(idLocalidad));
					cliente.setLocalidad(localidad);

					Usuario usuario = new Usuario();
					usuario.setNombreUsuario(usuarioNombre);
					usuario.setContrasena(contrasena);
					usuario.setActivo(false);

					TipoUsuario tipoUsuario = new TipoUsuario();
					tipoUsuario.setId(2);
					usuario.setTipoUsuario(tipoUsuario);

					clienteNegocio.registrarClienteConUsuario(cliente, usuario);
					request.setAttribute("toastTitulo", "Éxito");
					request.setAttribute("toastMensaje", "Cliente y usuario agregados.");
					request.setAttribute("toastTipo", "success");
				} catch (Exception ex) {

					request.setAttribute("toastTitulo", "Error");
					request.setAttribute("toastMensaje", ex.getMessage());
					request.setAttribute("toastTipo", "error");

					request.setAttribute("error", error);
					request.setAttribute("dni", dni);
					request.setAttribute("cuil", cuil);
					request.setAttribute("nombre", nombre);
					request.setAttribute("apellido", apellido);
					request.setAttribute("sexo", sexo);
					request.setAttribute("fechaNacimiento", fechaNac);
					request.setAttribute("direccion", direccion);
					request.setAttribute("correoElectronico", correo);
					request.setAttribute("telefono", telefono);
					request.setAttribute("usuario", usuarioNombre);
					request.setAttribute("idNacionalidad", idNacionalidad);
					request.setAttribute("idProvincia", idProvincia);
					request.setAttribute("idLocalidad", idLocalidad);

					request.setAttribute("provincias", provinciaNegocio.obtenerTodos());
					request.setAttribute("localidades", localidadNegocio.obtenerTodos());
					request.setAttribute("nacionalidades", nacionalidadNegocio.obtenerTodos());
					request.getRequestDispatcher("/admin/AltaCliente.jsp").forward(request, response);
					return;

				}
				List<Cliente> lista = clienteNegocio.obtenerTodos();
				request.setAttribute("listaClientes", lista);
				request.getRequestDispatcher("/admin/Clientes.jsp").forward(request, response);
				return;
			}
			case "modificar": {
				try {
					Cliente cli = new Cliente();
					cli.setId(Integer.parseInt(request.getParameter("id")));
					cli.setDni(request.getParameter("dni"));
					cli.setCuil(request.getParameter("cuil"));
					cli.setNombre(request.getParameter("nombre"));
					cli.setApellido(request.getParameter("apellido"));
					cli.setSexo(request.getParameter("sexo"));
					cli.setFechaNacimiento(request.getParameter("fechaNacimiento"));
					cli.setDireccion(request.getParameter("direccion"));
					cli.setCorreoElectronico(request.getParameter("correoElectronico"));
					cli.setTelefono(request.getParameter("telefono"));

					Nacionalidad nacMod = new Nacionalidad();
					nacMod.setId(Integer.parseInt(request.getParameter("idNacionalidad")));
					cli.setNacionalidad(nacMod);

					Provincia provMod = new Provincia();
					provMod.setId(Integer.parseInt(request.getParameter("idProvincia")));
					cli.setProvincia(provMod);

					Localidad locMod = new Localidad();
					locMod.setId(Integer.parseInt(request.getParameter("idLocalidad")));
					cli.setLocalidad(locMod);

					if (clienteNegocio.modificar(cli)) {
						request.setAttribute("toastTitulo", "Éxito");
						request.setAttribute("toastMensaje", "Cliente modificado correctamente.");
						request.setAttribute("toastTipo", "success");
					} else {
						throw new Exception("Error al modificar el cliente.");
					}
				} catch (Exception ex) {
					request.setAttribute("toastTitulo", "Error");
					request.setAttribute("toastMensaje", ex.getMessage());
					request.setAttribute("toastTipo", "error");
				}
				List<Cliente> listaMod = clienteNegocio.obtenerTodos();
				request.setAttribute("listaClientes", listaMod);
				request.getRequestDispatcher("/admin/Clientes.jsp").forward(request, response);
				return;
			}
			case "eliminar": {
				try {
					int idEliminar = Integer.parseInt(request.getParameter("id"));
					if (clienteNegocio.eliminar(idEliminar) && usuarioNegocio.eliminarPorIdCliente(idEliminar)) {
						request.setAttribute("toastTitulo", "Éxito");
						request.setAttribute("toastMensaje", "Cliente eliminado correctamente.");
						request.setAttribute("toastTipo", "success");
					} else {
						throw new Exception("Error al eliminar el cliente.");
					}
				} catch (Exception ex) {
					request.setAttribute("toastTitulo", "Error");
					request.setAttribute("toastMensaje", ex.getMessage());
					request.setAttribute("toastTipo", "error");
				}
				List<Cliente> listaElim = clienteNegocio.obtenerTodos();
				request.setAttribute("listaClientes", listaElim);
				request.getRequestDispatcher("/admin/Clientes.jsp").forward(request, response);
				return;
			}
			case "altaLogica": {
				try {
					int idAlta = Integer.parseInt(request.getParameter("id"));
					if (clienteNegocio.altaLogica(idAlta)
							&& cuentaNegocio.contarCuentasActivas(clienteNegocio.obtenerPorId(idAlta)) > 0) {
						request.setAttribute("toastTitulo", "Éxito");
						request.setAttribute("toastMensaje", "Cliente dado de alta correctamente.");
						request.setAttribute("toastTipo", "success");
					} else {
						throw new Exception("Error al dar de alta el cliente. Posiblemente no tiene cuentas activas.");
					}
				} catch (Exception ex) {
					request.setAttribute("toastTitulo", "Error");
					request.setAttribute("toastMensaje", ex.getMessage());
					request.setAttribute("toastTipo", "error");
				}
				List<Cliente> listaAltaLogica = clienteNegocio.obtenerTodos();
				request.setAttribute("listaClientes", listaAltaLogica);
				request.getRequestDispatcher("/admin/Clientes.jsp").forward(request, response);
				return;
			}
			default:
				response.sendRedirect("ClientesServlet?accion=listar");
		}
	}
}
