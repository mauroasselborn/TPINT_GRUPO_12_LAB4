<%@ page import="entidades.Usuario" %>
<!-- componentes/BarraSuperior.jsp -->
<%
    Usuario usuarioLogueado = (Usuario) session.getAttribute("usuarioLogueado");
    if (usuarioLogueado == null) {
        response.sendRedirect("../Login.jsp");
        return;
    }
%>
<div
    class="topbar d-flex justify-content-end align-items-center bg-dark text-white px-4"
    style="height: 60px; border-bottom: 1px solid #495057"
>
    <span class="me-2"> <i class="bi bi-person-circle me-1"></i> Usuario: <%= usuarioLogueado.getNombreUsuario()  %> </span>
</div>
