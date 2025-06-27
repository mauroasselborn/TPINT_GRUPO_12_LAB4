<jsp:include page="../componentes/Encabezado.jsp" />
	
	<!-- Sidebar -->
  <jsp:include page="../componentes/MenuLateralCliente.jsp" />

  <!-- Contenedor principal -->
  <div class="main-content">
    <!-- Navbar -->
    <jsp:include page="../componentes/BarraSuperior.jsp" />
    <!-- Contenido principal -->

  <div class="container-fluid content py-4">
    <h2>Mis Préstamos</h2>
    <a href="SolicitarPrestamo.jsp" class="btn btn-primary mb-3">Solicitar Nuevo Préstamo</a>

    <table class="table table-striped">
      <thead>
        <tr>
          <th>ID</th>
          <th>Importe</th>
          <th>Cuotas</th>
          <th>Estado</th>
          <th>Detalle</th>
        </tr>
      </thead>
      <tbody>
        <tr>
          <td>1</td>
          <td>$50.000</td>
          <td>12</td>
          <td><span class="badge bg-warning">PENDIENTE</span></td>
          <td><a href="DetallePrestamo.jsp" class="btn btn-info btn-sm"><i class="bi bi-eye"></i> Ver</a></td>
        </tr>
        <tr>
          <td>2</td>
          <td>$75.000</td>
          <td>24</td>
          <td><span class="badge bg-success">APROBADO</span></td>
          <td><a href="DetallePrestamo.jsp" class="btn btn-info btn-sm"><i class="bi bi-eye"></i> Ver</a></td>
        </tr>
        <tr>
          <td>3</td>
          <td>$30.000</td>
          <td>6</td>
          <td><span class="badge bg-danger">RECHAZADO</span></td>
          <td><a href="DetallePrestamo.jsp" class="btn btn-info btn-sm"><i class="bi bi-eye"></i> Ver</a></td>
        </tr>
      </tbody>
    </table>
  </div>
  
  <jsp:include page="../componentes/Footer.jsp" />
  
  </div>

  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>