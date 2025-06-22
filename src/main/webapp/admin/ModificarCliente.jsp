<!-- Encabezado -->
  <jsp:include page="../componentes/Encabezado.jsp" />

  <!-- Sidebar -->
  <jsp:include page="../componentes/MenuLateralAdmin.jsp" />

  <!-- Contenedor principal -->
  <div class="main-content">
    <!-- Navbar -->
    <jsp:include page="../componentes/BarraSuperiorAdmin.jsp" />
    <!-- Contenido principal -->
    <div class="container-fluid content py-4">
        <div class="w-75 mx-auto">
            <h2 class="text-center mb-4">Modificar Cliente</h2>

            <form action="#" method="post">
                <div class="mb-3">
                    <label for="cuil">CUIL</label>
                    <input type="text" class="form-control" name="cuil" placeholder="Ingrese el CUIL" 
                    value="20-25111301-7" required>
                </div>

                <div class="mb-3">
                    <label for="nombre">Nombre</label>
                    <input type="text" class="form-control" name="nombre" placeholder="Ingrese su nombre" 
                    value="Franco" required>
                </div>

                <div class="mb-3">
                    <label for="apellido">Apellido</label>
                    <input type="text" class="form-control" name="apellido" placeholder="Ingrese su apellido"
                     value="Bonzi" required>
                </div>

                <div class="mb-3">
                    <label>Sexo</label>
                    <select class="form-select" name="sexo" required>
                        <option>Masculino</option>
                        <option>Femenino</option>
                        <option>Otro</option>
                    </select>
                </div>

                <div class="mb-3">
                    <label>Nacionalidad</label>
                    <select class="form-select" name="nacionalidad" required>
                        <option value="argentina">Argentina</option>
                        <option value="bolivia">Bolivia</option>
                        <option value="brasil">Brasil</option>
                        <option value="china">China</option>
                        <option value="chile">Chile</option>
                        <option value="colombia">Colombia</option>
                        <option value="ecuador">Ecuador</option>
                        <option value="eeuu">Estados Unidos</option>
                        <option value="espa�a">Espa�a</option>
                        <option value="italia">Italia</option>
                        <option value="japon">Jap�n</option>
                        <option value="paraguay">Paraguay</option>
                        <option value="peru">Per�</option>
                        <option value="rusia">Rusia</option>
                        <option value="ucrania">Ucrania</option>
                        <option value="uruguay">Uruguay</option>
                        <option value="venezuela">Venezuela</option>
                    </select>
                </div>

                <div class="mb-3">
                    <label>Fecha de nacimiento</label>
                    <input type="date" class="form-control" name="fechaNacimiento" value="2003-12-19">
                </div>

                <div class="mb-3">
                    <label>Direcci�n</label>
                    <input type="text" class="form-control" name="direccion" value="Villalba 2045">
                </div>

                <div class="mb-3">
                    <label>Localidad</label>
                    <input type="text" class="form-control" name="localidad" value="San Mart�n">
                </div>

                <div class="mb-3">
                    <label>Provincia</label>
                    <select class="form-select" name="provincia" required>
                        <option value="ba">Buenos Aires</option>
                        <option value="catamarca">Catamarca</option>
                        <option value="chaco">Chaco</option>
                        <option value="chubut">Chubut</option>
                        <option value="cordoba">C�rdoba</option>
                        <option value="corrientes">Corrientes</option>
                        <option value="er">Entre R�os</option>
                        <option value="formosa">Formosa</option>
                        <option value="jujuy">Jujuy</option>
                        <option value="lp">La Pampa</option>
                        <option value="la">La Rioja</option>
                        <option value="mendoza">Mendoza</option>
                        <option value="neuquen">Neuqu�n</option>
                        <option value="rn">R�o Negro</option>
                        <option value="salta">Salta</option>
                        <option value="sj">San Juan</option>
                        <option value="sl">San Luis</option>
                        <option value="sc">Santa Cruz</option>
                        <option value="sf">Santa Fe</option>
                        <option value="santiagoe">Santiago del Estero</option>
                        <option value="tf">Tierra del Fuego</option>
                        <option value="antartida">Ant�rtida e Islas del Atl�ntico Sur</option>
                        <option value="tucuman">Tucum�n</option>
                    </select>
                </div>

                <div class="mb-3">
                    <label>Email</label>
                    <input type="email" class="form-control" name="correo" value="franco.bonzi@gmail.com" required>
                </div>

                <div class="mb-3">
                    <label>Usuario</label>
                    <input type="text" class="form-control" name="usuario" value="franco">
                </div>

                <div class="mb-3">
                    <label>Contrase�a</label>
                    <input type="password" class="form-control" name="contrase�a" placeholder="Ingrese su contrase�a" 
                    required>
                </div>

                <div class="mb-3">
                    <label>Reitere su contrase�a</label>
                    <input type="password" class="form-control" name="contrase�a2" placeholder="Repita la contrase�a" 
                    required>
                </div>

                <div class="text-center">
                    <button type="submit" class="btn btn-primary">Guardar cambios</button>
                </div>
            </form>
        </div>
    </div>
    <jsp:include page="../componentes/Footer.jsp" />
    </div>
</body>
</html>
