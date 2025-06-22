<!-- Encabezado -->
  <jsp:include page="../componentes/Encabezado.jsp" />

  <!-- Sidebar -->
  <jsp:include page="../componentes/MenuLateralCliente.jsp" />

  <!-- Contenedor principal -->
  <div class="main-content">
    <!-- Navbar -->
    <jsp:include page="../componentes/BarraSuperiorCliente.jsp" />
    <!-- Contenido principal -->
    <div class="container-fluid content py-4">
	<h1 class="text-primary mb-3">Mis Cuentas</h1>
      <div class="bg-light p-4 rounded shadow-sm">
        

        <div class="mb-3">
          <label for="selectCuenta" class="form-label fw-bold text-primary">Elige tu cuenta:</label>
          <select class="form-select" id="selectCuenta">
            <option selected>CTA$ 400567891 / cuenta corriente - Saldo: $1050001.00</option>
            <option selected>CTA$ 400567892 / caja de ahorro - Saldo: $500000.00</option>
            <option selected>CTA$ 400567893 / cuenta corriente - Saldo: $250000.00</option>
            <!-- Otras cuentas simuladas -->
          </select>
        </div>

        <button class="btn btn-success mb-3">Ver CBU</button>

        <div class="bg-white text-center py-4 rounded border shadow-sm mb-4">
          <div class="fs-6 text-secondary">Saldo:</div>
          <div class="fs-1 fw-semibold text-dark">$1050001.0</div>
        </div>

        <h5 class="text-dark mb-3">Detalle de Movimientos</h5>
        <div class="table-responsive">
          <table class="table table-bordered table-hover align-middle">
            <thead class="table-light">
              <tr>
                <th>#</th>
                <th>Fecha</th>
                <th>Tipo</th>
                <th>Detalle</th>
                <th>Importe</th>
              </tr>
            </thead>
            <tbody>
              <tr>
                <td>1</td>
                <td>2025-06-18</td>
                <td>Transferencia</td>
                <td>Envío a CTA$ 400567893</td>
                <td class="text-danger">-$10.000,00</td>
              </tr>
              <tr>
                <td>2</td>
                <td>2025-06-16</td>
                <td>Alta de cuenta</td>
                <td>Cuenta inicial</td>
                <td class="text-success">+$10.000,00</td>
              </tr>
              <!-- Más movimientos simulados -->
            </tbody>
          </table>
        </div>
      </div>
    </div>
	<!-- Footer -->
  <jsp:include page="../componentes/Footer.jsp" />
  </div>

</body>
</html>
