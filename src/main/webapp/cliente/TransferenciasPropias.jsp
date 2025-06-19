<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <head>
    <meta charset="UTF-8">
    <title>Transferencias Propias</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css" rel="stylesheet">
    <style>
        /* Estilos básicos para el formulario y resumen */
        body { font-family: Arial, sans-serif; margin: 20px; background-color: #f9f9f9; }
        h1 { color: #333; }
        .form-container, .result-container { max-width: 500px; padding: 20px; margin: auto; background-color: #fff; border: 1px solid #ccc; border-radius: 5px; }
        label { display: block; margin: 10px 0 5px; font-weight: bold; }
        select, input[type="number"] { width: 100%; padding: 8px; box-sizing: border-box; }
        .buttons { margin-top: 15px; }
        .buttons button { margin-right: 10px; padding: 8px 16px; }
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
    String cuentaOrigen = request.getParameter("cuentaOrigen");
    String cuentaDestino = request.getParameter("cuentaDestino");
    String importe = request.getParameter("importe");

    // Verificar si el formulario fue enviado (parámetros no nulos)
    boolean formEnviado = (cuentaOrigen != null && cuentaDestino != null && importe != null);
%>

<% if (!formEnviado) { %>
    <!-- Sección de Formulario de transferencia -->
    <div class="form-container">
    <h1>Transferencia entre Cuentas Propias</h1>
        <form action="TransferenciasPropias.jsp" method="post">
            <label for="origen">Cuenta Origen:</label>
            <select id="origen" name="cuentaOrigen" required>
                <%-- Opciones de cuentas de origen (simuladas) --%>
                <% for (String c : cuentas) { %>
                    <option value="<%= c %>"><%= c %></option>
                <% } %>
            </select>

            <label for="destino">Cuenta Destino:</label>
            <select id="destino" name="cuentaDestino" required>
                <%-- Opciones de cuentas de destino (simuladas) --%>
                <% for (String c : cuentas) { %>
                    <option value="<%= c %>"><%= c %></option>
                <% } %>
            </select>

            <label for="importe">Importe:</label>
            <input type="number" id="importe" name="importe" step="0.01" min="0" required />

            <div class="buttons">
                <button type="submit" class="btn btn-primary">Transferir</button>
                <button type="reset" class="btn btn-secondary">Limpiar</button>
            </div>
        </form>
    </div>
<% } else { %>
    <!-- Sección de Resumen de la operación (después de enviar el formulario) -->
    <h1>Resumen de la Transferencia.</h1>
    <div class="result-container">
        <p><strong>Cuenta Origen:</strong> <%= cuentaOrigen %></p>
        <p><strong>Cuenta Destino:</strong> <%= cuentaDestino %></p>
        <p><strong>Importe:</strong> <%= importe %></p>
        <hr />
        <p>¡Transferencia realizada con éxito!</p>
        <!-- Enlace para realizar otra transferencia (recargar formulario) -->
        <p><a href="TransferenciasPropias.jsp">Realizar otra transferencia</a></p>
    </div>
<% } %>
</body>
</html>