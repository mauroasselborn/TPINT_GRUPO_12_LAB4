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
      <form action="ClienteServlet?accion=agregar" method="post">
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
            <option value="1">Argentina</option>
            <option value="2">Bolivia</option>
            <option value="3">Brasil</option>
            <option value="4">China</option>
            <option value="5">Chile</option>
            <option value="6">Colombia</option>
            <option value="7">Ecuador</option>
            <option value="8">Estados Unidos</option>
            <option value="9">Espa�a</option>
            <option value="10">Italia</option>
            <option value="11">Jap�n</option>
            <option value="12">Paraguay</option>
            <option value="13">Per�</option>
            <option value="14">Rusia</option>
            <option value="15">Ucrania</option>
            <option value="16">Uruguay</option>
            <option value="17">Venezuela</option>
          </select>
        </div>

        <div class="mb-3">
          <label>Fecha de nacimiento</label>
          <input type="date" class="form-control" name="fechaNacimiento" required>
        </div>

        <div class="mb-3">
          <label>Direcci�n</label>
          <input type="text" class="form-control" name="direccion" placeholder="Ingrese su direcci�n" required>
        </div>

        <div class="mb-3">
          <label>Localidad</label>
          <select class="form-select" name="idLocalidad" required>
            <option value="1">San Isidro</option>
            <option value="2">Tigre</option>
            <option value="3">Pacheco</option>
            <!-- Agreg� m�s localidades con su ID -->
          </select>
        </div>

        <div class="mb-3">
          <label>Provincia</label>
          <select class="form-select" name="idProvincia" required>
            <option value="1">Buenos Aires</option>
            <option value="2">Catamarca</option>
            <option value="3">Chaco</option>
            <option value="4">Chubut</option>
            <option value="5">C�rdoba</option>
            <option value="6">Corrientes</option>
            <option value="7">Entre R�os</option>
            <option value="8">Formosa</option>
            <option value="9">Jujuy</option>
            <option value="10">La Pampa</option>
            <option value="11">La Rioja</option>
            <option value="12">Mendoza</option>
            <option value="13">Misiones</option>
            <option value="14">Neuqu�n</option>
            <option value="15">R�o Negro</option>
            <option value="16">Salta</option>
            <option value="17">San Juan</option>
            <option value="18">San Luis</option>
            <option value="19">Santa Cruz</option>
            <option value="20">Santa Fe</option>
            <option value="21">Santiago del Estero</option>
            <option value="22">Tierra del Fuego</option>
            <option value="23">Ant�rtida e Islas del Atl�ntico Sur</option>
            <option value="24">Tucum�n</option>
          </select>
        </div>

        <div class="mb-3">
          <label>Email</label>
          <input type="email" class="form-control" name="email" placeholder="Ingrese su correo" required>
        </div>

        <div class="mb-3">
          <label>Tel�fono</label>
          <input type="text" class="form-control" name="telefono" placeholder="Ingrese el n�mero telef�nico">
        </div>

        <div class="mb-3">
          <label>Usuario</label>
          <input type="text" class="form-control" name="usuario" placeholder="Ingrese el Usuario" required>
        </div>

        <div class="mb-3">
          <label>Contrase�a</label>
          <input type="password" class="form-control" name="contrasena" placeholder="Ingrese la Contrase�a" required>
        </div>

        <div class="mb-3">
          <label>Repetir Contrase�a</label>
          <input type="password" class="form-control" name="repContrasena" placeholder="Repita la Contrase�a" required>
        </div>

        <div class="text-center">
          <button type="submit" class="btn btn-primary">Crear Cliente</button>
        </div>
      </form>
    </div>
  </div>

  <jsp:include page="../componentes/Footer.jsp" />
</div>