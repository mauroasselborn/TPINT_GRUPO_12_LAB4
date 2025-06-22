<!-- Encabezado -->
<jsp:include page="../componentes/Encabezado.jsp" />

<!-- Sidebar -->
<jsp:include page="../componentes/MenuLateralAdmin.jsp" />

<!-- Contenedor principal -->
<div class="main-content">
	<!-- Navbar -->
	<jsp:include page="../componentes/BarraSuperiorAdmin.jsp" />
	<!-- Contenido principal -->
	<div class="container-fluid content py-4">

		<div class="p-4">
			<div class="d-flex justify-content-between align-items-center mb-3">
				<h2 class="mb-0">Cuentas</h2>
				<a href="AltaCuenta.jsp" class="btn btn-primary">Agregar nueva
					cuenta</a>
			</div>

			<div class="scroll-container">
				<table id="tablaCuentas"
					class="table table-bordered table-hover w-100">
					<thead class="table-dark">
						<tr>
							<th>N° Cuenta</th>
							<th>CBU</th>
							<th>Tipo</th>
							<th>Saldo</th>
							<th>Fecha Alta</th>
							<th>Cliente</th>
							<th>Acciones</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td>001-0001</td>
							<td>2850590940090418135201</td>
							<td>Caja de ahorro</td>
							<td>$10.000,00</td>
							<td>2025-06-01</td>
							<td>Juan Pérez</td>
							<td class="text-nowrap">
								<div class="d-flex justify-content-center">
									<a href="ModificarCuenta.jsp?id=1"
										class="btn btn-warning btn-sm btn-action">Modificar</a>
									<button class="btn btn-danger btn-sm"
										onclick="confirmarEliminacion(1)">Eliminar</button>
								</div>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
	</div>
	<jsp:include page="../componentes/Footer.jsp" />
</div>


<!-- Modal de confirmación -->
<div class="modal fade" id="modalEliminar" tabindex="-1"
	aria-labelledby="modalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="modalLabel">Confirmar Eliminación</h5>
				<button type="button" class="btn-close" data-bs-dismiss="modal"
					aria-label="Cerrar"></button>
			</div>
			<div class="modal-body">¿Estás seguro que querés eliminar esta
				cuenta?</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-secondary"
					data-bs-dismiss="modal">Cancelar</button>
				<form id="formEliminar" action="EliminarCuentaServlet" method="post">
					<input type="hidden" name="idCuenta" id="idCuentaEliminar">
					<button type="submit" class="btn btn-danger">Eliminar</button>
				</form>
			</div>
		</div>
	</div>
</div>
<!-- JQUERY -->
<script src="https://code.jquery.com/jquery-3.7.0.min.js"></script>
<!-- DATATABLES -->
<script
	src="https://cdn.datatables.net/1.13.6/js/jquery.dataTables.min.js"></script>

<script>
	$(document).ready(function() {
		var tabla = $('#tablaCuentas').DataTable({
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

	function confirmarEliminacion(id) {
		document.getElementById('idCuentaEliminar').value = id;
		let modal = new bootstrap.Modal(document
				.getElementById('modalEliminar'));
		modal.show();
	}
</script>
</body>
</html>
