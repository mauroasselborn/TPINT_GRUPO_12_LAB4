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

		<div class="d-flex justify-content-between align-items-center mb-3">
			<h2 class="mb-0">Clientes</h2>
			<a href="AltaCliente.jsp" class="btn btn-primary">Agregar nuevo
				cliente</a>
		</div>
		<div class="scroll-x">
			<table id="tablaClientes" class="table table-bordered table-hover">
				<thead class="table-dark">
					<tr>
						<th>DNI</th>
						<th>Nombre</th>
						<th>Apellido</th>
						<th>Email</th>
						<th>Tel�fono</th>
						<th>Fecha Nacimiento</th>
						<th>Acciones</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>12345678</td>
						<td>Juan</td>
						<td>P�rez</td>
						<td>juan@mail.com</td>
						<td>1122334455</td>
						<td>1990-01-01</td>
						<td>
							<div class="d-flex justify-content-center">
								<button class="btn btn-info btn-sm me-2"
									onclick="abrirModalCliente('detalle')">Detalle</button>
								<button class="btn btn-warning btn-sm me-2"
									onclick="abrirModalCliente('modificar')">Modificar</button>
								<button class="btn btn-danger btn-sm"
									onclick="abrirModalEliminarCliente()">Eliminar</button>
							</div>
						</td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>

	<!-- Modal Detalle/Modificar Cliente -->
	<div class="modal fade" id="modalCliente" tabindex="-1"
		aria-labelledby="modalClienteLabel" aria-hidden="true">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="modalClienteLabel">Detalle del
						Cliente</h5>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
				</div>
				<div class="modal-body">
					<form id="formCliente">
						<div class="row g-3">
							<div class="col-md-6">
								<label class="form-label">DNI</label> <input type="text"
									class="form-control" value="12345678" name="dni">
							</div>
							<div class="col-md-6">
								<label class="form-label">CUIL</label> <input type="text"
									class="form-control" value="20-12345678-3" name="cuil">
							</div>
							<div class="col-md-6">
								<label class="form-label">Nombre</label> <input type="text"
									class="form-control" value="Juan" name="nombre">
							</div>
							<div class="col-md-6">
								<label class="form-label">Apellido</label> <input type="text"
									class="form-control" value="P�rez" name="apellido">
							</div>
							<div class="col-md-6">
								<label class="form-label">Sexo</label> <input type="text"
									class="form-control" value="M" name="sexo">
							</div>
							<div class="col-md-6">
								<label class="form-label">Nacionalidad</label> <input
									type="text" class="form-control" value="Argentina"
									name="nacionalidad">
							</div>
							<div class="col-md-6">
								<label class="form-label">Fecha Nacimiento</label> <input
									type="date" class="form-control" value="1990-01-01"
									name="fechaNac">
							</div>
							<div class="col-md-6">
								<label class="form-label">Email</label> <input type="email"
									class="form-control" value="juan@mail.com" name="email">
							</div>
							<div class="col-md-6">
								<label class="form-label">Tel�fono</label> <input type="text"
									class="form-control" value="1122334455" name="telefono">
							</div>
							<div class="col-md-6">
								<label class="form-label">Direcci�n</label> <input type="text"
									class="form-control" value="Calle Falsa 123" name="direccion">
							</div>
							<div class="col-md-6">
								<label class="form-label">Localidad</label> <input type="text"
									class="form-control" value="Tigre" name="localidad">
							</div>
							<div class="col-md-6">
								<label class="form-label">Provincia</label> <input type="text"
									class="form-control" value="Buenos Aires" name="provincia">
							</div>
							<div class="col-md-6">
								<label class="form-label">Usuario</label> <input type="text"
									class="form-control" value="usuario01" name="usuario">
							</div>
							<div class="col-md-6">
								<label class="form-label">Contrase�a</label> <input
									type="password" class="form-control" value="1234"
									name="contrase�a">
							</div>
							<div class="col-md-6">
								<label class="form-label">Repetir Contrase�a</label> <input
									type="password" class="form-control" value="1234"
									name="RepContrase�a">
							</div>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-bs-dismiss="modal">Cerrar</button>
					<button type="submit" class="btn btn-success"
						id="btnGuardarCliente">Guardar Cambios</button>
				</div>
			</div>
		</div>
	</div>

	<!-- Modal Confirmar Eliminaci�n Cliente -->
	<div class="modal fade" id="modalEliminarCliente" tabindex="-1"
		aria-labelledby="modalEliminarClienteLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="modalEliminarClienteLabel">Confirmar
						Eliminaci�n</h5>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
				</div>
				<div class="modal-body">�Est�s seguro que quer�s eliminar este
					cliente?</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-bs-dismiss="modal">Cancelar</button>
					<button type="button" class="btn btn-danger">Aceptar</button>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="../componentes/Footer.jsp" />
</div>


<script>
$(document).ready(function () {
    $('#tablaClientes').DataTable({
        responsive: true,
        autoWidth: false,
        language: {
            search: "Filtrar:",
            lengthMenu: "Mostrar _MENU_ registros por p�gina",
            zeroRecords: "No se encontraron resultados",
            info: "Mostrando _START_ a _END_ de _TOTAL_ registros",
            infoEmpty: "Mostrando 0 a 0 de 0 registros",
            infoFiltered: "(filtrado de _MAX_ registros totales)",
            paginate: {
                first: "Primero",
                last: "�ltimo",
                next: "Siguiente",
                previous: "Anterior"
            }
        }
    });
});

function abrirModalCliente(modo) {
  const modal = new bootstrap.Modal(document.getElementById('modalCliente'));
  const form = document.getElementById('formCliente');
  const inputs = form.querySelectorAll('input');
  const btnGuardar = document.getElementById('btnGuardarCliente');
  const titulo = document.getElementById('modalClienteLabel');

  if (modo === 'detalle') {
    inputs.forEach(input => input.disabled = true);
    btnGuardar.style.display = 'none';
    titulo.innerText = 'Detalle del Cliente';
  } else {
    inputs.forEach(input => input.disabled = false);
    btnGuardar.style.display = 'inline-block';
    titulo.innerText = 'Modificar Cliente';
  }

  modal.show();
}

function abrirModalEliminarCliente() {
  const modal = new bootstrap.Modal(document.getElementById('modalEliminarCliente'));
  modal.show();
}
</script>
</body>
</html>