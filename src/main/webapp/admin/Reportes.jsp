<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Reporte de Movimientos</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #e6ecf0;
            margin: 0;
            padding: 0;
        }

        .container {
            max-width: 1000px;
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

        form {
            margin-bottom: 30px;
            display: flex;
            flex-wrap: wrap;
            gap: 20px;
            align-items: center;
            justify-content: space-between;
        }

        label {
            font-weight: bold;
        }

        input[type="date"],
        input[type="number"],
        select {
            padding: 8px;
            margin-top: 5px;
            border: 1px solid #ccc;
            border-radius: 6px;
            width: 180px;
        }

        input[type="submit"] {
            padding: 10px 20px;
            background-color: #17a2b8;
            color: white;
            border: none;
            border-radius: 6px;
            font-size: 14px;
            cursor: pointer;
            margin-top: 20px;
        }

        input[type="submit"]:hover {
            background-color: #117a8b;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 15px;
        }

        table, th, td {
            border: 1px solid #ddd;
        }

        th {
            background-color: #f5f5f5;
            padding: 10px;
            text-align: left;
        }

        td {
            padding: 10px;
        }

        .info {
            text-align: right;
            color: #555;
            font-size: 14px;
        }

        .pagination {
            text-align: center;
            margin-top: 20px;
        }

        .pagination a {
            margin: 0 5px;
            padding: 6px 12px;
            background-color: #ccc;
            color: #000;
            text-decoration: none;
            border-radius: 4px;
        }

        .pagination a.active {
            background-color: #17a2b8;
            color: white;
        }
    </style>
</head>
<body>

<div class="container">
    <div class="info">
        Usuario logueado: <strong>adminBanco</strong>
    </div>

    <h2>Reporte de Movimientos</h2>

    <form action="#" method="get">
        <div>
            <label>Fecha desde:</label><br>
            <input type="date" name="fechaDesde">
        </div>

        <div>
            <label>Fecha hasta:</label><br>
            <input type="date" name="fechaHasta">
        </div>

        <div>
            <label>Tipo de movimiento:</label><br>
            <select name="tipoMovimiento">
                <option value="">Todos</option>
                <option value="altaCuenta">Alta de cuenta</option>
                <option value="altaPrestamo">Alta de préstamo</option>
                <option value="pagoPrestamo">Pago de préstamo</option>
                <option value="transferencia">Transferencia</option>
            </select>
        </div>

        <div>
            <label>Importe mínimo:</label><br>
            <input type="number" name="importeMin" step="0.01" min="0" placeholder="Ej: 1000.00">
        </div>

        <div>
            <label>Importe máximo:</label><br>
            <input type="number" name="importeMax" step="0.01" min="0" placeholder="Ej: 50000.00">
        </div>

        <div style="flex-basis: 100%; text-align: right;">
            <input type="submit" value="Filtrar">
        </div>
    </form>

    <table>
        <thead>
        <tr>
            <th>Fecha</th>
            <th>Detalle</th>
            <th>Importe</th>
            <th>Tipo</th>
            <th>Cuenta</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td>2025-06-10</td>
            <td>Depósito inicial</td>
            <td>$10.000,00</td>
            <td>Alta de cuenta</td>
            <td>12345678</td>
        </tr>
        <tr>
            <td>2025-06-11</td>
            <td>Préstamo aprobado</td>
            <td>$50.000,00</td>
            <td>Alta de préstamo</td>
            <td>12345678</td>
        </tr>
        <tr>
            <td>2025-06-12</td>
            <td>Transferencia a cuenta 98765432</td>
            <td>-$5.000,00</td>
            <td>Transferencia</td>
            <td>12345678</td>
        </tr>
        </tbody>
    </table>

    <div class="pagination">
        <a href="#">1</a>
        <a href="#" class="active">2</a>
        <a href="#">3</a>
    </div>
</div>

</body>
</html>
