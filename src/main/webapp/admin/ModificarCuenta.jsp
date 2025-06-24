<%@ page contentType="text/html;charset=UTF-8" language="java" %>


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
    <div class="info">
      Usuario logueado: <strong>adminBanco</strong>
    </div>

    <h2>Modificar Cuenta</h2>

    <form action="CuentasServlet" method="post">
      <!-- Acción y ID ocultos -->
      <input type="hidden" name="accion" value="guardarModificacion" />
      <input type="hidden" name="id" value="${cuenta.id}" />

      <!-- Número de cuenta (lectura) -->
      <label for="numero">Número de cuenta:</label>
      <input type="text" id="numero" name="numero" 
             value="${cuenta.numero}" readonly class="form-control mb-3"/>

      <!-- Tipo de cuenta -->
      <label for="tipo">Tipo de cuenta:</label>
      <select id="tipo" name="tipo" required class="form-control mb-3">
        <option value="Caja de ahorro" ${cuenta.tipo == 'Caja de ahorro' ? 'selected' : ''}>Caja de ahorro</option>
        <option value="Cuenta corriente" ${cuenta.tipo == 'Cuenta corriente' ? 'selected' : ''}>Cuenta corriente</option>
      </select>

      <!-- CBU -->
      <label for="cbu">CBU:</label>
      <input type="text" id="cbu" name="cbu" maxlength="22" required
             value="${cuenta.cbu}" class="form-control mb-3"/>

      <!-- Saldo -->
      <label for="saldo">Saldo actual ($):</label>
      <input type="number" step="0.01" id="saldo" name="saldo" required
             value="${cuenta.saldo}" class="form-control mb-3"/>

      <button type="submit" class="btn btn-success">Guardar Cambios</button>
      <a href="CuentasServlet?accion=listar" class="btn btn-secondary">Cancelar</a>
    </form>

  </div>

  <jsp:include page="../componentes/Footer.jsp" />
</div>
