<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.*" %>

<jsp:include page="../componentes/Encabezado.jsp" />
<jsp:include page="../componentes/MenuLateralAdmin.jsp" />

<!-- Contenedor principal -->
<div class="main-content">
  <jsp:include page="../componentes/BarraSuperior.jsp" />

  <!-- Contenido principal -->
  <div class="container content py-4">
    <div class="w-100 mx-auto">
    
    <%
  java.time.LocalDate inicioAnio = java.time.LocalDate.now().withDayOfYear(1);
  java.time.LocalDate finAnio = java.time.LocalDate.now().withMonth(12).withDayOfMonth(31);
%>
    
    
    
<form method="get" action="reportes" class="mb-4 text-center">
  <label>
    Desde:
    <input type="date" name="desde" value="<%= inicioAnio %>" required>
  </label>
  <label>
    Hasta:
    <input type="date" name="hasta" value="<%= finAnio %>" required>
  </label>
  <button type="submit" class="btn btn-primary">Filtrar</button>
</form>

      <!-- Fila de gráficos -->
      <div style="display: flex; flex-wrap: wrap; justify-content: space-around; gap: 30px;">
        <!-- Cuentas por Tipo -->
        <div style="flex: 1 1 45%; min-width: 300px;">
          <h2 class="text-center mb-4">Cuentas por Tipo</h2>
          <%
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

        <!-- Clientes por Provincia -->
        <div style="flex: 1 1 45%; min-width: 300px;">
          <h2 class="text-center mb-4">Clientes por Provincia</h2>
          <%
            Map<String, Integer> clientesProv = (Map<String, Integer>) request.getAttribute("clientesPorProvincia");
            if (clientesProv != null && !clientesProv.isEmpty()) {
          %>
          <canvas id="graficoClientesProvincia" width="400" height="300"></canvas>
          <% } else { %>
            <div class="alert alert-warning text-center">
              No hay datos disponibles para mostrar el gráfico de clientes por provincia.
            </div>
          <% } %>
        </div>
      </div>
    </div>
  </div>

  <jsp:include page="../componentes/Footer.jsp" />
</div>

<!-- Chart.js y scripts -->
<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>

<script>
  <%-- Gráfico de Cuentas --%>
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

  <%-- Gráfico de Préstamos --%>
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

  <%-- Gráfico de Clientes por Provincia --%>
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
