<!-- Encabezado -->
<jsp:include page="../componentes/Encabezado.jsp" />

<!-- Sidebar -->
<jsp:include page="../componentes/MenuLateralCliente.jsp" />

<!-- Contenedor principal -->
<div class="main-content">
  <!-- Navbar -->
  <jsp:include page="../componentes/BarraSuperior.jsp" />
  
  <!-- Contenido principal -->
  <div class="container content py-4">
    
    <h2 class="mb-4">Solicitud de Préstamo</h2>

    <form action="#" method="post" class="border p-4 rounded bg-light">
      <div class="form-group mb-3">
        <label for="monto">Monto solicitado ($):</label>
        <input type="number" class="form-control" id="monto" name="monto" placeholder="Ej: 50000" step="0.01" min="1" required>
      </div>

      <div class="form-group mb-3">
        <label for="cuotas">Cantidad de cuotas:</label>
        <select class="form-select" id="cuotas" name="cuotas" required>
          <option value="">Seleccione</option>
          <option value="3">3 cuotas</option>
          <option value="6">6 cuotas</option>
          <option value="12">12 cuotas</option>
        </select>
      </div>

      <div class="form-group mb-4">
        <label for="cuentaDestino">Cuenta destino:</label>
        <select class="form-select" id="cuentaDestino" name="cuentaDestino" required>
          <option value="">Seleccione una cuenta</option>
          <option value="1">Caja de ahorro - Nº 12345678 - Saldo $12.500,00</option>
          <option value="2">Cuenta corriente - Nº 87654321 - Saldo $7.000,00</option>
        </select>
      </div>

      <input type="submit" class="btn btn-primary w-100" value="Solicitar Préstamo">
    </form>
  </div>

  <!-- Footer -->
  <jsp:include page="../componentes/Footer.jsp" />
</div>

</body>
</html>
