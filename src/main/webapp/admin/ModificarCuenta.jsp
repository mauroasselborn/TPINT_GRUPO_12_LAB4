<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
         import="entidades.Cuenta" %>

<!-- Encabezado -->
<jsp:include page="../componentes/Encabezado.jsp" />

<!-- Sidebar -->
<jsp:include page="../componentes/MenuLateralAdmin.jsp" />

<!-- Contenedor principal -->
<div class="main-content">

  <!-- Navbar -->
  <jsp:include page="../componentes/BarraSuperior.jsp" />

  <!-- Contenido principal -->
  <div class="container content py-4">
    

    <h2>Modificar Cuenta</h2>

    <%
    Cuenta cuenta = (Cuenta) request.getAttribute("cuenta");
	String tipo = "";
	if (cuenta.getTipoCuenta().getId() == 1) tipo = "Caja de ahorro";
	else if (cuenta.getTipoCuenta().getId() == 2) tipo = "Cuenta corriente";
    %>

    <form action="CuentasServlet" method="post">
      <!-- Acción y ID ocultos -->
      <input type="hidden" name="accion" value="guardarModificacion" />
      <input type="hidden" name="id" value="<%= cuenta.getId() %>" />

      <!-- Número de cuenta (solo lectura) -->
      <label for="numero">Número de cuenta:</label>
      <input type="text" id="numero" name="numero" value="<%= cuenta.getNumeroCuenta() %>" readonly class="form-control mb-3"/>

      <!-- Tipo de cuenta -->
      <label for="tipo">Tipo de cuenta:</label>
		<p class="form-control mb-3">
 		 <%= tipo %>
		</p>

<!-- Campo oculto con el valor correspondiente (1 para Caja de ahorro, 2 para Cuenta corriente) -->
		<% if (tipo.equals("Caja de ahorro")) { %>
  		<input type="hidden" name="tipo" value="1">
			<% } else if (tipo.equals("Cuenta corriente")) { %>
  					<input type="hidden" name="tipo" value="2">
		<% } %>

      <!-- CBU -->
      <label for="cbu">CBU:</label>
      <input type="text" id="cbu" name="cbu" maxlength="22" readonly
             value="<%= cuenta.getCbu() %>" class="form-control mb-3"/>

      <!-- Saldo -->
      <label for="saldo">Saldo actual ($):</label>
      <input type="number" step="0.01" id="saldo" name="saldo" required
             value="<%= cuenta.getSaldo() %>" class="form-control mb-3"/>

      <!-- Botones -->
      <button type="submit" class="btn btn-success">Guardar Cambios</button>
      <a href="CuentasServlet?accion=listar" class="btn btn-secondary">Cancelar</a>
    </form>
  </div>

  <!-- Footer -->
  <jsp:include page="../componentes/Footer.jsp" />
</div>
