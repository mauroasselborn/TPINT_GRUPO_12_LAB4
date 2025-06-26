<%@ page import="entidades.Cliente"%>
<%@ page import="entidades.Provincia"%>
<%@ page import="entidades.Localidad"%>
<%@ page import="entidades.Nacionalidad"%>
<%@ page import="entidades.Usuario"%>
<%@ page import="java.util.List"%>

<%
Cliente cliente = (Cliente) request.getAttribute("cliente");
Usuario usuario = (Usuario) request.getAttribute("usuario");
%>

<!-- Encabezado -->
<jsp:include page="../componentes/Encabezado.jsp" />
<!-- Sidebar -->
<jsp:include page="../componentes/MenuLateralAdmin.jsp" />
<!-- Contenedor principal -->
<div class="main-content">
	<jsp:include page="../componentes/BarraSuperiorAdmin.jsp" />

	<div class="container content py-4">
		<div class="w-75 mx-auto">
			<h2 class="text-center mb-4">Modificar Cliente</h2>

			<form action="ClientesServlet" method="post">
				<input type="hidden" name="id" value="<%=cliente.getId()%>" />
			    <input type="hidden" name="accion" value="modificar" />

				<div class="mb-3">
					<label for="dni">DNI</label> 
					<input type="text" class="form-control" name="dni" value="<%=cliente.getDni()%>" readonly>
				</div>

				<div class="mb-3">
					<label for="cuil">CUIL</label> 
					<input type="text"	class="form-control" name="cuil" value="<%=cliente.getCuil()%>"	readonly>
				</div>

				<div class="mb-3">
					<label for="nombre">Nombre</label> 
					<input type="text"	class="form-control" name="nombre"	value="<%=cliente.getNombre()%>" required>
				</div>

				<div class="mb-3">
					<label for="apellido">Apellido</label> 
					<input type="text"	class="form-control" name="apellido" value="<%=cliente.getApellido()%>" required>
				</div>

				<div class="mb-3">
					<label>Sexo</label> 
					<select class="form-select" name="sexo"	required>
						<option
							<%=cliente.getSexo().equals("Masculino") ? "selected" : ""%>>Masculino</option>
						<option
							<%=cliente.getSexo().equals("Femenino") ? "selected" : ""%>>Femenino</option>
						<option <%=cliente.getSexo().equals("Otro") ? "selected" : ""%>>Otro</option>
					</select>
				</div>

				<div class="mb-3">
					<label>Nacionalidad</label> 
					<select class="form-select"	name="idNacionalidad" required>
						<%
						List<Nacionalidad> nacionalidades = (List<Nacionalidad>) request.getAttribute("nacionalidades");
						if (nacionalidades != null) {
							for (int i = 0; i < nacionalidades.size(); i++) {
								Nacionalidad nac = nacionalidades.get(i);
						%>
						<option value="<%=nac.getId()%>"
							<%=nac.getId() == cliente.getNacionalidad().getId() ? "selected" : ""%>>
							<%=nac.getDescripcion()%>
						</option>
						<%
						}
						}
						%>
					</select>
				</div>

				<div class="mb-3">
					<label>Fecha de nacimiento</label> 
					<input type="date"	class="form-control" name="fechaNacimiento"	value="<%=cliente.getFechaNacimiento()%>">
				</div>

				<div class="mb-3">
					<label>Dirección</label> 
					<input type="text" class="form-control"	name="direccion" value="<%=cliente.getDireccion()%>">
				</div>


				<div class="mb-3">
					<label>Provincia</label> 
					<select class="form-select"	name="idProvincia" required>
						<%
						List<Provincia> provincias = (List<Provincia>) request.getAttribute("provincias");
						if (provincias != null) {
							for (int i = 0; i < provincias.size(); i++) {
								Provincia prov = provincias.get(i);
						%>
						<option value="<%=prov.getId()%>"
							<%=prov.getId() == cliente.getProvincia().getId() ? "selected" : ""%>>
							<%=prov.getNombre()%>
						</option>
						<%
						}
						}
						%>

					</select>
				</div>

				<div class="mb-3">
					<label>Localidad</label> 
					<select class="form-select"	name="idLocalidad" required>
						<%
						List<Localidad> localidades = (List<Localidad>) request.getAttribute("localidades");
						if (localidades != null) {
							for (int i = 0; i < localidades.size(); i++) {
								Localidad loc = localidades.get(i);
						%>
						<option value="<%=loc.getId()%>"
							<%=loc.getId() == cliente.getLocalidad().getId() ? "selected" : ""%>>
							<%=loc.getNombre()%>
						</option>
						<%
						}
						}
						%>

					</select>
				</div>



				<div class="mb-3">
					<label>Email</label> 
					<input type="email" class="form-control" name="correoElectronico" value="<%=cliente.getCorreoElectronico()%>" required>
				</div>

				<div class="mb-3">
					<label>Teléfono</label> 
					<input type="text" class="form-control"	name="telefono" value="<%=cliente.getTelefono()%>">
				</div>
				
				

				<div class="text-center">
					<a href="ClientesServlet?accion=listar" class="btn btn-primary">Volver</a>
					<button type="submit" class="btn btn-primary">Guardar cambios</button>
				</div>
			</form>
		</div>
	</div>

	<jsp:include page="../componentes/Footer.jsp" />
</div>
