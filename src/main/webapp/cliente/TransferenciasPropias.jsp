<%@ page import="java.util.List"%>
<%@ page import="entidades.Cuenta"%>

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

		<%
		if (!formEnviado) {
		%>
		<!-- Sección de Formulario de transferencia -->
		<div class="form-container">
			<h1>Transferencia entre Cuentas Propias</h1>
			<form action="TransferenciasServlet?accion=propias" method="post">
				<div class="mb-3">
					<label for="origen">Cuenta Origen:</label> <select id="origen"
						name="cuentaOrigen" required>
						<%
						if (cuentas != null) {
							for (Cuenta c : cuentas) {
						%>
						<% if(c.isActivo()){ %>
						<option value="<%=c.getId()%>">
							<%=c.getNumeroCuenta()%> - Saldo: $<%=c.getSaldo()%>
						</option>
						<%
						}
						}
						}
						%>
					</select>
				</div>

				<div class="mb-3">
					<label for="destino">Cuenta Destino:</label> <select id="destino"
						name="cbuDestino" required>
						<%
						if (cuentas != null) {
							for (Cuenta c : cuentas) {
						%>
						<option value="<%=c.getCbu()%>">
							<%=c.getNumeroCuenta()%> - Saldo: $<%=c.getSaldo()%>
						</option>
						<%
						}
						}
						%>
					</select>
				</div>

				<div class="mb-3">
					<label for="importe">Importe:</label> <input type="number"
						id="importe" name="monto" step="0.01" min="0" required />
				</div>

				<div class="buttons mt-3">
					<button type="submit" class="btn btn-primary">Transferir</button>
					<button type="reset" class="btn btn-secondary">Limpiar</button>
				</div>
			</form>
		</div>

		<%
		} else {
		%>
		<!-- Sección de Resumen de la operación (después de enviar el formulario) -->
		<div class="d-flex justify-content-center mt-5">
			<div class="card shadow" style="max-width: 500px; width: 100%;">
				<div class="card-body text-center">
					<h1 class="card-title mb-4">Resumen de la Transferencia</h1>

					<p>
						<strong>Cuenta Origen:</strong>
						<%=cuentaOrigen%></p>
					<p>
						<strong>CBU Destino:</strong>
						<%=cuentaDestino%></p>
					<p>
						<strong>Importe:</strong> $<%=importe%></p>
					<hr />
					<p class="text-success fw-bold">¡Transferencia propia realizada
						con éxito!</p>

					<div class="mt-3">
						<a href="TransferenciasServlet?accion=propias"
							class="btn btn-primary">Hacer otra transferencia...</a>
					</div>
				</div>
			</div>
		</div>
		<%
		}
		%>

	</div>
	<jsp:include page="/componentes/Footer.jsp" />
</div>
</body>
</html>