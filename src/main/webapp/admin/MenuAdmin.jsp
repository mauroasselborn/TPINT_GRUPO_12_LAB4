<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Panel Administrador - Banco Grupo12</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css" rel="stylesheet">
   <link rel="stylesheet" href="../css/styles.css">

</head>
<body>

    <!-- Sidebar -->
    <jsp:include page="../componentes/MenuLateralAdmin.jsp" />

    <!-- Contenedor principal -->
    <div class="main-content">
        <!-- Navbar -->
        <jsp:include page="../componentes/BarraSuperiorAdmin.jsp" />

        <!-- Contenido principal -->
        <div class="container-fluid content">
			<h1 class="mb-4">Bienvenido, Admin Mauro Asselborn</h1>
			<p>Desde este panel podés gestionar clientes, usuarios, cuentas, préstamos y ver reportes del sistema bancario.</p>
        </div>
    </div>

</body>
</html>