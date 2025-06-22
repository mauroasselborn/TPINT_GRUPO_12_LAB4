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

    <form action="#" method="post">
        <label for="numeroCuenta">Número de cuenta:</label>
        <input type="text" id="numeroCuenta" name="numeroCuenta" value="12345678" readonly>

        <label for="tipoCuenta">Tipo de cuenta:</label>
        <select id="tipoCuenta" name="tipoCuenta" required>
            <option value="CA">Caja de ahorro</option>
            <option value="CC">Cuenta corriente</option>
        </select>

        <label for="estadoCuenta">Estado de la cuenta:</label>
        <select id="estadoCuenta" name="estadoCuenta" required>
            <option value="activa">Activa</option>
            <option value="inactiva">Inactiva</option>
        </select>

        <label for="cbu">CBU:</label>
        <input type="text" id="cbu" name="cbu" value="2850590940098765432101">

        <label for="saldo">Saldo actual ($):</label>
        <input type="text" id="saldo" name="saldo" value="10000.00" readonly>

        <input type="submit" value="Guardar Cambios">
    </form>


</div>
<jsp:include page="../componentes/Footer.jsp" />
</div>
</body>
</html>
