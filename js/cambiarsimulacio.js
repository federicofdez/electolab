

// Función que genera el html que sale en simular cuando se quieren cambiar algun parametro de la simulación

$(document).ready(function() {
$("#botoneditar").on('click', function () {
    $("#editarsimular").html('<div class="container-fluid" id="main-content">' +
        '<h2>Cambie los parámetros del escenario creado</h2>' +
        '<div class="panel-group" id="accordion">' +
        '<div class="panel panel-default">' +
        '<div class="panel-heading">' +
        '<h4 class="panel-title">' +
        '<a data-toggle="collapse" data-parent="#accordion" href="#collapse1">Partidos políticos</a>' +
        '</h4>'+
        '</div>' +
        '<div id="collapse1" class="panel-collapse collapse in">' +
        '<div class="panel-body">Partidos políticos seleccionados</div>' +
        '<div>' +
        '<table class="table table-hover" id="partidosTable">' +
        '<thead>' +
        '<tr>' +
        '<th class="col-lg-3">Siglas</th>' +
        '<th class="col-lg-3">Nombre</th>' +
        '<th class="col-lg-3">Color</th>' +
        '<th class="col-lg-3">N. CCAA</th>' +
        '</tr>' +
        '</thead>' +
        '<tbody>' +
        '<tr>' +
        '<th scope="row"><h5>XX</h5></th>' +
        '<td><h5>Partido 1</h5></td>' +
        '<td>' +
        '<select id="colorselector">' +
        '<option value="87" data-color="#FF4500">orangered</option>' +
        '<option value="78" data-color="#8000FF">purple</option>' +
        '<option value="13" data-color="#2E64FE">darkblue</option>' +
        '<option value="15" data-color="#FF0000">red</option>' +
        '<option value="32" data-color="#01DF01">green</option>' +
        '<option value="42" data-color="#FFFF00">yellow</option>' +
        '<option value="47" data-color="#FE2EF7">pink</option>' +
        '<option value="123" data-color="#151515">black</option>' +
        '</select>' +
        '</td>' +
        '<script>'+
        '$("#colorselector").colorselector();'+
        '</script>' +
        '<td><h5>Todas</h5></td>' +
        '</tr>' +
        '</tbody>' +
        '</table>' +
        '<button onclick="addPartido()" type="button" class="btn btn-default center-block">Añadir partido</button>' +
            '<div style="margin-top: 6px;"></div>'+
        '</div>' +
        '</div>' +
        '</div>' +
        '<div class="panel panel-default">' +
        '<div class="panel-heading">' +
        '<h4 class="panel-title">' +
        '<a data-toggle="collapse" data-parent="#accordion" href="#collapse2">Circuscripciones</a>' +
        '</h4>' +
        '</div>' +
        '<div id="collapse2" class="panel-collapse collapse">' +
        '<div class="row">' +
        '<div class="col-lg-12 text-center" style="margin-top: 7px; "> Circunscripciones </div>'+
        '<div class="dropdown col-lg-2 center-block" style="margin-left: 470px; padding: 10px;">'+
        '<button class="btn btn-default dropdown-toggle circunscripciones" type="button" data-toggle="dropdown">Provincias'+
        '<span class="caret"></span>'+
        '</button>'+
        '<ul class="dropdown-menu">'+
        '<li><a class="provincias" href="#">Provincias</a></li>'+
        '<li><a class="comunidades" href="#">Comunidades autónomas</a></li>'+
        '<li><a class="espana" href="#">España</a></li>'+
        '</ul>'+
        '</div>'+
        '</div>' +
        '</div>' +
        '<div class="panel panel-default">' +
        '<div class="panel-heading">' +
        '<h4 class="panel-title">' +
        '<a data-toggle="collapse" data-parent="#accordion" href="#collapse3">Reparto de votos y escaños establecido</a>'+
        '</h4>'+
        '</div>' +
        '<div id="collapse3" class="panel-collapse collapse">'+
        '<div class="panel-body">Cambie los votos de cada partido por circunscripción:</div>'+
        '<table class="table table-hover" id="partidosTable">'+
        '<thead>'+
        '<tr>'+
        '<th class="col-lg-3">Circunscripción</th>'+
        '<th class="col-lg-3">Partido1</th>'+
        '<th class="col-lg-3">Partido2</th>'+
        '<th class="col-lg-3">Escaños</th>'+
        '</tr>'+
        '</thead>'+
        '<tbody>'+
        '<tr>'+
        '<th scope="row"><h5>Álava</h5></th>'+
        '<th> <input type="number" class="form-control" name="votar" id="votarAlava1" value="" ></th>'+
        '<th> <input type="number" class="form-control" name="votar" id="votarAlava2" value=""></th>'+
        '<th class="form-group"><input type="number" class="form-control"max="350" min="0" id="escañoAlava" value=""></th>'+
        '</tr>'+
        '<tr>'+
        '<th scope="row"><h5>Albacete</h5></th>'+
        '<th> <input type="number" class="form-control" name="votar" id="votar" value="" ></th>'+
        '<th> <input type="number" class="form-control" name="votar" id="votar" value=""></th>'+
        '<th class="form-group"><input type="number" class="form-control"max="350" min="0" id="escaño" value=""></th>'+
        '</tr>'+
        '<tr>'+
        '<th scope="row"><h5>Álicante</h5>'+
        '<th> <input type="number" class="form-control" name="votar" id="votar" value="" ></th>'+
        '<th> <input type="number" class="form-control" name="votar" id="votar" value=""></th>'+
        '<th class="form-group"><input type="number" class="form-control"max="350" min="0" id="escaño" value=""></th>'+
        '</tr>'+
        '<tr>'+
        '<th scope="row"><h5>Álmería</h5></th>'+
        '<th> <input type="text" class="form-control"</th>'+
        '<th> <input type="text" class="form-control"</th>'+
        '<th class="form-group"><input type="number" class="form-control"max="350" min="0" ></th>'+
        '</tr>'+
        '<tr>'+
        '<th scope="row"><h5>Ásturias</h5></th>'+
        '<th> <input type="text" class="form-control"</th>'+
        '<th> <input type="text" class="form-control"</th>'+
        '<th class="form-group"><input type="number" class="form-control"max="350" min="0"></th>'+
        '</tr>'+
        '<tr>'+
        '<th scope="row"><h5>Ávila</h5></th>'+
        '<th> <input type="text" class="form-control"</th>'+
        '<th> <input type="text" class="form-control"</th>'+
        '<th class="form-group"><input type="number" class="form-control"max="350" min="0"></th>'+
        '</tr>'+
        '<tr>'+
        '<th scope="row"><h5>Badajoz</h5></th>'+
        '<th> <input type="text" class="form-control"</th>'+
        '<th> <input type="text" class="form-control"</th>'+
        '<th class="form-group"><input type="number" class="form-control"max="350" min="0"></th>'+
        '</tr>'+
        '<tr>'+
        '<th scope="row"><h5>Barcelona</h5></th>'+
        '<th> <input type="text" class="form-control"</th>'+
        '<th> <input type="text" class="form-control"</th>'+
        '<th class="form-group"><input type="number" class="form-control"max="350" min="0" ></th>'+
        '</tr>'+
        '<tr>'+
        '<th scope="row"><h5>Burgos</h5></th>'+
        '<th> <input type="text" class="form-control"</th>'+
        '<th> <input type="text" class="form-control"</th>'+
        '<th class="form-group"><input type="number" class="form-control"max="350" min="0" ></th>'+
        '</tr>'+
        '<tr>'+
        '<th scope="row"><h5>Cáceres</h5></th>'+
        '<th> <input type="text" class="form-control"</th>'+
        '<th> <input type="text" class="form-control"</th>'+
        '<th class="form-group"><input type="number" class="form-control"max="350" min="0" ></th>'+
        '</tr>'+
        '<tr>'+
        '<th scope="row"><h5>Cádiz</h5></th>'+
        '<th> <input type="text" class="form-control"</th>'+
        '<th> <input type="text" class="form-control"</th>'+
        '<th class="form-group"><input type="number" class="form-control"max="350" min="0" ></th>' +
        '</tr>'+
        '<tr>' +
        '<th scope="row"><h5>Cantabria</h5></th>' +
        '<th> <input type="text" class="form-control"</th>' +
        '<th> <input type="text" class="form-control"</th>' +
        '<th class="form-group"><input type="number" class="form-control"max="350" min="0" ></th>' +
        '</tr>' +
        '<tr>' +
        '<th scope="row"><h5>Castellón</h5></th>' +
        '<th> <input type="text" class="form-control"</th>' +
        '<th> <input type="text" class="form-control"</th>' +
        '<th class="form-group"><input type="number" class="form-control"max="350" min="0" ></th>'+
        '</tr>'+
        '<tr>' +
        '<th scope="row"><h5>Ceuta</h5></th>' +
        '<th> <input type="text" class="form-control"</th>' +
        '<th> <input type="text" class="form-control"</th>' +
        '<th class="form-group"><input type="number" class="form-control"max="350" min="0" ></th>'+
        '</tr>'+
        '<tr>'+
        '<th scope="row"><h5>Ciudad Real</h5></th>'+
        '<th> <input type="text" class="form-control"</th>' +
        '<th> <input type="text" class="form-control"</th>' +
        '<th class="form-group"><input type="number" class="form-control"max="350" min="0" ></th>'+
        '</tr>'+
        '<tr>'+
        '<th scope="row"><h5>Córdoba</h5></th>' +
        '<th> <input type="text" class="form-control"</th>' +
        '<th> <input type="text" class="form-control"</th>' +
        '<th class="form-group"><input type="number" class="form-control"max="350" min="0" ></th>'+
        '</tr>' +
        '<tr>' +
        '<th scope="row"><h5>Cuenca</h5></th>' +
        '<th> <input type="text" class="form-control"</th>' +
        '<th> <input type="text" class="form-control"</th>' +
        '<th class="form-group"><input type="number" class="form-control"max="350" min="0" ></th>'+
        '</tr>' +
        '<tr>' +
        '<th scope="row"><h5>Gerona</h5></th>'+
        '<th> <input type="text" class="form-control"</th>' +
        '<th> <input type="text" class="form-control"</th>' +
        '<th class="form-group"><input type="number" class="form-control"max="350" min="0" ></th>'+
        '</tr>' +
        '<tr>' +
        '<th scope="row"><h5>Granada</h5></th>' +
        '<th> <input type="text" class="form-control"</th>' +
        '<th> <input type="text" class="form-control"</th>' +
        '<th class="form-group"><input type="number" class="form-control"max="350" min="0" ></th>'+
        '</tr>' +
        '<tr>' +
        '<th scope="row"><h5>Guadalajara</h5></th>'+
        '<th> <input type="text" class="form-control"</th>' +
        '<th> <input type="text" class="form-control"</th>' +
        '<th class="form-group"><input type="number" class="form-control"max="350" min="0" ></th>'+
        '</tr>'+
        '<tr>'+
        '<th scope="row"><h5>Guipúzcoa</h5></th>'+
        '<th> <input type="text" class="form-control"</th>' +
        '<th> <input type="text" class="form-control"</th>' +
        '<th class="form-group"><input type="number" class="form-control"max="350" min="0" ></th>'+
        '</tr>'+
        '<tr>'+
        '<th scope="row"><h5>Huelva</h5></th>'+
        '<th> <input type="text" class="form-control"</th>' +
        '<th> <input type="text" class="form-control"</th>' +
        '<th class="form-group"><input type="number" class="form-control"max="350" min="0" ></th>'+
        '</tr>'+
        '<tr>'+
        '<th scope="row"><h5>Huesca</h5></th>'+
        '<th> <input type="text" class="form-control"</th>' +
        '<th> <input type="text" class="form-control"</th>' +
        '<th class="form-group"><input type="number" class="form-control"max="350" min="0" ></th>'+
        '</tr>'+
        '<tr>'+
        '<th scope="row"><h5>Jaén</h5></th>'+
        '<th> <input type="text" class="form-control"</th>' +
        '<th> <input type="text" class="form-control"</th>' +
        '<th class="form-group"><input type="number" class="form-control"max="350" min="0" ></th>'+
        '</tr>'+
        '<tr>'+
        '<th scope="row"><h5>La coruña</h5></th>'+
        '<th> <input type="text" class="form-control"</th>' +
        '<th> <input type="text" class="form-control"</th>' +
        '<th class="form-group"><input type="number" class="form-control"max="350" min="0" ></th>'+
        '</tr>'+
        '<tr>'+
        '<th scope="row"><h5>La Rioja</h5></th>'+
        '<th> <input type="text" class="form-control"</th>' +
        '<th> <input type="text" class="form-control"</th>' +
        '<th class="form-group"><input type="number" class="form-control"max="350" min="0" ></th>'+
        '</tr>'+
        '<tr>'+
        '<th scope="row"><h5>Las Palmas</h5></th>'+
        '<th> <input type="text" class="form-control"</th>' +
        '<th> <input type="text" class="form-control"</th>' +
        '<th class="form-group"><input type="number" class="form-control"max="350" min="0" ></th>'+
        '</tr>'+
        '<tr>'+
        '<th scope="row"><h5>León</h5></th>'+
        '<th> <input type="text" class="form-control"</th>' +
        '<th> <input type="text" class="form-control"</th>' +
        '<th class="form-group"><input type="number" class="form-control"max="350" min="0" ></th>'+
        '</tr>'+
        '<tr>'+
        '<th scope="row"><h5>Lérida</h5></th>'+
        '<th> <input type="text" class="form-control"</th>' +
        '<th> <input type="text" class="form-control"</th>' +
        '<th class="form-group"><input type="number" class="form-control"max="350" min="0" ></th>'+
        '</tr>'+
        '<tr>'+
        '<th scope="row"><h5>Lugo</h5></th>'+
        '<th> <input type="text" class="form-control"</th>' +
        '<th> <input type="text" class="form-control"</th>' +
        '<th class="form-group"><input type="number" class="form-control"max="350" min="0" ></th>'+
        '</tr>'+
        '<tr>'+
        '<th scope="row"><h5>Madrid</h5></th>'+
        '<th> <input type="text" class="form-control"</th>' +
        '<th> <input type="text" class="form-control"</th>' +
        '<th class="form-group"><input type="number" class="form-control"max="350" min="0" ></th>'+
        '</tr>'+
        '<tr>'+
        '<th scope="row"><h5>Málaga</h5></th>'+
        '<th> <input type="text" class="form-control"</th>' +
        '<th> <input type="text" class="form-control"</th>' +
        '<th class="form-group"><input type="number" class="form-control"max="350" min="0" ></th>'+
        '</tr>'+
        '<tr>'+
        '<th scope="row"><h5>Melilla</h5></th>'+
        '<th> <input type="text" class="form-control"</th>' +
        '<th> <input type="text" class="form-control"</th>' +
        '<th class="form-group"><input type="number" class="form-control"max="350" min="0" ></th>'+
        '</tr>'+
        '<tr>'+
        '<th scope="row"><h5>Murcia</h5></th>'+
        '<th> <input type="text" class="form-control"</th>' +
        '<th> <input type="text" class="form-control"</th>' +
        '<th class="form-group"><input type="number" class="form-control"max="350" min="0" ></th>'+
        '</tr>'+
        '<tr>'+
        '<th scope="row"><h5>Navarra</h5></th>'+
        '<th> <input type="text" class="form-control"</th>' +
        '<th> <input type="text" class="form-control"</th>' +
        '<th class="form-group"><input type="number" class="form-control"max="350" min="0" ></th>'+
        '</tr>'+
        '<tr>'+
        '<th scope="row"><h5>Orense</h5></th>'+
        '<th> <input type="text" class="form-control"</th>' +
        '<th> <input type="text" class="form-control"</th>' +
        '<th class="form-group"><input type="number" class="form-control"max="350" min="0" ></th>'+
        '</tr>'+
        '<tr>'+
        '<th scope="row"><h5>Palencia</h5></th>'+
        '<th> <input type="text" class="form-control"</th>' +
        '<th> <input type="text" class="form-control"</th>' +
        '<th class="form-group"><input type="number" class="form-control"max="350" min="0" ></th>'+
        '</tr>'+
        '<tr>'+
        '<th scope="row"><h5>Pontevedra</h5></th>'+
        '<th> <input type="text" class="form-control"</th>' +
        '<th> <input type="text" class="form-control"</th>' +
        '<th class="form-group"><input type="number" class="form-control"max="350" min="0" ></th>'+
        '</tr>'+
        '<tr>'+
        '<th scope="row"><h5>Salamanca</h5></th>'+
        '<th> <input type="text" class="form-control"</th>' +
        '<th> <input type="text" class="form-control"</th>' +
        '<th class="form-group"><input type="number" class="form-control"max="350" min="0" ></th>'+
        '</tr>'+
        '<tr>'+
        '<th scope="row"><h5>Santa Cruz de Tenerife</h5></th>'+
        '<th> <input type="text" class="form-control"</th>' +
        '<th> <input type="text" class="form-control"</th>' +
        '<th class="form-group"><input type="number" class="form-control"max="350" min="0" ></th>'+
        '</tr>'+
        '<tr>'+
        '<th scope="row"><h5>Segovia</h5></th>'+
        '<th> <input type="text" class="form-control"</th>' +
        '<th> <input type="text" class="form-control"</th>' +
        '<th class="form-group"><input type="number" class="form-control"max="350" min="0" ></th>'+
        '</tr>'+
        '<tr>'+
        '<th scope="row"><h5>Sevilla</h5></th>'+
        '<th> <input type="text" class="form-control"</th>' +
        '<th> <input type="text" class="form-control"</th>' +
        '<th class="form-group"><input type="number" class="form-control"max="350" min="0" ></th>'+
        '</tr>'+
        '<tr>'+
        '<th scope="row"><h5>Soria</h5></th>'+
        '<th> <input type="text" class="form-control"</th>' +
        '<th> <input type="text" class="form-control"</th>' +
        '<th class="form-group"><input type="number" class="form-control"max="350" min="0"></th>'+
        '</tr>'+
        '<tr>'+
        '<th scope="row"><h5>Tarragona</h5></th>'+
        '<th> <input type="text" class="form-control"</th>' +
        '<th> <input type="text" class="form-control"</th>' +
        '<th class="form-group"><input type="number" class="form-control"max="350" min="0" ></th>'+
        '</tr>'+
        '<tr>'+
        '<th scope="row"><h5>Teruel</h5></th>'+
        '<th> <input type="text" class="form-control"</th>' +
        '<th> <input type="text" class="form-control"</th>' +
        '<th class="form-group"><input type="number" class="form-control"max="350" min="0"></th>'+
        '</tr>'+
        '<tr>'+
        '<th scope="row"><h5>Toledo</h5></th>'+
        '<th> <input type="text" class="form-control"</th>' +
        '<th> <input type="text" class="form-control"</th>' +
        '<th class="form-group"><input type="number" class="form-control"max="350" min="0" ></th>'+
        '</tr>'+
        '<tr>'+
        '<th scope="row"><h5>Valencia</h5></th>'+
        '<th> <input type="text" class="form-control"</th>' +
        '<th> <input type="text" class="form-control"</th>' +
        '<th class="form-group"><input type="number" class="form-control"max="350" min="0" ></th>'+
        '</tr>'+
        '<tr>'+
        '<th scope="row"><h5>Valladolid</h5></th>'+
        '<th> <input type="text" class="form-control"</th>' +
        '<th> <input type="text" class="form-control"</th>' +
        '<th class="form-group"><input type="number" class="form-control"max="350" min="0" ></th>'+
        '</tr>'+
        '<tr>'+
        '<th scope="row"><h5>Vizcaya</h5></th>'+
        '<th> <input type="text" class="form-control"</th>' +
        '<th> <input type="text" class="form-control"</th>' +
        '<th class="form-group"><input type="number" class="form-control"max="350" min="0" ></th>'+
        '</tr>'+
        '<tr>'+
        '<th scope="row"><h5>Zamora</h5></th>'+
        '<th> <input type="text" class="form-control"</th>' +
        '<th> <input type="text" class="form-control"</th>' +
        '<th class="form-group"><input type="number" class="form-control"max="350" min="0" ></th>'+
        '</tr>'+
        '<tr>'+
        '<th scope="row"><h5>Zaragoza</h5></th>'+
        '<th> <input type="text" class="form-control"</th>' +
        '<th> <input type="text" class="form-control"</th>' +
        '<th class="form-group"><input type="number" class="form-control"max="350" min="0" ></th>'+
        '</tr>'+
        '</tbody>'+
        '</table>'+
        '<table class="table table-hover" id="partidosTable" style="margin-top: 30px;">'+
        '<thead> <tr>'+
        '<th class="col-lg-3 center-block">Computación total</th>'+
        '<th class="col-lg-3 center-block">Total de Votos</th>'+
        '<th class="col-lg-3 center-block">Total de Circunscripciones</th>'+
        '<th class="col-lg-3 center-block">Total de Escaños</th>'+
        '</tr>'+
        '</thead>'+
        '<tbody>'+
        '<tr>'+
        '<th scope="row"><h5>SimulacioXX</h5></th>'+
        '<th><input type="text" class="form-control" placeholder="1.000.000"></th>'+
        '<th> <input type="text" class="form-control" placeholder="54"></th>'+
        '<th> <input type="text" class="form-control" placeholder="340"></th>'+
        '</tr>'+
        '</table>'+
        '</div>'+
        '</div>'+
        '</div>'+
        '<a href="carga.html" class="btn btn-info center-block" role="button" style="margin-top: 20px;">Simular cambios</a>'+
        '<div style="margin-top: 10px;"></div>'+
        '</div>')


    // Meter valores en los input
    document.getElementById('votarAlava1').value = 100;
    document.getElementById('votarAlava2').value = 100;
    document.getElementById('escañoAlava').value = 5;

});

});