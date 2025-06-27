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
                            <label class="form-label">Nombre</label>
                            <input type="text" class="form-control" value="Juan" readonly>
                        </div>
                        <div class="col">
                            <label class="form-label">Apellido</label>
                            <input type="text" class="form-control" value="Pérez" readonly>
                        </div>
                    </div>

                    <div class="row mb-3">
                        <div class="col">
                            <label class="form-label">DNI</label>
                            <input type="text" class="form-control" value="12345678" readonly>
                        </div>
                        <div class="col">
                            <label class="form-label">CUIL</label>
                            <input type="text" class="form-control" value="20-12345678-3" readonly>
                        </div>
                    </div>

                    <div class="mb-3">
                        <label class="form-label">Correo Electrónico</label>
                        <input type="email" class="form-control" value="juan.perez@email.com" readonly>
                    </div>

                    <div class="mb-3">
                        <label class="form-label">Dirección</label>
                        <input type="text" class="form-control" value="Av. Siempre Viva 123" readonly>
                    </div>

                    <div class="row mb-3">
                        <div class="col">
                            <label class="form-label">Localidad</label>
                            <input type="text" class="form-control" value="San Isidro" readonly>
                        </div>
                        <div class="col">
                            <label class="form-label">Provincia</label>
                            <input type="text" class="form-control" value="Buenos Aires" readonly>
                        </div>
                    </div>

                    <div class="row mb-3">
                        <div class="col">
                            <label class="form-label">Sexo</label>
                            <input type="text" class="form-control" value="Masculino" readonly>
                        </div>
                        <div class="col">
                            <label class="form-label">Fecha de nacimiento</label>
                            <input type="date" class="form-control" value="1990-05-10" readonly>
                        </div>
                    </div>

                    <div class="mb-3">
                        <label class="form-label">Teléfono</label>
                        <input type="text" class="form-control" value="1123456789" readonly>
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