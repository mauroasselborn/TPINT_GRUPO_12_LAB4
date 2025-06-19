<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
    <a class="btn btn-primary mb-3">Solicitar Nuevo Préstamo</a>
    <table class="table table-striped">
      <thead>
        <tr><th>ID</th><th>Importe</th><th>Cuotas</th><th>Estado</th><th>Detalle</th></tr>
      </thead>
      <tbody>

          <tr>
            <td>${p.id}</td>
            <td>${p.importe}</td>
            <td>${p.cuotas}</td>
            <td><span class="badge ${p.estado=='PENDIENTE'?'bg-warning':p.estado=='APROBADO'?'bg-success':'bg-secondary'}">${p.estado}</span></td>
            <td><button type="button" class="btn btn-info btn-sm">Detalle</button></td>
          </tr>

      </tbody>
    </table>
  </div>

  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>