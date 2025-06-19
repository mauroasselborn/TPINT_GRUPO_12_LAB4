<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>Gestionar Préstamos</title>
     <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css" rel="stylesheet">
</head>
<body>

  <div class="container mt-4">
    <h2>Gestión de Préstamos</h2>
    <table class="table table-bordered table-hover">
      <thead class="table-light">
        <tr>
          <th>ID</th><th>Cliente</th><th>Importe</th><th>Cuotas</th><th>Fecha</th><th>Estado</th><th>Acciones</th>
        </tr>
      </thead>
      <tbody>

          <tr>
            <td>${p.id}</td>
            <td>${p.cliente.apellido}, ${p.cliente.nombre}</td>
            <td>${p.importe}</td>
            <td>${p.cuotas}</td>
            <td>${p.fechaAlta}</td>
            <td>${p.estado}</td>
            <td>
              <button type="button" class="btn btn-success btn-sm">Aprobar</button>
              <button type="button" class="btn btn-danger btn-sm">Rechazar</button>
            </td>
          </tr>

      </tbody>
    </table>
  </div>

  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>