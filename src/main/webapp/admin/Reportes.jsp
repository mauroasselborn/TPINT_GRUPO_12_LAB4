<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.*" %>

<!-- Encabezado -->
<jsp:include page="../componentes/Encabezado.jsp" />

<!-- Sidebar -->
<jsp:include page="../componentes/MenuLateralAdmin.jsp" />

<!-- Contenedor principal -->
<div class="main-content">

  <!-- Navbar superior -->
  <jsp:include page="../componentes/BarraSuperior.jsp" />
  
  
  
  

  <!-- Contenido principal -->
  <div class="container content py-4">
    <div class="w-75 mx-auto">
      <h2 class="text-center mb-4">Cuentas por Tipo</h2>
      
      

      <%
        Map<String, Integer> datos = (Map<String, Integer>) request.getAttribute("cuentasPorTipo");
        if (datos != null && !datos.isEmpty()) {
      %>

      <canvas id="graficoCuentas" width="600" height="400"></canvas>

      <% } else { %>
        <div class="alert alert-warning text-center">
          No hay datos disponibles para mostrar el gráfico de cuentas.
        </div>
      <% } %>

      <hr class="my-5">

      <h2 class="text-center mb-4">Préstamos por Estado</h2>

      <%
        Map<String, Integer> prestamos = (Map<String, Integer>) request.getAttribute("prestamosPorEstado");
        if (prestamos != null && !prestamos.isEmpty()) {
      %>

      <canvas id="graficoPrestamos" width="600" height="400"></canvas>

      <% } else { %>
        <div class="alert alert-warning text-center">
          No hay datos disponibles para mostrar el gráfico de préstamos.
        </div>
      <% } %>
    </div>
  </div>

  <!-- Footer -->
  <jsp:include page="../componentes/Footer.jsp" />
</div>

<!-- Chart.js y scripts -->
<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>

<script>
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
          y: { beginAtZero: true }
        }
      }
    });
  <% } %>

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
            text: 'Distribución de Préstamos por Estado'
          }
        }
      }
    });
  <% } %>
</script>
