<%@ page import="java.util.List"%>
<%@ page
	import="entidades.Prestamo, entidades.Cliente, entidades.Cuenta"%>

<!-- Encabezado -->
<jsp:include page="../componentes/Encabezado.jsp" />

<!-- Sidebar -->
<jsp:include page="../componentes/MenuLateralAdmin.jsp" />

<!-- Contenedor principal -->
<div class="main-content">
	<!-- Navbar -->
	<jsp:include page="../componentes/BarraSuperior.jsp" />

	<!-- Contenido principal -->
	<div class="container-fluid content py-4">
		<h2 class="mb-4">Gestión de Préstamos</h2>
		<div class="scroll-x">
			<table id="tablaPrestamos" class="table table-bordered table-hover">
				<thead class="table-dark">
					<tr class="text-center">
						<th>ID</th>
						<th>Cliente</th>
						<th>Cuenta Destino</th>
						<th>Importe Pedido</th>
						<th>Cuotas</th>
						<th>Importe por Cuota</th>
						<th>Fecha Alta</th>
						<th>Estado</th>
						<th>Acciones</th>
					</tr>
				</thead>
				<tbody>
					<%
					@SuppressWarnings("unchecked")
					List<Prestamo> prestamos = (List<Prestamo>) request.getAttribute("prestamos");
					if (prestamos != null && !prestamos.isEmpty()) {
						for (Prestamo p : prestamos) {
							String estadoDesc = "";
							boolean pendiente = false;
							switch (p.getIdEstado()) {
						case 1 :
							estadoDesc = "PENDIENTE";
							pendiente = true;
							break;
						case 2 :
							estadoDesc = "APROBADO";
							break;
						case 3 :
							estadoDesc = "RECHAZADO";
							break;
							}

							Cliente c = p.getCliente();
							Cuenta cuenta = p.getCuenta();
							
					%>
					<tr class="text-center">
						<td><%=p.getId()%></td>
						<td><%=c != null ? (c.getApellido() + ", " + c.getNombre()) : "N/A"%></td>
						<td><%=cuenta != null ? cuenta.getNumeroCuenta() : "N/A"%></td>
						<td>$<%=p.getImportePedido()%></td>
						<td><%=p.getCantidadCuotas()%></td>
						<td>$<%=p.getImportePorCuota()%></td>
						<td><%=p.getFechaAlta()%></td>
						<td><%=estadoDesc%></td>
						<td>
							<form method="post" action="PrestamosServlet"
								class="d-flex justify-content-center gap-2">
								<input type="hidden" name="idPrestamo" value="<%=p.getId()%>" />
								<button type="submit" name="accion" value="aprobar"
									class="btn btn-success btn-sm" <%=pendiente ? "" : "disabled"%>>
									<i class="bi bi-check-circle"></i> Aprobar
								</button>
								<button type="submit" name="accion" value="rechazar"
									class="btn btn-danger btn-sm" <%=pendiente ? "" : "disabled"%>>
									<i class="bi bi-x-circle"></i> Rechazar
								</button>
							</form>
						</td>
					</tr>
					<%
					}
					}
					%>
				</tbody>
			</table>
		</div>
	</div>
	<jsp:include page="../componentes/Footer.jsp" />
</div>

<script>
	$(document).ready(function() {
		$('#tablaPrestamos').DataTable({
			responsive : true,
			autoWidth : false,
			language : {
				search : "Filtrar:",
				lengthMenu : "Mostrar _MENU_ registros por página",
				zeroRecords : "No se encontraron resultados",
				info : "Mostrando _START_ a _END_ de _TOTAL_ registros",
				infoEmpty : "Mostrando 0 a 0 de 0 registros",
				infoFiltered : "(filtrado de _MAX_ registros totales)",
				paginate : {
					first : "Primero",
					last : "Último",
					next : "Siguiente",
					previous : "Anterior"
				}
			}
		});
	});
</script>
</body>
</html>
