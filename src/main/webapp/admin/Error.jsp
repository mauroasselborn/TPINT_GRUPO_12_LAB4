
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
        <div class="w-75 mx-auto">
            <div class="alert alert-danger text-center">
                <h3>Ocurrió un error</h3>
                <p><strong>Mensaje:</strong> ${error}</p>
                <a href="${pageContext.request.contextPath}/admin/CuentasServlet?accion=listar" class="btn btn-secondary mt-3">
                    Volver al listado de cuentas
                </a>
            </div>
        </div>
    </div>

    <!-- Footer -->
    <jsp:include page="../componentes/Footer.jsp" />
</div>

