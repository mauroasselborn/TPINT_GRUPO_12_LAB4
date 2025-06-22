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
					<label>Nacionalidad</label> <select class="form-select"
						name="nacionalidad">
						<option>Argentina</option>
						<option>Bolivia</option>
						<option>Brasil</option>
						<option>China</option>
						<option>Chile</option>
						<option>Colombia</option>
						<option>Ecuador</option>
						<option>Estados Unidos</option>
						<option>Espa�a</option>
						<option>Italia</option>
						<option>Jap�n</option>
						<option>Paraguay</option>
						<option>Per�</option>
						<option>Rusia</option>
						<option>Ucrania</option>
						<option>Uruguay</option>
						<option>Venezuela</option>
					</select>
				</div>

				<div class="mb-3">
					<label>Fecha de nacimiento</label> <input type="date"
						class="form-control" name="fechaNacimiento">
				</div>

				<div class="mb-3">
					<label>Direcci�n</label> <input type="text" class="form-control"
						name="direccion" placeholder="Ingrese su direcci�n">
				</div>

				<div class="mb-3">
					<label>Localidad</label> <input type="text" class="form-control"
						name="localidad" placeholder="Ingrese su localidad">
				</div>

				<div class="mb-3">
					<label>Provincia</label> <select class="form-select"
						name="provincia">
						<option>Buenos Aires</option>
						<option>Catamarca</option>
						<option>Chaco</option>
						<option>Chubut</option>
						<option>C�rdoba</option>
						<option>Corrientes</option>
						<option>Entre R�os</option>
						<option>Formosa</option>
						<option>Jujuy</option>
						<option>La Pampa</option>
						<option>La Rioja</option>
						<option>Mendoza</option>
						<option>Misiones</option>
						<option>Neuqu�n</option>
						<option>R�o Negro</option>
						<option>Salta</option>
						<option>San Juan</option>
						<option>San Luis</option>
						<option>Santa Cruz</option>
						<option>Santa Fe</option>
						<option>Santiago del Estero</option>
						<option>Tierra del Fuego</option>
						<option>Ant�rtida e Islas del Atl�ntico Sur</option>
						<option>Tucum�n</option>
					</select>
				</div>

				<div class="mb-3">
					<label>Email</label> <input type="email" class="form-control"
						name="correo" placeholder="Ingrese su correo">
				</div>

				<div class="mb-3">
					<label>Tel�fono</label> <input type="number" class="form-control"
						name="telefono" placeholder="Ingrese el n�mero telef�nico">
				</div>

				<div class="mb-3">
					<label>Usuario</label> <input type="text" class="form-control"
						name="usuario" placeholder="Ingrese el Usuario">
				</div>

				<div class="mb-3">
					<label>Contrase�a</label> <input type="password"
						class="form-control" name="contrase�a"
						placeholder="Ingrese la Contrase�a">
				</div>

				<div class="mb-3">
					<label>Repetir Contrase�a</label> <input type="password"
						class="form-control" name="repContrase�a"
						placeholder="Repita la Contrase�a">
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
