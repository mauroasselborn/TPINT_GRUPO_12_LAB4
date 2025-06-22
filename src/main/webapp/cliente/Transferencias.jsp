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
    
        <div class="card mx-auto shadow-lg" style="max-width: 500px;">
            <div class="card-body">
                <h4 class="card-title text-center mb-4">
                    <i class="bi bi-arrow-left-right"></i> Transferencias
                </h4>

                <!-- Tipo de Transferencia -->
                <fieldset class="border rounded p-4 bg-white">
                    <legend class="w-auto px-2 fw-bold text-primary">Tipo de Transferencia</legend>

                    <div class="d-grid gap-3">
                        <button type="button" class="btn btn-outline-primary d-flex align-items-center justify-content-center"
                                onclick="window.location.href='TransferenciasPropias.jsp'">
                            <i class="bi bi-person-check me-2"></i> Entre mis cuentas
                        </button>

                        <button type="button" class="btn btn-outline-success d-flex align-items-center justify-content-center"
                                onclick="window.location.href='TransferenciasTerceros.jsp'">
                            <i class="bi bi-people-fill me-2"></i> A terceros
                        </button>
                    </div>
                </fieldset>
            </div>
        </div>
    </div>
    <jsp:include page="../componentes/Footer.jsp" />
</div>
</body>
</html>
