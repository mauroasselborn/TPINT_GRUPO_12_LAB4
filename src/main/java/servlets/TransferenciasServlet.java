package servlets;
import java.io.IOException;
import java.time.LocalDateTime;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import entidades.Cuenta;
import entidades.Usuario;
import entidades.Movimiento;
import entidades.TipoMovimiento;
import negocio.CuentaNegocio;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.*;

import negocio.MovimientoNegocio;
import negocioimpl.CuentaNegocioImpl;
import negocioimpl.MovimientoNegocioImpl;

@WebServlet("/cliente/TransferenciasServlet")
public class TransferenciasServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 
	

    private CuentaNegocio cuentaNegocio = new CuentaNegocioImpl();
    private MovimientoNegocio movimientoNegocio = new MovimientoNegocioImpl();
   
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String accion = request.getParameter("accion");
    	HttpSession session = request.getSession();
        if (session == null || session.getAttribute("usuarioLogueado") == null) {
            response.sendRedirect("../Login.jsp");
            return;
        }
        if (session.getAttribute("toastMensaje") != null &&
        	    session.getAttribute("toastTitulo") != null &&
        	    session.getAttribute("toastTipo") != null) {

        	    request.setAttribute("toastMensaje", session.getAttribute("toastMensaje"));
        	    request.setAttribute("toastTitulo", session.getAttribute("toastTitulo"));
        	    request.setAttribute("toastTipo", session.getAttribute("toastTipo"));

        	    session.removeAttribute("toastMensaje");
        	    session.removeAttribute("toastTitulo");
        	    session.removeAttribute("toastTipo");
        	}
       
        Usuario usuario = (Usuario) session.getAttribute("usuarioLogueado");
        System.out.println("Cliente logueado: " + usuario.getNombreUsuario());
        
        List<Cuenta> cuentasCliente = new ArrayList<>();
        try {
            for (Cuenta cuenta : cuentaNegocio.listarCuentas()) {
                if (cuenta.getCliente().getId() == usuario.getCliente().getId()) {
                    cuentasCliente.add(cuenta);
                }
            }

            request.setAttribute("cuentasCliente", cuentasCliente);
        } catch (Exception e) {
            request.setAttribute("mensaje", "Error al cargar las cuentas: " + e.getMessage());
            e.printStackTrace();
        }
        
        if ("propias".equals(accion)) {
            request.getRequestDispatcher("/cliente/TransferenciasPropias.jsp").forward(request, response);
        } else if ("terceros".equals(accion)) {
            request.getRequestDispatcher("/cliente/TransferenciasTerceros.jsp").forward(request, response);
        }
        
    }
    

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	HttpSession session = request.getSession();
        if (session == null || session.getAttribute("usuarioLogueado") == null) {
            response.sendRedirect("../Login.jsp");
            return;
        }
    	
    	String accion = request.getParameter("accion");
    	String cuentaOrigenIdStr = request.getParameter("cuentaOrigen");
        String cbuDestino = request.getParameter("cbuDestino");
        String montoStr = request.getParameter("monto");
        
        String toastMensaje = "";
	    String toastTitulo = "";
	    String toastTipo = "";

        try {
            int cuentaOrigenId = Integer.parseInt(cuentaOrigenIdStr);
            double monto = Double.parseDouble(montoStr);

            Cuenta cuentaOrigen = cuentaNegocio.obtenerCuenta(cuentaOrigenId);
            

            if (cuentaOrigen.getSaldo() < monto) {
            	toastMensaje = "Saldo insuficiente...";
                toastTitulo = "Error";
                toastTipo = "error";
            } else if(monto <= 0) {
            	toastMensaje = "No se puede transferir por un numero menor o igual a 0";
                toastTitulo = "Error";
                toastTipo = "error";            	
            }
            else {
                Cuenta cuentaDestino = null;
                for (Cuenta cuenta : cuentaNegocio.listarCuentas()) {
                    if (cuenta.getCbu().equals(cbuDestino)) {
                        cuentaDestino = cuenta;
                        break;
                    }
                }

                if (cuentaDestino == null) {
                	toastMensaje = "La cuenta a la que quieres transferir no existe.";
                    toastTitulo = "Error";
                    toastTipo = "error";
                }else if(cuentaOrigen.getCbu().equals(cbuDestino)) {
                	toastMensaje = "La cuenta destino es igual a la cuenta origen.";
                    toastTitulo = "Error";
                    toastTipo = "error";
                }else{
                    cuentaNegocio.descontarSaldo(cuentaOrigenId, monto);
                    cuentaNegocio.aumentarSaldo(cuentaDestino.getId(), monto);

                    Movimiento movOrigen = new Movimiento();
                    movOrigen.setFecha(LocalDateTime.now().toString());
                    movOrigen.setDetalle("Transferencia a CBU " + cbuDestino);
                    movOrigen.setTipoMovimiento(new TipoMovimiento(4, "Transferencia"));
                    movOrigen.setImporte(-monto);
                    movOrigen.setTipo("D");
                    movOrigen.setCuenta(cuentaOrigen);
                    movimientoNegocio.insertarMovimiento(movOrigen);

                    Movimiento movDestino = new Movimiento();
                    movDestino.setFecha(LocalDateTime.now().toString());
                    movDestino.setDetalle("Transferencia recibida de cuenta ID " + cuentaOrigenId);
                    movDestino.setTipoMovimiento(new TipoMovimiento(4, "Transferencia"));
                    movDestino.setImporte(monto);
                    movDestino.setTipo("C");
                    movDestino.setCuenta(cuentaDestino);
                    movimientoNegocio.insertarMovimiento(movDestino);

                    toastMensaje = "Transferencia exitosa!";
                    toastTitulo = "Éxito";
                    toastTipo = "success";
                    request.setAttribute("cuentaOrigen", cuentaOrigen.getNumeroCuenta());
            	    request.setAttribute("cuentaDestino", cuentaDestino.getNumeroCuenta());
            	    request.setAttribute("importe", montoStr);
                }
            
            }
        } catch (Exception e) {
        	toastMensaje = "Error al realizar la trasnferencia...";
            toastTitulo = "Error";
            toastTipo = "error";
            e.printStackTrace();
        }
        
          	    
        if ("propias".equals(accion)) {
        	session.setAttribute("toastMensaje", toastMensaje);
        	session.setAttribute("toastTitulo", toastTitulo);
        	session.setAttribute("toastTipo", toastTipo);
        	response.sendRedirect("TransferenciasServlet?accion=propias");
        } else if ("terceros".equals(accion)) {
        	session.setAttribute("toastMensaje", toastMensaje);
        	session.setAttribute("toastTitulo", toastTitulo);
        	session.setAttribute("toastTipo", toastTipo);
        	response.sendRedirect("TransferenciasServlet?accion=terceros");
        }
    }
}
