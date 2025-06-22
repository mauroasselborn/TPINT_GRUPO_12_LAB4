<!-- Encabezado -->
  <jsp:include page="../componentes/Encabezado.jsp" />

  <!-- Sidebar -->
  <jsp:include page="../componentes/MenuLateralAdmin.jsp" />

  <!-- Contenedor principal -->
  <div class="main-content">
    <!-- Navbar -->
    <jsp:include page="../componentes/BarraSuperiorAdmin.jsp" />
    <!-- Contenido principal -->
    <div class="container-fluid content py-4">
			

			<h2>Reporte de Movimientos</h2>

			<form action="#" method="get">
				<div>
					<label>Fecha desde:</label><br> <input type="date"
						name="fechaDesde">
				</div>

				<div>
					<label>Fecha hasta:</label><br> <input type="date"
						name="fechaHasta">
				</div>

				<div>
					<label>Tipo de movimiento:</label><br> <select
						name="tipoMovimiento">
						<option value="">Todos</option>
						<option value="altaCuenta">Alta de cuenta</option>
						<option value="altaPrestamo">Alta de pr�stamo</option>
						<option value="pagoPrestamo">Pago de pr�stamo</option>
						<option value="transferencia">Transferencia</option>
					</select>
				</div>

				<div>
					<label>Importe m�nimo:</label><br> <input type="number"
						name="importeMin" step="0.01" min="0" placeholder="Ej: 1000.00">
				</div>

				<div>
					<label>Importe m�ximo:</label><br> <input type="number"
						name="importeMax" step="0.01" min="0" placeholder="Ej: 50000.00">
				</div>

				<div style="flex-basis: 100%; text-align: right;">
					<input type="submit" value="Filtrar">
				</div>
			</form>

			<table>
				<thead>
					<tr>
						<th>Fecha</th>
						<th>Detalle</th>
						<th>Importe</th>
						<th>Tipo</th>
						<th>Cuenta</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>2025-06-10</td>
						<td>Dep�sito inicial</td>
						<td>$10.000,00</td>
						<td>Alta de cuenta</td>
						<td>12345678</td>
					</tr>
					<tr>
						<td>2025-06-11</td>
						<td>Pr�stamo aprobado</td>
						<td>$50.000,00</td>
						<td>Alta de pr�stamo</td>
						<td>12345678</td>
					</tr>
					<tr>
						<td>2025-06-12</td>
						<td>Transferencia a cuenta 98765432</td>
						<td>-$5.000,00</td>
						<td>Transferencia</td>
						<td>12345678</td>
					</tr>
				</tbody>
			</table>

			<div class="pagination">
				<a href="#">1</a> <a href="#" class="active">2</a> <a href="#">3</a>
			</div>
		</div>
		<jsp:include page="../componentes/Footer.jsp" />
	</div>

</body>
</html>
