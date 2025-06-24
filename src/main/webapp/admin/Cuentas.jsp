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
    <div class="p-4">
      <div class="d-flex justify-content-between align-items-center mb-3">
        <h2 class="mb-0">Cuentas</h2>
        <!-- Ahora llama al servlet para mostrar AltaCuenta.jsp -->
        <a href="CuentasServlet?accion=nuevo" class="btn btn-primary">
          Agregar nueva cuenta
        </a>
      </div>

      <div class="scroll-container">
        <table id="tablaCuentas" class="table table-bordered table-hover w-100">
          <thead class="table-dark">
            <tr>
              <th>N° Cuenta</th><th>CBU</th><th>Tipo</th><th>Saldo</th>
              <th>Fecha Alta</th><th>Cliente</th><th>Acciones</th>
            </tr>
          </thead>
          <tbody>
            <!-- Reemplazo de la fila estática por un forEach -->
            <c:forEach var="cuenta" items="${cuentas}">
              <tr>
                <td>${cuenta.numero}</td>
                <td>${cuenta.cbu}</td>
                <td>${cuenta.tipo}</td>
                <td>${cuenta.saldo}</td>
                <td>${cuenta.fechaCreacion}</td>
                <td>${cuenta.cliente.nombre} ${cuenta.cliente.apellido}</td>
                <td class="text-nowrap">
                  <div class="d-flex justify-content-center">
                    <!-- Ahora llama al servlet para editar -->
                    <a href="CuentasServlet?accion=editar&id=${cuenta.id}"
                       class="btn btn-warning btn-sm btn-action">
                      Modificar
                    </a>
                    <!-- Eliminar dispara el modal y luego GET al servlet -->
                    <button class="btn btn-danger btn-sm"
                            onclick="confirmarEliminacion(${cuenta.id})">
                      Eliminar
                    </button>
                  </div>
                </td>
              </tr>
            </c:forEach>
          </tbody>
        </table>
      </div>
    </div>
  </div>
  <jsp:include page="../componentes/Footer.jsp" />
</div>

<!-- Modal de confirmación -->
<div class="modal fade" id="modalEliminar" tabindex="-1" aria-labelledby="modalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="modalLabel">Confirmar Eliminación</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Cerrar"></button>
      </div>
      <div class="modal-body">
        ¿Estás seguro que querés eliminar esta cuenta?
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
        <!-- Ahora el form va al mismo servlet con GET y accion=borrar -->
        <form id="formEliminar" action="CuentasServlet" method="get">
          <input type="hidden" name="accion" value="borrar"/>
          <input type="hidden" name="id" id="idCuentaEliminar"/>
          <button type="submit" class="btn btn-danger">Eliminar</button>
        </form>
      </div>
    </div>
  </div>
</div>

<!-- Scripts -->
<script src="https://code.jquery.com/jquery-3.7.0.min.js"></script>
<script src="https://cdn.datatables.net/1.13.6/js/jquery.dataTables.min.js"></script>
<script>
  $(document).ready(function() {
    $('#tablaCuentas').DataTable({
      responsive: true,
      autoWidth: false,
      language: {
        search: "Filtrar:",
        lengthMenu: "Mostrar _MENU_ registros por página",
        zeroRecords: "No se encontraron resultados",
        info: "Mostrando _START_ a _END_ de _TOTAL_ registros",
        infoEmpty: "Mostrando 0 a 0 de 0 registros",
        infoFiltered: "(filtrado de _MAX_ registros totales)",
        paginate: { first: "Primero", last: "Último", next: "Siguiente", previous: "Anterior" }
      }
    });
  });

  function confirmarEliminacion(id) {
    document.getElementById('idCuentaEliminar').value = id;
    let modal = new bootstrap.Modal(document.getElementById('modalEliminar'));
    modal.show();
  }
</script>
