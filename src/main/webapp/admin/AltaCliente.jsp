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
        <label>DNI</label>
        <input type="number" class="form-control"
               name="dni" placeholder="Ingrese el DNI" required
               value="<%= request.getAttribute("dni") != null ? request.getAttribute("dni") : "" %>">
    </div>

    <div class="mb-3">
        <label>CUIL</label>
        <input type="text" class="form-control"
               name="cuil" placeholder="Ingrese su CUIL"
               value="<%= request.getAttribute("cuil") != null ? request.getAttribute("cuil") : "" %>">
    </div>

    <div class="mb-3">
        <label>Nombre</label>
        <input type="text" class="form-control"
               name="nombre" placeholder="Ingrese su nombre" required
               value="<%= request.getAttribute("nombre") != null ? request.getAttribute("nombre") : "" %>">
    </div>

    <div class="mb-3">
        <label>Apellido</label>
        <input type="text" class="form-control"
               name="apellido" placeholder="Ingrese el apellido" required
               value="<%= request.getAttribute("apellido") != null ? request.getAttribute("apellido") : "" %>">
    </div>

    <div class="mb-3">
        <label>Sexo</label>
        <select class="form-select" name="sexo" required>
            <option value="Masculino" <%= "Masculino".equals(request.getAttribute("sexo")) ? "selected" : "" %>>Masculino</option>
            <option value="Femenino" <%= "Femenino".equals(request.getAttribute("sexo")) ? "selected" : "" %>>Femenino</option>
            <option value="Otro" <%= "Otro".equals(request.getAttribute("sexo")) ? "selected" : "" %>>Otro</option>
        </select>
    </div>

    <div class="mb-3">
        <label>Nacionalidad</label>
        <select class="form-select" name="idNacionalidad" required>
            <%
            List<Nacionalidad> nacionalidades = (List<Nacionalidad>) request.getAttribute("nacionalidades");
            String idNacionalidad = String.valueOf(request.getAttribute("idNacionalidad"));
            if (nacionalidades != null) {
                for (Nacionalidad nac : nacionalidades) {
                    String selected = (idNacionalidad != null && idNacionalidad.equals(String.valueOf(nac.getId()))) ? "selected" : "";
            %>
            <option value="<%=nac.getId()%>" <%=selected%>><%=nac.getDescripcion()%></option>
            <%
                }
            }
            %>
        </select>
    </div>

    <div class="mb-3">
        <label>Fecha de nacimiento</label>
        <input type="date" class="form-control"
               name="fechaNacimiento" required
               value="<%= request.getAttribute("fechaNacimiento") != null ? request.getAttribute("fechaNacimiento") : "" %>">
    </div>

    <div class="mb-3">
        <label>Direccion</label>
        <input type="text" class="form-control"
               name="direccion" placeholder="Ingrese su direccion" required
               value="<%= request.getAttribute("direccion") != null ? request.getAttribute("direccion") : "" %>">
    </div>

    <div class="mb-3">
        <label>Provincia</label>
        <select class="form-select" name="idProvincia" onchange="this.form.submit()" required>
            <option value="0">Seleccione una provincia</option>
            <%
            List<Provincia> provincias = (List<Provincia>) request.getAttribute("provincias");
            // Toma el id de provincia desde selectedProvinciaId o idProvincia
            String idProvincia = String.valueOf(
                request.getAttribute("selectedProvinciaId") != null ?
                request.getAttribute("selectedProvinciaId") :
                request.getAttribute("idProvincia")
            );
            if (provincias != null) {
                for (Provincia prov : provincias) {
                    String selected = (idProvincia != null && idProvincia.equals(String.valueOf(prov.getId()))) ? "selected" : "";
            %>
            <option value="<%=prov.getId()%>" <%=selected%>><%=prov.getNombre()%></option>
            <%
                }
            }
            %>
        </select>
    </div>

    <div class="mb-3">
        <label>Localidad</label>
        <select class="form-select" name="idLocalidad" required>
            <%
            List<Localidad> localidades = (List<Localidad>) request.getAttribute("localidades");
            String idLocalidad = String.valueOf(request.getAttribute("idLocalidad"));
            if (localidades != null && !localidades.isEmpty()) {
                for (Localidad loc : localidades) {
                    String selected = (idLocalidad != null && idLocalidad.equals(String.valueOf(loc.getId()))) ? "selected" : "";
            %>
            <option value="<%=loc.getId()%>" <%=selected%>><%=loc.getNombre()%></option>
            <%
                }
            } else {
            %>
            <option value="">No hay localidades disponibles</option>
            <%
            }
            %>
        </select>
    </div>

    <div class="mb-3">
        <label>Email</label>
        <input type="email" class="form-control"
               name="correoElectronico" placeholder="Ingrese su correo" required
               value="<%= request.getAttribute("correoElectronico") != null ? request.getAttribute("correoElectronico") : "" %>">
    </div>

    <div class="mb-3">
        <label>Telefono</label>
        <input type="number" class="form-control"
               name="telefono" placeholder="Ingrese el numero telefonico"
               value="<%= request.getAttribute("telefono") != null ? request.getAttribute("telefono") : "" %>">
    </div>

    <div class="mb-3">
        <label>Usuario</label>
        <input type="text" class="form-control"
               name="usuario" placeholder="Ingrese el nombre de usuario" required
               value="<%= request.getAttribute("usuario") != null ? request.getAttribute("usuario") : "" %>">
    </div>

    <div class="mb-3">
        <label>Contrasenia</label>
        <input type="password" class="form-control"
               name="contrasena" placeholder="Ingrese la contrasenia" required>
    </div>

    <div class="mb-3">
        <label>Repetir contrasenia</label>
        <input type="password" class="form-control"
               name="repContrasena" placeholder="Repita la contrasenia" required>
    </div>

    <div class="text-center">
        <a href="ClientesServlet?accion=listar" class="btn btn-secondary">Cancelar</a>
        <button type="submit" class="btn btn-primary">Crear Cliente</button>
    </div>
</form>
		</div>
	</div>

	<jsp:include page="../componentes/Footer.jsp" />
</div>