<%@ page import="entidades.Cliente"%>
<%@ page import="entidades.Provincia"%>
<%@ page import="entidades.Localidad"%>
<%@ page import="entidades.Usuario"%>
<%@ page import="entidades.Nacionalidad"%>
<%@ page import="java.util.List"%>

<%
Cliente cliente = (Cliente) request.getAttribute("cliente");
Usuario usuario = (Usuario) request.getAttribute("usuario");
%>

<!-- Encabezado -->
<jsp:include page="../componentes/Encabezado.jsp" />
<!-- Sidebar -->
<jsp:include page="../componentes/MenuLateralAdmin.jsp" />
<!-- Contenedor principal -->
<div class="main-content">
	<jsp:include page="../componentes/BarraSuperior.jsp" />

	<div class="container content py-4">
		<div class="w-75 mx-auto">
			<h2 class="text-center mb-4">Detalle Cliente</h2>

			<div class="mb-3">
				<label for="dni">DNI</label> 
				<input type="text" class="form-control" name="dni" value="<%=cliente.getDni()%>" disabled>
			</div>

			<div class="mb-3">
				<label for="cuil">CUIL</label> 
				<input type="text" class="form-control" name="cuil" value="<%=cliente.getCuil()%>" disabled>
			</div>

			<div class="mb-3">
				<label for="nombre">Nombre</label> 
				<input type="text" class="form-control" name="nombre" value="<%=cliente.getNombre()%>"	disabled>
			</div>

			<div class="mb-3">
				<label for="apellido">Apellido</label> 
				<input type="text" class="form-control" name="apellido" value="<%=cliente.getApellido()%>" disabled>
			</div>

			<div class="mb-3">
				<label>Sexo</label> 
				<input type="text" class="form-control" name="sexo" value="<%=cliente.getSexo()%>" disabled>
			</div>

			<div class="mb-3">
				<label>Nacionalidad</label> 
				<input type="text" class="form-control" name="nacionalidad" value="<%=cliente.getNacionalidad().getDescripcion()%>" disabled>
			</div>

			<div class="mb-3">
				<label>Fecha de nacimiento</label> 
				<input type="date" class="form-control" name="fechaNacimiento" value="<%=cliente.getFechaNacimiento()%>" disabled>
			</div>

			<div class="mb-3">
				<label>Dirección</label> 
				<input type="text" class="form-control"	name="direccion" value="<%=cliente.getDireccion()%>" disabled>
			</div>


			<div class="mb-3">
				<label>Provincia</label> 
				<input type="text" class="form-control"	name="provincia" value="<%=cliente.getProvincia().getNombre()%>" disabled>
			</div>

			<div class="mb-3">
				<label>Localidad</label> 
				<input type="text" class="form-control" name="localidad" value="<%=cliente.getLocalidad().getNombre()%>" disabled>
			</div>

			<div class="mb-3">
				<label>Email</label> 
				<input type="email" class="form-control" name="correoElectronico"	value="<%=cliente.getCorreoElectronico()%>" disabled>
			</div>

			<div class="mb-3">
				<label>Teléfono</label> 
				<input type="text" class="form-control" name="telefono" value="<%=cliente.getTelefono()%>" disabled>
			</div>
			
			<div class="mb-3">
				<label>Usuario</label> 
				<input type="text" class="form-control" name="usuario" value="<%=usuario.getNombreUsuario()%>" disabled>
			</div>

			<div class="text-center">
				<a href="ClientesServlet?accion=listar" class="btn btn-primary">Volver</a>
			</div>

		</div>
	</div>

	<jsp:include page="../componentes/Footer.jsp" />
</div>
