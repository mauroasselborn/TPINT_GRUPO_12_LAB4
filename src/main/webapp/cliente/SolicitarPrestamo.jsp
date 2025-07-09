<%@ page import="java.util.List, entidades.Cuenta" %>
<%@ page import="java.text.DecimalFormat" %>

<!-- Encabezado -->
<jsp:include page="../componentes/Encabezado.jsp" />

<!-- Sidebar -->
<jsp:include page="../componentes/MenuLateralCliente.jsp" />

<!-- Contenedor principal -->
<div class="main-content">
	<!-- Navbar -->
	<jsp:include page="../componentes/BarraSuperior.jsp" />

	<!-- Contenido principal -->
	<div class="container content py-4">

		<h2 class="mb-4">Solicitud de Préstamo</h2>

		<form action="PrestamosServlet" method="post" class="border p-4 rounded bg-light">
			
			<div class="form-group mb-3">
				<label for="monto">Monto solicitado ($):</label>
				<input type="number" class="form-control" id="monto" name="monto" placeholder="Ej: 50000" step="0.01" min="1" required>
			</div>

			<div class="form-group mb-3">
				<label for="cuotas">Cantidad de cuotas:</label>
				<select class="form-select" id="cuotas" name="cuotas" required>
					<option value="">Seleccione</option>
					<option value="3">3 cuotas</option>
					<option value="6">6 cuotas</option>
					<option value="12">12 cuotas</option>
					<option value="24">24 cuotas</option>
				</select>
			</div>

			<%
				// Obtener cuentas enviadas por el servlet
				List<Cuenta> cuentas = (List<Cuenta>) request.getAttribute("cuentas");
			%>

			<div class="form-group mb-4">
				<label for="cuentaDestino" class="form-label fw-bold text-primary">Elige tu cuenta:</label> 
				<select class="form-select" id="cuentaDestino" name="cuentaDestino" required>
					<option value="">Seleccione una cuenta</option>
					<%
						if (cuentas != null) {
							for (Cuenta c : cuentas) {
					%>
					<option value="<%=c.getId()%>">
						CTA$ <%=c.getNumeroCuenta()%> /
						<%=c.getTipoCuenta().getDescripcion()%> - Saldo: $<%=c.getSaldo()%>
					</option>
					<%
							}
						}
					%>
				</select>
			</div>

			<input type="submit" class="btn btn-primary w-100" value="Solicitar Préstamo">
		</form>
	</div>

	<!-- Footer -->
	<jsp:include page="../componentes/Footer.jsp" />
</div>

</body>
</html>
