<%@ page import="entidades.Prestamo" %>
<%@ page import="java.util.List" %>

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

        <div class="d-flex justify-content-between align-items-center mb-3">
            <h2 class="mb-0">Mis Pr�stamos</h2>
            <a href="PrestamosServlet?accion=solicitar" class="btn btn-primary">
                Solicitar Nuevo Pr�stamo
            </a>
        </div>

        <div class="scroll-x">
            <table id="tablaPrestamos" class="table table-bordered table-hover">
                <thead class="table-dark">
                    <tr>
                        <th>Cuenta</th>
                        <th>Importe</th>
                        <th>Cantidad de Cuotas</th>
                        <th>Importe por Cuota</th>
                        <th>Fecha de Alta</th>
                        <th>Acciones</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        List<Prestamo> listaPrestamos = (List<Prestamo>) request.getAttribute("prestamos");
                        if (listaPrestamos != null) {
                            for (Prestamo p : listaPrestamos) {
                    %>
                    <tr>
                        <td><%= p.getCuenta().getNumeroCuenta() %></td>
                        <td>$<%= String.format("%.2f", p.getImportePedido())%></td>
                        <td><%= p.getCantidadCuotas() %></td>
                        <td><%= p.getImportePorCuota() %></td>
                        <td><%= p.getCuotasPendientes()%></td>
                        <td><%= p.getFechaAlta() %></td>
                        <td>
                            <div class="d-flex justify-content-center">
                                <a href="PrestamosServlet?accion=detalle&id=<%= p.getId() %>" class="btn btn-info btn-sm">
                                    <i class="bi bi-eye"></i> Ver
                                </a>
                            </div>
                        </td>
                    </tr>
                    <%
                            }
                        }
                    %>
                </tbody>
            </table>
        </div>
    </div>

    <jsp:include page="../componentes/Footer.jsp" />
</div>


<script>
$(document).ready(function () {
    $('#tablaPrestamos').DataTable({
        responsive: true,
        autoWidth: false,
        language: {
            search: "Filtrar:",
            lengthMenu: "Mostrar _MENU_ registros por p�gina",
            zeroRecords: "No se encontraron resultados",
            info: "Mostrando _START_ a _END_ de _TOTAL_ registros",
            infoEmpty: "Mostrando 0 a 0 de 0 registros",
            infoFiltered: "(filtrado de _MAX_ registros totales)",
            paginate: {
                first: "Primero",
                last: "�ltimo",
                next: "Siguiente",
                previous: "Anterior"
            }
        }
    });
});
</script>
</body>
</html>
