<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<!-- Encabezado -->
<jsp:include page="../componentes/Encabezado.jsp" />

<!-- Sidebar -->
<jsp:include page="../componentes/MenuLateralAdmin.jsp" />

<div class="main-content">
  <!-- Navbar -->
  <jsp:include page="../componentes/BarraSuperiorAdmin.jsp" />
  <!-- Contenido principal -->
  <div class="container-fluid content py-4">
    <div class="form-container">
      <h2>Alta Cuenta</h2>

      <!-- El servlet "CuentasServlet" en doGet, case "nuevo", debe haber cargado ${clientes} -->
      <form action="CuentasServlet" method="post">
        <input type="hidden" name="accion" value="crear"/>

        <div class="mb-3">
          <label for="clienteId" class="form-label">Cliente</label>
          <select id="clienteId" name="clienteId" class="form-select" required>
            <option value="" disabled selected>Seleccione un cliente</option>
            <c:forEach var="cliente" items="${clientes}">
              <option value="${cliente.id}">
                ${cliente.nombre} ${cliente.apellido} (DNI: ${cliente.dni})
              </option>
            </c:forEach>
          </select>
        </div>

        <div class="mb-3">
          <label for="tipo" class="form-label">Tipo de cuenta</label>
          <select id="tipo" name="tipo" class="form-select" required>
            <option value="" disabled selected>Seleccione un tipo</option>
            <option value="Caja de ahorro">Caja de ahorro</option>
            <option value="Cuenta corriente">Cuenta corriente</option>
          </select>
        </div>

        <div class="mb-3">
          <label for="cbu" class="form-label">CBU</label>
          <input type="text" id="cbu" name="cbu" class="form-control"
                 maxlength="22" required placeholder="22 dígitos numéricos"/>
        </div>

        <div class="mb-3">
          <label for="numero" class="form-label">Número de cuenta</label>
          <input type="text" id="numero" name="numero" class="form-control"
                 maxlength="20" required placeholder="Máximo 20 caracteres"/>
        </div>

        <button type="submit" class="btn btn-primary">Agregar Cuenta</button>
        <a href="CuentasServlet?accion=listar" class="btn btn-secondary">Cancelar</a>
      </form>
    </div>
  </div>

  <jsp:include page="../componentes/Footer.jsp" />
</div>