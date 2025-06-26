package servlets;

import entidades.Cliente;
import entidades.Provincia;
import entidades.Localidad;
import entidades.Nacionalidad;
import negocio.ClienteNegocio;
import negocioImpl.ClienteNegocioImpl;
import negocio.ProvinciaNegocio;
import negocio.LocalidadNegocio;
import negocio.NacionalidadNegocio;
import negocioImpl.ProvinciaNegocioImpl;
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
			
			// Obtener las listas de opciones para los combos
			ProvinciaNegocio provinciaNegocio = new ProvinciaNegocioImpl();
			LocalidadNegocio localidadNegocio = new LocalidadNegocioImpl();
			NacionalidadNegocio nacionalidadNegocio = new NacionalidadNegocioImpl();
			
			List<Provincia> listaProvicias = provinciaNegocio.obtenerTodos(); 
			List<Localidad> listaLocalidades = localidadNegocio.obtenerTodos(); 
			List<Nacionalidad> listaNacionalidades = nacionalidadNegocio.obtenerTodos(); 
			
			System.out.println(listaProvicias);
			System.out.println(listaLocalidades);
			System.out.println(listaNacionalidades);
			
			request.setAttribute("cliente", cliente);
			request.setAttribute("provincias", listaProvicias);
			request.setAttribute("localidades", listaLocalidades);
			request.setAttribute("nacionalidades", listaNacionalidades);

			RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/ModificarCliente.jsp");
			dispatcher.forward(request, response);
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String accion = request.getParameter("accion");

		if (accion == null) {
			response.sendRedirect("ClientesServlet?accion=listar");
			return;
		}

		switch (accion) {
		case "insertar":
			Cliente nuevoCliente = LlenarCliente(request);
			if (clienteNegocio.insertar(nuevoCliente)) {
				request.setAttribute("mensaje", "Cliente agregado correctamente.");
			} else {
				request.setAttribute("mensajeError", "Error al agregar el cliente.");
			}
			break;

		case "modificar":
			Cliente clienteModificado = LlenarCliente(request);
			clienteModificado.setId(Integer.parseInt(request.getParameter("id")));
			if (clienteNegocio.modificar(clienteModificado)) {
				request.setAttribute("mensaje", "Cliente modificado correctamente.");
			} else {
				request.setAttribute("mensajeError", "Error al modificar el cliente.");
			}
			break;

		case "eliminar":
			int idEliminar = Integer.parseInt(request.getParameter("id"));
			if (clienteNegocio.eliminar(idEliminar)) {
				request.setAttribute("mensaje", "Cliente eliminado correctamente.");
			} else {
				request.setAttribute("mensajeError", "Error al eliminar el cliente.");
			}
			break;
		}

		// Redirigir al listado actualizado
		List<Cliente> listaClientes = clienteNegocio.obtenerTodos();
		request.setAttribute("listaClientes", listaClientes);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/Clientes.jsp");
		dispatcher.forward(request, response);
	}

	private Cliente LlenarCliente(HttpServletRequest request) {
		Cliente cliente = new Cliente();
		cliente.setDni(request.getParameter("dni"));
		cliente.setCuil(request.getParameter("cuil"));
		cliente.setNombre(request.getParameter("nombre"));
		cliente.setApellido(request.getParameter("apellido"));
		cliente.setSexo(request.getParameter("sexo"));
		cliente.setFechaNacimiento(request.getParameter("fechaNacimiento"));
		cliente.setDireccion(request.getParameter("direccion"));

		cliente.setCorreoElectronico(request.getParameter("correoElectronico"));
		cliente.setTelefono(request.getParameter("telefono"));

		//cliente.setNacionalidad(new entidades.Nacionalidad(Integer.parseInt(request.getParameter("idNacionalidad"))));
		//cliente.setLocalidad(new entidades.Localidad(Integer.parseInt(request.getParameter("idLocalidad"))));
		//cliente.setProvincia(new entidades.Provincia(Integer.parseInt(request.getParameter("idProvincia"))));

		return cliente;
	}
}
