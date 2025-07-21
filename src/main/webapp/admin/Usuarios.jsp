<%@ page import="entidades.Usuario"%>
<%@ page import="entidades.Provincia"%>
<%@ page import="entidades.Localidad"%>
<%@ page import="entidades.Nacionalidad"%>
<%@ page import="java.util.List"%>


<%
@SuppressWarnings("unchecked")
List<Usuario> listaUsuarios = (List<Usuario>) request.getAttribute("listausuarios");
%>

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
			<h2 class="mb-0">Usuarios</h2>

			<!-- <a href="UsuariosServlet?accion=alta" class="btn btn-primary">Agregar nuevo usuario</a> -->
		</div>
		<div class="scroll-x">
			<table id="tablaUsuarios" class="table table-bordered table-hover">
				<thead class="table-dark text-center">
					<tr>
						<th>Usuario</th>
						<th>Nombre</th>
						<th>Apellido</th>
						<th>Email</th>
						<th>Rol</th>
						<th>Acciones</th>
					</tr>
				</thead>
				<tbody>
					<%
					if (listaUsuarios != null) {
						for (Usuario usuario : listaUsuarios) {
					%>
					<tr>
						<td><%=usuario.getNombreUsuario()%></td>
						<td><%=usuario.getCliente().getNombre()%></td>
						<td><%=usuario.getCliente().getApellido()%></td>
						<td><%=usuario.getCliente().getCorreoElectronico()%></td>
						<td><%=usuario.getTipoUsuario().getDescripcion()%></td>
						<td>
							<div class="d-flex justify-content-center">


								<button
									class="btn btn-<%=usuario.isActivo() ? "danger" : "success"%> btn-sm"
									onclick="confirmar(<%=usuario.getId()%>,<%=usuario.isActivo()%>)"><%=usuario.isActivo() ? "Eliminar" : "Activar"%></button>
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
	<jsp:include page="../componentes/Footer.jsp" />
</div>


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
				<form id="formEliminar" action="UsuariosServlet" method="post"
					style="display: none;">
					<input type="hidden" name="accion" value="eliminar" /> <input
						type="hidden" name="id" id="idUsuarioEliminar" />
					<button type="submit" class="btn btn-danger">Eliminar</button>
				</form>

				<form id="formAlta" action="UsuariosServlet" method="post"
					style="display: none;">
					<input type="hidden" name="accion" value="altaLogica" /> <input
						type="hidden" name="id" id="idUsuarioAlta" />
					<button type="submit" class="btn btn-success">Dar de Alta</button>
				</form>


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
	$(document).ready(function() {
		$('#tablaUsuarios').DataTable({
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

	

	function confirmar(id, activo) {
	    let modal = new bootstrap.Modal(document.getElementById('modalConfirmacion'));
	    let label = document.getElementById('modalLabel');
	    let mensaje = document.getElementById('mensaje');
	    let formAlta = document.getElementById('formAlta');
	    let formEliminar = document.getElementById('formEliminar');

	    document.getElementById('idUsuarioEliminar').value = id;
	    document.getElementById('idUsuarioAlta').value = id;

	    if (activo) {
	        label.innerText = "Confirmar eliminación";
	        mensaje.innerText = "¿Estás seguro que deseas eliminar este usuario?";
	        formEliminar.style.display = 'block';
	        formAlta.style.display = 'none';
	    } else {
	        label.innerText = "Confirmar activación";
	        mensaje.innerText = "¿Deseas volver a activar este usuario?";
	        formEliminar.style.display = 'none';
	        formAlta.style.display = 'block';
	    }

	    modal.show();
	}

</script>

</body>
</html>
