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
	<div class="container content py-4">
		<div class="w-75 mx-auto">
			<h2 class="text-center mb-4">Alta Cliente</h2>
			<form action="ClientesServlet" method="post">
				<input type="hidden" name="accion" value="alta" />

				<div class="mb-3">
					<label>DNI</label> <input type="text" name="dni" pattern="\d{7,8}"
						class="form-control" maxlength="8" title="Sólo 7 u 8 dígitos"
						placeholder="Ingrese el DNI" required
						value="<%=request.getAttribute("dni") != null ? request.getAttribute("dni") : ""%>" />
				</div>

				<div class="mb-3">
					<label>CUIL</label> <input type="text" name="cuil" pattern="\d{11}"
						class="form-control" maxlength="11" title="11 dígitos sin guiones"
						placeholder="Ingrese el CUIL" required
						value="<%=request.getAttribute("cuil") != null ? request.getAttribute("cuil") : ""%>" />
				</div>

				<div class="mb-3">
					<label>Nombre</label> <input type="text" name="nombre"
						pattern="[A-Za-zÁÉÍÓÚáéíóúñÑ ]+" placeholder="Ingrese el nombre"
						title="Sólo letras y espacios" required
						value="<%=request.getAttribute("nombre") != null ? request.getAttribute("nombre") : ""%>" />
				</div>

				<div class="mb-3">
					<label>Apellido</label> <input type="text" name="apellido"
						pattern="[A-Za-zÁÉÍÓÚáéíóúñÑ ]+" placeholder="Ingrese el apellido"
						title="Sólo letras y espacios" required
						value="<%=request.getAttribute("apellido") != null ? request.getAttribute("apellido") : ""%>" />

				</div>

				<div class="mb-3">
					<label>Sexo</label> <select class="form-select" name="sexo"
						required>
						<option value="Masculino">Masculino</option>
						<option value="Femenino">Femenino</option>
						<option value="Otro">Otro</option>
					</select>
				</div>

				<div class="mb-3">
					<label>Nacionalidad</label> <select class="form-select"
						name="idNacionalidad" required>
						<%
						@SuppressWarnings("unchecked")
						List<Nacionalidad> nacionalidades = (List<Nacionalidad>) request.getAttribute("nacionalidades");
						if (nacionalidades != null) {
							for (Nacionalidad nac : nacionalidades) {
						%>
						<option value="<%=nac.getId()%>"><%=nac.getDescripcion()%></option>
						<%
						}
						}
						%>
					</select>
				</div>

				<div class="mb-3">
					<label>Fecha de nacimiento</label> <input type="date"
						class="form-control" name="fechaNacimiento" required>
				</div>

				<div class="mb-3">
					<label>Dirección</label> <input type="text" class="form-control"
						name="direccion" placeholder="Ingrese su dirección" required>
				</div>

				<div class="mb-3">
					<label>Provincia</label> <select class="form-select"
						name="idProvincia" id="provinciaSelect" required>
						<%
						@SuppressWarnings("unchecked")
						List<Provincia> provincias = (List<Provincia>) request.getAttribute("provincias");
						if (provincias != null) {
							for (Provincia prov : provincias) {
						%>
						<option value="<%=prov.getId()%>"><%=prov.getNombre()%></option>
						<%
						}
						}
						%>
					</select>
				</div>

				<div class="mb-3">
					<label>Localidad</label> <select class="form-select"
						name="idLocalidad" id="localidadSelect" required>
						<%
						@SuppressWarnings("unchecked")
						List<Localidad> localidades = (List<Localidad>) request.getAttribute("localidades");
						if (localidades != null) {
							for (Localidad loc : localidades) {
						%>
						<option value="<%=loc.getId()%>"
							data-provincia="<%=loc.getProvincia()%>"><%=loc.getNombre()%></option>
						<%
						}
						}
						%>
					</select>
				</div>
				<script>
					document
							.getElementById("provinciaSelect")
							.addEventListener(
									"change",
									function() {
										var idProvincia = this.value;
										var localidadSelect = document
												.getElementById("localidadSelect");

										for (var i = 0; i < localidadSelect.options.length; i++) {
											var opcion = localidadSelect.options[i];
											var dataProv = opcion
													.getAttribute("data-provincia");

											// Mostrar solo las localidades con el ID de provincia seleccionado
											if (!dataProv
													|| dataProv === idProvincia) {
												opcion.style.display = "";
											} else {
												opcion.style.display = "none";
											}
										}
										// Recorremos y seleccionamos la primera localidad visible
										for (var i = 0; i < localidadSelect.options.length; i++) {
											var opcion = localidadSelect.options[i];
											if (opcion.style.display !== "none"
													&& opcion.value !== "") {
												localidadSelect.value = opcion.value;
												break;
											}
										}

									});
				</script>

				<div class="mb-3">
					<label>Email</label> <input type="email" name="correoElectronico"
						placeholder="Ingrese el correo electrónico"
						title="Formato: usuario@dominio.com" required
						value="<%=request.getAttribute("correoElectronico") != null ? request.getAttribute("correoElectronico") : ""%>" />
				</div>

				<div class="mb-3">
					<label>Teléfono</label> <input type="tel" name="telefono"
						pattern="\d{10}" placeholder="Ingrese el teléfono"
						title="10 dígitos numéricos" required
						value="<%=request.getAttribute("telefono") != null ? request.getAttribute("telefono") : ""%>" />
				</div>

				<div class="mb-3">
					<label>Usuario</label> <input type="text" class="form-control"
						name="usuario" placeholder="Ingrese el nombre de usuario" required>
				</div>

				<div class="mb-3">
					<label>Contraseña</label> <input type="password"
						class="form-control" name="contrasena"
						placeholder="Ingrese la contraseña" required>
				</div>

				<div class="mb-3">
					<label>Repetir contraseña</label> <input type="password"
						class="form-control" name="repContrasena"
						placeholder="Repita la contraseña" required>
				</div>

				<div class="text-center">
					<a href="ClientesServlet?accion=listar" class="btn btn-secondary">Cancelar</a>
					<button type="submit" class="btn btn-primary">Crear
						Cliente</button>
				</div>
			</form>
		</div>
	</div>

	<jsp:include page="../componentes/Footer.jsp" />
</div>