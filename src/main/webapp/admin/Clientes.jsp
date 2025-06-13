<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Gestión de Clientes</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css"
	rel="stylesheet">
<link rel="stylesheet" type="text/css"
	href="https://cdn.datatables.net/1.13.6/css/jquery.dataTables.min.css" />
<style>
body {
	display: flex;
	min-height: 100vh;
	overflow-x: hidden;
	font-family: Arial, sans-serif;
	background-color: #f5f5f5;
}

#sidebar {
	min-width: 240px;
	max-width: 240px;
	background-color: #000;
}

#sidebar .nav-link, #sidebar h4, #sidebar strong {
	color: #ffffff;
}

#sidebar .nav-link:hover {
	background-color: #495057;
}

#content {
	flex-grow: 1;
	width: 100%;
}

.topbar {
	background-color: #000;
	padding: 0.75rem 1rem;
	border-bottom: 1px solid #495057;
	color: white;
}

.dataTables_wrapper .dataTables_filter input {
	margin-left: 0.5em;
	display: inline-block;
	width: auto;
}

.btn-action {
	margin-right: 5px;
}

.table td, .table th {
	vertical-align: middle;
}

.scroll-container {
	overflow-x: auto;
	width: 100%;
}
</style>
</head>
<body>


	<jsp:include page="../componentes/MenuLateral.jsp" />

	<!-- Contenido Principal -->
	<div id="content">
		<!-- Barra superior -->
		<jsp:include page="../componentes/BarraSuperior.jsp" />

		<div class="p-4">
			<div class="d-flex justify-content-between align-items-center mb-3">
				<h2 class="mb-0">Clientes</h2>
				<a href="AltaCliente.jsp" class="btn btn-primary">Agregar nuevo
					cliente</a>
			</div>

			<div class="scroll-container">
				<table id="tablaClientes"
					class="table table-bordered table-hover w-100">
					<thead class="table-dark">
						<tr>
							<th>DNI</th>
							<th>CUIL</th>
							<th>Nombre</th>
							<th>Apellido</th>
							<th>Sexo</th>
							<th>Nacionalidad</th>
							<th>Fecha Nac.</th>
							<th>Dirección</th>
							<th>Localidad</th>
							<th>Provincia</th>
							<th>Email</th>
							<th>Teléfono</th>
							<th>Acciones</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td>12345678</td>
							<td>20-12345678-3</td>
							<td>Juan</td>
							<td>Pérez</td>
							<td>M</td>
							<td>Argentina</td>
							<td>1990-01-01</td>
							<td>Calle Falsa 123</td>
							<td>Tigre</td>
							<td>Buenos Aires</td>
							<td>juan@mail.com</td>
							<td>1122334455</td>
							<td class="text-nowrap">
								<div class="d-flex">
									<a href="ModificarCliente.jsp?id=1"
										class="btn btn-warning btn-sm btn-action">Modificar</a> <a
										href="EliminarCliente.jsp?id=1" class="btn btn-danger btn-sm">Eliminar</a>
								</div>
							</td>
						</tr>
						<!-- Más registros de ejemplo... -->
					</tbody>
				</table>
			</div>
		</div>
	</div>

	<script src="https://code.jquery.com/jquery-3.7.0.min.js"></script>
	<script
		src="https://cdn.datatables.net/1.13.6/js/jquery.dataTables.min.js"></script>
	<script>
		$(document).ready(function() {
			$('#tablaClientes').DataTable({
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
