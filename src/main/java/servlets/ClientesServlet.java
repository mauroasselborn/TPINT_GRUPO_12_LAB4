package servlets;

import entidades.Cliente;
import entidades.Provincia;
import entidades.TipoUsuario;
import entidades.Usuario;
import negocio.UsuarioNegocio;
import entidades.Localidad;
import entidades.Nacionalidad;
import negocio.ClienteNegocio;
import negocioImpl.ClienteNegocioImpl;
import negocio.ProvinciaNegocio;
import negocio.LocalidadNegocio;
import negocio.NacionalidadNegocio;
import negocioImpl.ProvinciaNegocioImpl;
import negocioImpl.UsuarioNegocioImpl;
import negocioImpl.LocalidadNegocioImpl;
import negocioImpl.NacionalidadNegocioImpl;

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

		if (accion != null && accion.equals("listar")) {
			List<Cliente> listaClientes = clienteNegocio.obtenerTodos();
			request.setAttribute("listaClientes", listaClientes);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/Clientes.jsp");
			dispatcher.forward(request, response);
		}

		if (accion.equals("editar")) {
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
		}
		
		if (accion.equals("detalle")) {
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
		}
		
		if (accion.equals("alta")) {
			
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

	    if (accion == null) {
	        response.sendRedirect("ClientesServlet?accion=listar");
	        return;
	    }

	    String toastMensaje = "";
	    String toastTitulo = "";
	    String toastTipo = "";

	    switch (accion) {
	    case "alta":
	        // Alta de cliente
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
	            toastMensaje = "Las contraseñas no coinciden.";
	            toastTitulo = "Error";
	            toastTipo = "error";
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
	            tipoCliente.setId(2);
	            nuevoUsuario.setTipoUsuario(tipoCliente);
	            nuevoUsuario.setCliente(clienteRecienInsertado);

	            if (usuarioNegocio.insertarUsuario(nuevoUsuario)) {
	                toastMensaje = "Cliente y usuario agregados correctamente.";
	                toastTitulo = "Éxito";
	                toastTipo = "success";
	            } else {
	                toastMensaje = "Cliente creado, pero no se pudo agregar el usuario.";
	                toastTitulo = "Advertencia";
	                toastTipo = "warning";
	            }
	        } else {
	            toastMensaje = "Error al agregar el cliente.";
	            toastTitulo = "Error";
	            toastTipo = "error";
	        }
	        break;

	    case "modificar":
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
	            toastMensaje = "Error al modificar el cliente.";
	            toastTitulo = "Error";
	            toastTipo = "error";
	        }
	        break;

	    case "eliminar":
	        int idEliminar = Integer.parseInt(request.getParameter("id"));
	        if (clienteNegocio.eliminar(idEliminar)) {
	            toastMensaje = "Cliente eliminado correctamente.";
	            toastTitulo = "Éxito";
	            toastTipo = "success";
	        } else {
	            toastMensaje = "Error al eliminar el cliente.";
	            toastTitulo = "Error";
	            toastTipo = "error";
	        }
	        break;

	    case "altaLogica":
	        int idAlta = Integer.parseInt(request.getParameter("id"));
	        if (clienteNegocio.altaLogica(idAlta)) {
	            toastMensaje = "Cliente dado de alta correctamente.";
	            toastTitulo = "Éxito";
	            toastTipo = "success";
	        } else {
	            toastMensaje = "Error al dar de alta el cliente.";
	            toastTitulo = "Error";
	            toastTipo = "error";
	        }
	        break;
	    }

	    // Obtener y reenviar lista actualizada con toast
	    List<Cliente> listaClientes = clienteNegocio.obtenerTodos();
	    request.setAttribute("listaClientes", listaClientes);
	    request.setAttribute("toastMensaje", toastMensaje);
	    request.setAttribute("toastTitulo", toastTitulo);
	    request.setAttribute("toastTipo", toastTipo);

	    RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/Clientes.jsp");
	    dispatcher.forward(request, response);
	}
}
