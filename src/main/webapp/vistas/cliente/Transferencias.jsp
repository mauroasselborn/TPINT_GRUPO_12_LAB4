<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Transferencias</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css" rel="stylesheet">
</head>
<body class="bg-light">

    <div class="container mt-5">
        <div class="card mx-auto shadow-lg" style="max-width: 500px;">
            <div class="card-body">
                <h4 class="card-title text-center mb-4">
                    <i class="bi bi-arrow-left-right"></i> Transferencias
                </h4>

                <!-- Tipo de Transferencia -->
                <fieldset class="border rounded p-4 bg-white">
                    <legend class="w-auto px-2 fw-bold text-primary">Tipo de Transferencia</legend>

                    <div class="d-grid gap-3">
                        <button type="button" class="btn btn-outline-primary d-flex align-items-center justify-content-center"
                                onclick="window.location.href='transferenciaPropia.jsp'">
                            <i class="bi bi-person-check me-2"></i> Entre mis cuentas
                        </button>

                        <button type="button" class="btn btn-outline-success d-flex align-items-center justify-content-center"
                                onclick="window.location.href='transferenciaTerceros.jsp'">
                            <i class="bi bi-people-fill me-2"></i> A terceros
                        </button>
                    </div>
                </fieldset>
            </div>
        </div>
    </div>

</body>
</html>
