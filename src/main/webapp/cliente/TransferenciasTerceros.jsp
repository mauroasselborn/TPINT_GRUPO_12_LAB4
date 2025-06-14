<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Transferencias Terceros</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css" rel="stylesheet">
    <style>
        body { font-family: Arial, sans-serif; margin: 20px; background-color: #f9f9f9; }
        h1 { color: #333; }
        .form-container, .result-container { max-width: 500px; padding: 20px; margin: auto; background-color: #fff; border: 1px solid #ccc; border-radius: 5px; }
        label { display: block; margin: 10px 0 5px; font-weight: bold; }
        select, input[type="text"], input[type="number"] { width: 100%; padding: 8px; box-sizing: border-box; }
        .buttons { margin-top: 15px; }
        .buttons button, .buttons a { margin-right: 10px; padding: 8px 16px; }
    </style>
</head>
<body>
<%
    // Lista de cuentas simuladas (mock) del cliente
    String[] cuentas = {
        "Caja de Ahorro (Pesos) - 00123456",
        "Cuenta Corriente (Pesos) - 00123457",
        "Caja de Ahorro (Dólares) - 00123458"
    };

    // Obtener parámetros del formulario (si existen)
    String cuentaOrigen   = request.getParameter("cuentaOrigen");
    String cbuDestino     = request.getParameter("cbuDestino");
    String importe        = request.getParameter("importe");

    // Verificar si el formulario fue enviado
    boolean formEnviado = (cuentaOrigen != null && cbuDestino != null && importe != null);
%>

<% if (!formEnviado) { %>
    <!-- Sección de Formulario de transferencia a terceros -->
    <div class="form-container">
        <h1>Transferencia a Terceros</h1>
        <form action="transferenciasTerceros.jsp" method="post">
            <label for="origen">Cuenta Origen:</label>
            <select id="origen" name="cuentaOrigen" class="form-select" required>
                <% for (String c : cuentas) { %>
                    <option value="<%= c %>"><%= c %></option>
                <% } %>
            </select>

            <label for="cbu">CBU Destino:</label>
            <input type="text" id="cbu" name="cbuDestino" class="form-control"
                   placeholder="Ingrese CBU de la cuenta de destino"
                   pattern="\\d{16}" title="16 dígitos numéricos" required />

            <label for="importe">Importe:</label>
            <input type="number" id="importe" name="importe" class="form-control"
                   step="0.01" min="0" placeholder="0.00" required />

            <div class="buttons">
                <button type="submit" class="btn btn-primary">Transferir</button>
                <button type="reset" class="btn btn-secondary">Limpiar</button>
            </div>
        </form>
    </div>
<% } else { %>
    <!-- Sección de Resumen de la operación -->
    <h1>Resumen de la Transferencia</h1>
    <div class="result-container">
        <p><strong>Cuenta Origen:</strong> <%= cuentaOrigen %></p>
        <p><strong>CBU Destino:</strong> <%= cbuDestino %></p>
        <p><strong>Importe:</strong> <%= importe %></p>
        <hr />
        <p>¡Transferencia a tercero realizada con éxito!</p>
        <div class="buttons">
            <a href="transferenciasTerceros.jsp" class="btn btn-primary">Hacer otra transferencia...</a>
        </div>
    </div>
<% } %>
</body>
</html>