<!-- Encabezado -->
  <jsp:include page="../componentes/Encabezado.jsp" />

  <!-- Sidebar -->
  <jsp:include page="../componentes/MenuLateralAdmin.jsp" />

  <!-- Contenedor principal -->
  <div class="main-content">
    <!-- Navbar -->
    <jsp:include page="../componentes/BarraSuperior.jsp" />
    <!-- Contenido principal -->
    <div class="container-fluid content py-4">
			<h1 class="mb-4">Bienvenido, Admin</h1>
			<p>Desde este panel podés gestionar clientes, usuarios, cuentas, préstamos y ver reportes del sistema bancario.</p>
        </div>
        
       	<jsp:include page="../componentes/Footer.jsp" />
    </div>

</body>
</html>