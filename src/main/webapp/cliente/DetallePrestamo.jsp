<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>Detalle de Préstamo</title>
  <!-- Enlace a Bootstrap 5.3.0 -->
     <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css" rel="stylesheet">
</head>
<body>

  <div class="container mt-4">
    <h2>Detalle de Préstamo</h2>
    <div class="card">
      <div class="card-body">
        <form>
          <input type="hidden" name="idPrestamo" value="${prestamo.id}" />
          <div class="mb-3 row">
            <label class="col-sm-2 col-form-label">Cliente:</label>
            <div class="col-sm-10">
              <input type="text" readonly class="form-control" value="${prestamo.cliente.nombre} ${prestamo.cliente.apellido}" />
            </div>
          </div>
          <div class="mb-3 row">
            <label class="col-sm-2 col-form-label">Importe:</label>
            <div class="col-sm-4">
              <input type="text" readonly class="form-control" value="${prestamo.importe}" />
            </div>
            <label class="col-sm-2 col-form-label">Cuotas:</label>
            <div class="col-sm-4">
              <input type="text" readonly class="form-control" value="${prestamo.cuotas}" />
            </div>
          </div>
          <a href="Prestamos.jsp" class="btn btn-secondary">Volver</a>
        </form>
      </div>
    </div>
  </div>


  <!-- Scripts de Bootstrap -->
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>