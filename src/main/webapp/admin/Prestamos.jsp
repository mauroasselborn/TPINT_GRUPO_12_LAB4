<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Gestionar Préstamos</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
	rel="stylesheet">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css"
	rel="stylesheet">
<link rel="stylesheet" href="../css/styles.css">
</head>
<body>

	<!-- Sidebar -->
	<jsp:include page="../componentes/MenuLateralAdmin.jsp" />
	<div class="main-content">
		<!-- Navbar -->
		<jsp:include page="../componentes/BarraSuperiorAdmin.jsp" />
		<div class="container content">
			<h2>Gestión de Préstamos</h2>
			<table class="table table-bordered table-hover">
				<thead class="table-light">
					<tr>
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
					<tr>
						<td>1</td>
						<td>Pérez, Juan</td>
						<td>$50.000</td>
						<td>12</td>
						<td>2025-06-10</td>
						<td>PENDIENTE</td>
						<td>
							<button type="button" class="btn btn-success btn-sm">
								<i class="bi bi-check-circle"></i> Aprobar
							</button>
							<button type="button" class="btn btn-danger btn-sm">
								<i class="bi bi-x-circle"></i> Rechazar
							</button>
						</td>
					</tr>
					<tr>
						<td>2</td>
						<td>Gómez, Laura</td>
						<td>$80.000</td>
						<td>18</td>
						<td>2025-06-15</td>
						<td>APROBADO</td>
						<td>
							<button type="button" class="btn btn-success btn-sm" disabled>
								<i class="bi bi-check-circle"></i> Aprobado
							</button>
							<button type="button" class="btn btn-danger btn-sm" disabled>
								<i class="bi bi-x-circle"></i> Rechazar
							</button>
						</td>
					</tr>
					<tr>
						<td>3</td>
						<td>Martínez, Pedro</td>
						<td>$40.000</td>
						<td>6</td>
						<td>2025-06-18</td>
						<td>RECHAZADO</td>
						<td>
							<button type="button" class="btn btn-success btn-sm" disabled>
								<i class="bi bi-check-circle"></i> Aprobar
							</button>
							<button type="button" class="btn btn-danger btn-sm" disabled>
								<i class="bi bi-x-circle"></i> Rechazado
							</button>
						</td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
