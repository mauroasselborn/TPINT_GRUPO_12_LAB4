<%@ page import="java.util.List" %>
<%@ page import="entidades.Cuenta" %>
<!-- Encabezado -->
  <jsp:include page="/componentes/Encabezado.jsp" />

  <!-- Sidebar -->
  <jsp:include page="/componentes/MenuLateralCliente.jsp" />

  <!-- Contenedor principal -->
  <div class="main-content">
    <!-- Navbar -->
    <jsp:include page="/componentes/BarraSuperior.jsp" />
    <!-- Contenido principal -->
    <div class="container-fluid content py-4">
<%
	@SuppressWarnings("unchecked")
    List<Cuenta> cuentas = (List<Cuenta>) request.getAttribute("cuentasCliente");

String cuentaOrigen = (String) request.getAttribute("cuentaOrigen");
String cuentaDestino = (String) request.getAttribute("cuentaDestino");
String importe = (String) request.getAttribute("importe");

    boolean formEnviado = (cuentaOrigen != null && cuentaDestino != null && importe != null);
%>

<% if (!formEnviado) { %>
    <!-- Sección de Formulario de transferencia a terceros -->
    <div class="form-container">
        <h1>Transferencia a Terceros</h1>
       <form action="TransferenciasServlet?accion=terceros" method="post">
            <label for="origen">Cuenta Origen:</label>
            <select id="origen" name="cuentaOrigen" class="form-select" required>
                <% if (cuentas != null) {
       for (Cuenta c : cuentas) { %>
    <option value="<%= c.getId() %>">
        <%= c.getNumeroCuenta() %> - Saldo: $<%= c.getSaldo() %>
    </option>
<%   }
   } %>
            </select>

            <label for="cbu">CBU Destino:</label>
            <input type="text" id="cbu" name="cbuDestino" class="form-control"
                   placeholder="Ingrese CBU de la cuenta de destino"
                   title="16 dígitos numéricos" required />

            <label for="importe">Importe:</label>
            <input type="number" id="importe" name="monto" class="form-control"
                   step="0.01" min="0" placeholder="0.00" required />

            <div class="buttons">
                <button type="submit" class="btn btn-primary">Transferir</button>
                <button type="reset" class="btn btn-secondary">Limpiar</button>
            </div>
        </form>
    </div>
<% } else { %>
    <!-- Sección de Resumen de la operación -->
    <div class="d-flex justify-content-center mt-5">
  <div class="card shadow" style="max-width: 500px; width: 100%;">
    <div class="card-body text-center">
      <h1 class="card-title mb-4">Resumen de la Transferencia</h1>

      <p><strong>Cuenta Origen:</strong> <%= cuentaOrigen %></p>
      <p><strong>CBU Destino:</strong> <%= cuentaDestino %></p>
      <p><strong>Importe:</strong> $<%= importe %></p>
      <hr />
      <p class="text-success fw-bold">¡Transferencia a tercero realizada con éxito!</p>

      <div class="mt-3">
        <a href="TransferenciasServlet?accion=terceros" class="btn btn-primary">Hacer otra transferencia...</a>
      </div>
    </div>
  </div>
</div>
<% } %>
</div>
<jsp:include page="/componentes/Footer.jsp" />
</div>
<% if (request.getAttribute("toastMensaje") != null && request.getAttribute("toastTitulo") != null && request.getAttribute("toastTipo") != null) { %>
<script>
$(document).ready(function() {
    toastr.options = {
      "closeButton": true,
      "debug": false,
      "newestOnTop": false,
      "progressBar": true,
      "positionClass": "toast-bottom-right",
      "preventDuplicates": true,
      "showDuration": "300",
      "hideDuration": "1000",
      "timeOut": "2000",
      "extendedTimeOut": "1000",
      "showEasing": "swing",
      "hideEasing": "linear",
      "showMethod": "fadeIn",
      "hideMethod": "fadeOut"
    };

    Command: toastr["<%= request.getAttribute("toastTipo") %>"](
        "<%= request.getAttribute("toastMensaje") %>",
        "<%= request.getAttribute("toastTitulo") %>"
    );
});
</script>
<% } %>
</body>
</html>