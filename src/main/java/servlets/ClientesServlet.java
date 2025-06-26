package servlets;

import negocio.ClienteNegocio;
import negocioImpl.ClienteNegocioImpl;
import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidades.Cliente;

/**
 * Servlet implementation class ClientesServlet
 */
@WebServlet("/admin/ClientesServlet")
public class ClientesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	 private ClienteNegocio clienteNegocio = new ClienteNegocioImpl();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ClientesServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String accion = request.getParameter("accion");

        if (accion != null && accion.equals("listar")) {
            List<Cliente> listaClientes = clienteNegocio.obtenerTodos();
            request.setAttribute("listaClientes", listaClientes);

            RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/Clientes.jsp");
            dispatcher.forward(request, response);
        }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
