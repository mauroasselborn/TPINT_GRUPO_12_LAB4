<%@ page import="entidades.Provincia" %>
<%@ page import="entidades.Localidad" %>
<%@ page import="entidades.Nacionalidad" %>
<%@ page import="java.util.List" %>

<!-- Encabezado -->
<jsp:include page="../componentes/Encabezado.jsp" />

<!-- Sidebar -->
<jsp:include page="../componentes/MenuLateralAdmin.jsp" />

<!-- Contenedor principal -->
<div class="main-content">
  <!-- Navbar -->
  <jsp:include page="../componentes/BarraSuperiorAdmin.jsp" />

  <!-- Contenido principal -->
  <div class="container content py-4">
    <div class="w-75 mx-auto">
      <h2 class="text-center mb-4">Alta Cliente</h2>
      <form action="ClientesServlet" method="post">
        <input type="hidden" name="accion" value="alta" />

        <div class="mb-3">
          <label>DNI</label>
          <input type="number" class="form-control" name="dni" placeholder="Ingrese el DNI" required>
        </div>

        <div class="mb-3">
          <label>CUIL</label>
          <input type="text" class="form-control" name="cuil" placeholder="Ingrese su CUIL">
        </div>

        <div class="mb-3">
          <label>Nombre</label>
          <input type="text" class="form-control" name="nombre" placeholder="Ingrese su nombre" required>
        </div>

        <div class="mb-3">
          <label>Apellido</label>
          <input type="text" class="form-control" name="apellido" placeholder="Ingrese el apellido" required>
        </div>

        <div class="mb-3">
          <label>Sexo</label>
          <select class="form-select" name="sexo" required>
            <option value="Masculino">Masculino</option>
            <option value="Femenino">Femenino</option>
            <option value="Otro">Otro</option>
          </select>
        </div>

        <div class="mb-3">
          <label>Nacionalidad</label>
          <select class="form-select" name="idNacionalidad" required>
            <% List<Nacionalidad> nacionalidades = (List<Nacionalidad>) request.getAttribute("nacionalidades");
               if (nacionalidades != null) {
                 for (Nacionalidad nac : nacionalidades) { %>
              <option value="<%=nac.getId()%>"><%=nac.getDescripcion()%></option>
            <% }} %>
          </select>
        </div>

        <div class="mb-3">
          <label>Fecha de nacimiento</label>
          <input type="date" class="form-control" name="fechaNacimiento" required>
        </div>

        <div class="mb-3">
          <label>Dirección</label>
          <input type="text" class="form-control" name="direccion" placeholder="Ingrese su dirección" required>
        </div>

		<div class="mb-3">
          <label>Provincia</label>
          <select class="form-select" name="idProvincia" required>
            <% List<Provincia> provincias = (List<Provincia>) request.getAttribute("provincias");
               if (provincias != null) {
                 for (Provincia prov : provincias) { %>
              <option value="<%=prov.getId()%>"><%=prov.getNombre()%></option>
            <% }} %>
          </select>
        </div>

        <div class="mb-3">
          <label>Localidad</label>
          <select class="form-select" name="idLocalidad" required>
            <% List<Localidad> localidades = (List<Localidad>) request.getAttribute("localidades");
               if (localidades != null) {
                 for (Localidad loc : localidades) { %>
              <option value="<%=loc.getId()%>"><%=loc.getNombre()%></option>
            <% }} %>
          </select>
        </div>

        <div class="mb-3">
          <label>Email</label>
          <input type="email" class="form-control" name="correoElectronico" placeholder="Ingrese su correo" required>
        </div>

        <div class="mb-3">
          <label>Teléfono</label>
          <input type="text" class="form-control" name="telefono" placeholder="Ingrese el número telefónico">
        </div>

        <div class="mb-3">
          <label>Usuario</label>
          <input type="text" class="form-control" name="usuario" placeholder="Ingrese el nombre de usuario" required>
        </div>

        <div class="mb-3">
          <label>Contraseña</label>
          <input type="password" class="form-control" name="contrasena" placeholder="Ingrese la contraseña" required>
        </div>

        <div class="mb-3">
          <label>Repetir contraseña</label>
          <input type="password" class="form-control" name="repContrasena" placeholder="Repita la contraseña" required>
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