<div id="sidebar" class="sidebar d-flex flex-column justify-content-between p-3 text-white bg-dark">
    <div>
        <h4 class="text-white text-center">Banco Grupo12</h4>
        <hr>
        <ul class="nav nav-pills flex-column mb-auto">
            <li><a href="MenuAdmin.jsp" class="nav-link text-white"><i class="bi bi-people-fill me-2"></i>Inicio</a></li>
            <li><a href="ClientesServlet?accion=listar" class="nav-link text-white"><i class="bi bi-people-fill me-2"></i>Clientes</a></li>
            <li><a href="UsuariosServlet?accion=listar" class="nav-link text-white"><i class="bi bi-person-lines-fill me-2"></i>Usuarios</a></li>
            <li><a href="CuentasServlet?accion=listar" class="nav-link text-white"><i class="bi bi-credit-card-2-front-fill me-2"></i>Cuentas</a></li>
            <li><a href="PrestamosServlet?accion=listarSolicitudes" class="nav-link text-white"><i class="bi bi-cash-stack me-2"></i>Pr�stamos</a></li>
            <li><a href="${pageContext.request.contextPath}/admin/reportes" class="nav-link text-white"><i class="bi bi-bar-chart-fill me-2"></i>Reportes</a></li>

   
            
        </ul>
    </div>
    <div>
        <hr>
        <a href="../LoginServlet?accion=logout" class="btn btn-outline-light w-100">
            <i class="bi bi-box-arrow-right me-2"></i>Cerrar sesi�n
        </a>
    </div>
</div>
