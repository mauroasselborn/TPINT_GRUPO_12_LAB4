<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Alta Cuenta</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">

	<style>
		body {
            background-color: #f8f9fa;
            margin: 0;
            padding: 0;
        }

        .form-container {
            max-width: 500px;
            margin: 40px auto;
            background-color: white;
            padding: 30px;
            border-radius: 12px;
            box-shadow: 0 0 12px rgba(0, 0, 0, 0.15);
        }

        h2 {
            text-align: center;
            margin-bottom: 25px;
            color: #343a40;
        }

        label {
            font-weight: 600;
            margin-top: 15px;
        }

        .btn-success {
            width: 100%;
            margin-top: 25px;
        }
    </style>
</head>
<body>

	<div class="form-container">
		<h2>Alta Cuenta</h2>
		
		<form action="#" method="post">
			<div class="mb-3">
				<label for="cliente" class="form-label">Cliente</label>
				<input type="text" class="form-control" id="cliente" name="cliente">
			</div>
			
			<div class="mb-3">
                <label>Fecha de creación</label>
                <input type="date" class="form-control" name="fechaCreacion">
            </div>
            
            <div class="mb-3">
            	<label>Tipo de cuenta</label>
            	<select class="form-select" id="tipoCuenta" name="tipoCuenta">
            		<option selected disabled>Seleccione un tipo</option>
            		<option>Caja de ahorro</option>
            		<option>Cuenta Corriente</option>
            	</select>
            </div>
            
			<button type="submit" name="btnAgregarCuenta" class="btn btn-primary">Agregar Cuenta</button>	
		</form>
	</div>
</body>
</html>