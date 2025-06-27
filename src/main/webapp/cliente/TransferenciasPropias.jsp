<!-- Encabezado -->
  <jsp:include page="../componentes/Encabezado.jsp" />

  <!-- Sidebar -->
  <jsp:include page="../componentes/MenuLateralCliente.jsp" />

  <!-- Contenedor principal -->
  <div class="main-content">
    <!-- Navbar -->
    <jsp:include page="../componentes/BarraSuperior.jsp" />
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
    String cuentaOrigen = request.getParameter("cuentaOrigen");
    String cuentaDestino = request.getParameter("cuentaDestino");
    String importe = request.getParameter("importe");

    // Verificar si el formulario fue enviado (parámetros no nulos)
    boolean formEnviado = (cuentaOrigen != null && cuentaDestino != null && importe != null);
%>

<% if (!formEnviado) { %>
    <!-- Sección de Formulario de transferencia -->
    <div class="form-container">
    <h1>Transferencia entre Cuentas Propias</h1>
        <form action="TransferenciasPropias.jsp" method="post">
            <label for="origen">Cuenta Origen:</label>
            <select id="origen" name="cuentaOrigen" required>
                <%-- Opciones de cuentas de origen (simuladas) --%>
                <% for (String c : cuentas) { %>
                    <option value="<%= c %>"><%= c %></option>
                <% } %>
            </select>
		<div class="mt-3">
            <label for="destino">Cuenta Destino:</label>
            <select id="destino" name="cuentaDestino" required>
                <%-- Opciones de cuentas de destino (simuladas) --%>
                <% for (String c : cuentas) { %>
                    <option value="<%= c %>"><%= c %></option>
                <% } %>
            </select>
		
		</div>
		
		<div  class="mt-3">
		
            <label for="importe">Importe:</label>
            <input type="number" id="importe" name="importe" step="0.01" min="0" required />
		</div>


            <div class="buttons mt-3">
                <button type="submit" class="btn btn-primary">Transferir</button>
                <button type="reset" class="btn btn-secondary">Limpiar</button>
            </div>
        </form>
    </div>
<% } else { %>
    <!-- Sección de Resumen de la operación (después de enviar el formulario) -->
    <h1>Resumen de la Transferencia.</h1>
    <div class="result-container">
        <p><strong>Cuenta Origen:</strong> <%= cuentaOrigen %></p>
        <p><strong>Cuenta Destino:</strong> <%= cuentaDestino %></p>
        <p><strong>Importe:</strong> <%= importe %></p>
        <hr />
        <p>¡Transferencia realizada con éxito!</p>
        <!-- Enlace para realizar otra transferencia (recargar formulario) -->
        <p><a href="TransferenciasPropias.jsp">Realizar otra transferencia</a></p>
    </div>
<% } %>
	</div>
	<jsp:include page="../componentes/Footer.jsp" />
</div>
</body>
</html>