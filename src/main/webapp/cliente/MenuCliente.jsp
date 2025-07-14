<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="java.util.List, entidades.Cuenta, entidades.Movimiento"%>

<%@ page import="java.text.DecimalFormat"%>
<link rel="stylesheet"
	href="https://cdn.datatables.net/1.13.4/css/jquery.dataTables.min.css">
<!-- noUiSlider CSS -->
<link href="https://cdnjs.cloudflare.com/ajax/libs/noUiSlider/15.7.1/nouislider.min.css" rel="stylesheet" />

<!-- noUiSlider JS -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/noUiSlider/15.7.1/nouislider.min.js"></script>

<!-- jQuery -->
<script src="https://code.jquery.com/jquery-3.5.1.js"></script>

<!-- DataTables JS -->
<script
	src="https://cdn.datatables.net/1.13.4/js/jquery.dataTables.min.js"></script>

<!-- Encabezado -->
<jsp:include page="../componentes/Encabezado.jsp" />

<!-- Sidebar -->
<jsp:include page="../componentes/MenuLateralCliente.jsp" />

<!-- Contenedor principal -->
<div class="main-content">
	<!-- Navbar -->
	<jsp:include page="../componentes/BarraSuperior.jsp" />

	<!-- Contenido principal -->
	<div class="container-fluid content py-4">
		<h1 class="text-primary mb-3">Mis Cuentas</h1>
		<div class="bg-light p-4 rounded shadow-sm">

			<%
			// Obtener atributos enviados por el servlet
			List<Cuenta> cuentas = (List<Cuenta>) request.getAttribute("cuentas");
			Cuenta cuentaSeleccionada = (Cuenta) request.getAttribute("cuentaSeleccionada");
			List<Movimiento> movimientos = (List<Movimiento>) request.getAttribute("movimientos");

			double saldo = cuentaSeleccionada.getSaldo();

			DecimalFormat formatoSaldo = new DecimalFormat("#,##0.00");
			String saldoFormateado = formatoSaldo.format(saldo);
			%>

			<form action="MenuClienteServlet" method="get">
				<div class="mb-3">
					<label for="selectCuenta" class="form-label fw-bold text-primary">Elige
						tu cuenta:</label> <select class="form-select" id="selectCuenta"
						name="idCuenta" onchange="this.form.submit()">
						<%
						if (cuentas != null) {
							for (Cuenta c : cuentas) {
						%>
						<option value="<%=c.getId()%>"
							<%=(cuentaSeleccionada != null && c.getId() == cuentaSeleccionada.getId()) ? "selected" : ""%>>
							CTA$
							<%=c.getNumeroCuenta()%> /
							<%=c.getTipoCuenta().getDescripcion()%> - Saldo: $<%=c.getSaldo()%>
						</option>
						<%
						}
						}
						%>
					</select>
				</div>
			</form>

			<%
			if (cuentaSeleccionada != null) {
			%>
			<!-- Botón Ver CBU y campo para mostrarlo/ocultarlo -->
			<div class="mb-3 d-flex align-items-center">
				<button type="button" class="btn btn-success me-3"
					onclick="toggleCBU()">Ver CBU</button>

				<!-- Span para mostrar el CBU -->
				<span id="cbuSpan" class="fw-bold text-primary me-3"
					style="display: none;"> <%=cuentaSeleccionada.getCbu()%>
				</span> <span id="copyBtn" class="fw-bold text-primary me-3"
					style="display: none;">

					<form action="MenuClienteServlet" method="post" id="copiarForm">
						<input type="hidden" name="accion" value="copiarCBU"> <input
							type="hidden" name="cbu" id="copyBtn"
							value="<%=cuentaSeleccionada.getCbu()%>"> <input
							type="hidden" name="idCuenta"
							value="<%=cuentaSeleccionada.getId()%>">
						<button type="button" class="btn btn-secondary"
							onclick="copiarCBU()">📋</button>
					</form>
				</span>

			</div>

			<script>
				function toggleCBU() {
					var cbuSpan = document.getElementById("cbuSpan");
					var copyBtn = document.getElementById("copyBtn");
					if (cbuSpan.style.display === "none") {
						cbuSpan.style.display = "inline-block";
						copyBtn.style.display = "inline-block";
					} else {
						cbuSpan.style.display = "none";
						copyBtn.style.display = "none";
					}
				}
				function copiarCBU() {
					var cbuSpan = document.getElementById("cbuSpan");
					var tempInput = document.createElement("input");
					tempInput.value = cbuSpan.textContent.trim();
					document.body.appendChild(tempInput);
					tempInput.select();
					tempInput.setSelectionRange(0, 99999);
					document.execCommand("copy");
					document.body.removeChild(tempInput);

					// Mostrar Toast directamente
					toastr.options = {
						"closeButton" : true,
						"progressBar" : true,
						"positionClass" : "toast-bottom-right",
						"timeOut" : "3000"
					};
					toastr.success("CBU copiado en portapapeles.", "Éxito");
				}
			</script>

			<div class="bg-white text-center py-4 rounded border shadow-sm mb-4">
				<div class="fs-6 text-secondary">Saldo:</div>
				<div class="fs-1 fw-semibold text-dark">
					$<%=saldoFormateado%></div>
			</div>

			<h5 class="text-dark mb-3">Detalle de Movimientos</h5>
<div class="d-flex justify-content-start align-items-center mb-2">
  <div class="text-start">
    <div id="sliderRangoMontos" style="width: 250px;"></div>
  </div>
</div>
			<div class="table-responsive">
				<table id="TablaMovimientos"
					class="table table-bordered table-hover align-middle">
					<thead class="table-light">
						<tr>
							<th>#</th>
							<th>Fecha</th>
							<th>Tipo</th>
							<th>Detalle</th>
							<th>Importe</th>
						</tr>
					</thead>
					<tbody>
						<%
						if (movimientos != null && !movimientos.isEmpty()) {
							int i = 1;
							for (Movimiento m : movimientos) {
						%>
						<tr>
							<td><%=i%></td>
							<td><%=m.getFecha()%></td>
							<td><%=m.getTipoMovimiento().getDescripcion()%></td>
							<td><%=m.getDetalle()%></td>
							<td
								class="<%=m.getImporte() >= 0 ? "text-success" : "text-danger"%>">
								<%=m.getImporte() >= 0 ? "+$" : "-$"%><%=Math.abs(m.getImporte())%>
							</td>
						</tr>
						<%
						i++;
						}
						} else {
						%>

						<p class="text-center">No hay movimientos para esta cuenta.</p>

						<%
						}
						%>
					</tbody>
				</table>
				<script>
  var rangoMin = 0;
  var rangoMax = Infinity;
</script>
				<script>
				
				$(document).ready(function () {
					  var table = $('#TablaMovimientos').DataTable({
					    responsive: true,
					    autoWidth: false,
					    pageLength: 10,
					    lengthChange: false,
					    language: {
					      search: "Filtrar:",
					      lengthMenu: "Mostrar _MENU_ registros por página",
					      zeroRecords: "No se encontraron resultados",
					      info: "Mostrando _START_ a _END_ de _TOTAL_ registros",
					      infoEmpty: "Mostrando 0 a 0 de 0 registros",
					      infoFiltered: "(filtrado de _MAX_ registros totales)",
					      paginate: {
					        first: "Primero",
					        last: "Último",
					        next: "Siguiente",
					        previous: "Anterior"
					      }
					    }
					  });

					  // Obtener montos para el rango
					  var montos = [];
					  $('#TablaMovimientos tbody tr').each(function () {
					    var texto = $(this).find('td:eq(4)').text().replace(/[^\d.-]/g, '');
					    var valor = parseFloat(texto);
					    if (!isNaN(valor)) {
					      montos.push(Math.abs(valor));
					    }
					  });

					  if (montos.length === 0) return;

					  var minValor = Math.floor(Math.min(...montos));
					  var maxValor = Math.ceil(Math.max(...montos));

					  var slider = document.getElementById('sliderRangoMontos');

					  noUiSlider.create(slider, {
					    start: [minValor, maxValor],
					    connect: true,
					    range: {
					      min: minValor,
					      max: maxValor
					    },
					    step: 1,
					    behaviour: 'drag',
					    tooltips: [true, true],
					    format: {
					      to: function (value) {
					        return Math.round(value);
					      },
					      from: function (value) {
					        return Number(value);
					      }
					    }
					  });

					  slider.querySelectorAll('.noUi-connect').forEach(el => el.style.background = 'black');
					  slider.querySelectorAll('.noUi-handle').forEach(el => el.style.borderRadius = '50%');

					  // ✅ Filtro personalizado para DataTables (por monto absoluto)
					  $.fn.dataTable.ext.search.push(function (settings, data, dataIndex) {
					    var texto = data[4].replace(/[^\d.-]/g, ''); // Columna Importe
					    var valor = parseFloat(texto);
					    if (isNaN(valor)) return false;
					    return Math.abs(valor) >= rangoMin && Math.abs(valor) <= rangoMax;
					  });

					  // Actualizar rango y redibujar tabla al mover el slider
					  slider.noUiSlider.on('update', function (valores) {
					    rangoMin = parseFloat(valores[0]);
					    rangoMax = parseFloat(valores[1]);
					    table.draw(); // 🔁 esto sí actualiza todo correctamente
					  });
					});
				</script>
			</div>
			<%
			}
			%>

		</div>
	</div>

	<!-- Footer -->
	<jsp:include page="../componentes/Footer.jsp" />
</div>

</body>
</html>
