<%@page import="entidades.Nacionalidad" %>
<%@page import="entidades.Provincia" %>
<%@page import="entidades.Localidad" %>
<%@page import="java.util.ArrayList" %>

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
		<div class="w-75 mx-auto">
			<h2 class="text-center mb-4">Alta Cliente</h2>
			<form>
				<div class="mb-3">
					<label>DNI</label> <input type="number" class="form-control"
						name="dni" placeholder="Ingrese el DNI">
				</div>

				<div class="mb-3">
					<label>CUIL</label> <input type="text" class="form-control"
						name="cuil" placeholder="Ingrese su CUIL">
				</div>

				<div class="mb-3">
					<label>Nombre</label> <input type="text" class="form-control"
						name="nombre" placeholder="Ingrese su nombre">
				</div>

				<div class="mb-3">
					<label>Apellido</label> <input type="text" class="form-control"
						name="apellido" placeholder="Ingrese el apellido">
				</div>


				<div class="mb-3">
					<label>Sexo</label> <select class="form-select" name="sexo">
						<option>Masculino</option>
						<option>Femenino</option>
						<option>Otro</option>
					</select>
				</div>

				<div class="mb-3">
					<label for="nacionalidad" class="form-label">Nacionalidad</label> 
					<select id="nacionalidad" name="nacionalidad" class="form-select" required> 
						<option value="">Seleccione la nacionalidad</option>
						
						<%
							///Obtener la lista de nacionalidades de la solicitud
							ArrayList<Nacionalidad> listaNacionalidades = (ArrayList<Nacionalidad>) request.getAttribute("listaNacionalidades");
							for(Nacionalidad nacionalidad : listaNacionalidades){
						
						%>
							<option value="<%= nacionalidad.getId() %>"><%= nacionalidad.getDescripcion() %></option>
						<%
							}
						%>
					</select>
				</div>

				<div class="mb-3">
					<label>Fecha de nacimiento</label> <input type="date"
						class="form-control" name="fechaNacimiento">
				</div>

				<div class="mb-3">
					<label>Dirección</label> <input type="text" class="form-control"
						name="direccion" placeholder="Ingrese su dirección">
				</div>

				<div class="mb-3">
					<label>Localidad</label> <input type="text" class="form-control"
						name="localidad" placeholder="Ingrese su localidad">
				</div>

				<div class="mb-3">
					<label for="provincia" class="form-label">Provincia</label> 
					<select id="provincia" name="provincia" class="form-select" required>
						<option value="">Seleccione la provincia</option>
						<%
							ArrayList<Provincia> listaProvincias = (ArrayList<Provincia>) request.getAttribute("listaProvincias");
							for(Provincia provincia :  listaProvincias){
						%>
						
							<option value="<%= provincia.getId() %>"><%= provincia.getNombre() %></option>
							
						<%
							}
						%>	
					</select>
				</div>

				<div class="mb-3">
					<label>Email</label> <input type="email" class="form-control"
						name="correo" placeholder="Ingrese su correo">
				</div>

				<div class="mb-3">
					<label>Teléfono</label> <input type="number" class="form-control"
						name="telefono" placeholder="Ingrese el número telefónico">
				</div>

				<div class="mb-3">
					<label>Usuario</label> <input type="text" class="form-control"
						name="usuario" placeholder="Ingrese el Usuario">
				</div>

				<div class="mb-3">
					<label>Contraseña</label> <input type="password"
						class="form-control" name="contrasena"
						placeholder="Ingrese la Contraseña">
				</div>

				<div class="mb-3">
					<label>Repetir Contraseña</label> <input type="password"
						class="form-control" name="repContrasena"
						placeholder="Repita la Contraseña">
				</div>

				<div class="text-center">
					<button type="submit" class="btn btn-primary">Crear
						Cliente</button>
				</div>
			</form>
		</div>
	</div>
	<jsp:include page="../componentes/Footer.jsp" />
	</div>
</body>
</html>
