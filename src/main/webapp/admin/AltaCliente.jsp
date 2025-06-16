<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Alta Cliente</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body>
    <div class="container mt-4">
        <div class="w-75 mx-auto">
            <h2 class="text-center mb-4">Alta Cliente</h2>
            <form>
                <div class="mb-3">
                    <label>DNI</label>
                    <input type="number" class="form-control" name="dni" placeholder="Ingrese el DNI">
                </div>

                <div class="mb-3">
                    <label>CUIL</label>
                    <input type="text" class="form-control" name="cuil" placeholder="Ingrese su CUIL">
                </div>

                <div class="mb-3">
                    <label>Nombre</label>
                    <input type="text" class="form-control" name="nombre" placeholder="Ingrese su nombre">
                </div>

                <div class="mb-3">
                    <label>Apellido</label>
                    <input type="text" class="form-control" name="apellido" placeholder="Ingrese el apellido">
                </div>

                <div class="mb-3">
                    <label>Sexo</label>
                    <select class="form-select" name="sexo">
                        <option>Masculino</option>
                        <option>Femenino</option>
                        <option>Otro</option>
                    </select>
                </div>

                <div class="mb-3">
                    <label>Nacionalidad</label>
                    <select class="form-select" name="nacionalidad">
                        <option>Argentina</option>
                        <option>Bolivia</option>
                        <option>Brasil</option>
                        <option>China</option>
                        <option>Chile</option>
                        <option>Colombia</option>
                        <option>Ecuador</option>
                        <option>Estados Unidos</option>
                        <option>España</option>
                        <option>Italia</option>
                        <option>Japón</option>
                        <option>Paraguay</option>
                        <option>Perú</option>
                        <option>Rusia</option>
                        <option>Ucrania</option>
                        <option>Uruguay</option>
                        <option>Venezuela</option>
                    </select>
                </div>

                <div class="mb-3">
                    <label>Fecha de nacimiento</label>
                    <input type="date" class="form-control" name="fechaNacimiento">
                </div>

                <div class="mb-3">
                    <label>Dirección</label>
                    <input type="text" class="form-control" name="direccion" placeholder="Ingrese su dirección">
                </div>

                <div class="mb-3">
                    <label>Localidad</label>
                    <input type="text" class="form-control" name="localidad" placeholder="Ingrese su localidad">
                </div>

                <div class="mb-3">
                    <label>Provincia</label>
                    <select class="form-select" name="provincia">
                        <option>Buenos Aires</option>
                        <option>Catamarca</option>
                        <option>Chaco</option>
                        <option>Chubut</option>
                        <option>Córdoba</option>
                        <option>Corrientes</option>
                        <option>Entre Ríos</option>
                        <option>Formosa</option>
                        <option>Jujuy</option>
                        <option>La Pampa</option>
                        <option>La Rioja</option>
                        <option>Mendoza</option>
                        <option>Misiones</option>
                        <option>Neuquén</option>
                        <option>Río Negro</option>
                        <option>Salta</option>
                        <option>San Juan</option>
                        <option>San Luis</option>
                        <option>Santa Cruz</option>
                        <option>Santa Fe</option>
                        <option>Santiago del Estero</option>
                        <option>Tierra del Fuego</option>
                        <option>Antártida e Islas del Atlántico Sur</option>
                        <option>Tucumán</option>
                    </select>
                </div>

                <div class="mb-3">
                    <label>Email</label>
                    <input type="email" class="form-control" name="correo" placeholder="Ingrese su correo">
                </div>

                <div class="mb-3">
                    <label>Teléfono</label>
                    <input type="number" class="form-control" name="telefono" placeholder="Ingrese el número telefónico">
                </div>

                <div class="mb-3">
                    <label>Usuario</label>
                    <input type="text" class="form-control" name="usuario" placeholder="Ingrese el nombre de usuario">
                </div>

                <div class="mb-3">
                    <label>Contraseña</label>
                    <input type="password" class="form-control" name="contraseña" placeholder="Ingrese su contraseña">
                </div>

                <div class="mb-3">
                    <label>Reitere su contraseña</label>
                    <input type="password" class="form-control" name="contraseña2" placeholder="Repita la contraseña">
                </div>

                <div class="text-center">
                    <button type="submit" class="btn btn-primary">Crear Cliente</button>
                </div>
            </form>
        </div>
    </div>
</body>
</html>
