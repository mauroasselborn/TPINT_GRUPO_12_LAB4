<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Modificar Cuenta</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #eef2f3;
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

        input[type="text"],
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
            background-color: #28a745;
            color: white;
            border: none;
            border-radius: 6px;
            font-size: 16px;
            cursor: pointer;
        }

        input[type="submit"]:hover {
            background-color: #218838;
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
        Usuario logueado: <strong>adminBanco</strong>
    </div>

    <h2>Modificar Cuenta</h2>

    <form action="#" method="post">
        <label for="numeroCuenta">Número de cuenta:</label>
        <input type="text" id="numeroCuenta" name="numeroCuenta" value="12345678" readonly>

        <label for="tipoCuenta">Tipo de cuenta:</label>
        <select id="tipoCuenta" name="tipoCuenta" required>
            <option value="CA">Caja de ahorro</option>
            <option value="CC">Cuenta corriente</option>
        </select>

        <label for="estadoCuenta">Estado de la cuenta:</label>
        <select id="estadoCuenta" name="estadoCuenta" required>
            <option value="activa">Activa</option>
            <option value="inactiva">Inactiva</option>
        </select>

        <label for="cbu">CBU:</label>
        <input type="text" id="cbu" name="cbu" value="2850590940098765432101">

        <label for="saldo">Saldo actual ($):</label>
        <input type="text" id="saldo" name="saldo" value="10000.00" readonly>

        <input type="submit" value="Guardar Cambios">
    </form>


</div>

</body>
</html>
