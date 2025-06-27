<%@ page import="entidades.Cliente"%>
<%@ page import="entidades.Provincia"%>
<%@ page import="entidades.Localidad"%>
<%@ page import="entidades.Nacionalidad"%>
<%@ page import="java.util.List"%>


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


		<div class="d-flex justify-content-between align-items-center mb-3">
			<h2 class="mb-0">Clientes</h2>
			<a href="ClientesServlet?accion=alta" class="btn btn-primary">Agregar nuevo
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
						<th>Teléfono</th>
						<th>Fecha Nacimiento</th>
						<th>Acciones</th>
					</tr>
				</thead>



				<tbody>
					<%
					List<Cliente> listaClientes = (List<Cliente>) request.getAttribute("listaClientes");
					if (listaClientes != null) {
						for (int i = 0; i < listaClientes.size(); i++) {
							Cliente cliente = listaClientes.get(i);
					%>
					<tr>
						<td><%=cliente.getDni()%></td>
						<td><%=cliente.getNombre()%></td>
						<td><%=cliente.getApellido()%></td>
						<td><%=cliente.getCorreoElectronico()%></td>
						<td><%=cliente.getTelefono()%></td>
						<td><%=cliente.getFechaNacimiento()%></td>
						<td>
							<div class="d-flex justify-content-center">
								<a href="ClientesServlet?accion=detalle&id=<%=cliente.getId()%>" class="btn btn-info btn-sm me-2">Detalle</a> 
								<a href="ClientesServlet?accion=editar&id=<%=cliente.getId()%>" class="btn btn-warning btn-sm me-2">Modificar</a>
								<button
									class="btn btn-<%=cliente.isActivo() ? "danger" : "success"%> btn-sm"
									onclick="confirmar(<%=cliente.getId()%>,<%=cliente.isActivo()%>)"><%=cliente.isActivo() ? "Eliminar" : "Activar"%></button>
							</div>
						</td>
					</tr>
					<%}}%>
				</tbody>
			</table>

		</div>
	</div>


	<jsp:include page="../componentes/Footer.jsp" />

	<!-- Modal de confirmación -->
	<div class="modal fade" id="modalConfirmacion" tabindex="-1"
		aria-labelledby="modalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="modalLabel"></h5>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Cerrar"></button>
				</div>
				<div class="modal-body" id="mensaje"></div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-bs-dismiss="modal">Cancelar</button>
					<form id="formEliminar" action="ClientesServlet" method="post"
						style="display: none;">
						<input type="hidden" name="accion" value="eliminar" /> 
						<input type="hidden" name="id" id="idClienteEliminar" />
						<button type="submit" class="btn btn-danger">Eliminar</button>
					</form>

					<form id="formAlta" action="ClientesServlet" method="post"
						style="display: none;">
						<input type="hidden" name="accion" value="altaLogica" /> 
						<input type="hidden" name="id" id="idClienteAlta" />
						<button type="submit" class="btn btn-success">Dar de Alta</button>
					</form>


				</div>
			</div>
		</div>
	</div>
</div>


<script>
$(document).ready(function () {
    $('#tablaClientes').DataTable({
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


function confirmar(id, activo) {
    let modal = new bootstrap.Modal(document.getElementById('modalConfirmacion'));
    let label = document.getElementById('modalLabel');
    let mensaje = document.getElementById('mensaje');
    let formAlta = document.getElementById('formAlta');
    let formEliminar = document.getElementById('formEliminar');

    document.getElementById('idClienteEliminar').value = id;
    document.getElementById('idClienteAlta').value = id;

    if (activo) {
        label.innerText = "Confirmar eliminación";
        mensaje.innerText = "¿Estás seguro que deseas eliminar este cliente?";
        formEliminar.style.display = 'block';
        formAlta.style.display = 'none';
    } else {
        label.innerText = "Confirmar activación";
        mensaje.innerText = "¿Deseas volver a activar este cliente?";
        formEliminar.style.display = 'none';
        formAlta.style.display = 'block';
    }

    modal.show();
}

</script>
</body>
</html>