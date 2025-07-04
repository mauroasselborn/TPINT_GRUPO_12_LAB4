
<footer class="bg-dark text-white text-center py-3 w-100">&copy; 2025 BancoApp - Todos los derechos reservados</footer>

<!-- JQUERY -->
<script src="https://code.jquery.com/jquery-3.7.0.min.js"></script>
<!-- DATATABLES -->
<script src="https://cdn.datatables.net/1.13.6/js/jquery.dataTables.min.js"></script>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>

<script src="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.js"></script>
<% if (request.getAttribute("toastMensaje") != null && request.getAttribute("toastTitulo") != null && request.getAttribute("toastTipo") != null) { %>
<script>
$(document).ready(function() {
    toastr.options = {
      "closeButton": true,
      "debug": false,
      "newestOnTop": false,
      "progressBar": true,
      "positionClass": "toast-bottom-right",
      "preventDuplicates": true,
      "showDuration": "300",
      "hideDuration": "1000",
      "timeOut": "2000",
      "extendedTimeOut": "1000",
      "showEasing": "swing",
      "hideEasing": "linear",
      "showMethod": "fadeIn",
      "hideMethod": "fadeOut"
    };

    Command: toastr["<%= request.getAttribute("toastTipo") %>"](
        "<%= request.getAttribute("toastMensaje") %>",
        "<%= request.getAttribute("toastTitulo") %>"
    );
});
</script>
<% } %>