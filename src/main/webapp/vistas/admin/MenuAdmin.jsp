<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Panel Administrador - Banco UTN</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css" rel="stylesheet">
    <style>
        body {
            display: flex;
            min-height: 100vh;
            overflow-x: hidden;
        }
        #sidebar {
            min-width: 240px;
            max-width: 240px;
            background-color: #343a40;
        }
        #sidebar .nav-link {
            color: #ffffff;
        }
        #sidebar .nav-link:hover {
            background-color: #495057;
        }
        #content {
            flex-grow: 1;
            width: 100%;
        }
        .topbar {
            background-color: #343a40;
            padding: 0.75rem 1rem;
            border-bottom: 1px solid #495057;
            color: white;
        }
    </style>
</head>
<body>

    <!-- Sidebar -->
    <div id="sidebar" class="d-flex flex-column p-3 text-white">
        <h4 class="text-white text-center">Administrador</h4>
        <hr>
        <ul class="nav nav-pills flex-column mb-auto">
            <li><strong>Clientes</strong></li>
            <li><a href="Clientes.jsp" class="nav-link">Gestión de Clientes</a></li>

            <li class="mt-3"><strong>Cuentas</strong></li>
            <li><a href="Cuentas.jsp" class="nav-link">Gestión de Cuentas</a></li>

            <li class="mt-3"><strong>Préstamos</strong></li>
            <li><a href="Prestamos.jsp" class="nav-link">Solicitudes de Préstamo</a></li>

            <li class="mt-3"><strong>Reportes</strong></li>
            <li><a href="Reportes.jsp" class="nav-link">Ver Reportes</a></li>
        </ul>
        <hr>
        <a href="../../Login.jsp" class="btn btn-outline-light w-100">Cerrar Sesión</a>
    </div>

    <!-- Contenido -->
    <div id="content">
        <!-- Barra superior -->
        <div class="topbar d-flex justify-content-end align-items-center">
            <span class="me-2">
                <i class="bi bi-person-circle me-1"></i> Administrador
            </span>
        </div>

        <!-- Contenido principal -->
        <div class="p-4">
            <h2>Bienvenido Administrador</h2>
            <p>Usá el menú lateral para gestionar clientes, cuentas, préstamos y reportes del sistema bancario.</p>
        </div>
    </div>

</body>
</html>