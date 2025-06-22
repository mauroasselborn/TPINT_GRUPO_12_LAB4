<jsp:include page="../componentes/Encabezado.jsp" />
	
	<!-- Sidebar -->
  <jsp:include page="../componentes/MenuLateralCliente.jsp" />

  <!-- Contenedor principal -->
  <div class="main-content">
    <!-- Navbar -->
    <jsp:include page="../componentes/BarraSuperiorCliente.jsp" />
    <!-- Contenido principal -->
  <div class="container-fluid content py-4">
    <h2 class="mb-4">Detalle del Préstamo</h2>

    <div class="table-responsive mb-4">
      <table class="table table-bordered align-middle">
        <tbody>
          <tr>
            <th scope="row" class="w-25">Importe solicitado</th>
            <td>$50.000</td>
          </tr>
          <tr>
            <th scope="row">Cuenta destino</th>
            <td>CTA$ 400567892 - Caja de ahorro</td>
          </tr>
          <tr>
            <th scope="row">Cantidad de cuotas</th>
            <td>12</td>
          </tr>
          <tr>
            <th scope="row">Cuotas pendientes</th>
            <td>6</td>
          </tr>
          <tr>
            <th scope="row">Próxima cuota a pagar</th>
            <td>Cuota 7 de 12 - Monto: $4.500</td>
          </tr>
          <tr>
            <th scope="row">Estado</th>
            <td>Aprobado</td>
          </tr>
          <tr>
            <th scope="row">Fecha de alta</th>
            <td>2025-06-15</td>
          </tr>
        </tbody>
      </table>
    </div>

    <!-- Acciones -->
    <div class="d-flex gap-2">
      <a href="GestionarPrestamos.jsp" class="btn btn-secondary">
        <i class="bi bi-arrow-left"></i> Volver
      </a>

      <!-- Botón para pagar la próxima cuota -->
      <form action="PagarCuotaServlet" method="post">
        <input type="hidden" name="idPrestamo" value="1" />
        <input type="hidden" name="idCuota" value="123" />
        <button type="submit" class="btn btn-success">
          <i class="bi bi-cash-coin"></i> Pagar cuota 7 <!-- aca teno que poner el mismo numero de cuota que en el td-->
        </button>
      </form>
    </div>
  </div>
<jsp:include page="../componentes/Footer.jsp" />
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>