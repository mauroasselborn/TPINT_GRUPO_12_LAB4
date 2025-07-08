<%@ page import="entidades.Usuario"%>
<%@ page import="entidades.Cliente"%>
<%@ page import="entidades.Localidad"%>
<%@ page import="entidades.Provincia"%>

<%
Usuario usuario = (Usuario) session.getAttribute("usuarioLogueado");
if (usuario == null) {
	response.sendRedirect("../Login.jsp");
	return;
}
Cliente cliente = usuario.getCliente();
%>

<!-- Encabezado -->
<jsp:include page="../componentes/Encabezado.jsp" />

<!-- Sidebar -->
<jsp:include page="../componentes/MenuLateralCliente.jsp" />

<!-- Contenedor principal -->
<div class="main-content">
	<!-- Navbar -->
	<jsp:include page="../componentes/BarraSuperior.jsp" />
	<!-- Contenido principal -->
	<div class="container-fluid content py-4">
		<div class="card mx-auto shadow" style="max-width: 600px;">
			<div class="card-body">
				<h4 class="card-title text-center mb-4">Mi Perfil</h4>

				<form>
					<div class="row mb-3">
						<div class="col">
							<label class="form-label">Nombre</label> <input type="text"
								class="form-control" value="<%=cliente.getNombre()%>" readonly>
						</div>
						<div class="col">
							<label class="form-label">Apellido</label> <input type="text"
								class="form-control" value="<%=cliente.getApellido()%>" readonly>
						</div>
					</div>

					<div class="row mb-3">
						<div class="col">
							<label class="form-label">DNI</label> <input type="text"
								class="form-control" value="<%=cliente.getDni()%>" readonly>
						</div>
						<div class="col">
							<label class="form-label">CUIL</label> <input type="text"
								class="form-control" value="<%=cliente.getCuil()%>" readonly>
						</div>
					</div>

					<div class="mb-3">
						<label class="form-label">Correo Electrónico</label> <input
							type="email" class="form-control"
							value="<%=cliente.getCorreoElectronico()%>" readonly>
					</div>


					<div class="row mb-3">
						<div class="col">
							<label class="form-label">Dirección</label> <input type="text"
								class="form-control" value="<%=cliente.getDireccion()%>"
								readonly>
						</div>

						<div class="col">
							<label class="form-label">Teléfono</label> <input type="text"
								class="form-control" value="<%=cliente.getTelefono()%>" readonly>
						</div>

					</div>
					<div class="row mb-3">
						<div class="col">
							<label class="form-label">Nacionalidad</label> <input type="text"
								class="form-control"
								value="<%=cliente.getNacionalidad().getDescripcion()%>" readonly>
						</div>


						<div class="col">
							<label class="form-label">Provincia</label> <input type="text"
								class="form-control"
								value="<%=cliente.getProvincia().getNombre()%>" readonly>
						</div>
						<div class="col">
							<label class="form-label">Localidad</label> <input type="text"
								class="form-control"
								value="<%=cliente.getLocalidad().getNombre()%>" readonly>
						</div>

					</div>
					<div class="row mb-3">
						<div class="col">
							<label class="form-label">Sexo</label> <input type="text"
								class="form-control" value="<%=cliente.getSexo()%>" readonly>
						</div>
						<div class="col">
							<label class="form-label">Fecha de nacimiento</label> <input
								type="date" class="form-control"
								value="<%=cliente.getFechaNacimiento()%>" readonly>
						</div>
					</div>
					<div class="mb-3">
						<label class="form-label text-center">Nombre de Usuario</label> 
						<input	type="email" class="form-control text-center"
							value="<%=usuario.getNombreUsuario()%>" readonly>
					</div>

				</form>
			</div>
		</div>
	</div>
	<!-- Footer -->
	<jsp:include page="../componentes/Footer.jsp" />
</div>
</body>
</html>
