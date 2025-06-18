<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="UTF-8">
<title>Panel Cliente - Banco Grupo12</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
<link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css" rel="stylesheet">
<link rel="stylesheet" href="../css/styles.css">

</head>
<body>

	<!-- Sidebar -->
	<jsp:include page="../componentes/MenuLateralCliente.jsp" />

	<!-- Contenedor principal -->
	<div class="main-content">
		<!-- Navbar -->
		<jsp:include page="../componentes/BarraSuperiorCliente.jsp" />
		
		<!-- Contenido principal -->
		<div class="container-fluid content">
			<h1 class="mb-4">Bienvenido al panel de cliente</h1>
			<p>Seleccioná una opción del menú para comenzar.</p>
		</div>
	</div>

</body>
</html>
