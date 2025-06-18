<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Solicitar Préstamo</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f2f2f2;
            margin: 0;
            padding: 0;
        }

        .container {
            max-width: 600px;
            margin: 40px auto;
            background-color: white;
            padding: 30px;
            border-radius: 12px;
            box-shadow: 0 0 12px rgba(0, 0, 0, 0.15);
        }

        h2 {
            text-align: center;
            color: #333;
        }

        label {
            display: block;
            margin-top: 15px;
            font-weight: bold;
        }

        input[type="number"],
        select {
            width: 100%;
            padding: 10px;
            margin-top: 5px;
            border: 1px solid #ccc;
            border-radius: 6px;
        }

        input[type="submit"] {
            margin-top: 25px;
            width: 100%;
            padding: 12px;
            background-color: #007bff;
            color: white;
            border: none;
            border-radius: 6px;
            font-size: 16px;
            cursor: pointer;
        }

        input[type="submit"]:hover {
            background-color: #0056b3;
        }

        .info {
            text-align: right;
            color: #555;
            font-size: 14px;
        }

        .nota {
            margin-top: 10px;
            font-size: 13px;
            color: #777;
        }
    </style>
</head>
<body>

<div class="container">
    <div class="info">
        Usuario logueado: <strong>usuario123</strong>
    </div>

    <h2>Solicitud de Préstamo</h2>

    <form action="#" method="post">
        <label for="monto">Monto solicitado ($):</label>
        <input type="number" id="monto" name="monto" placeholder="Ej: 50000" step="0.01" min="1" required>

        <label for="cuotas">Cantidad de cuotas:</label>
        <select id="cuotas" name="cuotas" required>
            <option value="">Seleccione</option>
            <option value="3">3 cuotas</option>
            <option value="6">6 cuotas</option>
            <option value="12">12 cuotas</option>
        </select>

        <label for="cuentaDestino">Cuenta destino:</label>
        <select id="cuentaDestino" name="cuentaDestino" required>
            <option value="">Seleccione una cuenta</option>
            <option value="1">Caja de ahorro - Nº 12345678 - Saldo $12.500,00</option>
            <option value="2">Cuenta corriente - Nº 87654321 - Saldo $7.000,00</option>
        </select>

        <input type="submit" value="Solicitar Préstamo">
    </form>


</div>

</body>
</html>
