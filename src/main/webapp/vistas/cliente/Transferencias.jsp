
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Transferencias</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">

    <div class="container mt-5">
        <div class="card mx-auto shadow" style="max-width: 500px;">
            <div class="card-body">
                <h4 class="card-title text-center mb-4">Realizar Transferencia</h4>

                <form>
                    <div class="mb-3">
                        <label for="cuentaOrigen" class="form-label">Cuenta Origen</label>
                        <select class="form-select" id="cuentaOrigen" required>
                            <option selected disabled>Seleccione una cuenta</option>
                            <option value="1">Cuenta 123456 - $10.000</option>
                            <option value="2">Cuenta 789012 - $5.500</option>
                        </select>
                    </div>

                    <div class="mb-3">
                        <label for="cbuDestino" class="form-label">CBU Destino</label>
                        <input type="text" class="form-control" id="cbuDestino" maxlength="22" required>
                    </div>

                    <div class="mb-3">
                        <label for="monto" class="form-label">Monto</label>
                        <input type="number" class="form-control" id="monto" step="0.01" min="0.01" required>
                    </div>

                    <button type="submit" class="btn btn-success w-100">Transferir</button>
                </form>
            </div>
        </div>
    </div>

</body>
</html>