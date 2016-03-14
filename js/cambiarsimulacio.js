

// Función que genera el html que sale en simular cuando se quieren cambiar algun parametro de la simulación

$(document).ready(function() {
    $("#botoncrear").on('click', function () {
        location.href="./crear.html"
    });
    $(".volversimular").on('click', function () {
        location.href="./carga.html"
    });
});