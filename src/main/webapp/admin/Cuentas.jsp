<%@ page import="java.util.List"%>
<%@ page import="entidades.Cuenta"%>



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
			<h2 class="mb-0">Cuentas</h2>
			<a href="CuentasServlet?accion=nuevo" class="btn btn-primary">Agregar
				nueva cuenta</a>
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
					<%
					@SuppressWarnings("unchecked")
					List<Cuenta> cuentas = (List<Cuenta>) request.getAttribute("cuentas");
					if (cuentas != null) {
						for (int i = 0; i < cuentas.size(); i++) {
							Cuenta c = cuentas.get(i);
					%>
					<tr>
						<td><%=c.getNumeroCuenta()%></td>
						<td><%=c.getCbu()%></td>
						<td><%=c.getTipoCuenta().getDescripcion()%></td>
						<td><%=c.getSaldo()%></td>
						<td><%=c.getFechaCreacion()%></td>
						<td><%=c.getCliente().getApellido()%>, <%=c.getCliente().getNombre()%></td>
						<td class="text-nowrap">
							<div class="d-flex justify-content-center">
								<a href="CuentasServlet?accion=editar&id=<%=c.getId()%>"
									class="btn btn-warning btn-sm me-1">Modificar</a>
								<button
									onclick="confirmar(<%=c.getId()%>,<%=c.isActivo()%>)"
									class="btn btn-<%=c.isActivo() ? "danger" : "success"%> btn-sm"
									onclick="confirmarEliminacion(<%=c.getId()%>)"><%=c.isActivo() ? "Desactivar" : "Activar"%></button>
							</div>
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

	<!-- Footer -->
	<jsp:include page="../componentes/Footer.jsp" />

</div>

<!-- Modal de confirmación -->
<div class="modal fade" id="modalConfirmacion" tabindex="-1"
	aria-labelledby="modalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="modalLabel"></h5>
				<button type="button" class="btn-close" data-bs-dismiss="modal"	aria-label="Cerrar"></button>
			</div>
			<div class="modal-body" id="mensaje"></div>
			<div class="modal-footer">
				<button type="button" class="btn btn-secondary"	data-bs-dismiss="modal">Cancelar</button>
				<form id="formEliminar" action="CuentasServlet" method="post" style="display: none;">
					<input type="hidden" name="accion" value="eliminar" /> 
					<input type="hidden" name="id" id="idCuentaEliminar" />
					<button type="submit" class="btn btn-danger">Desactivar</button>
				</form>

				<form id="formAlta" action="CuentasServlet" method="post" style="display: none;">
					<input type="hidden" name="accion" value="altaLogica" /> 
					<input type="hidden" name="id" id="idCuentaAlta" />
					<button type="submit" class="btn btn-success">Activar</button>
				</form>


			</div>
		</div>
	</div>
</div>

<!-- Scripts -->
<script src="https://code.jquery.com/jquery-3.7.0.min.js"></script>
<script
	src="https://cdn.datatables.net/1.13.6/js/jquery.dataTables.min.js"></script>
<script>
  $(document).ready(function() {
    $('#tablaCuentas').DataTable({
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
          first: "Primero", last: "Último",
          next: "Siguiente", previous: "Anterior"
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

	    document.getElementById('idCuentaEliminar').value = id;
	    document.getElementById('idCuentaAlta').value = id;

	    if (activo) {
	        label.innerText = "Confirmar eliminación";
	        mensaje.innerText = "¿Estás seguro que deseas desactivar esta cuenta?";
	        formEliminar.style.display = 'block';
	        formAlta.style.display = 'none';
	    } else {
	        label.innerText = "Confirmar activación";
	        mensaje.innerText = "¿Deseas volver a activar esta cuenta?";
	        formEliminar.style.display = 'none';
	        formAlta.style.display = 'block';
	    }

	    modal.show();
	}
</script>