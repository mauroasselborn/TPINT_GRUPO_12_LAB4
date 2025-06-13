<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Gestión de Clientes</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css"
	rel="stylesheet">
<link rel="stylesheet" type="text/css"
	href="https://cdn.datatables.net/1.13.6/css/jquery.dataTables.min.css" />
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

	<!-- Contenido Principal -->
	<div id="content">
		<!-- Barra superior -->
		<jsp:include page="../componentes/BarraSuperior.jsp" />

		<div class="p-4">
			<div class="d-flex justify-content-between align-items-center mb-3">
				<h2 class="mb-0">Clientes</h2>
				<a href="AltaCliente.jsp" class="btn btn-primary">Agregar nuevo
					cliente</a>
			</div>

			<div class="scroll-container">
				<table id="tablaClientes"
					class="table table-bordered table-hover w-100">
					<thead class="table-dark">
						<tr>
							<th>DNI</th>
							<th>CUIL</th>
							<th>Nombre</th>
							<th>Apellido</th>
							<th>Sexo</th>
							<th>Nacionalidad</th>
							<th>Fecha Nac.</th>
							<th>Dirección</th>
							<th>Localidad</th>
							<th>Provincia</th>
							<th>Email</th>
							<th>Teléfono</th>
							<th>Acciones</th>
						</tr>
					</thead>
					<tbody>
    <tr>
        <td>12345678</td>
        <td>20-12345678-3</td>
        <td>Juan</td>
        <td>Pérez</td>
        <td>M</td>
        <td>Argentina</td>
        <td>1990-01-01</td>
        <td>Calle Falsa 123</td>
        <td>Tigre</td>
        <td>Buenos Aires</td>
        <td>juan@mail.com</td>
        <td>1122334455</td>
        <td class="text-nowrap">
            <div class="d-flex">
                <a href="ModificarCliente.jsp?id=1" class="btn btn-warning btn-sm btn-action">Modificar</a>
                <a href="EliminarCliente.jsp?id=1" class="btn btn-danger btn-sm">Eliminar</a>
            </div>
        </td>
    </tr>
    <%-- 20 filas generadas automáticamente --%>
    <tr><td>23456789</td><td>20-23456789-4</td><td>Ana</td><td>Gómez</td><td>F</td><td>Argentina</td><td>1985-02-15</td><td>Av. Libertador 123</td><td>San Isidro</td><td>Buenos Aires</td><td>ana@mail.com</td><td>1133445566</td><td class="text-nowrap"><div class="d-flex"><a href="ModificarCliente.jsp?id=2" class="btn btn-warning btn-sm btn-action">Modificar</a><a href="EliminarCliente.jsp?id=2" class="btn btn-danger btn-sm">Eliminar</a></div></td></tr>
    <tr><td>34567890</td><td>20-34567890-5</td><td>Carlos</td><td>López</td><td>M</td><td>Argentina</td><td>1978-07-22</td><td>Mitre 456</td><td>Morón</td><td>Buenos Aires</td><td>carlos@mail.com</td><td>1144556677</td><td class="text-nowrap"><div class="d-flex"><a href="ModificarCliente.jsp?id=3" class="btn btn-warning btn-sm btn-action">Modificar</a><a href="EliminarCliente.jsp?id=3" class="btn btn-danger btn-sm">Eliminar</a></div></td></tr>
    <tr><td>45678901</td><td>20-45678901-6</td><td>Lucía</td><td>Martínez</td><td>F</td><td>Argentina</td><td>1992-11-03</td><td>Rivadavia 789</td><td>La Plata</td><td>Buenos Aires</td><td>lucia@mail.com</td><td>1155667788</td><td class="text-nowrap"><div class="d-flex"><a href="ModificarCliente.jsp?id=4" class="btn btn-warning btn-sm btn-action">Modificar</a><a href="EliminarCliente.jsp?id=4" class="btn btn-danger btn-sm">Eliminar</a></div></td></tr>
    <tr><td>56789012</td><td>20-56789012-7</td><td>Mario</td><td>Fernández</td><td>M</td><td>Argentina</td><td>1980-05-11</td><td>Belgrano 321</td><td>Quilmes</td><td>Buenos Aires</td><td>mario@mail.com</td><td>1166778899</td><td class="text-nowrap"><div class="d-flex"><a href="ModificarCliente.jsp?id=5" class="btn btn-warning btn-sm btn-action">Modificar</a><a href="EliminarCliente.jsp?id=5" class="btn btn-danger btn-sm">Eliminar</a></div></td></tr>
    <tr><td>67890123</td><td>20-67890123-8</td><td>Valeria</td><td>Ruiz</td><td>F</td><td>Argentina</td><td>1995-09-30</td><td>San Martín 654</td><td>Vicente López</td><td>Buenos Aires</td><td>valeria@mail.com</td><td>1177889900</td><td class="text-nowrap"><div class="d-flex"><a href="ModificarCliente.jsp?id=6" class="btn btn-warning btn-sm btn-action">Modificar</a><a href="EliminarCliente.jsp?id=6" class="btn btn-danger btn-sm">Eliminar</a></div></td></tr>
    <tr><td>78901234</td><td>20-78901234-9</td><td>Pedro</td><td>Alvarez</td><td>M</td><td>Argentina</td><td>1975-06-18</td><td>Lavalle 888</td><td>Lanús</td><td>Buenos Aires</td><td>pedro@mail.com</td><td>1188990011</td><td class="text-nowrap"><div class="d-flex"><a href="ModificarCliente.jsp?id=7" class="btn btn-warning btn-sm btn-action">Modificar</a><a href="EliminarCliente.jsp?id=7" class="btn btn-danger btn-sm">Eliminar</a></div></td></tr>
    <tr><td>89012345</td><td>20-89012345-0</td><td>Sofía</td><td>Castro</td><td>F</td><td>Argentina</td><td>1988-08-08</td><td>Maipú 999</td><td>Berazategui</td><td>Buenos Aires</td><td>sofia@mail.com</td><td>1199001122</td><td class="text-nowrap"><div class="d-flex"><a href="ModificarCliente.jsp?id=8" class="btn btn-warning btn-sm btn-action">Modificar</a><a href="EliminarCliente.jsp?id=8" class="btn btn-danger btn-sm">Eliminar</a></div></td></tr>
    <tr><td>90123456</td><td>20-90123456-1</td><td>Diego</td><td>Romero</td><td>M</td><td>Argentina</td><td>1993-03-14</td><td>Mitre 111</td><td>Escobar</td><td>Buenos Aires</td><td>diego@mail.com</td><td>1200112233</td><td class="text-nowrap"><div class="d-flex"><a href="ModificarCliente.jsp?id=9" class="btn btn-warning btn-sm btn-action">Modificar</a><a href="EliminarCliente.jsp?id=9" class="btn btn-danger btn-sm">Eliminar</a></div></td></tr>
    <tr><td>11223344</td><td>20-11223344-2</td><td>Martina</td><td>Silva</td><td>F</td><td>Argentina</td><td>1983-04-25</td><td>Santa Fe 222</td><td>San Fernando</td><td>Buenos Aires</td><td>martina@mail.com</td><td>1211223344</td><td class="text-nowrap"><div class="d-flex"><a href="ModificarCliente.jsp?id=10" class="btn btn-warning btn-sm btn-action">Modificar</a><a href="EliminarCliente.jsp?id=10" class="btn btn-danger btn-sm">Eliminar</a></div></td></tr>
    <tr><td>22334455</td><td>20-22334455-3</td><td>Gonzalo</td><td>Ibarra</td><td>M</td><td>Argentina</td><td>1991-10-10</td><td>Córdoba 333</td><td>Campana</td><td>Buenos Aires</td><td>gonzalo@mail.com</td><td>1222334455</td><td class="text-nowrap"><div class="d-flex"><a href="ModificarCliente.jsp?id=11" class="btn btn-warning btn-sm btn-action">Modificar</a><a href="EliminarCliente.jsp?id=11" class="btn btn-danger btn-sm">Eliminar</a></div></td></tr>
    <tr><td>33445566</td><td>20-33445566-4</td><td>Florencia</td><td>Molina</td><td>F</td><td>Argentina</td><td>1996-12-12</td><td>9 de Julio 444</td><td>San Martín</td><td>Buenos Aires</td><td>flor@mail.com</td><td>1233445566</td><td class="text-nowrap"><div class="d-flex"><a href="ModificarCliente.jsp?id=12" class="btn btn-warning btn-sm btn-action">Modificar</a><a href="EliminarCliente.jsp?id=12" class="btn btn-danger btn-sm">Eliminar</a></div></td></tr>
    <tr><td>44556677</td><td>20-44556677-5</td><td>Tomás</td><td>Paredes</td><td>M</td><td>Argentina</td><td>1994-01-05</td><td>Urquiza 555</td><td>Tigre</td><td>Buenos Aires</td><td>tomas@mail.com</td><td>1244556677</td><td class="text-nowrap"><div class="d-flex"><a href="ModificarCliente.jsp?id=13" class="btn btn-warning btn-sm btn-action">Modificar</a><a href="EliminarCliente.jsp?id=13" class="btn btn-danger btn-sm">Eliminar</a></div></td></tr>
    <tr><td>55667788</td><td>20-55667788-6</td><td>Julia</td><td>Sánchez</td><td>F</td><td>Argentina</td><td>1990-06-30</td><td>Alsina 666</td><td>Ituzaingó</td><td>Buenos Aires</td><td>julia@mail.com</td><td>1255667788</td><td class="text-nowrap"><div class="d-flex"><a href="ModificarCliente.jsp?id=14" class="btn btn-warning btn-sm btn-action">Modificar</a><a href="EliminarCliente.jsp?id=14" class="btn btn-danger btn-sm">Eliminar</a></div></td></tr>
    <tr><td>66778899</td><td>20-66778899-7</td><td>Franco</td><td>Vega</td><td>M</td><td>Argentina</td><td>1986-08-17</td><td>Brown 777</td><td>Merlo</td><td>Buenos Aires</td><td>franco@mail.com</td><td>1266778899</td><td class="text-nowrap"><div class="d-flex"><a href="ModificarCliente.jsp?id=15" class="btn btn-warning btn-sm btn-action">Modificar</a><a href="EliminarCliente.jsp?id=15" class="btn btn-danger btn-sm">Eliminar</a></div></td></tr>
    <tr><td>77889900</td><td>20-77889900-8</td><td>Camila</td><td>Herrera</td><td>F</td><td>Argentina</td><td>1997-11-19</td><td>Jujuy 888</td><td>Moreno</td><td>Buenos Aires</td><td>camila@mail.com</td><td>1277889900</td><td class="text-nowrap"><div class="d-flex"><a href="ModificarCliente.jsp?id=16" class="btn btn-warning btn-sm btn-action">Modificar</a><a href="EliminarCliente.jsp?id=16" class="btn btn-danger btn-sm">Eliminar</a></div></td></tr>
    <tr><td>88990011</td><td>20-88990011-9</td><td>Rodrigo</td><td>Morales</td><td>M</td><td>Argentina</td><td>1982-03-09</td><td>Perón 999</td><td>Hurlingham</td><td>Buenos Aires</td><td>rodrigo@mail.com</td><td>1288990011</td><td class="text-nowrap"><div class="d-flex"><a href="ModificarCliente.jsp?id=17" class="btn btn-warning btn-sm btn-action">Modificar</a><a href="EliminarCliente.jsp?id=17" class="btn btn-danger btn-sm">Eliminar</a></div></td></tr>
    <tr><td>99001122</td><td>20-99001122-0</td><td>Agustina</td><td>Torres</td><td>F</td><td>Argentina</td><td>1998-02-28</td><td>Catamarca 1010</td><td>Pilar</td><td>Buenos Aires</td><td>agustina@mail.com</td><td>1299001122</td><td class="text-nowrap"><div class="d-flex"><a href="ModificarCliente.jsp?id=18" class="btn btn-warning btn-sm btn-action">Modificar</a><a href="EliminarCliente.jsp?id=18" class="btn btn-danger btn-sm">Eliminar</a></div></td></tr>
    <tr><td>10011223</td><td>20-10011223-1</td><td>Federico</td><td>Ríos</td><td>M</td><td>Argentina</td><td>1989-04-20</td><td>Santa Cruz 1111</td><td>Luján</td><td>Buenos Aires</td><td>fede@mail.com</td><td>1300112233</td><td class="text-nowrap"><div class="d-flex"><a href="ModificarCliente.jsp?id=19" class="btn btn-warning btn-sm btn-action">Modificar</a><a href="EliminarCliente.jsp?id=19" class="btn btn-danger btn-sm">Eliminar</a></div></td></tr>
    <tr><td>11122334</td><td>20-11122334-2</td><td>Milagros</td><td>Campos</td><td>F</td><td>Argentina</td><td>1991-07-07</td><td>La Rioja 1212</td><td>San Miguel</td><td>Buenos Aires</td><td>milagros@mail.com</td><td>1311223344</td><td class="text-nowrap"><div class="d-flex"><a href="ModificarCliente.jsp?id=20" class="btn btn-warning btn-sm btn-action">Modificar</a><a href="EliminarCliente.jsp?id=20" class="btn btn-danger btn-sm">Eliminar</a></div></td></tr>
</tbody>

				</table>
			</div>
		</div>
	</div>

	<script src="https://code.jquery.com/jquery-3.7.0.min.js"></script>
	<script
		src="https://cdn.datatables.net/1.13.6/js/jquery.dataTables.min.js"></script>
	<script>
		$(document).ready(function() {
			$('#tablaClientes').DataTable({
				responsive : true,
				autoWidth : false,
				language : {
					search : "Filtrar:",
					lengthMenu : "Mostrar _MENU_ registros por página",
					zeroRecords : "No se encontraron resultados",
					info : "Mostrando _START_ a _END_ de _TOTAL_ registros",
					infoEmpty : "Mostrando 0 a 0 de 0 registros",
					infoFiltered : "(filtrado de _MAX_ registros totales)",
					paginate : {
						first : "Primero",
						last : "Último",
						next : "Siguiente",
						previous : "Anterior"
					}
				}
			});
		});
	</script>
</body>
</html>
