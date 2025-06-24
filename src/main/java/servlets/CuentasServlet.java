package servlets;

import entidades.Cuenta;
import entidades.Cliente;
import negocioImpl.ImpNegocioCuentas;
import negocioImpl.ImpNegocioClientes;
import negocio.NegocioCuentas;
import negocio.NegocioClientes;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/CuentasServlet")
public class CuentasServlet extends HttpServlet {
    private final NegocioCuentas negocioCuentas = new ImpNegocioCuentas();
    private final NegocioClientes negocioClientes = new ImpNegocioClientes();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String accion = req.getParameter("accion");
        try {
            switch (accion) {
                case "nuevo":
                    // Cargar lista de clientes para el combo
                    List<Cliente> clientes = negocioClientes.listarClientes();
                    req.setAttribute("clientes", clientes);
                    req.getRequestDispatcher("jsp/admin/AltaCuenta.jsp").forward(req, resp);
                    break;

                case "editar":
                    int idEd = Integer.parseInt(req.getParameter("id"));
                    Cuenta cuentaEd = negocioCuentas.obtenerCuenta(idEd);
                    req.setAttribute("cuenta", cuentaEd);
                    req.getRequestDispatcher("jsp/admin/ModificarCuenta.jsp").forward(req, resp);
                    break;

                case "borrar":
                    int idB = Integer.parseInt(req.getParameter("id"));
                    negocioCuentas.eliminarCuenta(idB);
                    resp.sendRedirect("CuentasServlet?accion=listar");
                    break;

                case "listar":
                default:
                    List<Cuenta> cuentas = negocioCuentas.listarCuentas();
                    req.setAttribute("cuentas", cuentas);
                    req.getRequestDispatcher("jsp/admin/Cuentas.jsp").forward(req, resp);
                    break;
            }
        } catch (Exception e) {
            req.setAttribute("error", e.getMessage());
            req.getRequestDispatcher("jsp/admin/Error.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String accion = req.getParameter("accion");
        try {
            if ("crear".equals(accion)) {
                // Recoger parámetros para alta
                Cuenta nueva = new Cuenta();
                //nueva.setCliente(new Cliente(Integer.parseInt(req.getParameter("clienteId"))));
                //nueva.setNumero(req.getParameter("numero"));
                nueva.setCbu(req.getParameter("cbu"));
                //nueva.setTipo(req.getParameter("tipo"));
                // La lógica de negocio asigna saldo inicial y valida
                negocioCuentas.crearCuenta(nueva);

            } else if ("guardarModificacion".equals(accion)) {
                // Recoger parámetros para modificación
                Cuenta mod = new Cuenta();
                mod.setId(Integer.parseInt(req.getParameter("id")));
                mod.setTipoCuenta(req.getParameter("tipo"));
                mod.setCbu(req.getParameter("cbu"));
                mod.setSaldo(Double.parseDouble(req.getParameter("saldo")));
                negocioCuentas.modificarCuenta(mod);
            }
            // Tras alta o modificación, vuelvo al listado
            resp.sendRedirect("CuentasServlet?accion=listar");
        } catch (Exception e) {
            req.setAttribute("error", e.getMessage());
            req.getRequestDispatcher("jsp/admin/Error.jsp").forward(req, resp);
        }
    }
}
