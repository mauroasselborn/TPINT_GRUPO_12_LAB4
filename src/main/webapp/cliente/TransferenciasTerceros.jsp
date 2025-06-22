<!-- Encabezado -->
  <jsp:include page="../componentes/Encabezado.jsp" />

  <!-- Sidebar -->
  <jsp:include page="../componentes/MenuLateralCliente.jsp" />

  <!-- Contenedor principal -->
  <div class="main-content">
    <!-- Navbar -->
    <jsp:include page="../componentes/BarraSuperiorCliente.jsp" />
    <!-- Contenido principal -->
    <div class="container-fluid content py-4">
<%
    // Lista de cuentas simuladas (mock) del cliente
    String[] cuentas = {
        "Caja de Ahorro (Pesos) - 00123456",
        "Cuenta Corriente (Pesos) - 00123457",
        "Caja de Ahorro (Dólares) - 00123458"
    };

    // Obtener parámetros del formulario (si existen)
    String cuentaOrigen   = request.getParameter("cuentaOrigen");
    String cbuDestino     = request.getParameter("cbuDestino");
    String importe        = request.getParameter("importe");

    // Verificar si el formulario fue enviado
    boolean formEnviado = (cuentaOrigen != null && cbuDestino != null && importe != null);
%>

<% if (!formEnviado) { %>
    <!-- Sección de Formulario de transferencia a terceros -->
    <div class="form-container">
        <h1>Transferencia a Terceros</h1>
        <form action="TransferenciasTerceros.jsp" method="post">
            <label for="origen">Cuenta Origen:</label>
            <select id="origen" name="cuentaOrigen" class="form-select" required>
                <% for (String c : cuentas) { %>
                    <option value="<%= c %>"><%= c %></option>
                <% } %>
            </select>

            <label for="cbu">CBU Destino:</label>
            <input type="text" id="cbu" name="cbuDestino" class="form-control"
                   placeholder="Ingrese CBU de la cuenta de destino"
                   title="16 dígitos numéricos" required />

            <label for="importe">Importe:</label>
            <input type="number" id="importe" name="importe" class="form-control"
                   step="0.01" min="0" placeholder="0.00" required />

            <div class="buttons">
                <button type="submit" class="btn btn-primary">Transferir</button>
                <button type="reset" class="btn btn-secondary">Limpiar</button>
            </div>
        </form>
    </div>
<% } else { %>
    <!-- Sección de Resumen de la operación -->
    <h1>Resumen de la Transferencia</h1>
    <div class="result-container">
        <p><strong>Cuenta Origen:</strong> <%= cuentaOrigen %></p>
        <p><strong>CBU Destino:</strong> <%= cbuDestino %></p>
        <p><strong>Importe:</strong> <%= importe %></p>
        <hr />
        <p>¡Transferencia a tercero realizada con éxito!</p>
        <div class="buttons">
            <a href="TransferenciasTerceros.jsp" class="btn btn-primary">Hacer otra transferencia...</a>
        </div>
    </div>
<% } %>
</div>
<jsp:include page="../componentes/Footer.jsp" />
</div>
</body>
</html>