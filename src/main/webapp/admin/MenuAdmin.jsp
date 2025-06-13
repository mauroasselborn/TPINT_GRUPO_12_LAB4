<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="UTF-8">
<title>Panel Administrador - Banco UTN</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css"
	rel="stylesheet">
<style>
body {
	display: flex;
	min-height: 100vh;
	overflow-x: hidden;
	font-family: Arial, sans-serif;
	background-color: #f5f5f5;
}

#sidebar {
	min-width: 240px;
	max-width: 240px;
	background-color: #000;
}

#sidebar .nav-link, #sidebar h4, #sidebar strong {
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
	background-color: #000;
	padding: 0.75rem 1rem;
	border-bottom: 1px solid #495057;
	color: white;
}

.dataTables_wrapper .dataTables_filter input {
	margin-left: 0.5em;
	display: inline-block;
	width: auto;
}

.btn-action {
	margin-right: 5px;
}

.table td, .table th {
	vertical-align: middle;
}

.scroll-container {
	overflow-x: auto;
	width: 100%;
}
</style>
</head>
<body>


	<jsp:include page="../componentes/MenuLateral.jsp" />

	<!-- Contenido -->
	<div id="content">

		<!-- Barra superior -->
		<jsp:include page="../componentes/BarraSuperior.jsp" />

		<!-- Contenido principal -->
		<div class="p-4">
			<h2>Bienvenido Administrador</h2>
			<p>Usá el menú lateral para gestionar clientes, cuentas,
				préstamos y reportes del sistema bancario.</p>
		</div>
	</div>

</body>
</html>
