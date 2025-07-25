<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page import="entidades.Prestamo, entidades.Cuota"%>

<jsp:include page="../componentes/Encabezado.jsp" />
<jsp:include page="../componentes/MenuLateralCliente.jsp" />

<div class="main-content">
	<jsp:include page="../componentes/BarraSuperior.jsp" />

	<div class="container content py-4">
		<h2 class="mb-4">Detalle del Préstamo</h2>

		<%
		Prestamo prestamo = (Prestamo) request.getAttribute("prestamo");
		Cuota proximaCuota = (Cuota) request.getAttribute("proximaCuota");
		Integer cuotasPendientes = (Integer) request.getAttribute("cuotasPendientes");
		String pago = request.getParameter("pago");
		%>

		<div class="table-responsive mb-4">
			<table class="table table-bordered align-middle">
				<tbody>
					<tr>
						<th>Importe solicitado</th>
						<td>$<%=String.format("%.2f", prestamo.getImportePedido())%></td>
					</tr>
					<tr>
						<th>Cuenta destino</th>
						<td><b><%=prestamo.getCuenta().getNumeroCuenta()%></b></td>
					</tr>
					<tr>
						<th>Saldo en Cuenta</th>
						<td>$<%=prestamo.getCuenta().getSaldo()%></td>
					</tr>
					<tr>
						<th>Cantidad de cuotas</th>
						<td><%=prestamo.getCantidadCuotas()%></td>
					</tr>
					<tr>
						<th>Cuotas pendientes</th>
						<td><%=cuotasPendientes%></td>
					</tr>

					<%
					if (proximaCuota != null) {
					%>
					<tr>
						<th>Próxima cuota</th>
						<td>Cuota <%=proximaCuota.getNroCuota()%> – $<%=String.format("%.2f", proximaCuota.getMonto())%>
						</td>
					</tr>
					<tr>
						<th>Fecha de alta</th>
						<td><%=prestamo.getFechaAlta()%></td>
					</tr>
					<%
					} else {
					%>
					<tr>
						<th colspan="2" class="text-center text-muted">Préstamo
							finalizado. No quedan cuotas por pagar.</th>
					</tr>
					<%
					}
					%>
				</tbody>
			</table>
		</div>

		<div class="d-flex gap-2 mb-3">
			<a href="PrestamosServlet?accion=listar" class="btn btn-secondary">
				<i class="bi bi-arrow-left"></i> Volver
			</a>

			<%
			if (proximaCuota != null) {
			%>
			<button type="button" class="btn btn-success"
				onclick="confirmarPago(<%=proximaCuota.getId()%>, <%=proximaCuota.getNroCuota()%>)">
				<i class="bi bi-cash-coin"></i> Pagar cuota
				<%=proximaCuota.getNroCuota()%>
			</button>
			<%
			}
			%>
		</div>

	</div>
	<jsp:include page="../componentes/Footer.jsp" />

	<!-- Modal de confirmación de pago de cuota -->
	<div class="modal fade" id="modalConfirmarPago" tabindex="-1"
		aria-labelledby="modalLabelPago" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="modalLabelPago">Confirmar pago</h5>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Cerrar"></button>
				</div>
				<div class="modal-body" id="mensajePago"></div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-bs-dismiss="modal">Cancelar</button>
					<form id="formPagarCuota" action="CuotasServlet" method="post">
						<input type="hidden" name="accion" value="pagarCuota" /> <input
							type="hidden" name="idPrestamo" value="<%=prestamo.getId()%>" />
						<input type="hidden" name="idCuota" id="idCuotaPago" />
						<button type="submit" class="btn btn-success">Pagar cuota
						</button>
					</form>
				</div>
			</div>
		</div>
	</div>

	<script>
  function confirmarPago(idCuota, nroCuota) {
    document.getElementById('idCuotaPago').value = idCuota;
    document.getElementById('mensajePago').innerText =
      '¿Deseas pagar la cuota número ' + nroCuota + '?';
    let modal = new bootstrap.Modal(
      document.getElementById('modalConfirmarPago')
    );
    modal.show();
  }
</script>


</div>

