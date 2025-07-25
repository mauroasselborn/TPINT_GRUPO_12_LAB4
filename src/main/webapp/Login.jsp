<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body style="
    background-image: url('imagenes/FondoLogin.png');
    background-size: cover;
    background-position: center;
    background-repeat: no-repeat;
    height: 100vh;
">
<!-- Título superior -->
    <div class="container d-flex flex-column justify-content-center align-items-center vh-100">
     <div class="text-white mb-2 text-center" style="font-size: 1.8rem; font-weight: bold; text-shadow: 1px 1px 3px black;">
        BANCO GRUPO 12
    </div>
        <div class="card p-4 shadow" style="background-color: rgba(255, 255, 255, 0.9); width: 100%; max-width: 400px;">
            <h5 class="text-center mb-4">Iniciar Sesión</h5>

            <% if (request.getAttribute("mensajeError") != null) { %>
                <div style="color:red; text-align:center;">
                    <%= request.getAttribute("mensajeError") %>
                </div>
            <% } %>

            <form action="LoginServlet" method="post">
                <div class="mb-3">
                    <label for="usuario" class="form-label">Usuario</label>
                    <input type="text" class="form-control" id="usuario" name="usuario" required>
                </div>
                <div class="mb-3">
                    <label for="password" class="form-label">Contraseña</label>
                    <input type="password" class="form-control" id="password" name="password">
                </div>
                <button type="submit" class="btn btn-primary w-100">Ingresar</button>
            </form>
        </div>
    </div>
</body>
</html>
