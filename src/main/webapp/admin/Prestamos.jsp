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
		<h2>Gestión de Préstamos</h2>
		<table class="table table-bordered table-hover">
			<thead class="table-light">
				<tr class="text-center">
					<th>ID</th>
					<th>Cliente</th>
					<th>Importe</th>
					<th>Cuotas</th>
					<th>Fecha</th>
					<th>Estado</th>
					<th>Acciones</th>
				</tr>
			</thead>
			<tbody>
				<tr class="text-center">
					<td>1</td>
					<td>Pérez, Juan</td>
					<td>$50.000</td>
					<td>12</td>
					<td>2025-06-10</td>
					<td>PENDIENTE</td>
					<td class="text-center">
						<button type="button" class="btn btn-success btn-sm">
							<i class="bi bi-check-circle"></i> Aprobado
						</button>
						<button type="button" class="btn btn-danger btn-sm">
							<i class="bi bi-x-circle"></i> Rechazar
						</button>
					</td>

				</tr>
				<tr class="text-center">
					<td>2</td>
					<td>Gómez, Laura</td>
					<td>$80.000</td>
					<td>18</td>
					<td>2025-06-15</td>
					<td>APROBADO</td>
					<td class="text-center">
						<button type="button" class="btn btn-success btn-sm" disabled>
							<i class="bi bi-check-circle"></i> Aprobado
						</button>
						<button type="button" class="btn btn-danger btn-sm" disabled>
							<i class="bi bi-x-circle"></i> Rechazar
						</button>
					</td>

				</tr>
				<tr class="text-center">
					<td>3</td>
					<td>Martínez, Pedro</td>
					<td>$40.000</td>
					<td>6</td>
					<td>2025-06-18</td>
					<td>RECHAZADO</td>
					<td class="text-center">
						<button type="button" class="btn btn-success btn-sm" disabled>
							<i class="bi bi-check-circle"></i> Aprobado
						</button>
						<button type="button" class="btn btn-danger btn-sm" disabled>
							<i class="bi bi-x-circle"></i> Rechazar
						</button>
					</td>

				</tr>
			</tbody>
		</table>
	</div>
	<jsp:include page="../componentes/Footer.jsp" />
</div>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
