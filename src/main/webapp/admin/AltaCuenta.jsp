<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.List, entidades.Cliente"%>

<!-- Encabezado -->
<jsp:include page="../componentes/Encabezado.jsp" />

<!-- Menú lateral -->
<jsp:include page="../componentes/MenuLateralAdmin.jsp" />

<!-- Contenedor principal -->
<div class="main-content">

	<!-- Barra superior -->
	<jsp:include page="../componentes/BarraSuperior.jsp" />

	<!-- Contenido -->
	<div class="container-fluid content py-4">
		<div class="form-container">
			<h2>Alta de Cuenta</h2>

			<!-- Formulario -->
			<form action="CuentasServlet" method="post">
				<input type="hidden" name="accion" value="crear" />

				<!-- Lista de clientes -->
				<div class="mb-3">
					<label for="clienteId" class="form-label">Cliente</label> <select
						id="clienteId" name="clienteId" class="form-select" required>
						<option value="" disabled selected>Seleccione un cliente</option>
						<%
						@SuppressWarnings("unchecked")
              List<Cliente> listaClientes = (List<Cliente>) request.getAttribute("clientes");
              if (listaClientes != null) {
                for (int i = 0; i < listaClientes.size(); i++) {
                  Cliente c = listaClientes.get(i);
            %>
						<option value="<%= c.getId() %>"><%= c.getApellido() %>,
							<%= c.getNombre() %> -
							<%= c.getDni() %></option>
						<%
                }
              }else{%>
						<option disabled>No hay clientes cargados</option>
						<%}
            %>
					</select>
				</div>

				<!-- Tipo de cuenta -->
				<div class="mb-3">
					<label for="tipo" class="form-label">Tipo de cuenta</label> <select
						id="tipo" name="tipo" class="form-select" required>
						<option value="" disabled selected>Seleccione un tipo</option>
						<option value="1">Caja de ahorro</option>
						<option value="2">Cuenta corriente</option>
					</select>
				</div>

				<!-- CBU generado automáticamente -->
				<div class="mb-3">
					<label for="cbu" class="form-label">CBU</label> <input type="text"
						id="cbu" name="cbu" class="form-control" maxlength="22" readonly
						value="<%= request.getAttribute("cbu") != null ? request.getAttribute("cbu") : "" %>" />
				</div>

				<!-- Número de cuenta generado automáticamente -->
				<div class="mb-3">
					<label for="numero" class="form-label">Número de cuenta</label> <input
						type="text" id="numero" name="numero" class="form-control"
						maxlength="20" readonly
						value="<%= request.getAttribute("numeroCuenta") != null ? request.getAttribute("numeroCuenta") : "" %>" />
				</div>
				<!-- Saldo inicial de $10.000 mostrado como lectura -->
				<div class="mb-3">
  <label for="saldo" class="form-label">Saldo inicial</label>
  <input type="number" id="saldo" name="saldo" class="form-control" min="10000" step="0.01"
         value="<%= request.getAttribute("saldo") != null ? request.getAttribute("saldo") : "10000" %>" required />
</div>

				<!-- Botones -->
				<div class="mb-3">
					<button type="submit" class="btn btn-primary">Agregar</button>
					<a href="CuentasServlet?accion=listar" class="btn btn-secondary">Cancelar</a>
				</div>
			</form>
		</div>
	</div>

	<!-- Footer -->
	<jsp:include page="../componentes/Footer.jsp" />

</div>