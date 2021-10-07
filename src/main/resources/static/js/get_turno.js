$(document).ready(function(){
    (function(){
        $.ajax({
            type : "GET",
            url : "/turnos/",
            success: function(response){
              $.each(response, (i, turno) => {

                let tr_id = 'tr_' + turno.id;
                let turnoRow = '<tr id=\"' + tr_id + "\"" + '>' +
                          '<td>' + turno.id + '</td>' +
                          '<td class=\"td_fecha\">' + turno.fecha + '</td>' +
                          '<td class=\"td_odontologo\">' + turno.odontologo.nombre + '</td>' +
                          '<td class=\"td_paciente\">' + turno.paciente.nombre + '</td>' +
                          '</tr>';
                $('#turnoTable tbody').append(turnoRow);
              });
            },
            error : function(e) {
              alert("Error inesperado", e);
            }
        });
    })();

    (function(){
        let pathname = window.location.pathname;
        if (pathname == "/turnos.html") {
            $(".nav .nav-item a:last").addClass("active");
        }
    })();
});