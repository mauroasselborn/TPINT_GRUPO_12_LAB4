<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>Mis Préstamos</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css" rel="stylesheet">
</head>
<body>

  <div class="container mt-4">
    <h2>Mis Préstamos</h2>
    <a href="SolicitarPrestamo.jsp" class="btn btn-primary mb-3">Solicitar Nuevo Préstamo</a>

    <table class="table table-striped">
      <thead>
        <tr>
          <th>ID</th>
          <th>Importe</th>
          <th>Cuotas</th>
          <th>Estado</th>
          <th>Detalle</th>
        </tr>
      </thead>
      <tbody>
        <tr>
          <td>1</td>
          <td>$50.000</td>
          <td>12</td>
          <td><span class="badge bg-warning">PENDIENTE</span></td>
          <td><a href="DetallePrestamo.jsp" class="btn btn-info btn-sm"><i class="bi bi-eye"></i> Ver</a></td>
        </tr>
        <tr>
          <td>2</td>
          <td>$75.000</td>
          <td>24</td>
          <td><span class="badge bg-success">APROBADO</span></td>
          <td><a href="DetallePrestamo.jsp" class="btn btn-info btn-sm"><i class="bi bi-eye"></i> Ver</a></td>
        </tr>
        <tr>
          <td>3</td>
          <td>$30.000</td>
          <td>6</td>
          <td><span class="badge bg-danger">RECHAZADO</span></td>
          <td><a href="DetallePrestamo.jsp" class="btn btn-info btn-sm"><i class="bi bi-eye"></i> Ver</a></td>
        </tr>
      </tbody>
    </table>
  </div>

  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>