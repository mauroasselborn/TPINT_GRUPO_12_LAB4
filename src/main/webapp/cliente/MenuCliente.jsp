<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="UTF-8">
<title>Panel Cliente - Banco UTN</title>
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
	margin: 0;
	font-family: Arial, sans-serif;
}

.sidebar {
	width: 220px;
	background-color: #198754; /* Verde Bootstrap */
	color: #fff;
	padding: 20px 0;
	position: fixed;
	height: 100vh;
}

.sidebar h4 {
	text-align: center;
	margin-bottom: 20px;
}

.sidebar a {
	display: block;
	color: #fff;
	padding: 10px 20px;
	text-decoration: none;
}

.sidebar a:hover {
	background-color: #157347;
}

.main-content {
	margin-left: 220px;
	padding: 0;
	width: calc(100% - 220px);
	display: flex;
	flex-direction: column;
}

.topbar {
	background-color: #198754; /* Verde más oscuro */
	color: white;
	padding: 10px 20px;
	display: flex;
	justify-content: flex-end;
	align-items: center;
}

.topbar i {
	margin-right: 10px;
}

.main-body {
	padding: 30px;
	background-color: #f8f9fa;
	flex: 1;
}
</style>
</head>
<body>

	<div class="sidebar">
		<h4>Banco UTN</h4>
		<a href="Movimientos.jsp"><i class="bi bi-list-ul"></i>
			Movimientos</a> <a href="Transferencias.jsp"><i
			class="bi bi-arrow-left-right"></i> Transferencias</a> <a
			href="SolicitarPrestamo.jsp"><i class="bi bi-cash-stack"></i>
			Pedir Préstamo</a> <a href="PagosPrestamo.jsp"><i
			class="bi bi-credit-card-2-back"></i> Pagos de Préstamos</a> <a
			href="PerfilCliente.jsp"><i class="bi bi-person"></i> Mi Perfil</a>
	</div>

	<div class="main-content">
		<div class="topbar">
			<i class="bi bi-person-circle"></i> NombreUsuario
		</div>
		<div class="main-body">
			<h2>Bienvenido al panel de cliente</h2>
			<p>Seleccioná una opción del menú para comenzar.</p>
		</div>
	</div>

</body>
</html>
