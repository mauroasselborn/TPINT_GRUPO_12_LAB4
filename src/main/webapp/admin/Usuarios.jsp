<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Gestión de Usuarios</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css"
	rel="stylesheet">
<link
	href="https://cdn.datatables.net/1.13.6/css/jquery.dataTables.min.css"
	rel="stylesheet">
<link rel="stylesheet" href="../css/styles.css">
</head>
<body>

	<jsp:include page="../componentes/MenuLateralAdmin.jsp" />
	<div id="content">
		<jsp:include page="../componentes/BarraSuperiorAdmin.jsp" />

		<div class="p-4">
			<div class="d-flex justify-content-between align-items-center mb-3">
				<h2 class="mb-0">Usuarios</h2>

				<a href="#" class="btn btn-primary"
					onclick="abrirModalAltaUsuario()">Agregar nuevo usuario</a>
			</div>
			<div class="scroll-x">
				<table id="tablaUsuarios" class="table table-bordered table-hover">
					<thead class="table-dark">
						<tr>
							<th>Usuario</th>
							<th>Nombre</th>
							<th>Apellido</th>
							<th>Email</th>
							<th>Rol</th>
							<th>Estado</th>
							<th>Acciones</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td>jadmin</td>
							<td>Juan</td>
							<td>Pérez</td>
							<td>juan@mail.com</td>
							<td>Administrador</td>
							<td>Activo</td>
							<td>
								<div class="d-flex justify-content-center">
									<button class="btn btn-warning btn-sm me-2"
										onclick="abrirModalUsuario('modificar')">Modificar</button>
									<button class="btn btn-danger btn-sm"
										onclick="abrirModalEliminarUsuario()">Eliminar</button>
								</div>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
	</div>

	<!-- Modal Alta Usuario -->
	<div class="modal fade" id="modalAltaUsuario" tabindex="-1"
		aria-labelledby="modalAltaUsuarioLabel" aria-hidden="true">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="modalAltaUsuarioLabel">Alta de
						Usuario</h5>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
				</div>
				<div class="modal-body">
					<form id="formAltaUsuario">
						<div class="row g-3">
							<div class="col-md-6">
								<label class="form-label">Usuario</label> <input type="text"
									class="form-control" name="usuario">
							</div>
							<div class="col-md-6">
								<label class="form-label">Nombre</label> <input type="text"
									class="form-control" name="nombre">
							</div>
							<div class="col-md-6">
								<label class="form-label">Apellido</label> <input type="text"
									class="form-control" name="apellido">
							</div>
							<div class="col-md-6">
								<label class="form-label">Email</label> <input type="email"
									class="form-control" name="email">
							</div>
							<div class="col-md-6">
								<label class="form-label">Rol</label> <input type="text"
									class="form-control" name="rol">
							</div>
							<div class="col-md-6">
								<label class="form-label">Estado</label> <input type="text"
									class="form-control" name="estado">
							</div>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-bs-dismiss="modal">Cancelar</button>
					<button type="submit" class="btn btn-success"
						form="formAltaUsuario">Guardar Usuario</button>
				</div>
			</div>
		</div>
	</div>

	<!-- Modal Confirmar Eliminación Usuario -->
	<div class="modal fade" id="modalEliminarUsuario" tabindex="-1"
		aria-labelledby="modalEliminarUsuarioLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="modalEliminarUsuarioLabel">Confirmar
						Eliminación</h5>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
				</div>
				<div class="modal-body">¿Estás seguro que querés eliminar este
					usuario?</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-bs-dismiss="modal">Cancelar</button>
					<button type="button" class="btn btn-danger">Aceptar</button>
				</div>
			</div>
		</div>
	</div>

	<!-- Modal Modificar Usuario -->
	<div class="modal fade" id="modalUsuario" tabindex="-1"
		aria-labelledby="modalUsuarioLabel" aria-hidden="true">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="modalUsuarioLabel">Modificar
						Usuario</h5>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
				</div>
				<div class="modal-body">
					<form id="formUsuario">
						<div class="row g-3">
							<div class="col-md-6">
								<label class="form-label">Usuario</label> <input type="text"
									class="form-control" value="jadmin" name="usuario">
							</div>
							<div class="col-md-6">
								<label class="form-label">Nombre</label> <input type="text"
									class="form-control" value="Juan" name="nombre">
							</div>
							<div class="col-md-6">
								<label class="form-label">Apellido</label> <input type="text"
									class="form-control" value="Pérez" name="apellido">
							</div>
							<div class="col-md-6">
								<label class="form-label">Email</label> <input type="email"
									class="form-control" value="juan@mail.com" name="email">
							</div>
							<div class="col-md-6">
								<label class="form-label">Rol</label> <input type="text"
									class="form-control" value="Administrador" name="rol">
							</div>
							<div class="col-md-6">
								<label class="form-label">Estado</label> <input type="text"
									class="form-control" value="Activo" name="estado">
							</div>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-bs-dismiss="modal">Cerrar</button>
					<button type="submit" class="btn btn-success"
						id="btnGuardarUsuario">Guardar Cambios</button>
				</div>
			</div>
		</div>
	</div>

	<!-- Scripts -->
	<script src="https://code.jquery.com/jquery-3.7.0.min.js"></script>
	<script
		src="https://cdn.datatables.net/1.13.6/js/jquery.dataTables.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
	<script>
$(document).ready(function () {
    $('#tablaUsuarios').DataTable({
        responsive: true,
        autoWidth: false,
        language: {
            search: "Filtrar:",
            lengthMenu: "Mostrar _MENU_ registros por página",
            zeroRecords: "No se encontraron resultados",
            info: "Mostrando _START_ a _END_ de _TOTAL_ registros",
            infoEmpty: "Mostrando 0 a 0 de 0 registros",
            infoFiltered: "(filtrado de _MAX_ registros totales)",
            paginate: {
                first: "Primero",
                last: "Último",
                next: "Siguiente",
                previous: "Anterior"
            }
        }
    });
});

function abrirModalUsuario(modo) {
  const modal = new bootstrap.Modal(document.getElementById('modalUsuario'));
  const form = document.getElementById('formUsuario');
  const inputs = form.querySelectorAll('input');
  const btnGuardar = document.getElementById('btnGuardarUsuario');
  const titulo = document.getElementById('modalUsuarioLabel');

  inputs.forEach(input => input.disabled = false);
  btnGuardar.style.display = 'inline-block';
  titulo.innerText = 'Modificar Usuario';

  modal.show();
}

function abrirModalEliminarUsuario() {
  const modal = new bootstrap.Modal(document.getElementById('modalEliminarUsuario'));
  modal.show();
}

function abrirModalAltaUsuario() {
  const modal = new bootstrap.Modal(document.getElementById('modalAltaUsuario'));
  modal.show();
}
</script>

</body>
</html>
