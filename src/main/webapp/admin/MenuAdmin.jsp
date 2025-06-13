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
            margin: 0;
            padding: 0;
            display: flex;
            min-height: 100vh;
            overflow: hidden;
            background-color: #212529;
            color: white;
        }
        .main-container {
            flex-grow: 1;
            display: flex;
            flex-direction: column;
            width: 100%;
            height: 100vh;
        }
        .content {
            flex-grow: 1;
            overflow-y: auto;
            padding: 2rem;
            background-color: #343a40;
        }
    </style>
</head>
<body>

    <!-- Sidebar -->
    <jsp:include page="../componentes/MenuLateral.jsp" />

    <!-- Contenedor principal -->
    <div class="main-container">
        <!-- Navbar -->
        <jsp:include page="../componentes/BarraSuperior.jsp" />

        <!-- Contenido principal -->
        <div class="container-fluid content">
            <h1 class="mb-4">Bienvenido, Admin Juan Pérez</h1>
            <p>Desde este panel podés gestionar clientes, usuarios, cuentas, préstamos y ver reportes del sistema bancario.</p>
        </div>
    </div>

</body>
</html>