<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.*" %>

<jsp:include page="../componentes/Encabezado.jsp" />
<jsp:include page="../componentes/MenuLateralAdmin.jsp" />

<!-- Contenedor principal -->
<div class="main-content">
  <jsp:include page="../componentes/BarraSuperior.jsp" />

  <!-- Contenedor de filtros + gráficos filtrables -->
  <div class="container py-4">
    <div class="w-100 mx-auto">

<%
  String inicioAnioStr = java.time.LocalDate.now().withDayOfYear(1).toString();
  String finAnioStr = java.time.LocalDate.now().withMonth(12).withDayOfMonth(31).toString();
%>



<!-- Sección: Gráficos con filtro -->
<div class="mb-5">
  <form method="get" action="reportes" class="mb-4">
    <div class="row justify-content-center align-items-end gx-3">
      <div class="col-auto">
        <label for="desde" class="form-label fw-bold">Desde</label>
        <input type="date" id="desde" name="desde" 
               value="<%= request.getParameter("desde") != null ? request.getParameter("desde") : inicioAnioStr %>" 
               class="form-control" required>
      </div>

      <div class="col-auto">
        <label for="hasta" class="form-label fw-bold">Hasta</label>
        <input type="date" id="hasta" name="hasta" 
               value="<%= request.getParameter("hasta") != null ? request.getParameter("hasta") : finAnioStr %>" 
               class="form-control" required>
      </div>

      <div class="col-auto">
        <button type="submit" class="btn btn-primary mt-2">
          <i class="bi bi-funnel-fill"></i> Filtrar
        </button>
      </div>
    </div>
  </form>

  <!-- Gráficos filtrables -->
  <div class="d-flex flex-wrap justify-content-around gap-4">
    <!-- Cuentas por Tipo -->
    <div style="flex: 1 1 45%; min-width: 300px;">
      <h2 class="text-center mb-4">Cuentas por Tipo</h2>
      <%
        @SuppressWarnings("unchecked")
        Map<String, Integer> datos = (Map<String, Integer>) request.getAttribute("cuentasPorTipo");
        if (datos != null && !datos.isEmpty()) {
      %>
      <canvas id="graficoCuentas" width="400" height="300"></canvas>
      <% } else { %>
      <div class="alert alert-warning text-center">
        No hay datos disponibles para mostrar el gráfico de cuentas.
      </div>
      <% } %>
    </div>

    <!-- Préstamos por Estado -->
    <div style="flex: 1 1 45%; min-width: 300px;">
      <h2 class="text-center mb-4">Préstamos por Estado</h2>
      <%
        @SuppressWarnings("unchecked")
        Map<String, Integer> prestamos = (Map<String, Integer>) request.getAttribute("prestamosPorEstado");
        if (prestamos != null && !prestamos.isEmpty()) {
      %>
      <canvas id="graficoPrestamos" width="400" height="300"></canvas>
      <% } else { %>
      <div class="alert alert-warning text-center">
        No hay datos disponibles para mostrar el gráfico de préstamos.
      </div>
      <% } %>
    </div>
  </div>
</div>

    </div>
  </div> <!-- CIERRE DEL PRIMER CONTAINER -->

  <!-- Segundo contenedor independiente -->
  <div class="container py-4">
    <div class="border-top pt-4">
      <h2 class="text-center mb-4">Clientes por Provincia</h2>
      <%
        @SuppressWarnings("unchecked")
        Map<String, Integer> clientesProv = (Map<String, Integer>) request.getAttribute("clientesPorProvincia");
        if (clientesProv != null && !clientesProv.isEmpty()) {
      %>
      <div class="d-flex justify-content-center">
        <canvas id="graficoClientesProvincia" width="600" height="350"></canvas>
      </div>
      <% } else { %>
      <div class="alert alert-warning text-center">
        No hay datos disponibles para mostrar el gráfico de clientes por provincia.
      </div>
      <% } %>
    </div>
  </div>

  <jsp:include page="../componentes/Footer.jsp" />
</div>

<!-- Chart.js y scripts -->
<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>

<script>
  // Gráfico de Cuentas 
  <% if (datos != null && !datos.isEmpty()) { %>
    const labelsCuentas = [<% for (String tipo : datos.keySet()) { %>"<%= tipo %>",<% } %>];
    const dataCuentas = [<% for (Integer valor : datos.values()) { %><%= valor %>,<% } %>];

    const ctxCuentas = document.getElementById('graficoCuentas').getContext('2d');
    new Chart(ctxCuentas, {
      type: 'bar',
      data: {
        labels: labelsCuentas,
        datasets: [{
          label: 'Cantidad de cuentas',
          data: dataCuentas,
          backgroundColor: 'rgba(54, 162, 235, 0.6)',
          borderColor: 'rgba(54, 162, 235, 1)',
          borderWidth: 1
        }]
      },
      options: {
        responsive: true,
        plugins: {
          title: {
            display: true,
            text: 'Cantidad de Cuentas por Tipo'
          }
        },
        scales: {
          y: {
            beginAtZero: true,
            ticks: {
              stepSize: 1,
              precision: 0,
              callback: function(value) {
                if (Number.isInteger(value)) return value;
              }
            }
          }
        }
      }
    });
  <% } %>

  // Gráfico de Préstamos 
  <% if (prestamos != null && !prestamos.isEmpty()) { %>
    const labelsPrestamos = [<% for (String estado : prestamos.keySet()) { %>"<%= estado %>",<% } %>];
    const dataPrestamos = [<% for (Integer valor : prestamos.values()) { %><%= valor %>,<% } %>];

    const ctxPrestamos = document.getElementById('graficoPrestamos').getContext('2d');
    new Chart(ctxPrestamos, {
      type: 'pie',
      data: {
        labels: labelsPrestamos,
        datasets: [{
          label: 'Préstamos',
          data: dataPrestamos,
          backgroundColor: [
            'rgba(75, 192, 192, 0.6)',
            'rgba(255, 205, 86, 0.6)',
            'rgba(255, 99, 132, 0.6)'
          ],
          borderColor: 'rgba(255,255,255,1)',
          borderWidth: 1
        }]
      },
      options: {
        responsive: true,
        plugins: {
          title: {
            display: true,
            text: 'Cantidad de Préstamos por Estado'
          }
        }
      }
    });
  <% } %>

  // Gráfico de Clientes por Provincia 
  <% if (clientesProv != null && !clientesProv.isEmpty()) { %>
    const labelsClientesProvincia = [<% for (String prov : clientesProv.keySet()) { %>"<%= prov %>",<% } %>];
    const dataClientesProvincia = [<% for (Integer val : clientesProv.values()) { %><%= val %>,<% } %>];

    const ctxClientesProvincia = document.getElementById('graficoClientesProvincia').getContext('2d');
    new Chart(ctxClientesProvincia, {
      type: 'bar',
      data: {
        labels: labelsClientesProvincia,
        datasets: [{
          label: 'Cantidad de clientes',
          data: dataClientesProvincia,
          backgroundColor: 'rgba(153, 102, 255, 0.6)',
          borderColor: 'rgba(153, 102, 255, 1)',
          borderWidth: 1
        }]
      },
      options: {
        responsive: true,
        plugins: {
          title: {
            display: true,
            text: 'Clientes por Provincia'
          }
        },
        scales: {
          y: {
            beginAtZero: true,
            ticks: {
              stepSize: 1,
              precision: 0,
              callback: function(value) {
                if (Number.isInteger(value)) return value;
              }
            }
          }
        }
      }
    });
  <% } %>
</script>
