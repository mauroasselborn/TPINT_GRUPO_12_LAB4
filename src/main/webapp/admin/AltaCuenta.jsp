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
	<div class="form-container">
		<h2>Alta Cuenta</h2>
		
		<form action="#" method="post">
			<div class="mb-3">
				<label for="cliente" class="form-label">Cliente</label>
				<input type="text" class="form-control" id="cliente" name="cliente">
			</div>
			
			<div class="mb-3">
                <label>Fecha de creación</label>
                <input type="date" class="form-control" name="fechaCreacion">
            </div>
            
            <div class="mb-3">
            	<label>Tipo de cuenta</label>
            	<select class="form-select" id="tipoCuenta" name="tipoCuenta">
            		<option selected disabled>Seleccione un tipo</option>
            		<option>Caja de ahorro</option>
            		<option>Cuenta Corriente</option>
            	</select>
            </div>
            
			<button type="submit" name="btnAgregarCuenta" class="btn btn-primary">Agregar Cuenta</button>	
		</form>
	</div>
	</div>
	<jsp:include page="../componentes/Footer.jsp" />
	</div>
</body>
</html>