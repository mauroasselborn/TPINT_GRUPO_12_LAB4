package servlets;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UsuarioDao;
import entidades.Cliente;
import entidades.Localidad;
import entidades.Nacionalidad;
import entidades.Provincia;
import entidades.TipoUsuario;
import entidades.Usuario;
import negocioImpl.ClienteNegocioImpl;
import negocioImpl.NacionalidadNegocioImpl;

@WebServlet("/AltaClienteServlet")
public class AltaClienteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AltaClienteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//Obtener los datos de formulario
		String dni = request.getParameter("dni");
		String cuil = request.getParameter("cuil");
		String nombre = request.getParameter("nombre");
		String apellido = request.getParameter("apellido");
		String sexo = request.getParameter("sexo");
		String nacionalidadId = request.getParameter("nacionalidad");
		String fechaNacimiento = request.getParameter("fechaNacimiento");
		String dirrecion = request.getParameter("direccion");
		String localidadId = request.getParameter("localidad");
		String provinciaId = request.getParameter("provincia");
		String email = request.getParameter("email");
		String telefono = request.getParameter("telefono");
		String usuario = request.getParameter("usuario");
		String contrasenia = request.getParameter("contrasena");
		String contraseniaRep = request.getParameter("contraseniaRep");
		
		//Crear el objeto Usuario
		Usuario nuevoUsuario = new Usuario();
		TipoUsuario tipoUsuario = new TipoUsuario(2,"Cliente");
		nuevoUsuario.setNombreUsuario(usuario);
		nuevoUsuario.setContrasena(contrasenia);
		nuevoUsuario.setTipoUsuario(tipoUsuario);
		nuevoUsuario.setActivo(true);
		
		//Obtener las entidades asociadas
		NacionalidadNegocioImpl nacionalidadNegocio = new NacionalidadNegocioImpl();
		
		//Crear el objeto Cliente
		Cliente nuevoCliente = new Cliente();
		nuevoCliente.setNombre(nombre);
		nuevoCliente.setApellido(apellido);
		nuevoCliente.setDni(dni);
		nuevoCliente.setCuil(cuil);
		nuevoCliente.setSexo(sexo);
		nuevoCliente.setFechaNacimiento(fechaNacimiento);
		nuevoCliente.setDireccion(dirrecion);
		nuevoCliente.setCorreoElectronico(email);
		nuevoCliente.setTelefono(telefono);
		nuevoCliente.setActivo(true);
		
		ClienteNegocioImpl clienteNegocio = new ClienteNegocioImpl();
	}

}
