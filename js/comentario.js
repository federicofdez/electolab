
//Función para que salga un comentario en grande

$(document).ready(function() {
    $(".comentario").on("click", function () {
            $(".comentariogrande").html('<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit.' +
                ' Accusantium aliquid amet architecto at atque aut beatae dolorem eaque eos eum harum ' +
                'minus nam nobis nulla odit praesentium quibusdam quis, repudiandae.</p>');
    });
});
