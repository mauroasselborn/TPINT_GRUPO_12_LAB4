<!-- Encabezado -->
  <jsp:include page="../componentes/Encabezado.jsp" />

  <!-- Sidebar -->
  <jsp:include page="../componentes/MenuLateralAdmin.jsp" />

  <!-- Contenedor principal -->
  <div class="main-content">
    <!-- Navbar -->
    <jsp:include page="../componentes/BarraSuperior.jsp" />
    <!-- Contenido principal -->
    <div class="container-fluid content py-4 d-flex justify-content-center align-items-center" style="height: calc(100vh - 130px);">
    <div class="text-center">
        <h1 class="mb-3" style="font-weight: bold;">Bienvenido, Admin</h1>
        <p style="font-size: 1.1rem; color: #555;">
            Desde este panel podés gestionar <strong>clientes</strong>, <strong>usuarios</strong>, <strong>cuentas</strong>, <strong>préstamos</strong> y ver <strong>reportes</strong> del sistema bancario.
        </p>
    </div>
</div>
        
       	<jsp:include page="../componentes/Footer.jsp" />
    </div>

</body>
</html>