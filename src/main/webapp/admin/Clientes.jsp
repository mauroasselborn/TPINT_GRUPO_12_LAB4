<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Gestión de Clientes</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
<link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css" rel="stylesheet">
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
.table td, .table th {
	vertical-align: middle;
}
.scroll-x {
	overflow-x: auto;
	width: 100%;
}
</style>
</head>
<body>

<jsp:include page="../componentes/MenuLateral.jsp" />
<div id="content">
	<jsp:include page="../componentes/BarraSuperior.jsp" />

	<div class="p-4">
		<div class="d-flex justify-content-between align-items-center mb-3">
			<h2 class="mb-0">Clientes</h2>
			<a href="AltaCliente.jsp" class="btn btn-primary">Agregar nuevo cliente</a>
		</div>
		<div class="scroll-x">
			<table class="table table-bordered table-hover">
				<thead class="table-dark">
					<tr>
						<th>DNI</th>
						<th>Nombre</th>
						<th>Apellido</th>
						<th>Email</th>
						<th>Teléfono</th>
						<th>Fecha Nacimiento</th>
						<th>Acciones</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>12345678</td>
						<td>Juan</td>
						<td>Pérez</td>
						<td>juan@mail.com</td>
						<td>1122334455</td>
						<td>1990-01-01</td>
						<td>
							<div class="d-flex justify-content-center">
								<button class="btn btn-info btn-sm me-2" onclick="abrirModalCliente('detalle')">Detalle</button>
								<button class="btn btn-warning btn-sm me-2" onclick="abrirModalCliente('modificar')">Modificar</button>
								<button class="btn btn-danger btn-sm" onclick="abrirModalEliminar()">Eliminar</button>
							</div>
						</td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
</div>

<!-- Modal Confirmar Eliminación -->
<div class="modal fade" id="modalEliminar" tabindex="-1" aria-labelledby="modalEliminarLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="modalEliminarLabel">Confirmar Eliminación</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
        ¿Estás seguro que querés eliminar este cliente?
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
        <button type="button" class="btn btn-danger">Aceptar</button>
      </div>
    </div>
  </div>
</div>

<!-- Modal Detalle/Modificar Cliente -->
<div class="modal fade" id="modalCliente" tabindex="-1" aria-labelledby="modalClienteLabel" aria-hidden="true">
  <div class="modal-dialog modal-lg">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="modalClienteLabel">Detalle del Cliente</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
        <form id="formCliente">
          <div class="row g-3">
            <div class="col-md-6">
              <label class="form-label">DNI</label>
              <input type="text" class="form-control" value="12345678" name="dni">
            </div>
            <div class="col-md-6">
              <label class="form-label">CUIL</label>
              <input type="text" class="form-control" value="20-12345678-3" name="cuil">
            </div>
            <div class="col-md-6">
              <label class="form-label">Nombre</label>
              <input type="text" class="form-control" value="Juan" name="nombre">
            </div>
            <div class="col-md-6">
              <label class="form-label">Apellido</label>
              <input type="text" class="form-control" value="Pérez" name="apellido">
            </div>
            <div class="col-md-6">
              <label class="form-label">Sexo</label>
              <input type="text" class="form-control" value="M" name="sexo">
            </div>
            <div class="col-md-6">
              <label class="form-label">Nacionalidad</label>
              <input type="text" class="form-control" value="Argentina" name="nacionalidad">
            </div>
            <div class="col-md-6">
              <label class="form-label">Fecha Nacimiento</label>
              <input type="date" class="form-control" value="1990-01-01" name="fechaNac">
            </div>
            <div class="col-md-6">
              <label class="form-label">Email</label>
              <input type="email" class="form-control" value="juan@mail.com" name="email">
            </div>
            <div class="col-md-6">
              <label class="form-label">Teléfono</label>
              <input type="text" class="form-control" value="1122334455" name="telefono">
            </div>
            <div class="col-md-6">
              <label class="form-label">Dirección</label>
              <input type="text" class="form-control" value="Calle Falsa 123" name="direccion">
            </div>
            <div class="col-md-6">
              <label class="form-label">Localidad</label>
              <input type="text" class="form-control" value="Tigre" name="localidad">
            </div>
            <div class="col-md-6">
              <label class="form-label">Provincia</label>
              <input type="text" class="form-control" value="Buenos Aires" name="provincia">
            </div>
          </div>
        </form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cerrar</button>
        <button type="submit" class="btn btn-success" id="btnGuardarCambios">Guardar Cambios</button>
      </div>
    </div>
  </div>
</div>

<script>
function abrirModalCliente(modo) {
  const modal = new bootstrap.Modal(document.getElementById('modalCliente'));
  const form = document.getElementById('formCliente');
  const inputs = form.querySelectorAll('input');
  const btnGuardar = document.getElementById('btnGuardarCambios');
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

function abrirModalEliminar() {
  const modal = new bootstrap.Modal(document.getElementById('modalEliminar'));
  modal.show();
}
</script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
