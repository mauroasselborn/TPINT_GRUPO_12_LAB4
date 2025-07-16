<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page import="entidades.Prestamo"%>
<%@ page import="entidades.Cuota"%>
<%@ page import="java.util.List"%>

<jsp:include page="../componentes/Encabezado.jsp" />

<!-- Sidebar -->
<jsp:include page="../componentes/MenuLateralCliente.jsp" />

<!-- Contenedor principal -->
<div class="main-content">
	<!-- Navbar -->
	<jsp:include page="../componentes/BarraSuperior.jsp" />

	<!-- Contenido principal -->
	<div class="container content py-4">
		<h2 class="mb-4">Detalle del Préstamo</h2>

		<%
		Prestamo prestamo = (Prestamo) request.getAttribute("prestamo");
		List<Cuota> listaCuotas = (List<Cuota>) request.getAttribute("cuotas");

		int cuotasPendientes = 0;
		Cuota proximaCuota = null;

		if (listaCuotas != null) {
			for (Cuota c : listaCuotas) {
				if (c.getFechaPago() == null) {
			cuotasPendientes++;
			if (proximaCuota == null) {
				proximaCuota = c;
			}
				}
			}
		}
		%>

		<div class="table-responsive mb-4">
			<table class="table table-bordered align-middle">
				<tbody>
					<tr>
						<th scope="row" class="w-25">Importe solicitado</th>
						<td>$<%=String.format("%.2f", prestamo.getImportePedido())%></td>
					</tr>
					<tr>
						<th scope="row">Cuenta destino</th>
						<td>CTA$ <%=prestamo.getCuenta().getNumeroCuenta()%>
						</td>
					</tr>
					<tr>
						<th scope="row">Cantidad de cuotas</th>
						<td><%=prestamo.getCantidadCuotas()%></td>
					</tr>
					<tr>
						<th scope="row">Cuotas pendientes</th>
						<td><%=cuotasPendientes%></td>
					</tr>
					<tr>
						<th scope="row">Próxima Cuota</th>
						<td>
							<%
							if (proximaCuota != null) {
							%> Cuota <%=proximaCuota.getNroCuota()%> de <%=prestamo.getCantidadCuotas()%>
							<%
							 } else {
							 %> <span class="text-muted">Sin cuotas pendientes</span> <%
							 }
							 %>
						</td>
					</tr>
					<tr>
						<th scope="row">Monto</th>
						<td>$<%=String.format("%.2f", proximaCuota.getMonto())%></td>
					</tr>
					<tr>
						<th scope="row">Fecha de alta</th>
						<td><%=prestamo.getFechaAlta()%></td>
					</tr>
				</tbody>
			</table>
		</div>

		<!-- Acciones -->
		<div class="d-flex gap-2">
			<a href="PrestamosServlet?accion=listar" class="btn btn-secondary">
				<i class="bi bi-arrow-left"></i> Volver
			</a>

			<%
			if (proximaCuota != null) {
			%>
			<form action="PrestamosServlet" method="post">
				<input type="hidden" name="accion" value="pagar" /> 
				<input type="hidden" name="idPrestamo" value="<%=prestamo.getId()%>" /> 
				<input type="hidden" name="idCuota" value="<%=proximaCuota.getId()%>" />
				<button type="submit" class="btn btn-success">
					<i class="bi bi-cash-coin"></i> Pagar cuota
					<%=proximaCuota.getNroCuota()%>
				</button>
			</form>
			<%
			}
			%>
		</div>

		<%
		String pago = (String) request.getAttribute("pago");
		if ("true".equals(pago)) {
		%>
		<div class="alert alert-success mt-3">¡El pago se realizó
			correctamente!</div>
		<%
		} else if ("false".equals(pago)) {
		%>
		<div class="alert alert-danger mt-3">Error al realizar el pago.
			Intenta nuevamente.</div>
		<%
		}
		%>
	</div>

	<jsp:include page="../componentes/Footer.jsp" />
</div>

</body>
</html>
